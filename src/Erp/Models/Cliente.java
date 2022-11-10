package Erp.Models;

import java.util.Scanner;
import java.util.UUID;

public class Cliente extends Pessoa {
    private String email;

    public Cliente() {
    }

    public Cliente(String email) {
        this.email = email;
    }

    public Cliente(String nome, String dataNascimento, String cpf, String telefone, String email) {
        super(nome, dataNascimento, cpf, telefone);
        this.email = email;
    }

    public Cliente(String nome, String dataNascimento, String cpf, String telefone) {
        super(nome, dataNascimento, cpf, telefone);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getDataNascimento() {
        return super.getDataNascimento();
    }

    @Override
    public void setDataNascimento(String dataNascimento) {
        super.setDataNascimento(dataNascimento);
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }

    @Override
    public void setCpf(String cpf) {
        super.setCpf(cpf);
    }

    @Override
    public String getTelefone() {
        return super.getTelefone();
    }

    @Override
    public void setTelefone(String telefone) {
        super.setTelefone(telefone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void cadastrarCliente(Scanner sc)
    {
        System.out.println("Digite o nome do cliente: ");
        super.nome = sc.next();

        System.out.println("Digite o cpf do cliente: ");
        super.cpf = sc.next();

        System.out.println("Digite a data de nascimento do cliente: (dd/MM/AAAA");
        super.dataNascimento = sc.next();

        System.out.println("Digite o telefone do cliente: ");
        super.telefone = sc.next();

        System.out.println("Digite o email do cliente: ");
        email = sc.next();

        Usuario u = new Usuario();
        String email = u.cadastrarUsuario(sc);

        if(email == null) {
            System.out.println("Usuario ja esta cadastrado no sistema!");
            return;
        }

        this.email = email;

        Estoque.getClientes().add(this);
        Estoque.getUsuarios().add(u);
    }

    public void testeClientes(){
        Cliente c = new Cliente("AdrianTurbina", "10/02/1980", "1947853622", "12345678915", "adrian.turbina@gmail.com");
        Cliente c1 = new Cliente("Zezinho", "10/10/1970", "1947853621","12345678916", "ze.assembly@gmail.com");
        Estoque.cadastrarCliente(c);
        Estoque.cadastrarCliente(c1);
    }

    public Cliente validaCliente(String cpf){
        for(Cliente c : Estoque.getClientes()){
            if(c.getCpf().equals(cpf)){
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "============"+ super.cpf +"============\n" +
                "NOME:"+ super.nome+"\n" +
                "DATA:"+ super.dataNascimento+"\n" +
                "TEL:"+ super.telefone+"\n"  +
                "EMAIL:"+ this.email+"\n"+
                "====================================\n";
    }
}
