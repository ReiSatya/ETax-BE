package com.etax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

import jakarta.persistence.Column;

@Entity
@Table(name = "tax_log")
public class LogPajak {
    @Id
    @Column(name = "log_id")
    private String logId;

    @Column(name = "log_cif")
    private String logCif;

    @Column(name = "log_generate_date")
    private LocalDate logGenerateDate;
    
    @Column(name = "log_status")
    private String logStatus;

    @Column(name = "log_message")
    private String logMessage;
    
    @Column(name = "log_path_pdf")
    private String logPathPdf;
    
    @Column(name = "log_period")
    private String logPeriod;
    
    @Column(name = "log_nosurat")
    private String logNosurat;
    
    @Column(name = "log_send_date")
    private LocalDate logSendDate;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogCif() {
		return logCif;
	}

	public void setLogCif(String logCif) {
		this.logCif = logCif;
	}

	public LocalDate getLogGenerateDate() {
		return logGenerateDate;
	}

	public void setLogGenerateDate(LocalDate logGenerateDate) {
		this.logGenerateDate = logGenerateDate;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLogPathPdf() {
		return logPathPdf;
	}

	public void setLogPathPdf(String logPathPdf) {
		this.logPathPdf = logPathPdf;
	}

	public String getLogPeriod() {
		return logPeriod;
	}

	public void setLogPeriod(String logPeriod) {
		this.logPeriod = logPeriod;
	}

	public String getLogNosurat() {
		return logNosurat;
	}

	public void setLogNosurat(String logNosurat) {
		this.logNosurat = logNosurat;
	}

	public LocalDate getLogSendDate() {
		return logSendDate;
	}

	public void setLogSendDate(LocalDate logSendDate) {
		this.logSendDate = logSendDate;
	}
    
    
    
}
