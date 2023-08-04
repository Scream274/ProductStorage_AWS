package com.example.saver.config;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class BucketS3Service implements CommandLineRunner {

    private final S3Config amazonS3Config;
    private final AmazonS3 amazonS3;

    @Autowired
    public BucketS3Service(S3Config amazonS3Config, AmazonS3 amazonS3) {
        this.amazonS3Config = amazonS3Config;
        this.amazonS3 = amazonS3;
    }

    public void createBucketIfNeeded(String bucketName) {
        if (!amazonS3.doesBucketExist(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
    }

    @Override
    public void run(String... args) {
        createBucketIfNeeded(amazonS3Config.getBucketName());
    }
}