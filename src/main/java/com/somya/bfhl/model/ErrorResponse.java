package com.somya.bfhl.model;

public class ErrorResponse {
    private boolean is_success;
    private String official_email;
    private String error;

    public ErrorResponse(boolean is_success, String official_email, String error) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.error = error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}