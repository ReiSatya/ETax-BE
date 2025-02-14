package com.etax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pajak")
public class Pajak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    private String cif;

    private String jenispenghasilan;

    private BigDecimal jumlahbunga;

    private Integer tarif;

    private BigDecimal pphpotong;

    private String dnln;

    private LocalDate tanggalpembuatan;

    private String status;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getJenispenghasilan() {
		return jenispenghasilan;
	}

	public void setJenispenghasilan(String jenispenghasilan) {
		this.jenispenghasilan = jenispenghasilan;
	}

	public BigDecimal getJumlahbunga() {
		return jumlahbunga;
	}

	public void setJumlahbunga(BigDecimal jumlahbunga) {
		this.jumlahbunga = jumlahbunga;
	}

	public Integer getTarif() {
		return tarif;
	}

	public void setTarif(Integer tarif) {
		this.tarif = tarif;
	}

	public BigDecimal getPphpotong() {
		return pphpotong;
	}

	public void setPphpotong(BigDecimal pphpotong) {
		this.pphpotong = pphpotong;
	}

	public String getDnln() {
		return dnln;
	}

	public void setDnln(String dnln) {
		this.dnln = dnln;
	}

	public LocalDate getTanggalpembuatan() {
		return tanggalpembuatan;
	}

	public void setTanggalpembuatan(LocalDate tanggalpembuatan) {
		this.tanggalpembuatan = tanggalpembuatan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
