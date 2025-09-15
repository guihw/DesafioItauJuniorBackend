package dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DtoTransaction {
    @NonNull
    @Min(0)
    private BigDecimal valor;

    @NonNull
    @Past
    private OffsetDateTime dataHora;
}
