package com.etax.dto;


public class NasabahDTO {
	private String cif;
	private String npwp;
    private String nama;
    private String alamat;
    
    
	public NasabahDTO(String cif, String npwp, String nama, String alamat) {
		this.cif = cif;
		this.npwp = npwp;
		this.nama = nama;
		this.alamat = alamat;
	}
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
    
    

}
