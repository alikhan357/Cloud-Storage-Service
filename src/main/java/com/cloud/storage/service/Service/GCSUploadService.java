package com.cloud.storage.service.Service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

public class GCSUploadService implements  CloudUploadService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GCSUploadService.class);

    @Autowired
    private Storage gcpGcs;

    @Value("${server.storage}")
    private String storage;


    @Value("${gcp.gcs.bucket_name}")
    private String gcsBucketName;

    @Override
    public String uploadFile(String fileName, InputStream stream, ObjectMetadata metadata,String contentType) {

        LOGGER.info("UPLOADING FILE TO GCS");
        BlobId blobId = BlobId.of(gcsBucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Blob blob = gcpGcs.create(blobInfo, stream);
        LOGGER.info("UPLOADING COMPLETE");
        return blob.getMediaLink();
    }
}
