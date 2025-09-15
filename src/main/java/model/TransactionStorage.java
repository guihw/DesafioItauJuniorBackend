package model;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TransactionStorage {
    private ArrayList<TransactionModel> transacoes;

    public ArrayList<TransactionModel> save(TransactionModel transacao){
        transacoes.add(transacao);
        return transacoes;
    }

    public String delete(){
        if(transacoes.isEmpty()) return "API já está vazia";
        transacoes.clear();
        return "Todas as transações foram removidas";
    }

    public ArrayList<TransactionModel> findAll(){
        return transacoes;
    }

    public DoubleSummaryStatistics stats(List<TransactionModel> transacoesFiltradas){
         return transacoesFiltradas
                .stream()
                .mapToDouble(TransactionModel -> TransactionModel.getValor().doubleValue())
                .summaryStatistics();
    }
}
