package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.DocumentNotFoundException;
import com.amalitechnss.Lizzy_fileServer.Exceptions.Exceptions.DocumentTitleExistsException;
import com.amalitechnss.Lizzy_fileServer.Requests.DocumentUploadRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.EditDocumentRequest;
import com.amalitechnss.Lizzy_fileServer.Repository.DocumentRepository;

import com.amalitechnss.Lizzy_fileServer.Service.Enums.EmailTemplate;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DocumentServiceImpl implements DocumentService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);
    @Value("${file.storage}")
    private String storageLocation;
    private final EmailService emailService;
    private final DocumentRepository documentRepository;

    public void UploadDocument(MultipartFile file, DocumentUploadRequest documentUploadRequest) throws IOException {
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

    public UrlResource DownloadDocument(String title) throws MalformedURLException {

        Optional<Document> document = documentRepository.findByTitle(title);
        if (document.isEmpty()) {

            throw new DocumentNotFoundException(" file  document not found");
        }

        var documentFile = document.get();

        String directory = System.getProperty("user.dir") + File.separator + storageLocation;

        Path Target = Paths.get(directory).resolve(documentFile.getFilename()).normalize();

        documentFile.IncrementDownloadsCount();
        documentRepository.save(documentFile);
        return new UrlResource(Target.toUri());

    }

    public Page<Document> FetchDocuments(int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return documentRepository.findAll(pageable);

    }

    public Optional<Document> SearchDocument(String title) {

        Optional<Document> document = documentRepository.findByTitle(title);
        if (document.isEmpty()) {

            throw new DocumentNotFoundException("no matching document  with title found");
        }
        return document;


    }

    public void DeleteDocument(String Id) {
        Optional<Document> document = documentRepository.findById(Id);

        if (document.isEmpty()) {

            throw new DocumentNotFoundException("document not found");


        }
        documentRepository.deleteById(Id);

    }


    public void ShareFile(String Recipient, String title) throws MessagingException {


        Optional<Document> optionalDocument = documentRepository.findByTitle(title);

        if (optionalDocument.isEmpty()) {
            throw new DocumentNotFoundException(" document not found");
        }
        String subject = "New Attachment";
        Document document = optionalDocument.get();

        String filename = document.getFilename();
        String directory = System.getProperty("user.dir") + File.separator + storageLocation;
        Path Target = Paths.get(directory).resolve(filename).normalize();
        emailService.SendAttachmentMail(Recipient, subject, Target.toFile(), EmailTemplate.ATTACHMENT);
        document.IncrementMailedFilesCount();
        documentRepository.save(document);


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


}
