package com.example.tour_travel.service.impl;

import com.example.tour_travel.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileLocation;

    public FileStorageServiceImpl(@Value("${upload.dir}") String uploadDir) throws Exception {
        this.fileLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation.resolve("users"));
            Files.createDirectories(this.fileLocation.resolve("tours"));
            Files.createDirectories(this.fileLocation.resolve("packages"));
            Files.createDirectories(this.fileLocation.resolve("hotels"));
        } catch (Exception e) {
            throw new Exception("Could not create directory");
        }
    }

    @Override
    public String storeFile(MultipartFile file, String fileType) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")){
                throw new RuntimeException("File name contains invalid path sequence" + fileName);
            }
            Path targetLocation = this.fileLocation.resolve(fileType).resolve(fileName).normalize();
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file "+fileName+ " please try again");
        }
    }

    @Override
    public Resource loadFile(String fileName, String fileType) {
        try {
            Path filePath = this.fileLocation.resolve(fileType).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not find the file " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the file "+fileName+ " please try again");
        }
    }
}






















