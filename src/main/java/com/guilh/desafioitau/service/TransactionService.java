package com.guilh.desafioitau.service;

import com.guilh.desafioitau.dto.DtoTransaction;
import com.guilh.desafioitau.model.TransactionModel;
import com.guilh.desafioitau.model.TransactionStorage;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionStorage transactionStorage;

    public TransactionService(TransactionStorage transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    public ArrayList<TransactionModel> save(DtoTransaction dtoTransaction){
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setValor(dtoTransaction.getValor());
        transactionModel.setDataHora(dtoTransaction.getDataHora());

        return transactionStorage.save(transactionModel);
    }

    public void delete(){
        transactionStorage.delete();
    }

    public String getStatistics(){
        int segundos = 60;
         List<TransactionModel> transacoesFiltradas = transactionStorage
                 .findAll()
                 .stream()
                 .filter(transactionModel -> transactionModel.getDataHora().isAfter(OffsetDateTime.now().minusMinutes(segundos/60)))
                 .collect(Collectors.toList());

         if(transacoesFiltradas.isEmpty()){
             return "{\n" +
                     "    \"count\": 0,\n" +
                     "    \"sum\": 0.00,\n" +
                     "    \"avg\": 0.00,\n" +
                     "    \"min\": 0.00,\n" +
                     "    \"max\": 0.00\n" +
                     "}";
         }
        DoubleSummaryStatistics stats = transactionStorage.stats(transacoesFiltradas);
        return String.format("{\n" +
                "    \"count\": %d,\n" +
                "    \"sum\": %.2f,\n" +
                "    \"avg\": %.2f,\n" +
                "    \"min\": %.2f,\n" +
                "    \"max\": %.2f\n" +
                "}", stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());
    }
}
