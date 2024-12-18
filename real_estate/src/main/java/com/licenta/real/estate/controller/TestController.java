package com.licenta.real.estate.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER') or hasRole('ADMIN') ")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/buyer")
    @PreAuthorize("hasRole('BUYER')")
    public String buyerAccess() {
        return "Buyer Board.";
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public String sellerAccess() {
        return "Seller Board.";
    }

}
