package com.amalitechnss.Lizzy_fileServer.Service;


import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Model.DocumentDTO;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service


public interface DocumentService {
    //void SaveDocument(MultipartFile file) throws IOException;


    String UploadDocument(MultipartFile file, DocumentDTO documentDTO) throws IOException;

    UrlResource DownloadDocument(String file) throws IOException;

    Page<Document> FetchDocuments(int page, int size);
    Optional<Document> SearchDocument(String title );

    void DeleteDocument(String Id  ) throws IOException;
}
