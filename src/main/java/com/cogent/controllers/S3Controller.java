package com.cogent.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cogent.services.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/storage/")
public class S3Controller {

    @Autowired
    private AmazonService amazonService;

    @PostMapping("/uploadFile")
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.amazonService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }


}
