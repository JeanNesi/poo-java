package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Balanco extends EntityId {
    private LocalDate dataBalanco;
    private String responsavel;

    private List<OperacaoFinanceira> operacoes = new ArrayList<>();

    public LocalDate getDataBalanco() {
        return dataBalanco;
    }

    public void setDataBalanco(LocalDate dataBalanco) {
        this.dataBalanco = dataBalanco;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public List<OperacaoFinanceira> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<OperacaoFinanceira> operacoes) {
        this.operacoes = operacoes;
    }

    public void addOperacoes(OperacaoFinanceira operacao) {
        this.operacoes.add(operacao);
    }

    public void delOperacoes(OperacaoFinanceira operacao) {
        this.operacoes.remove(operacao);
    }

    public Double getValorTotal(TipoOperacao tipo) {
        return this.getOperacoes().stream().filter(op -> op.getTipoOperacao().equals(tipo)).mapToDouble(OperacaoFinanceira::getValorTotalOperacao).sum();
    }

    public String getTipoOperacao(OperacaoFinanceira operacao){
        if(operacao instanceof Compra){
            return "Compra";
        }
        if(operacao instanceof Venda){
            return "Venda";
        }
        return "Locação";
    }

    public void imprimirBalanco() {
        System.out.println("-----------------------------");
        System.out.println("Balanço numero: " + this.getId());
        System.out.println("Data: " + this.getDataBalanco());
        System.out.println("Responsável: " + this.getResponsavel());
        System.out.println("-----------------------------");
        System.out.println("Itens: ");
        for (OperacaoFinanceira op : this.getOperacoes()) {
            System.out.println("Data operacão: " + op.getDataOperacao() +
                    " Tipo operação: " + op.getTipoOperacao() +
                    " Valor operação: " + op.getValorTotalOperacao() +
                    " - (" + getTipoOperacao(op) +")"
            );
        }
        System.out.println("-----------------------------");
        System.out.println("Total de débitos: " + this.getValorTotal(TipoOperacao.DEBITO));
        System.out.println("Total de créditos: " + this.getValorTotal(TipoOperacao.CREDITO));
        System.out.println("Valor Total: " + (this.getValorTotal(TipoOperacao.CREDITO) - this.getValorTotal(TipoOperacao.DEBITO)));
    }

    @Override
    public String toString() {
        return "Balanco{" +
                "dataBalanco=" + dataBalanco +
                ", responsavel='" + responsavel + '\'' +
                ", operacoes=" + operacoes +
                '}';
    }
}
