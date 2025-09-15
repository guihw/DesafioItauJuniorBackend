package service;

import dto.DtoTransaction;
import model.TransactionModel;
import model.TransactionStorage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
         List<TransactionModel> transacoesFiltradas = transactionStorage
                 .findAll()
                 .stream()
                 .filter(transactionModel -> transactionModel.getDataHora().isBefore(OffsetDateTime.now().minusMinutes(1)))
                 .collect(Collectors.toList());

         if(transacoesFiltradas.isEmpty()){
             return "\"count\": 10,\n" +
                     "    \"sum\": 1234.56,\n" +
                     "    \"avg\": 123.456,\n" +
                     "    \"min\": 12.34,\n" +
                     "    \"max\": 123.56";
         }

         return transactionStorage.stats(transacoesFiltradas).toString();
    }
}
