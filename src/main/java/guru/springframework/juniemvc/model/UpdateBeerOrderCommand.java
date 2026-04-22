package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record UpdateBeerOrderCommand(
    @NotBlank String customerRef,
    BigDecimal paymentAmount,
    String status
) {}
