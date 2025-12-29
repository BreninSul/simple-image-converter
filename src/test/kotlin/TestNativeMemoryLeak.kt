
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.Resolution
import io.github.breninsul.simpleimageconvertor.dto.settings.transformation.ScaleToSettings
import io.github.breninsul.simpleimageconvertor.dto.settings.Settings
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
//        "https://m.media-amazon.com/images/I/71rG17yL9PL.jpg",
//        "https://m.media-amazon.com/images/I/71aEvvWS9JL.jpg",
//        "https://m.media-amazon.com/images/I/71mRNHWTE6L.jpg",
//        "https://m.media-amazon.com/images/I/81Wf8JV6pXL.jpg",
//        "https://m.media-amazon.com/images/I/51gXiNdHOoL.jpg",
//        "https://m.media-amazon.com/images/I/51gXiNdHOoL.jpg",
        "https://m.media-amazon.com/images/I/81Wf8JV6pXL.jpg",
        "https://www.gstatic.com/webp/gallery/5.webp",
        "https://colinbendell.github.io/webperf/animated-gif-decode/1.webp"
    )


    data class ResizeTask(val width: Int, val height: Int, val type: String, val keepAspectRatio: Boolean)

    @Test
    fun testNativeMemoryLeakJpegResize() {
        runLeakTest(
            "JpegResize",
            "jpg",
             imageUrls,
            { width, height, type ->
                listOf(
                    ScaleToSettings(Resolution(width, height, true)),
                    ConvertSettings(format = ImageFormat.JPEG)
                )
            }
        )
    }

    @Test
    fun testNativeMemoryLeakWebpStatic() {
        runLeakTest(
            "WebpStatic",
            "webp",
             imageUrls,
            { width, height, type ->
                listOf(
                    ScaleToSettings(Resolution(width, height, true)),
                    ConvertSettings(format = ImageFormat.WEBP)
                )
            }
        )
    }

    @Test
    fun testNativeMemoryLeakPdf() {
        runLeakTest(
            "Pdf",
            "pdf",
            imageUrls,
            { width, height, type ->
                listOf(
                    ScaleToSettings(Resolution(width, height, true)),
                    ConvertSettings(format = ImageFormat.PDF)
                )
            }
        )
    }

    @Test
    fun testNativeMemoryLeakWebpAnimated() {
        val animatedGifUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif"
        runLeakTest(
            "WebpAnimated",
            "webp",
            listOf(animatedGifUrl),
            { width, height, type ->
                listOf(
                    ScaleToSettings(Resolution(width, height, true)),
                    ConvertSettings(format = ImageFormat.WEBP)
                )
            }
        )
    }


    private fun runLeakTest(
        testName: String,
        extension: String,
        urls: List<String>,
        settingsFactory: (Int, Int, String) -> List<Settings>
    ) {
        printNMT("$testName Start")
        println("Downloading images for $testName...")
        val imagesData = urls.mapIndexed { index, url ->
            println("Downloading $url")
            try {
                val connection = URI.create(url).toURL().openConnection() as java.net.HttpURLConnection
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                connection.inputStream.use { it.readBytes() }
            } catch (e: Exception) {
                println("Failed to download $url: ${e.message}")
                null
            }
        }.filterNotNull()

        if (imagesData.isEmpty()) {
            println("No images downloaded for $testName. Skipping test.")
            return
        }
        println("Downloaded ${imagesData.size} images.")

        val resizeTasks = listOf(
            ResizeTask(200, 200, "small", true),
            ResizeTask(400, 400, "medium", true)
        )

        val iterations = 50
        val outputDir = File("testwrite/native_memory_leak_test/$testName")
        outputDir.mkdirs()

        val runtime = Runtime.getRuntime()

        for (i in 1..iterations) {
            println("Iteration $i / $iterations")

            imagesData.forEachIndexed { index, bytes ->
                try {
                    resizeTasks.forEach { task ->
                        val inputStream = ByteArrayInputStream(bytes)
                        val outFile = File(outputDir, "iter_${i}_img_${index}_${task.type}.$extension")

                        outFile.outputStream().use { outputStream ->
                            processor.process(
                                inputStream,
                                outputStream,
                                settingsFactory(task.width, task.height, task.type),
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
                printNMT("$testName Iteration $i")
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

