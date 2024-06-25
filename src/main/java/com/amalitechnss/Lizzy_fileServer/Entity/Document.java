package com.amalitechnss.Lizzy_fileServer.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Consumer;


@NoArgsConstructor
@Entity
@Data

public class Document  {

    private static final Logger log = LoggerFactory.getLogger(Document.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private  String  Description;


  private  String filename;
    private Date uploadedAt;
    private String filePath;
    private int  MailedFilesCount;
    private int DownloadsCount;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "downloader",  foreignKey = @ForeignKey(name = "F_k_downloader"))
//    private User  downloadedBy;




    public  void IncrementDownloadsCount(){

         this.DownloadsCount++;
    }


    public void IncrementMailedFilesCount () {


        this.MailedFilesCount++;

    }
}


