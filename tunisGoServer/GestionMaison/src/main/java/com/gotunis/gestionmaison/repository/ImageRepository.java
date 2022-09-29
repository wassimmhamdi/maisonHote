package com.gotunis.gestionmaison.repository;

import com.gotunis.gestionmaison.models.Image;
import com.gotunis.gestionmaison.models.Maison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository <Image, Long> {
    Optional<Image> findByNameImage(String nameImage);
    Optional<Image> findById(long id);


}
