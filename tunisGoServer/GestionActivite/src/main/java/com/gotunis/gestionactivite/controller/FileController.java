package com.gotunis.gestionactivite.controller;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotunis.gestionactivite.message.ResponseFile;
import com.gotunis.gestionactivite.message.ResponseMessage;
import com.gotunis.gestionactivite.model.FileDB;
import com.gotunis.gestionactivite.service.ActiviteService;
import com.gotunis.gestionactivite.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/FileActivity")
public class FileController {
    @Autowired
    private FileStorageService storageService;
    @Autowired
    ActiviteService activiteService;

    @PostMapping("/add/{activityId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile[] file
    , @PathVariable("activityId") long activityId) throws IOException {
        String message = "";
        Long idOfActivity = activiteService.getById(activityId).get().getId();
       // Long idOfActivity = new ObjectMapper().readValue(activityId, Long.class);
        try {
                Arrays.asList(file).stream().forEach(files -> {
                    try {
                        storageService.storeWithHome(files,idOfActivity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            message = "Uploaded  files successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file:";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable String id) throws Exception {
        storageService.deleteImage(id);
        return HttpStatus.ACCEPTED;
    }
}
