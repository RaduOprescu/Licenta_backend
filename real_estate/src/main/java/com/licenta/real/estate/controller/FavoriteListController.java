//package com.licenta.real.estate.controller;
//
//
//import com.licenta.real.estate.dtos.UserDTO;
//import com.licenta.real.estate.entities.FavoriteList;
//import com.licenta.real.estate.service.FavoriteListService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequiredArgsConstructor
//
//@RequestMapping("/api/favoriteList")
//public class FavoriteListController {
//
//    private final FavoriteListService favoriteListService;
//
//    @GetMapping("/addToFavoriteList/{id}")
//    public FavoriteList addToFavoriteList(@PathVariable long id, @RequestBody UserDTO userDTO) {
//
//        FavoriteList favoriteList = userDTO.getFavoriteList();
//        //favoriteList = favoriteListService.findById(id);
//        if(favoriteList == null){
//            favoriteListService.addToFavoriteListFirstTime(id);
////            favoriteList = new FavoriteList();
////            favoriteList.setPropertyId(id);
////            favoriteListService.create(favoriteList);
//        }else{
//            favoriteListService.addToExistingFavoriteList(favoriteList.getId(), id);
//
//        }
//        return favoriteList;
//    }
//
//    //remove from favorite list
//    @DeleteMapping(value = "/removeFromFavoriteList/{id}")
//    public void removeFromFavoriteList(@PathVariable long id, @RequestBody UserDTO userDTO) {
//
//        FavoriteList favoriteList = userDTO.getFavoriteList();
//
//        favoriteListService.removeFavoriteProperty(favoriteList.getId(), id);
//    }
//
//    //delete the favorite list
//    @DeleteMapping(value = "/deleteFavoriteList")
//    public void deleteFavoriteList(@RequestBody UserDTO userDTO) {
//
//        FavoriteList favoriteList = userDTO.getFavoriteList();
//
//        favoriteListService.delete(favoriteList.getId());
//    }
//
//
//}
