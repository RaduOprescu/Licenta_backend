package com.licenta.real.estate.controller;


import com.licenta.real.estate.entities.Image;
import com.licenta.real.estate.payload.response.ResponseImage;
import com.licenta.real.estate.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor

@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestParam Long propertyId) throws IOException{
        imageService.store(file, propertyId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/get/files")
    public ResponseEntity<List<ResponseImage>> getFiles(@RequestParam Long propertyId) throws MalformedURLException {
        List<ResponseImage> files = imageService.findImagesByProperty(propertyId).stream().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/image/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseImage(
                    dbFile.getImageName(),
                    fileDownloadUri,
                    dbFile.getImageType(),
                    dbFile.getImage().length,
                    propertyId);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);

    }

    @GetMapping("/get/image/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Image fileDB = imageService.getImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getImageName() + "\"")
                .body(fileDB.getImage());
    }

}
