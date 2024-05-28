package com.amalitechnss.Lizzy_fileServer.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service


public interface DocumentService {
    void SaveDocument(MultipartFile file) throws IOException;


}
