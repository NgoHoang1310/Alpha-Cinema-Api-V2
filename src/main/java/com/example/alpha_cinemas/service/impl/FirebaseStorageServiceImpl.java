package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.service.FirebaseStorageService;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;

import com.google.firebase.cloud.StorageClient;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Value("${firebase.image-url}")
    private String imageUrl;

    @Override
    public String getImageUrl(String name) {
        return String.format(imageUrl, name);
    }

    @Override
    public String save(MultipartFile file, String folder) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = folder + "/" + generateFileName(file.getOriginalFilename());

        bucket.create(name, file.getBytes(), file.getContentType());
        return name;
    }

    @Override
    public String save(BufferedImage bufferedImage, String originalFileName) throws IOException {

        byte[] bytes = getByteArrays(bufferedImage, getExtension(originalFileName));

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = generateFileName(originalFileName);

        bucket.create(name, bytes);

        return name;
    }

    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        if (!StringUtils.hasText(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob != null) {
            blob.delete();
        }

    }
}
