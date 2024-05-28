package com.annodocs.annodocsbackend.gesetz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface gesetzRepository extends JpaRepository<gesetzEntity, Long> {

}
