package com.example.storage.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.storage.config.S3Config;
import com.example.storage.service.BucketImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class BucketImageServiceImpl implements BucketImageService {

    private final AmazonS3 amazonS3;
    private final S3Config amazonS3Config;

    @Autowired
    public BucketImageServiceImpl(AmazonS3 amazonS3, S3Config amazonS3Config) {
        this.amazonS3 = amazonS3;
        this.amazonS3Config = amazonS3Config;
    }

    @Override
    public String savePhoto(String name, InputStream is, ObjectMetadata metadata) {
        String bucketName = amazonS3Config.getBucketName();
        amazonS3.putObject(bucketName, name, is, metadata);
        return String.valueOf(amazonS3.getUrl(bucketName, name));
    }
}