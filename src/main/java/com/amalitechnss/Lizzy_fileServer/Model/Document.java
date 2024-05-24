package com.amalitechnss.Lizzy_fileServer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, unique = true)
    private String Title;
    @Column(nullable = false)
    private  String  Description;


    private List<Document> Tags;
    @OneToOne
    @JoinColumn(name = "UploadedBy")
    private  User user;

    private LocalDateTime uploadedAt;
     private  String Category;
    private   String FilePath;
private int  MailedFailedCount;
    private int DownloadsCount;
    public Document( String  filepath, String  title , String description, String category ,List tags) {
        FilePath=filepath;
        Description=description;
        Title=title;
        Category=category;
        Tags=tags;

    }



}


