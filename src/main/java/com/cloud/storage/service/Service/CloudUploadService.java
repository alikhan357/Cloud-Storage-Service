package com.cloud.storage.service.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface CloudUploadService {

    String uploadFile(String fileName, InputStream stream, ObjectMetadata metadata,String contentType);
}
