package com.amalitechnss.Lizzy_fileServer.Repository;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,String> {

    Optional<Document> findByTitle(String  title);
    boolean  existsByTitle(String title);
    Optional<Document>findByFilePath(String FilePath);

    @Query("SELECT d FROM Document d WHERE LOWER(d.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Document> findByAlikeTerms(@Param("title") String title);

}
