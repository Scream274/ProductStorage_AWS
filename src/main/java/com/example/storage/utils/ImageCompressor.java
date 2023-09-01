package com.example.storage.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageCompressor {
    public static MultipartFile compressImage(MultipartFile inputFile,
                                              int targetWidth,
                                              int targetHeight,
                                              float quality) throws IOException {
        try (InputStream inputStream = inputFile.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Thumbnails.of(inputStream)
                    .size(targetWidth, targetHeight)
                    .outputFormat("JPEG")
                    .outputQuality(quality)
                    .toOutputStream(outputStream);

            byte[] data = outputStream.toByteArray();

            return new MockMultipartFile(inputFile.getName(), inputFile.getOriginalFilename(),
                    inputFile.getContentType(), new ByteArrayInputStream(data));
        }
    }
}
