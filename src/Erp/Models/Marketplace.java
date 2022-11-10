package Erp.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Marketplace {
    private int id;
    private String nome;
    private static List<Anuncio> anuncios = new ArrayList<>();

    public Marketplace() {

    }

    public Marketplace(int id, String nome, List<Anuncio> anuncios) {
        this.id = id;
        this.nome = nome;
        this.anuncios = anuncios;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    public void cadastrarMarketplace(Scanner sc) {
        Anuncio anuncio = new Anuncio();
        int igual = 0;
        System.out.println("Digite o nome do marketplace: ");
        this.nome = sc.next();
        this.id = (Estoque.getMarketplaces().size() + 1);
        Estoque.listarAnuncios();
        anuncio.cadastrarAnuncio(sc);
        Estoque.cadastrarMarketplace(this);
    }

    public void testeMarketPlaces(){
        Marketplace m1 = new Marketplace(01, "Mercado Preso", null);
        Estoque.cadastrarMarketplace(m1);

        Marketplace m2 = new Marketplace(02, "Mago Luiz", null);
        Estoque.cadastrarMarketplace(m2);
    }

    public void listarMarketplaces(){
        for(Marketplace mp : Estoque.getMarketplaces()){
            System.out.printf("[%d] %s\n", mp.id, mp.nome);
        }
    }

    public void listarAnuncios(){
        for(Anuncio anuncio : Estoque.getAnuncios()){
            System.out.println(anuncio.getProduto().toString());
        }
    }

    public static void cadastrarAnunciosMarketplaces(Anuncio anuncio){
        for(Marketplace mp : Estoque.getMarketplaces()) {
            mp.anuncios.add(anuncio);
            anuncios.add((Anuncio) mp.getAnuncios());
        }
        System.out.println("Anuncio cadastrado com sucesso");
    }

    public Marketplace verificarMarketplaces(int id){
        for(Marketplace marketplace : Estoque.getMarketplaces()){
            if(marketplace.getId() == id){
                return marketplace;
            }
        }

        return null;
    }
}