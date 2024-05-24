package com.amalitechnss.Lizzy_fileServer.Service;


import com.amalitechnss.Lizzy_fileServer.Model.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DocumentService {
    Document SaveDocument(MultipartFile file);
}
