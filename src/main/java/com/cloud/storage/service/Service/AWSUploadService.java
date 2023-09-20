package com.cloud.storage.service.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

public class AWSUploadService implements  CloudUploadService{

    @Value("${aws.s3.bucket_name}")
    private String s3BucketName;

    @Value("${aws.s3.url}")
    private String s3Url;

    @Autowired
    private AmazonS3 amazonS3;

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSUploadService.class);

    @Override
    public String uploadFile(String fileName, InputStream stream, ObjectMetadata metadata,String contentType) {

        LOGGER.info("UPLOADING FILE TO S3");
        amazonS3.putObject(s3BucketName, fileName, stream, metadata);
        LOGGER.info("UPLOADING COMPLETE");
        return s3Url + fileName;
    }
}
