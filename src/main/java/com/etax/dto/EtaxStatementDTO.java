package com.etax.dto;

import java.util.List;

public class EtaxStatementDTO {
	 private String cif;
	 private String npwp;
	 private String nama;
	 private String alamat;
	 private List<EtaxStatementDetailDTO> pajak;
	 
	 
	 
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String cif) {
		this.npwp = cif;
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
	public List<EtaxStatementDetailDTO> getPajak() {
		return pajak;
	}
	public void setPajak(List<EtaxStatementDetailDTO> pajak) {
		this.pajak = pajak;
	}
	 
	 

}
