package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.FileUploadResponse;
import com.ecommerce.services.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class FileUploadController {
    private final FileUploadService fileUploadService;
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/uploads")
    public ResponseEntity<FileUploadResponse> uploadFile ( @RequestParam("File") MultipartFile file, @RequestParam("folderName") String folderName) {
        System.out.println("nombre de la carpeta:" + folderName);
        return ResponseEntity.ok(fileUploadService.uploadFile(file, folderName));
    }

    @DeleteMapping("/cloudinary/deleteImg")
    public ResponseEntity<Void> deleteImg (@RequestParam("publicId") String publicId ) {

        fileUploadService.deleteFile(publicId);
        return ResponseEntity.noContent().build();
    }

}
