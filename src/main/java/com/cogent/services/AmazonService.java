package com.cogent.services;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonService {

    void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess);

    void deleteFileFromS3Bucket(String fileName);
}
