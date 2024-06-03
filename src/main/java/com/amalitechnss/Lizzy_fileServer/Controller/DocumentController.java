package com.amalitechnss.Lizzy_fileServer.Controller;
import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Model.DocumentDTO;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

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
    public ResponseEntity Upload(@RequestParam() MultipartFile file , @ModelAttribute DocumentDTO documentDTO) {

        try {
            documentService.UploadDocument(file,documentDTO);

            return  ResponseEntity.ok().body("File Uploaded successfully ");
        } catch (IOException e) {

            return  ResponseEntity.internalServerError().build();
        }
    }

     @GetMapping("api/download/{file}")

    public ResponseEntity<UrlResource> download (@PathVariable("file")  String File) throws IOException {
                try {

                    UrlResource resource = documentService.DownloadDocument(File);
                    if (resource!=null) {
                        return   ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= \"" +resource.getFilename()+"\"").contentType(MediaType.APPLICATION_OCTET_STREAM)  .body(resource);
                    }

                } catch  (IOException e){
                   log.warn(e.getMessage());
                     ResponseEntity.internalServerError().build();
         }

         return  null;

     }

    @GetMapping("api/get/all")
    public ResponseEntity<Stream<Document>> GetAllDocuments (@RequestParam(defaultValue = "0" )int page, @RequestParam ( defaultValue = "10 " )int size ) {


        Page<Document> fetchedDocuments= documentService.FetchDocuments(page, size);
        if(fetchedDocuments.hasContent())
           return  ResponseEntity.ok(fetchedDocuments.get());
        else
            return ResponseEntity.noContent().build();
        }


    @GetMapping("api/search/file")
    public ResponseEntity<Optional<Document>> SearchDocument (@RequestParam String title ) {
        Optional < Document> document =  documentService.SearchDocument(title);

      return     ResponseEntity.ok().body(document);


    }
    @DeleteMapping("api/delete/file/{Id}")

    public ResponseEntity<String> DeleteDocument ( @PathVariable Long Id)  {
        try {
            documentService.DeleteDocument(String.valueOf(Id));

            return  ResponseEntity.ok().body("File deleted successfully ");
        } catch (IOException e) {


            return  ResponseEntity.internalServerError().build();
        }



    }

    }






