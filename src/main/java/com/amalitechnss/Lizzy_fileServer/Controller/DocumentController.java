package com.amalitechnss.Lizzy_fileServer.Controller;
import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Model.DocumentDTO;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController

public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);
    private DocumentService documentService;
    private DocumentDTO documentDTO;

    public DocumentController(DocumentService documentService, DocumentDTO documentDTO) {
        this.documentService = documentService;
        this.documentDTO=documentDTO;
    }

    //@RequestMapping("file")
    @PostMapping("/api/upload/single")
    @ResponseBody
    public ResponseEntity upload(@RequestParam() MultipartFile file , @ModelAttribute DocumentDTO documentDTO) {

        try {
            documentService.UploadDocument(file,documentDTO);

            return  ResponseEntity.status(HttpStatus.OK).body("File Uploaded successfully ");
        } catch (IOException e) {

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

     @GetMapping("api/download/{file}")

    public ResponseEntity<UrlResource> download (@PathVariable("file")  String File) throws IOException {
                try {

                    UrlResource resource = documentService.DownloadDocument(File);
                    if (resource!=null) {
                        return   ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= \"" +resource.getFilename()+"\"").contentType(MediaType.APPLICATION_OCTET_STREAM)  .body(resource);
                    }

                } catch  (IOException e){
                   log.warn(e.getMessage());
                     ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }

         return  null;

     }

    @GetMapping("api/get/all")
    public List<Document> getDocuments () {
            return documentService.fetchDocuments();

        }

    }






