package com.guilh.desafioitau.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
@Repository
public class TransactionStorage {
    private ArrayList<TransactionModel> transacoes = new ArrayList<>();

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
