package com.guilh.desafioitau.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DtoTransaction {
    @NonNull
    @DecimalMin(value = "0.0")
    private BigDecimal valor;

    @NonNull
    @PastOrPresent
    private OffsetDateTime dataHora;
}
