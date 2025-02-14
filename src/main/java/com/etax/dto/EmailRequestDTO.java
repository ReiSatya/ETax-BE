package com.etax.dto;

import java.util.List;

public class EmailRequestDTO {
    private List<String> cifs;
    private String startDate;
    private String endDate;

    public List<String> getCifs() {
        return cifs;
    }

    public void setCifs(List<String> cifs) {
        this.cifs = cifs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

