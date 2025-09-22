package com.carddemo.repository;

import com.carddemo.entity.CardCrossReference;
import com.carddemo.entity.CardCrossReferenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCrossReferenceRepository extends JpaRepository<CardCrossReference, CardCrossReferenceId> {
}
