package com.totalcross.sample.pushnotification_firebase.util;

public class ResponseDataURL {
    Args args;
    Boolean error;
    String acessToken;

    public Args getArgs() {
        return args;
    }
    public void setArgs(Args args) {
        this.args = args;
    }
    public Boolean getError() {
        return error;
    }
    public void setError(Boolean error) {
        this.error = error;
    }
    public String getAcessToken() {
        return acessToken;
    }
    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }
}