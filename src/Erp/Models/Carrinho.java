package Erp.Models;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<ItemCarrinho> items = new ArrayList<>();
    private Cliente cliente;

    private Marketplace marketplace;

    public Carrinho() {
    }

    public Carrinho(ArrayList<ItemCarrinho> items, Cliente cliente, Marketplace marketplace) {
        this.items = items;
        this.cliente = cliente;
        this.marketplace = marketplace;
    }

    public ArrayList<ItemCarrinho> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemCarrinho> items) {
        this.items = items;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Carrinho cadastraCarrinho(Cliente cliente, Marketplace market) {
        this.setCliente(cliente);
        this.setMarketplace(market);

        return this;
    }

    public static Carrinho verificaCarrinho(Cliente cliente, Marketplace market){
        Carrinho car = new Carrinho();
        boolean achou = false;

        for(Carrinho c : Estoque.getCarrinhos()) {
            if(c.getCliente().getCpf().equals(cliente.getCpf())) {
                car = c;
                achou = true;
                break;
            }
        }

        if (achou) return car;

        return null;
    }

    public static void adicionarAoCarrinho(ItemCarrinho ic, Cliente cliente, Marketplace market){
        Carrinho car = new Carrinho();
        boolean achouCarrinho = false, achouItem = false, achouMarketplace = false;

        for(Carrinho c : Estoque.getCarrinhos()){
            if(c.getCliente().getCpf().equals(cliente.getCpf())) {
                achouCarrinho = true;
                car = c;
                break;
            }
        }

//        for(Marketplace marketplace : Estoque.getMarketplaces())
//        {
//            if(marketplace.getId() == car.getMarketplace().getId())
//            {
//                achouMarketplace = true;
//                break;
//            }
//        }

        if(!achouCarrinho){
            // criar carrinho para o cliente
            car = car.cadastraCarrinho(cliente, market);
        }

        // verificar se o produto ja se encontra no carrinho
        for(ItemCarrinho icr : car.getItems()){
            if(icr.getProduto().getSku().equals(ic.getProduto().getSku())) {
                achouItem = true;
                break;
            }
        }

        if(achouItem) System.out.println("Produto ja adicionado no carrinho.");
        else {
            car.getItems().add(ic);
            System.out.println("Item adicionado com sucesso ao carrinho.");
        }

        Estoque.getCarrinhos().add(car);
    }

    public static void listarItensCarrinho(Carrinho car) {
        if(car == null){
            System.out.println("O cliente nao possui nenhum item no carrinho");
        } else {
            for(ItemCarrinho ic : car.getItems()){
                System.out.println("*----------------------------*");
                System.out.printf("NOME: %s\t\n", ic.getProduto().getNome());
                System.out.printf("PRECO: %.2f\t\n", ic.getProduto().getPreco());
                System.out.printf("QTDE: %d\t\n", ic.getQtdeProduto());
                System.out.println("*----------------------------*");
                System.out.printf("Valor Parcial: %.2f\t\n", ic.getQtdeProduto() * ic.getProduto().getPreco());
                System.out.println("*----------------------------*");
            }
        }

    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "cliente=" + cliente +
                '}';
    }
}
