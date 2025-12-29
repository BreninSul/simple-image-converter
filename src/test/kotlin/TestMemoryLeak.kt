
import io.github.breninsul.simpleimageconvertor.dto.ImageFormat
import io.github.breninsul.simpleimageconvertor.dto.settings.writer.ConvertSettings
import io.github.breninsul.simpleimageconvertor.service.consumer.DefaultImageConsumer
import io.github.breninsul.simpleimageconvertor.service.convert.DefaultImageConverter
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URI

class TestMemoryLeak {
    private val writer = DefaultImageConverter()
    private val reader = DefaultImageConsumer()

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

    @Test
    fun testMemoryLeak() {
        // 1. Pre-download images to avoid network usage during the loop
        println("Downloading images...")
        val imagesData = imageUrls.map { url ->
            println("Downloading $url")
            URI.create(url).toURL().openStream().use { it.readBytes() }
        }
        println("Downloaded ${imagesData.size} images.")

        // 2. Run conversion loop
        val iterations = 50 // Run enough times to trigger GC or OOM if leaking
        val outputDir = File("testwrite/memory_leak_test")
        outputDir.mkdirs()

        val runtime = Runtime.getRuntime()
        
        for (i in 1..iterations) {
            println("Iteration $i / $iterations")
            
            imagesData.forEachIndexed { index, bytes ->
                try {
                    val inputStream = ByteArrayInputStream(bytes)
                    val image = reader.read(inputStream, emptyList())
                    val outFile = File(outputDir, "iter_${i}_img_$index.png")
                    
                    // Convert to PNG
                    writer.convert(
                        image, 
                        listOf(ConvertSettings(format = ImageFormat.PNG)), 
                        FileOutputStream(outFile)
                    )
                } catch (e: Exception) {
                    println("Error processing image $index at iteration $i: ${e.message}")
                    e.printStackTrace()
                }
            }

            if (i % 10 == 0) {
                 System.gc()
                 Thread.sleep(100) // Give GC a moment
                 val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024
                 println("Memory used after iteration $i: $usedMemory MB")
            }
        }
    }
}
