package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.DocumentNotFoundException;
import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.DocumentTitleExistsException;
import com.amalitechnss.Lizzy_fileServer.Requests.DocumentUploadRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.EditDocumentRequest;
import com.amalitechnss.Lizzy_fileServer.Repository.DocumentRepository;
import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DocumentServiceImpl implements DocumentService {
    @Value("${file.storage}")
    private String storageLocation;
    private final EmailService emailService;
    private final DocumentRepository documentRepository;
@SneakyThrows
    public void UploadDocument(MultipartFile file, DocumentUploadRequest documentUploadRequest)  {
        String Filename = file.getOriginalFilename();
        if (Filename != null) {
            String dir = System.getProperty("user.dir") + File.separator + storageLocation;
            Path Target = Paths.get(dir).resolve(Filename);
            file.transferTo(Target.toFile());
            Document document = new Document();
            document.setTitle(documentUploadRequest.getTitle());

            document.setDescription(documentUploadRequest.getDescription());
            document.setUploadedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            document.setFilePath(Target.toString());
            document.setFilename(Filename);

            if (documentRepository.existsByTitle(documentUploadRequest.getTitle())) {
                throw new DocumentTitleExistsException("document title already exists");
            }
            documentRepository.save(document);

        }

    }

    public Resource DownloadDocument(String title) throws IOException,DocumentNotFoundException {

        Optional<Document> document = documentRepository.findByTitle(title);
        if (document.isEmpty()) {
            throw new DocumentNotFoundException(" Document not found");
        }
        var documentFile = document.get();
        String directory = System.getProperty("user.dir") + File.separator + storageLocation;
        Path Target = Paths.get(directory).resolve(documentFile.getFilename()).normalize();
        if (Target == null) {
            throw new FileNotFoundException(" file does not exist");
        }
        Resource resource = new UrlResource(Target.toUri());
        documentFile.IncrementDownloadsCount();
        documentRepository.save(documentFile);
        return resource;
    }


    public Page<Document> FetchDocuments(int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return documentRepository.findAll(pageable);

    }

    public List<Document> SearchDocument(String title) {

        List<Document> document = documentRepository.findByAlikeTerms(title);
        if (document.isEmpty()) {

            throw new DocumentNotFoundException("no matching document  with title found");
        }
        return document;


    }

    public void DeleteDocument(String Id) {
        Optional<Document> document = documentRepository.findById(Id);

        if (document.isEmpty()) {

            throw new DocumentNotFoundException("Document not found");


        }
        documentRepository.deleteById(Id);

    }


    @SneakyThrows
    public void ShareFile(String Recipient, String title)  {


        Optional<Document> optionalDocument = documentRepository.findByTitle(title);

        if (optionalDocument.isEmpty()) {
            throw new DocumentNotFoundException(" document not found");
        }
        String subject = "New Attachment";
        Document document = optionalDocument.get();
 try {

     String filename = document.getFilename();
     String directory = System.getProperty("user.dir") + File.separator + storageLocation;
     Path Target = Paths.get(directory).resolve(filename).normalize();
     emailService.SendAttachmentMail(Recipient, subject, Target.toFile(), EmailTemplate.ATTACHMENT);
     document.IncrementMailedFilesCount();
     documentRepository.save(document);
 }
  catch(Exception e){
     throw  new FileNotFoundException(" file not found");
  }
    }

    public void EditDocument(String Id, EditDocumentRequest editDocumentRequest) {

        Optional<Document> optionalDocument = documentRepository.findById(Id);
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            document.setTitle(editDocumentRequest.getNewTitle());
            document.setDescription(editDocumentRequest.getNewDescription());
            if (documentRepository.existsByTitle(editDocumentRequest.getNewTitle())) {
                throw new DocumentTitleExistsException(" document with this title already exists");
            }
            documentRepository.save(document);
        }

    }

    public long getTotalDocuments() {

     return  documentRepository.count();
    }



}
