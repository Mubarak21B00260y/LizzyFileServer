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
import java.util.List;

public class DocumentServiceImpl implements  DocumentService{

    private String dir;
    private List foo;

    DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.documentRepository = repository;

    }
    // I try to Inject the  storage directory  dependency through  this constructor
    public DocumentServiceImpl(@Value("${file.storage}")String StorageDirectory) {
        dir =System.getProperty("user.dir") + "/" + StorageDirectory;
        try {
            Files.createDirectories(Path.of(dir));
        }
        catch (IOException e){
            throw  new RuntimeException( e.getMessage());
        }

    }

    public void SaveDocument(MultipartFile file) throws IOException {
        Path FilePath= Paths.get(dir+ "//"+" Filename");
 try {

     Files.copy(file.getInputStream(),FilePath, StandardCopyOption.REPLACE_EXISTING);

 }  catch (IOException e){

     throw  new IOException(e.getMessage());
 }
 // mock   of the document saving, to be fully implemented later
        Document document= new Document(FilePath.toString(),"","", "",foo);
        documentRepository.save(document);

    }
}
