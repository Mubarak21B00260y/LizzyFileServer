package com.amalitechnss.Lizzy_fileServer.Controller;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Requests.DocumentUploadRequest;
import com.amalitechnss.Lizzy_fileServer.Requests.EditDocumentRequest;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class DocumentController {


    private final DocumentService documentService;

    @SneakyThrows
    @PostMapping("/api/upload/single")
    public ResponseEntity<?> Upload(@RequestParam() MultipartFile file, @ModelAttribute DocumentUploadRequest documentUploadRequest) {
        documentService.UploadDocument(file,documentUploadRequest);
        return  ResponseEntity.ok().build();
    }
    @GetMapping("api/download/file")

    public ResponseEntity<Resource> Download(@RequestParam String title) throws IOException {
   Resource  document= documentService.DownloadDocument(title);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= \"" + document.getFilename() + "\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(document);

    }

    @GetMapping("api/get/all")
    public ResponseEntity<Stream<Document>> GetAllDocuments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20 ") int size)  {

        Page<Document> fetchedDocuments = documentService.FetchDocuments(page, size);
        if (fetchedDocuments.hasContent()) {
            HttpHeaders  headers = new HttpHeaders();
            headers.add("TotalDocuments",String.valueOf(documentService.getTotalDocuments()));
            return ResponseEntity.ok(). headers(headers).body(fetchedDocuments.get()); }
        else {
            return ResponseEntity.noContent().build();

        }
    }


    @GetMapping("api/search/file")
    public ResponseEntity<List<Document>> SearchDocument(@RequestParam String title) {
        List<Document> document = documentService.SearchDocument(title);
        return ResponseEntity.ok().body(document);

    }
    @DeleteMapping("api/delete/file/{Id}")
     @SneakyThrows
    public ResponseEntity<?> DeleteDocument(@PathVariable Long Id) {
        documentService.DeleteDocument(String.valueOf(Id));
        return  ResponseEntity.ok().build();
    }

    @PostMapping("api/share/file")

    public ResponseEntity<String> ShareDocument(@RequestParam String Recipient, @RequestParam String title) throws MessagingException {
        documentService.ShareFile(Recipient, title);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("api/edit/file/{Id}")

    public ResponseEntity<?> editDocument(@PathVariable Long Id,  @RequestBody EditDocumentRequest editDocumentRequest) {
        documentService.EditDocument(String.valueOf(Id), editDocumentRequest);

        return ResponseEntity.accepted().build();

    }
}



