package com.etax.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etax.model.Parameter;

@Repository
public interface ParameterRepo extends JpaRepository<Parameter, Integer> {
    Optional<Parameter> findByParameterName(String parameterName);
}

