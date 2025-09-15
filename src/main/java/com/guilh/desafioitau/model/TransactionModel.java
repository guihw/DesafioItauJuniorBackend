package com.guilh.desafioitau.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TransactionModel {
    @NonNull
    private BigDecimal valor;

    @NonNull
    private OffsetDateTime dataHora;

}
