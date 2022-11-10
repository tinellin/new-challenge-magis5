package Erp.Models;

import java.util.Scanner;
import java.util.UUID;

public class Usuario {
    private UUID uuid;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String cadastrarUsuario(Scanner sc) {
        boolean achouUsuario = false;
        this.uuid = UUID.randomUUID();

        System.out.println("Digite seu e-mail: ");
        this.email = sc.next();

        for(Usuario u : Estoque.getUsuarios()) {
            if(u.email.equals(this.email)) {
                achouUsuario = true;
                break;
            }
        }

        if(achouUsuario) return null;


        System.out.println("Digite sua senha: ");
        this.senha = sc.next();

        return email;
    }

    public Usuario efetuarLogin(String email, String senha) {
        for(Usuario u : Estoque.getUsuarios()) {
            if(u.getEmail().equals(email) && u.getSenha().equals(senha))
                return u;
        }

        return null;
    }
}
