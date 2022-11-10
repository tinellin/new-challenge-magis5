package Erp.Models;

import java.util.Iterator;
import java.util.Scanner;

public class Produto {
    private String sku;
    private String nome;
    private double preco;
    private int tamanho;
    private int qtde;
    private Categoria categoria;
    private int status;

    public Produto() {
    }

    public Produto(String sku, String nome, double preco, int tamanho, int qtde, Categoria categoria, int status) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
        this.tamanho = tamanho;
        this.qtde = qtde;
        this.categoria = categoria;
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void cadastrarProduto(Scanner sc) {
        boolean achou = false;


        System.out.println("Digite o nome do produto: ");
        this.nome = sc.next();

        System.out.println("Digite o preço: ");
        this.preco = sc.nextFloat();

        System.out.println("Digite a qtde: ");
        this.qtde = sc.nextInt();

        System.out.println("Digite o tamanho: ");
        this.tamanho = sc.nextInt();

        int tamCat = Estoque.getCategorias().size();
        System.out.println("tamanho: "+tamCat);
        if (tamCat == 0) {
            // cadastrar categoria
            System.out.println("*------Cadastrando Categoria------*");
            Categoria cat = new Categoria();
            cat.cadastrarCategoria(sc);
            Estoque.cadastrarCategoria(cat);
        } else {
            while (!achou) {
                Estoque.listarCategorias();

                System.out.println("Digite o código da categoria: ");
                int cat = sc.nextInt();

                for(Categoria categoria : Estoque.getCategorias()){
                    if(categoria.getCodigo() == cat){
                        achou = true;
                        this.categoria = categoria;
                        break;
                    }
                }

                if (!achou)
                    System.out.println("ERRO: categoria não existe!\n");

            }

            this.status = 1;

            System.out.println("Digite o codigo Sku do produto: ");
            this.sku = sc.next();
            achou = false;

            for(Produto produto : Estoque.getProdutos()){
                if(produto.getSku().equals(this.sku)){
                    achou = true;
                    break;
                }
            }

            if (!achou) Estoque.getProdutos().add(this);
        }


    }

    public void testeProdutos(){
        Categoria cg = new Categoria(01, "nike");

        Produto prod1 = new Produto("DO9392-200", "Air Max 1 x Cactus Jack", 1099,41, 41, cg, 1);
        Produto prod2 = new Produto("DO4532-103", "Air Max 2 x Monkey Eminem", 500,43, 9, cg, 1);
        Produto prod3 = new Produto("DO5277-004", "All Star Clássico", 150,39, 9, cg, 1);
        Produto prod4 = new Produto("DO8364-311", "Adidas Predator Ai Messi", 200,38, 5, cg, 1);
        Produto prod5 = new Produto("DO9925-768", "Nike Mercurial", 340, 8, 10, cg, 1);
        Estoque.getProdutos().add(prod1);
        Estoque.getProdutos().add(prod2);
        Estoque.getProdutos().add(prod3);
        Estoque.getProdutos().add(prod4);
        Estoque.getProdutos().add(prod5);
    }

    public void removerProduto(Scanner sc){
        System.out.println("Digite o codigo Sku do produto: ");
        String skuRem = sc.next();
        boolean achou = false;

        for(Produto produto : Estoque.getProdutos())
        {
            if(produto.getSku().equals(skuRem))
            {
                produto.status = 0;
                achou = true;
                break;
            }
        }
        if(!achou)
            System.out.println("ERRO: Produto não cadastrado!");
    }

    public Produto validaProduto(String sku){
        for(Produto p : Estoque.getProdutos()){
            if (p.getSku().equals(sku)) {
                return p;
            }
        }

        return null;
    }

    public boolean verificaQtdeDisponivel(Produto produto, int qtde){
        if(qtde <= produto.getQtde())
            return true;
        else
            System.out.printf("Este produto possui apenas %d unidades disponiveis.\n", produto.getQtde());
        return false;
    }

    @Override
    public String toString() {
        return "============"+ sku +"============\n" +
                "NOME:"+ nome +"\n" +
                "PRECO:"+ preco +"\n" +
                "QTDE:"+ qtde +"\n"  +
                "TAMANHO:"+ tamanho +"\n"+
                "STATUS:"+ status +"\n"+
                "====================================\n";
    }
}
