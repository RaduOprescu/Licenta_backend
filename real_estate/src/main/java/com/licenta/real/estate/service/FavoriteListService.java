package com.licenta.real.estate.service;


import com.licenta.real.estate.dtos.FavoriteListDTO;
import com.licenta.real.estate.dtos.PropertyDTO;
import com.licenta.real.estate.entities.FavoriteList;
import com.licenta.real.estate.entities.FavoriteProperty;
import com.licenta.real.estate.entities.Property;
import com.licenta.real.estate.mapper.FavoriteListMapper;
import com.licenta.real.estate.mapper.PropertyMapper;
import com.licenta.real.estate.repository.FavoriteListRepository;
import com.licenta.real.estate.repository.FavoritePropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteListService {

    private final FavoriteListRepository favoriteListRepository;
    private final FavoriteListMapper favoriteListMapper;

    private final FavoritePropertyRepository favoritePropertyRepository;
    private final PropertyService propertyService;

    private final PropertyMapper propertyMapper;


    public FavoriteList addToFavoriteListFirstTime(Long id) {
        FavoriteList favoriteList = new FavoriteList();
        FavoriteProperty favoriteProperty = new FavoriteProperty();

        PropertyDTO propertyDTO = propertyService.findById(id);
        Property property = propertyMapper.fromDto(propertyDTO);

//        property = propertyService.findById(id);
        favoriteProperty.setProperty(property);

        favoriteList.getFavoriteProperties().add(favoriteProperty);

        return favoriteListRepository.save(favoriteList);

    }

    public FavoriteList findById(long id){
        FavoriteList favoriteList = favoriteListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("FavoriteList not found"));
        return favoriteList;
    }

    public FavoriteList addToExistingFavoriteList(Long idList, Long idProperty) {

        FavoriteList favoriteList = findById(idList);

        PropertyDTO propertyDTO = propertyService.findById(idProperty);
        Property property = propertyMapper.fromDto(propertyDTO);

        Boolean propertyDoesExistInTheCart = false;
        if (favoriteList != null) {
            List<FavoriteProperty> favoriteProperties = favoriteList.getFavoriteProperties();
            for (FavoriteProperty fav : favoriteProperties) {
                if (fav.getProperty().equals(property)) {
                    propertyDoesExistInTheCart = true;
                    break;
                }

            }
        }
        if(!propertyDoesExistInTheCart && (favoriteList != null))
        {
            FavoriteProperty favoriteProperty = new FavoriteProperty();
            favoriteProperty.setProperty(property);
            favoriteList.getFavoriteProperties().add(favoriteProperty);
            return favoriteListRepository.save(favoriteList);
        }

        return null;

    }

    public FavoriteList removeFavoriteProperty(Long idList, Long idProperty) {

        FavoriteList favoriteList = findById(idList);
        PropertyDTO propertyDTO = propertyService.findById(idProperty);
        Property property = propertyMapper.fromDto(propertyDTO);

        List<FavoriteProperty> favoriteProperties = favoriteList.getFavoriteProperties();
        FavoriteProperty deleteProperty = null;
        for(FavoriteProperty delete : favoriteProperties) {
            if(delete.getId()==idProperty) {
                deleteProperty = delete;
            }
        }
        favoriteProperties.remove(deleteProperty);
        favoritePropertyRepository.delete(deleteProperty);
        favoriteList.setFavoriteProperties(favoriteProperties);
        return favoriteListRepository.save(favoriteList);
    }


    public List<FavoriteListDTO> findAll(){
        List<FavoriteListDTO> properties = favoriteListRepository.findAll().stream().map(favoriteListMapper::toDto).collect(Collectors.toList());
        return favoriteListRepository.findAll()
                .stream()
                .map(favoriteListMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        favoriteListRepository.deleteById(id);
    }

//    public FavoriteListDTO create(FavoriteListDTO favoriteListDTO){
//       // favoriteListDTO.setNoOfViews(0);
//        FavoriteList favoriteList = favoriteListMapper.fromDto(favoriteListDTO);
//        favoriteList = favoriteListRepository.save(favoriteList);
//        return favoriteListMapper.toDto(favoriteList);
//    }




}
