package com.amalitechnss.Lizzy_fileServer.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
@Service
public class AppUrlServiceImpl implements  AppUrlService{

    public  String  AppUrl(HttpServletRequest request) {



        return  "Http://"+ request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
