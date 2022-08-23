package com.licenta.real.estate.repository;

import com.licenta.real.estate.entities.FavoriteProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritePropertyRepository extends JpaRepository<FavoriteProperty, Long> {

}
