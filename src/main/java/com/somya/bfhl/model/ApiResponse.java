package com.somya.bfhl.model;

public class ApiResponse {
    private boolean is_success;
    private String official_email;
    private Object data;

    public ApiResponse(boolean is_success, String official_email, Object data) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.data = data;
    }

    public boolean getIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getOfficial_email() {
        return official_email;
    }

    public void setOfficial_email(String official_email) {
        this.official_email = official_email;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}