package com.example.springBoot.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//indicating this class is repository
@Repository
public interface MuseumRepository extends JpaRepository<spring, Integer>{
   ArrayList<spring> findAll();
   }
