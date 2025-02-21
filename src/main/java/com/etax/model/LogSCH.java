package com.etax.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tax_log_sch")
public class LogSCH {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_sch_id")
    private Long id;
	
    @Column(name = "log_sch_sd")
    private LocalDate logSchSd;

    @Column(name = "log_sch_fd")
    private LocalDate logSchFd;

    @Column(name = "log_sch_total")
    private String logSchTotal;
    
    @Column(name = "log_sch_success")
    private String logSchSuccess;

    @Column(name = "log_sch_failed")
    private String logSchFailed;
    
    @Column(name = "log_sch_type")
    private String logSchType;

	public LocalDate getLogSchSd() {
		return logSchSd;
	}

	public void setLogSchSd(LocalDate logSchSd) {
		this.logSchSd = logSchSd;
	}

	public LocalDate getLogSchFd() {
		return logSchFd;
	}

	public void setLogSchFd(LocalDate logSchFd) {
		this.logSchFd = logSchFd;
	}

	public String getLogSchTotal() {
		return logSchTotal;
	}

	public void setLogSchTotal(String logSchTotal) {
		this.logSchTotal = logSchTotal;
	}

	public String getLogSchSuccess() {
		return logSchSuccess;
	}

	public void setLogSchSuccess(String logSchSuccess) {
		this.logSchSuccess = logSchSuccess;
	}

	public String getLogSchFailed() {
		return logSchFailed;
	}

	public void setLogSchFailed(String logSchFailed) {
		this.logSchFailed = logSchFailed;
	}

	public String getLogSchType() {
		return logSchType;
	}

	public void setLogSchType(String logSchType) {
		this.logSchType = logSchType;
	}
    
   
    
}
