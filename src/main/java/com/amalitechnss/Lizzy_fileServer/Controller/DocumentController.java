package com.amalitechnss.Lizzy_fileServer.Controller;

import com.amalitechnss.Lizzy_fileServer.Model.Document;
import com.amalitechnss.Lizzy_fileServer.Service.DocumentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class DocumentController {

    private  DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }


    @RequestMapping("/api/Documents")

    @PostMapping("/upload")
    public Document upload (@RequestParam()MultipartFile file)  {

        Document document=  null;

        document=  documentService.SaveDocument(file);


return  null;




    }
}
