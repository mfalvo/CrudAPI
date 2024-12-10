package com.example.crudsqlite.model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private long id;
    private String nome;
    private String email;
    private String phone;

    public Pessoa(){}

    public Pessoa(String nome, String email, String phone){
        this.nome = nome;
        this.email = email;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){return this.getNome();}
}
