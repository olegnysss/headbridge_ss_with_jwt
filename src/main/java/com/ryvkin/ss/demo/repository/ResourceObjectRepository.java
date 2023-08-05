package com.ryvkin.ss.demo.repository;

import com.ryvkin.ss.demo.domain.entity.ResourceObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceObjectRepository extends JpaRepository<ResourceObjectEntity, Integer> {
}
