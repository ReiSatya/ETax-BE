package com.etax.dto;

import java.time.LocalDate;

public class LogPajakNasabahDTO {
    private String logId;
    private String logCif;
    private LocalDate logGenerateDate;
    private String logStatus;
    private String logMessage;
    private String logPathPdf;
    private String logPeriod;
    private String logNosurat;
    private LocalDate logSendDate;
    private String nasabahName;
    private String nasabahEmail;

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

    public String getNasabahName() {
        return nasabahName;
    }

    public void setNasabahName(String nasabahName) {
        this.nasabahName = nasabahName;
    }

    public String getNasabahEmail() {
        return nasabahEmail;
    }

    public void setNasabahEmail(String nasabahEmail) {
        this.nasabahEmail = nasabahEmail;
    }
}
