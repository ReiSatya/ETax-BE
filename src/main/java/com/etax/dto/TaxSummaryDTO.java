package com.etax.dto;

import java.math.BigDecimal;
import java.util.List;

public class TaxSummaryDTO {
    private String cif;
    private String nama;
    private String alamat;
    private String kota;
    private String period;
    private List<TaxSummaryDetailDTO> pajak;
    private BigDecimal totalpenghasilan;
    private BigDecimal totalpajak;
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public List<TaxSummaryDetailDTO> getPajak() {
		return pajak;
	}
	public void setPajak(List<TaxSummaryDetailDTO> pajak) {
		this.pajak = pajak;
	}
	public BigDecimal getTotalpenghasilan() {
		return totalpenghasilan;
	}
	public void setTotalpenghasilan(BigDecimal totalpenghasilan) {
		this.totalpenghasilan = totalpenghasilan;
	}
	public BigDecimal getTotalpajak() {
		return totalpajak;
	}
	public void setTotalpajak(BigDecimal totalpajak) {
		this.totalpajak = totalpajak;
	}

    
}
