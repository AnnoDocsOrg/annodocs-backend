package com.annodocs.annodocsbackend.gesetz.paragraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paragraphRepository extends JpaRepository<paragraphEntity, Long> {
}
