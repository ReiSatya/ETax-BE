package com.etax.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PajakNasabahDTO {
    private String cif;
    private String npwp;
    private String nama;
    private String alamat;
    private String kota;
    private String banknpwp;
    private String namabank;
    private String email;
    private String namaofficetax;
    private List<PajakDTO> pajak;

    

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



	public List<PajakDTO> getPajak() {
		return pajak;
	}



	public void setPajak(List<PajakDTO> pajak) {
		this.pajak = pajak;
	}



	public static class PajakDTO {
        private Long tableId;
        private String cif;
        private String jenispenghasilan;
        private BigDecimal jumlahbunga;
        private int tarif;
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
}

