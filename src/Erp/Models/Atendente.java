package Erp.Models;

public class Atendente extends Pessoa {
    private int atendente_id;
    private static String password = "admin";
    private static int count = 0;

    public Atendente() {
        setAtendente_id(++count);
    }

    public int getAtendente_id() {
        return atendente_id;
    }

    public void setAtendente_id(int atendente_id) {
        this.atendente_id = atendente_id;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
