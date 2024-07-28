package com.example.tour_travel.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file,String fileType);
    Resource loadFile(String fileName,String fileType);
    void  deleteFile(String fileName);
}
