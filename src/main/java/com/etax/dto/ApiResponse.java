package com.etax.dto;

import java.util.List;

public class ApiResponse {
    private String message;
    private List<NasabahDTO> data;

    public ApiResponse() {
    }

    public ApiResponse(String message, List<NasabahDTO> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NasabahDTO> getData() {
        return data;
    }

    public void setData(List<NasabahDTO> data) {
        this.data = data;
    }
}

