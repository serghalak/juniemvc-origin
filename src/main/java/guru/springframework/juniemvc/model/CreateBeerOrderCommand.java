package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

public record CreateBeerOrderCommand(
    @NotBlank String customerRef,
    BigDecimal paymentAmount,
    Set<CreateBeerOrderLineCommand> beerOrderLines
) {}
