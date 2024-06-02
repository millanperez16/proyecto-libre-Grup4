package com.example.frontend.models;

public class Token {
    private Long idCliente;

    private String token;

    public Token(Long idCliente, String token) {
        this.idCliente = idCliente;
        this.token = token;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
