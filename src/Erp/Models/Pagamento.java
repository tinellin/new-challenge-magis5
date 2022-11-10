package Erp.Models;

import java.util.Scanner;

public class Pagamento {
    private int metodoPagamento;
    private int parcelas;
    private Cartao cartao;

    public Pagamento() {}

    public Pagamento(int metodoPagamento, int parcelas, Cartao cartao) {
        this.metodoPagamento = metodoPagamento;
        this.parcelas = parcelas;
        this.cartao = cartao;
    }

    public int getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(int metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Pagamento efetuarPagamento(int mt, Scanner sc) {
        //Crédito
        if(mt == 0) {
            this.metodoPagamento = mt;

            Cartao c = new Cartao();
            System.out.println("Digite o numero do cartao:");
            c.setNumero(sc.next());

            System.out.println("Digite o nome impresso no cartao:");
            c.setNomeImpresso(sc.next());

            System.out.println("Digite a data de validade:");
            c.setValidade(sc.next());

            System.out.println("Digite o codigo de seguranca (CVV):");
            c.setCvv(sc.next());

            System.out.println("Digite a quantidade de parcelas:");

            this.setParcelas(sc.nextInt());
            this.setCartao(c);
        } else {
            this.metodoPagamento = mt;
            this.setParcelas(1);
        }

        return this;
    }
}
