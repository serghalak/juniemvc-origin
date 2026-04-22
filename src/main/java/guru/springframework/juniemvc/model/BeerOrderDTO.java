package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerOrderDTO {
    private Integer id;
    private Integer version;

    @NotBlank
    private String customerRef;

    private BigDecimal paymentAmount;
    private String status;
    private Set<BeerOrderLineDTO> beerOrderLines;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
