package Erp.Models;

public class ItemCarrinho {
    private Produto produto;
    private int qtdeProduto;

    public ItemCarrinho() {
    }

    public ItemCarrinho(Produto produto, int qtdeProduto) {
        this.produto = produto;
        this.qtdeProduto = qtdeProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(int qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    public ItemCarrinho cadastrarItemCarrinho(Produto produto, int qtde){
        this.setProduto(produto);
        this.setQtdeProduto(qtde);

        return this;
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "produto=" + produto +
                ", qtdeProduto=" + qtdeProduto +
                '}';
    }
}
