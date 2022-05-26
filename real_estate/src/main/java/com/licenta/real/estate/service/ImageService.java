package com.licenta.real.estate.service;

import com.licenta.real.estate.entities.Image;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.repository.ImageRepository;
import com.licenta.real.estate.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageService {
    //TODO: implement image service methods here

    private final ImageRepository imageRepository;

    private final PropertyRepository propertyRepository;

    private static final String FILE_DIRECTORY = "/var/files";
    private final Path rootLocation = Paths.get("filestorage");

    public void store(MultipartFile file, Long propertyId) throws IOException {
        System.out.println("storeQQQQ");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image file1 = new Image(file.getBytes(), fileName, file.getContentType());
        Optional<Property> optionalProperty;
        optionalProperty = propertyRepository.findById(propertyId);
        optionalProperty.ifPresent(file1::setProperty);
        imageRepository.save(file1);
    }


    public Image getImage(Long id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Image> getAllImages() {
        return imageRepository.findAll().stream();
    }

    public List<Image> findImagesByProperty(Long propertyId) {
        Optional<Property> optionalProperty;
        optionalProperty = propertyRepository.findById(propertyId);
        if (!optionalProperty.isPresent()) {
            throw new ServiceException("No such property");
        }
        return imageRepository.findImageByProperty(optionalProperty.get());
    }
//
//    public List<Image> findImagesByProperty(Long tutorialId) {
//        Optional<Tutorial> optionalTutorial;
//        optionalTutorial = tutorialRepo.findById(tutorialId);
//        if (!optionalTutorial.isPresent()) {
//            throw new ServiceException("No such tutorial!");
//        }
//        List<Chapter> chapters = chapterRepo.findChaptersByTutorial(optionalTutorial.get());
//        List<Subchapter> subchapters = new ArrayList<>();
//        for (Chapter c : chapters) {
//            List<Subchapter> subchapters1 = subChapterRepo.findSubchaptersByChapter(c);
//            if(subchapters1!=null){
//                subchapters.addAll(subchapters1);
//            }
//        }
//        List<File> files = new ArrayList<>();
//        for (Subchapter s : subchapters) {
//            List<File> files1 = fileRepo.findFileBySubchapter(s);
//            if(files1!=null){
//                files.addAll(files1);
//            }
//        }
//        return files;
//    }

}
