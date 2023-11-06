package com.example.university_info.repository;

import com.example.university_info.entity.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findAllByFullNameContainingIgnoreCase(String template);

}
