package com.licenta.real.estate.repository;

import com.licenta.real.estate.entities.Image;
import com.licenta.real.estate.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByImageName(String name);
    List<Image> findImageByProperty(Property property);
}
