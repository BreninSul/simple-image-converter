
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.processor.DefaultImageProcessorService
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.management.BufferPoolMXBean
import java.lang.management.ManagementFactory
import java.net.URI

class TestNativeMemoryLeak {
    private val processor = DefaultImageProcessorService()
    private val reader = DefaultImageConsumer()
    val poolBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean::class.java)

    private val imageUrls = listOf(
        "https://m.media-amazon.com/images/I/61Pcx-RIs+L.jpg",
        "https://m.media-amazon.com/images/I/71rG17yL9PL.jpg",
        "https://m.media-amazon.com/images/I/71aEvvWS9JL.jpg",
        "https://m.media-amazon.com/images/I/71mRNHWTE6L.jpg",
        "https://m.media-amazon.com/images/I/81Wf8JV6pXL.jpg",
        "https://m.media-amazon.com/images/I/51gXiNdHOoL.jpg",
        "https://m.media-amazon.com/images/I/51gXiNdHOoL.jpg",
        "https://m.media-amazon.com/images/I/81Wf8JV6pXL.jpg"
    )

    data class ResizeTask(val width: Int, val height: Int, val type: String, val keepAspectRatio: Boolean)

    @Test
    fun testNativeMemoryLeakJpegResize() {
        printNMT("Start")
        println("Downloading images...")
        val imagesData = imageUrls.map { url ->
            println("Downloading $url")
            URI.create(url).toURL().openStream().use { it.readBytes() }
        }
        println("Downloaded ${imagesData.size} images.")

        val resizeTasks = listOf(
            ResizeTask(200, 200, "small", true),
            ResizeTask(400, 400, "medium", true)
        )

        val iterations = 50
        val outputDir = File("testwrite/native_memory_leak_test")
        outputDir.mkdirs()

        val runtime = Runtime.getRuntime()

        for (i in 1..iterations) {
            println("Iteration $i / $iterations")

            imagesData.forEachIndexed { index, bytes ->
                try {
                    resizeTasks.forEach { task ->
                        val inputStream = ByteArrayInputStream(bytes)
                        val outFile = File(outputDir, "iter_${i}_img_${index}_${task.type}.jpg")
                        
                        // Use DefaultImageProcessorService for entire pipeline (read -> transform -> write)
                        outFile.outputStream().use { outputStream ->
                            processor.process(
                                inputStream, 
                                outputStream, 
                                listOf(
                                    ScaleToSettings(Resolution(task.width, task.height, task.keepAspectRatio)),
                                    ConvertSettings(format = ImageFormat.JPEG)
                                ),
                                null, 
                                "iter_${i}_img_${index}_${task.type}" 
                            )
                        }
                    }
                } catch (e: Exception) {
                    println("Error processing image $index at iteration $i: ${e.message}")
                    e.printStackTrace()
                }
            }

            if (i % 10 == 0) {
                System.gc()
                Thread.sleep(100)
                val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024
                println("Heap Memory used after iteration $i: $usedMemory MB")
                printDirectMemory()
                printNMT("Iteration $i")
            }
        }
        
    }
    
    private fun printDirectMemory() {
        poolBeans.forEach { bean ->
             println("BufferPool ${bean.name}: Count=${bean.count}, MemoryUsed=${bean.memoryUsed / 1024 / 1024} MB, TotalCap=${bean.totalCapacity / 1024 / 1024} MB")
        }
    }

    private fun printNMT(label: String) {
        println("=== NMT Report: $label ===")
        try {
            val pid = ProcessHandle.current().pid()
            val process = Runtime.getRuntime().exec("jcmd $pid VM.native_memory summary")
            process.inputStream.bufferedReader().use { reader ->
                reader.lines().forEach { line -> println(line) }
            }
            process.waitFor()
        } catch (e: Exception) {
            println("Failed to run jcmd NMT: ${e.message}")
        }
        println("==========================")
    }
}
