package com.etax.dto;

import java.math.BigDecimal;

public class TaxSummaryDetailDTO {
    private int no;
    private String jenispenghasilan;
    private BigDecimal jumlahbunga;
    private int tarif;
    private BigDecimal pphpotong;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getTarif() {
		return tarif;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
	public BigDecimal getPphpotong() {
		return pphpotong;
	}
	public void setPphpotong(BigDecimal pphpotong) {
		this.pphpotong = pphpotong;
	}

    
}
