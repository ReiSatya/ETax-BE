package com.etax.dto;

import java.time.LocalDate;

public class StatusDeliveryDTO {
    private int no;
    private String cif;
    private String email;
    private LocalDate tanggalpembuatan;
    private String status;
    private String message;
    
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

    
}

