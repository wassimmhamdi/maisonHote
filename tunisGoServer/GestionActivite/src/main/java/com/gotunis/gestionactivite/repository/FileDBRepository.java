package com.gotunis.gestionactivite.repository;

import com.gotunis.gestionactivite.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

    Optional<FileDB> findById(String id);

    Optional<FileDB> findByName(String name);
}
