package com.ecommerce.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.customException.FileUploadException;
import com.ecommerce.customException.InvalidFileExtensionException;
import com.ecommerce.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileUploadService {
    @Autowired
    private Cloudinary cloudinary;

    public FileUploadResponse uploadFile(MultipartFile file, String fold) {
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "webp", "avif");
        String extension = null;
        if (file.getOriginalFilename() != null) {
            String[] fileNameSplit = file.getOriginalFilename().split("\\.");
            extension = fileNameSplit[fileNameSplit.length - 1];
        }
        if (!allowedExtensions.contains(extension)) throw new InvalidFileExtensionException("Extension no soportada: ." + extension);
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> resultUpload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder","Ecommerce/"+fold));
            String imageUrl = resultUpload.get("secure_url").toString();
            String publicId = resultUpload.get("public_id").toString();
            return FileUploadResponse.builder().imageUrl(imageUrl).publicId(publicId).build();
        } catch (IOException e){
            throw new FileUploadException("Error al subir una imagen", e.getCause());
        }
    }

    public void deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e){
            throw new FileUploadException("Error al eliminar una imagen", e.getCause());
        }
    }

}
