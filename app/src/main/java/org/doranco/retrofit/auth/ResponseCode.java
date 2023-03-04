package org.doranco.retrofit.auth;

public enum ResponseCode {

    OK(200);

    private int reponseCode;

    ResponseCode(int reponseCode) {
        this.reponseCode = reponseCode;
    }

    public int getReponseCode() {
        return reponseCode;
    }
}
