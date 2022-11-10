package Erp.Models;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Anuncio {
    private Produto produto;
    private int status;
    private int qtdeVendida;
    private int qtdeDisponivel;

    public Anuncio() {
    }

    public Anuncio(Produto produto, int status, int qtdeVendida, int qtdeDisponivel) {
        this.produto = produto;
        this.status = status;
        this.qtdeVendida = qtdeVendida;
        this.qtdeDisponivel = qtdeDisponivel;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQtdeVendida() {
        return qtdeVendida;
    }

    public void setQtdeVendida(int qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
    }

    public int getQtdeDisponivel() {
        return qtdeDisponivel;
    }

    public void setQtdeDisponivel(int qtdeDisponivel) {
        this.qtdeDisponivel = qtdeDisponivel;
    }

    public void cadastrarAnuncio(Scanner sc) {
        Anuncio anuncio = new Anuncio();

        Estoque.listarProdutos();

        System.out.println("Insira o codigo sku do produto: ");
        String skuAnuncio = sc.next();

        Produto p = new Produto();
        p = p.validaProduto(skuAnuncio);
        anuncio.setProduto(p);

        boolean achou = false;

        for(Produto produto : Estoque.getProdutos())
        {
            if(produto.getSku().equals(anuncio.getProduto().getSku())) {
                achou = true;
                break;
            }
        }

        if (achou) {
            anuncio.setQtdeVendida(0);
            anuncio.setStatus(1);

            Marketplace.cadastrarAnunciosMarketplaces(anuncio);

        }
    }
    public void testeAnuncio(){

        for(Produto p : Estoque.getProdutos()){
            Anuncio a1 = new Anuncio(p,p.getStatus(), 0,p.getQtde());
            Marketplace.cadastrarAnunciosMarketplaces(a1);
            Estoque.getAnuncios().add(a1);
        }

    }
}
