package Erp.Models;

public class Cartao {
    public enum TipoCartao { CREDITO, DEBITO }

    private String numero;
    private String nomeImpresso;
    private String validade;
    private String cvv;
    private TipoCartao tipoCartao;

    public Cartao() {
    }

    public Cartao(String numero, String nomeImpresso, String validade, String cvv) {
        this.numero = numero;
        this.nomeImpresso = nomeImpresso;
        this.validade = validade;
        this.cvv = cvv;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
