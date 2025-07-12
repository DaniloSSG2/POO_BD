/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unesp.exemplo1.model;

/**
 *
 * @author davi
 */
public class Usuario {
    private float id;
    private String nome;
    private String email;

    public Usuario(float id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    
    
    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
