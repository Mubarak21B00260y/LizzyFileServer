package com.amalitechnss.Lizzy_fileServer.Repository;

import com.amalitechnss.Lizzy_fileServer.Entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,String> {
}
