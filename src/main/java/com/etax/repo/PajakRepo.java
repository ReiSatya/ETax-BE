package com.etax.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etax.dto.StatusDeliveryDTO;
import com.etax.model.Pajak;

import java.time.LocalDate;
import java.util.List;

public interface PajakRepo extends JpaRepository<Pajak, Long> {
    List<Pajak> findAll();
    List<Pajak> findByCif(String cif);
    
    @Query("SELECT p FROM Pajak p WHERE (:tableid is null or p.tableId = :tableid) " +
            "and (:cif is null or p.cif = :cif) " +
            "and (:status is null or p.status = :status) ")
    List<Pajak> findByParams(
            @Param("tableid") Long tableid,
            @Param("cif") String cif,
            @Param("status") String status);

    List<Pajak> findByTanggalpembuatanBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Pajak p WHERE p.tanggalpembuatan >= :startDate")
    List<Pajak> findByTanggalpembuatanAfter(@Param("startDate") LocalDate startDate);

    @Query("SELECT p FROM Pajak p WHERE p.tanggalpembuatan <= :endDate")
    List<Pajak> findByTanggalpembuatanBefore(@Param("endDate") LocalDate endDate);
    
    
    
    
}

