package com.amalitechnss.Lizzy_fileServer.Controller;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Requests.DocumentUploadRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.EditDocumentRequest;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);
    private final DocumentService documentService;

    @PostMapping("/api/upload/single")
    @ResponseBody
    public ResponseEntity<?> Upload(@RequestParam() MultipartFile file, @ModelAttribute DocumentUploadRequest documentUploadRequest) {

        try {
            documentService.UploadDocument(file, documentUploadRequest);

            return ResponseEntity.ok().body("File Uploaded successfully ");
        } catch (IOException e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("api/download/file")

    public ResponseEntity<UrlResource> Download(@RequestParam String title) throws IOException {

        UrlResource resource = documentService.DownloadDocument(title);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"" + resource.getFilename() + "\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

    }

    @GetMapping("api/get/all")
    public ResponseEntity<Stream<Document>> GetAllDocuments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10 ") int size) {


        Page<Document> fetchedDocuments = documentService.FetchDocuments(page, size);
        if (fetchedDocuments.hasContent())
            return ResponseEntity.ok(fetchedDocuments.get());
        else
            return ResponseEntity.noContent().build();
    }


    @GetMapping("api/search/file")
    public ResponseEntity<Optional<Document>> SearchDocument(@RequestParam String title) {
        Optional<Document> document = documentService.SearchDocument(title);

        return ResponseEntity.ok().body(document);


    }

    @DeleteMapping("api/delete/file/{Id}")

    public ResponseEntity<?> DeleteDocument(@PathVariable Long Id) {
        try {
            documentService.DeleteDocument(String.valueOf(Id));

            return ResponseEntity.ok().body("File deleted successfully ");
        } catch (IOException e) {


            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping("api/share/file")

    public ResponseEntity<String> ShareDocument(@RequestParam String Recipient, @RequestParam String title) throws MessagingException {
        documentService.ShareFile(Recipient, title);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("api/edit/file/{Id}")

    public ResponseEntity<?> editDocument(@PathVariable Long Id, @Validated @RequestBody EditDocumentRequest editDocumentRequest) {
        documentService.EditDocument(String.valueOf(Id), editDocumentRequest);

        return ResponseEntity.accepted().build();

    }
}



