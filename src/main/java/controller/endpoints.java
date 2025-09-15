package controller;

import dto.DtoTransaction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TransactionService;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.time.OffsetDateTime;

@RestController
public class endpoints {
    private final TransactionService transactionService;

    public endpoints(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> enviarTransacao(@Valid @RequestBody DtoTransaction dtoTransaction){
        transactionService.save(dtoTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<HttpStatus> excluirTudo(){
        transactionService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/estatistica")
    public ResponseEntity<String> obterStats(){
        return new ResponseEntity<>(transactionService.getStatistics(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "tested";
    }

}
