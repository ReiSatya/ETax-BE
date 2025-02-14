package com.etax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "nasabah")
public class Nasabah {
    @Id
    @Column(name = "cif")
    private String cif;

    @Column(name = "npwp", unique = true, nullable = false)
    private String npwp;

    @Column(name = "nama")
    private String nama;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "kota")
    private String kota;
    
    @Column(name = "banknpwp")
    private String banknpwp;
    
    @Column(name = "namabank")
    private String namabank;

    @Column(name = "email")
    private String email;

    @Column(name = "namaofficetax")
    private String namaofficetax;

    
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNamaofficetax() {
		return namaofficetax;
	}

	public void setNamaofficetax(String namaofficetax) {
		this.namaofficetax = namaofficetax;
	}

    
}


