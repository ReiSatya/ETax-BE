package com.etax.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etax.model.Bank;
import com.etax.model.Nasabah;

public interface BankRepo extends JpaRepository<Bank, String> {
	
	Bank findByBanknpwp(String banknpwp);
	Optional<Bank> findByBanknpwpAndNamabank(String banknpwp, String namabank);
}

