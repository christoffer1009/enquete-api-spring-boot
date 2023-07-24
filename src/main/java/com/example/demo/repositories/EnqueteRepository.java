package com.example.demo.repositories;

import com.example.demo.models.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnqueteRepository extends JpaRepository<Enquete, Long> {
}
