package com.amalitechnss.Lizzy_fileServer.Service;
import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import com.amalitechnss.Lizzy_fileServer.Exceptions.DocumentNotFoundException;
import com.amalitechnss.Lizzy_fileServer.Exceptions.DocumentTitleExistsException;
import com.amalitechnss.Lizzy_fileServer.Model.DocumentDTO;
import com.amalitechnss.Lizzy_fileServer.Repository.DocumentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service

public class DocumentServiceImpl implements  DocumentService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);
    @Value("${file.storage}")
    private String FilePath;

    private DocumentRepository documentRepository;
   private DocumentDTO documentDTO;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentDTO documentDTO ) {
        this.documentRepository = documentRepository;
        this.documentDTO=documentDTO;

    }


    public String UploadDocument(MultipartFile file, DocumentDTO documentDTO) throws IOException {
        String Filename= file.getOriginalFilename();
         String dir= System.getProperty("user.dir")+ File.separator+ FilePath;
         Path Target=Paths.get(dir).resolve(Filename);

 try {

     file.transferTo(Target.toFile());
   log.info(" saved  file ");

   System.out.println(FilePath);
 }  catch (IOException e){
      log.warn(" not save file");
     throw  new IOException(e.getMessage());
 }

    Document document= new Document();
    document.setTitle(documentDTO.getTitle());

  document.setDescription(documentDTO.getDescription());
  document.setUploadedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()) );
  document.setFilePath(Target.toString());

     if (documentRepository.existsByTitle(documentDTO.getTitle()))
     throw  new DocumentTitleExistsException("document title already exists");
  documentRepository.save(document);

        return Filename;
    }
    public UrlResource DownloadDocument (String Filename) throws  IOException {

        try{
            String dir= System.getProperty("user.dir")+ File.separator+  FilePath;

            Path Target=Paths.get(dir).resolve(Filename).normalize();
               log.info(Target.toString());
               Optional<Document> optionalDocument=  documentRepository.findByFilePath(Target.toString());
                Document document=optionalDocument.get();
                document.incrementDownloadsCount();
                documentRepository.save(document);


            return   new UrlResource(Target.toUri());

        }
         catch (IOException e ) {

            throw  new IOException(e.getMessage());
        }

    }

     public Page<Document> FetchDocuments(int page, int size) {

         PageRequest pageable= PageRequest.of(page, size);

        return  documentRepository.findAll(pageable);

     }

    public Optional<Document> SearchDocument(String Title ) {

        Optional<Document> document= documentRepository.findByTitle(Title);
        if (document.isEmpty()){

            throw new DocumentNotFoundException("no matching document  with title found");
        }
        return  document;


    }

    public void DeleteDocument(String Id  ) throws IOException{
            Optional<Document> document= documentRepository.findById(Id);

            if( document.isEmpty()) {

                throw  new DocumentNotFoundException("document not found");


    }
        documentRepository.deleteById(Id);

} }
