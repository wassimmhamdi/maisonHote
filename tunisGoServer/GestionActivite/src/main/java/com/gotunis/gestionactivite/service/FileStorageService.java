package com.gotunis.gestionactivite.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.gotunis.gestionactivite.model.Activite;
import com.gotunis.gestionactivite.model.Convention;
import com.gotunis.gestionactivite.model.FileDB;
import com.gotunis.gestionactivite.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    ActiviteService activiteService;

    // enregistrer image pour Activity
    public FileDB storeWithHome(MultipartFile file, long activitieId) throws IOException {
        FileDB result =null;
        Activite activite = activiteService.getById(activitieId).get();
        if (activite != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileDB image = new FileDB(fileName, activite, file.getContentType(), file.getBytes());

            result= fileDBRepository.save(image);
        }
        return result;
    }

    public FileDB getFile(String  id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void deleteImage(String id) throws Exception {
        FileDB image = fileDBRepository.getOne(id);
        if (image != null) {
            fileDBRepository.deleteById(id);
        } else {
            throw new RuntimeException("image not found");
        }
    }
    public Optional<FileDB> findImageByname(@PathVariable("name") String name) {
        return fileDBRepository.findByName(name);
    }
}
