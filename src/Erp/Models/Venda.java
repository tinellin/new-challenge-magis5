package Erp.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Venda {
    private UUID id;
    private double valorTotal;
    private ArrayList<ItemCarrinho> produtos = new ArrayList<>();
    private String dataVenda;
    private String horarioVenda;
    private Pagamento pagamento;

    public Venda() {}

    public Venda(UUID id, double valorTotal, ArrayList<ItemCarrinho> produtos, String dataVenda, String horarioVenda, Pagamento pagamento) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.dataVenda = dataVenda;
        this.horarioVenda = horarioVenda;
        this.pagamento = pagamento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<ItemCarrinho> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ItemCarrinho> produtos) {
        this.produtos = produtos;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getHorarioVenda() {
        return horarioVenda;
    }

    public void setHorarioVenda(String horarioVenda) {
        this.horarioVenda = horarioVenda;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void gerarVenda(Carrinho c, int mt, Scanner sc) {
        Pagamento pagamento = new Pagamento();
        this.pagamento = pagamento.efetuarPagamento(mt, sc);

        this.id = UUID.randomUUID();

        System.out.println("Digite a data da venda: ");
        this.dataVenda = sc.next();

        this.produtos = c.getItems();

        for(ItemCarrinho ic : c.getItems()){
            this.valorTotal += ic.getQtdeProduto() * ic.getProduto().getPreco();
        }

        tirarEstoque(c);

        Estoque.cadastrarVenda(c.getCliente().getCpf(), this);


    }

    public void tirarEstoque(Carrinho carrinho){

        for(Produto produto: Estoque.getProdutos()){
            for(ItemCarrinho ic : carrinho.getItems()){

                if(ic.getProduto().getSku().equals(produto.getSku())){
                    System.out.printf("Baixa estoque: [%d] -> [%d - %d = %d]", produto.getQtde(), produto.getQtde(), ic.getQtdeProduto(), produto.getQtde() - ic.getQtdeProduto());
                    produto.setQtde(produto.getQtde() - ic.getQtdeProduto());
                }
            }
        }
    }
}
