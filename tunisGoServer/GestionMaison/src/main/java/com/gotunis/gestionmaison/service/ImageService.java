package com.gotunis.gestionmaison.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.gotunis.gestionmaison.models.Chambre;
import com.gotunis.gestionmaison.models.Image;
import com.gotunis.gestionmaison.models.Maison;
import com.gotunis.gestionmaison.repository.ChambreRepository;
import com.gotunis.gestionmaison.repository.ImageRepository;
import com.gotunis.gestionmaison.repository.MaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    MaisonRepository maisonRepository;

    @Autowired
    ChambreRepository chambreRepository;


    // enregistrer image pour maison
    public Image storeMaison(MultipartFile file, long maisonId) throws IOException {
        Image result =null;
        Maison maison = maisonRepository.findById(maisonId).get();
        if (maison != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image image = new Image(fileName, maison,null, file.getContentType(), file.getBytes());

            result= imageRepository.save(image);
        }

        return result;

    }


    //enregistrer image pour chambre
    public Image storeChambre(MultipartFile file, long chambreId) throws IOException {
        Image result =null;
        Chambre chambre = chambreRepository.findById(chambreId).get();
        if (chambre != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image image = new Image(fileName,null,chambre, file.getContentType(), file.getBytes());

            result= imageRepository.save(image);
        }

        return result;

    }


    public Image getFile(Long id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return imageRepository.findAll().stream();
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> findImageByname(@PathVariable("name") String name) {
        return imageRepository.findByNameImage(name);
    }
}





