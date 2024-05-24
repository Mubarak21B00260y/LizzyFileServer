package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Model.Document;
import com.amalitechnss.Lizzy_fileServer.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocumentServiceImpl {


    private String dir;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.documentRepository = repository;
    }

    DocumentRepository documentRepository;
    public String DocumentStoringLocation(@Value("${file.storage}")String StorageDirectory) {

       dir =System.getProperty("user.dir") + "/" + StorageDirectory;

        try {
            Files.createDirectories(Path.of(dir));
        }

catch (IOException e){
throw  new RuntimeException(" Error creating directory");

}
        return null;
    }
    Document SaveDocument(MultipartFile file) throws IOException {


         Path FilePath= Paths.get(dir+ "//"+" Filename");
        Files.copy(file.getInputStream(),FilePath, StandardCopyOption.REPLACE_EXISTING);


        return  null;
    }
}
