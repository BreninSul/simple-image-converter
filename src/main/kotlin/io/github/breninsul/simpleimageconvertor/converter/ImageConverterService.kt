//package io.github.breninsul.simpleimageconvertor.converter
//
//import io.github.breninsul.simpleimageconvertor.dto.ImageFormatEnum
//import java.io.InputStream
//import java.io.OutputStream
//import java.util.concurrent.CompletableFuture
//import java.util.concurrent.Executor
//import java.util.function.Supplier
//
//open class ImageConverterService(
//    protected open val executorService: Executor,
//)  {
//    open fun convertFuture(
//        id: Long,
//        inputStreamSupplier: Supplier<InputStream>,
//        width: Int,
//        height: Int,
//        keepAspectRatio: Boolean,
//        format: ImageFormatEnum,
//        outputStreamSupplier: Supplier<OutputStream>,
//    ): CompletableFuture<OutputStream> {
//        val result =
//            CompletableFuture.supplyAsync(
//                { convert(id, inputStreamSupplier, width, height,keepAspectRatio, format, outputStreamSupplier.get())  },
//                executorService,
//            )
//        return result
//    }
//
//    open fun convert(
//        id: Long,
//        inputStreamSupplier: Supplier<InputStream>,
//        width: Int,
//        height: Int,
//        keepAspectRatio: Boolean,
//        format: ImageFormatEnum,
//        outputStream: OutputStream,
//    ): OutputStream {
//        return outputStream.use { output ->
//            val time = System.currentTimeMillis()
//            val result = GeneralConverter.convert(inputStreamSupplier, width, height,keepAspectRatio, format, output)
//            logger.info("Convert $id took ${System.currentTimeMillis() - time} ms")
//            return@use result
//        }
//    }
//}
