package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateBeerOrderLineCommand(
    @NotNull Integer beerId,
    @NotNull @Positive Integer orderQuantity
) {}
