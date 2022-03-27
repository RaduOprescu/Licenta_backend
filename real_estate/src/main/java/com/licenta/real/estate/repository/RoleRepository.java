package com.licenta.real.estate.repository;


import com.licenta.real.estate.entities.ERole;
import com.licenta.real.estate.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(ERole role);

}
