package com.amalitechnss.Lizzy_fileServer.Service;


import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Requests.DocumentUploadRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.EditDocumentRequest;
import jakarta.mail.MessagingException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service


public interface DocumentService {



    void UploadDocument(MultipartFile file, DocumentUploadRequest documentUploadRequest) throws IOException;

    Resource DownloadDocument(String title) throws IOException;

    Page<Document> FetchDocuments(int page, int size);
    List<Document> SearchDocument(String title );

    void DeleteDocument(String Id  ) throws IOException;

    void ShareFile(String Recipient, String title) throws MessagingException;
    void EditDocument(String Id, EditDocumentRequest editDocumentRequest);
}
