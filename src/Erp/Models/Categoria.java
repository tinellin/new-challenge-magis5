package Erp.Models;

import java.util.Iterator;
import java.util.Scanner;

public class Categoria {
    int codigo;
    String tipo;

    public Categoria() {}

    public Categoria(int codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void cadastrarCategoria(Scanner sc){
        int tam = Estoque.getCategorias().size();
        this.codigo = tam + 1;

        boolean achou = false;

        Categoria c = new Categoria();

        c.setCodigo(1);

        do{
            System.out.println("Digite o nome da categoria: ");
            this.tipo = sc.next();

            for(Categoria categoria : Estoque.getCategorias()){
                if(categoria.getTipo().equals(this.tipo)){
                    achou = true;
                    break;
                }
            }

            if(achou)
                System.out.println("ERRO: o nome digitado já foi cadastrado.");

        } while(achou);


        if (!achou) Estoque.getCategorias().add(this);
    }

    @Override
    public String toString() {
        return "============"+ tipo +"============\n" +
                "COD:"+ codigo+"\n"+
                "====================================\n";
    }
    public void testeCategoria(){
        Categoria c = new Categoria(9, "tenis");
        Categoria c1 = new Categoria(10, "acessorios");
        Estoque.getCategorias().add(c);
        Estoque.getCategorias().add(c1);
    }
}
