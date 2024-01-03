package com.growder.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.growder.entity.CitizenAppEntity;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
