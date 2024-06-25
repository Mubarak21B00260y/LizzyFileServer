package com.amalitechnss.Lizzy_fileServer.Requests;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data


@Component
public class DocumentUploadRequest {


        private String title;
        private String description;
       private LocalDateTime  uploadedAt;
        // Getters and Setters


}
