package Erp.Models;

import java.sql.Array;
import java.util.*;

public class Estoque {
    static private ArrayList<Cliente> clientes = new ArrayList<>();
    static private ArrayList<Categoria> categorias = new ArrayList<>();
    static private ArrayList<Produto> produtos = new ArrayList<>();
    static private ArrayList<Carrinho> carrinhos = new ArrayList<>();
    static private ArrayList<Marketplace> marketplaces = new ArrayList<>();
    static private ArrayList<Anuncio> anuncios = new ArrayList<>();
    static private ArrayList<Usuario> usuarios = new ArrayList<>();
    static private Map<String, List<Venda>> vendas = new HashMap<>();

    public Estoque() {
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(ArrayList<Cliente> clientes) {
        Estoque.clientes = clientes;
    }

    public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public static void setCategorias(ArrayList<Categoria> categorias) {
        Estoque.categorias = categorias;
    }

    public static ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public static void setProdutos(ArrayList<Produto> produtos) {
        Estoque.produtos = produtos;
    }

    public static ArrayList<Carrinho> getCarrinhos() {
        return carrinhos;
    }

    public static void setCarrinhos(ArrayList<Carrinho> carrinhos) {
        Estoque.carrinhos = carrinhos;
    }

    public static ArrayList<Marketplace> getMarketplaces() {
        return marketplaces;
    }

    public static void setMarketplaces(ArrayList<Marketplace> marketplaces) {
        Estoque.marketplaces = marketplaces;
    }

    public static ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setAnuncios(ArrayList<Anuncio> anuncios) {
        Estoque.anuncios = anuncios;
    }

    public static void cadastrarCliente(Cliente cliente)
    {
        clientes.add(cliente);
        listarClientes();
        System.out.println("Cliente cadastrado com sucesso.");
    }

    public static void cadastrarCategoria(Categoria categoria)
    {
        categorias.add(categoria);
        System.out.println("Categoria cadastrada com sucesso.");
    }

    public static void cadastrarProduto(Produto produto)
    {
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso.");
    }

    public static void cadastrarMarketplace(Marketplace mp) {
        marketplaces.add(mp);
        System.out.println("Marketplace cadastrado com sucesso.");
    }

    public static void cadastrarVenda(String cpf, Venda v) {
        if(!vendas.containsKey(cpf)) vendas.put(cpf, new ArrayList<Venda>());
        vendas.get(cpf).add(v);
        System.out.println("Venda cadastrada com sucesso.");
    }

    public static void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario cadastrado com sucesso!");
    }

    public static void listarClientes()
    {
        System.out.println("*-------------[ Listagem Clientes ]---------------*");
        for(Cliente c : clientes)
            System.out.printf("[%s] %s\n", c.getCpf(), c.getNome());
        System.out.println("*------------------------------------*");
    }

    public static void listarVendasPorCliente(String cpf) {
        if(!Estoque.vendas.containsKey(cpf))
            System.out.println("O cliente procurado nao tem nenhuma compra!");

        for(Venda v : Estoque.vendas.get(cpf)) {
            System.out.println("*-------------[" + v.getId() + "]---------------*");
            System.out.printf("|  Valor total: %.2f\t|\n", v.getValorTotal());
            System.out.printf("|  Data da venda: %s\t|\n", v.getDataVenda());

            for(ItemCarrinho ic : v.getProdutos()) {
                System.out.println("Produto: " + ic.getProduto());
                System.out.println("Quantidade: " + ic.getQtdeProduto());
            }

            System.out.println("*------------------------------------*");
        }
    }

    public static void listarProdutos()
    {
        for(Produto produto : produtos){
            if(produto.getQtde() > 0){
                System.out.println("*-------------[" + produto.getSku() + "]---------------*");
                System.out.printf("|  Nome: %s\t|\n", produto.getNome());
                System.out.printf("|  Preco: %.2f\t|\n", produto.getPreco());
                System.out.printf("|  Quantidade: %d\t|\n", produto.getQtde());
                System.out.printf("|  Tamanho: %d\t|\n", produto.getTamanho());
                System.out.printf("|  Status: %d\t|\n", produto.getStatus());
                System.out.printf("|  Categoria:%s\t\n|", produto.getCategoria().getTipo());
                System.out.println("*------------------------------------*\n");
            }

        }
    }

    public static void listarCategorias() {
        if(Estoque.getCategorias().size() == 0)
        {
            System.out.println("ERRO: Nenhuma categoria cadastrada!");
            return;
        }

        for(Categoria cat : categorias)
            System.out.printf("Código: [%d] \t Nome: %s\n", cat.getCodigo(), cat.getTipo());
    }

    public static void listarAnuncios()
    {
        if(Estoque.getAnuncios().size() == 0)
        {
            System.out.println("ERRO: Nenhum anúncio  cadastrado!");
            return;
        }

        System.out.println("*-------------[ Listagem Anuncios ]---------------*");
        for(Anuncio a : anuncios)
            System.out.printf("%s\nStatus: %s\nQtd disponivel: %d\nQTd Vendida: %d\n", a.getProduto().toString(), a.getStatus(), a.getQtdeDisponivel(), a.getQtdeVendida());
    }
}
