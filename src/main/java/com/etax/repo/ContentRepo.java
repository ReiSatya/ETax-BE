package com.etax.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etax.model.Content;

public interface ContentRepo extends JpaRepository<Content, String> {
	Optional<Content> findById(String id);
}

