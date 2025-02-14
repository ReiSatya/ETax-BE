package com.etax.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.etax.model.Nasabah;

public interface NasabahRepo extends JpaRepository<Nasabah, String> {

	Optional<Nasabah> findByNpwp(String npwp);
	Optional<Nasabah> findByCif(String cif);
	
	@Query("SELECT n FROM Nasabah n WHERE (:cif is null or n.cif = :cif) " +
            "and (:npwp is null or n.npwp = :npwp) " +
            "and (:nama is null or n.nama = :nama) " +
            "and (:banknpwp is null or n.banknpwp = :banknpwp) " +
            "and (:namabank is null or n.namabank = :namabank)")
    List<Nasabah> findByParams(
            @Param("cif") String cif,
            @Param("npwp") String npwp,
            @Param("nama") String nama,
            @Param("banknpwp") String banknpwp,
            @Param("namabank") String namabank);
}
