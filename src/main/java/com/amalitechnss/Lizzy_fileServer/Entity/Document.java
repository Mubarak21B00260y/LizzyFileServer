package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@NoArgsConstructor
@Entity
@Data

public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private  String  Description;



    private Date uploadedAt;
    private   String filePath;
private int  MailedFailedCount;
    private int DownloadsCount;






    public  void  incrementDownloadsCount(){

         this.DownloadsCount++;
    }
}


