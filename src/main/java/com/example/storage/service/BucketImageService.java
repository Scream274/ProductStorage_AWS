package com.example.storage.service;


import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface BucketImageService {
    String savePhoto(String name, InputStream is, ObjectMetadata metadata);
}