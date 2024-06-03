package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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



    private LocalDateTime uploadedAt;
    private   String FilePath;
private int  MailedFailedCount;
    private int DownloadsCount;
//  public Document( String  filepath, String  title , String description, String category ) {
// FilePath=filepath;
//  Description=description;
//  Title=title;
//  Category=category;
//}



}


