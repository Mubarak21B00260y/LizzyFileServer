package com.amalitechnss.Lizzy_fileServer.Controller;

import com.amalitechnss.Lizzy_fileServer.Model.Document;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class DocumentController {

    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }


    @RequestMapping("/api/Documents")

    @PostMapping("/Single/Upload")
    public ResponseEntity upload(@RequestParam() MultipartFile file) {

        try {
            documentService.SaveDocument(file);

            return  ResponseEntity.status(HttpStatus.OK).body("File Uploaded successfully ");
        } catch (IOException e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }


    }

}

