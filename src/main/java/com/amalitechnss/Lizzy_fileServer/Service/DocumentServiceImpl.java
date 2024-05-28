package com.amalitechnss.Lizzy_fileServer.Service;
import com.amalitechnss.Lizzy_fileServer.Entity.Document;
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
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DocumentServiceImpl implements  DocumentService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);
    @Value("${file.storage}")
    private String FilePath;
    DocumentRepository documentRepository;

    DocumentDTO documentDTO;
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentDTO documentDTO ) {
        this.documentRepository = documentRepository;
        this.documentDTO=documentDTO;

    }


    public void UploadDocument(MultipartFile file, DocumentDTO documentDTO) throws IOException {
        String Filename= file.getOriginalFilename();
         String dir= System.getProperty("user.dir")+ File.separator+ FilePath;
         Path Target=Paths.get(dir).resolve(Filename);

 try {

     file.transferTo(Target.toFile());
   log.info(" saved  file ");
   //System.out.println(dir);
   System.out.println(FilePath);
 }  catch (IOException e){
      log.warn(" not save file");
     throw  new IOException(e.getMessage());
 }
 // mock   of the document saving, to be fully implemented later
    Document document= new Document();
  document.setTitle(documentDTO.getTitle());
  document.setDescription(documentDTO.getDescription());
  document.setUploadedAt(LocalDateTime.now());
  document.setFilePath(Target.toString());

  documentRepository.save(document);

    }
    public UrlResource DownloadDocument (String Filename) throws  IOException {

        try{
            String dir= System.getProperty("user.dir")+ File.separator+  FilePath;

            Path Target=Paths.get(dir).resolve(Filename).normalize();
               log.info(Target.toString());
            return   new UrlResource(Target.toUri());
        }
         catch (IOException e ) {

            throw  new IOException(e.getMessage());
        }

    }

     public Page<Document> fetchDocuments(int page, int size) {
         PageRequest pageable= PageRequest.of(page, size);

        return  documentRepository.findAll(pageable);

     }

    public Optional<Document> SearchDocument(String Title ) {

        return  documentRepository.findByTitle(Title);

    }




}
