package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
