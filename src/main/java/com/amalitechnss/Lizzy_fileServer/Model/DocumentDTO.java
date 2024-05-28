package com.amalitechnss.Lizzy_fileServer.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Data


@Component
public class DocumentDTO {


        private String title;
        private String description;
       private LocalDateTime  uploadedAt;
        // Getters and Setters


}
