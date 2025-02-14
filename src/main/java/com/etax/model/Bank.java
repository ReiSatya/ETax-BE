package com.etax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @Column(name = "banknpwp")
    private String banknpwp;

    @Column(name = "namabank", unique = true, nullable = false)
    private String namabank;

    @Column(name = "alamatbank")
    private String alamatbank;

    
	public String getBanknpwp() {
		return banknpwp;
	}

	public void setBanknpwp(String banknpwp) {
		this.banknpwp = banknpwp;
	}

	public String getNamabank() {
		return namabank;
	}

	public void setNamabank(String namabank) {
		this.namabank = namabank;
	}

	public String getAlamatbank() {
		return alamatbank;
	}

	public void setAlamatbank(String alamatbank) {
		this.alamatbank = alamatbank;
	}

    
}
