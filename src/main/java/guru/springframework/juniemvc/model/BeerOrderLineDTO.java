package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerOrderLineDTO {
    private Integer id;
    private Integer version;

    @NotNull
    private Integer beerId;

    @NotNull
    @Positive
    private Integer orderQuantity;

    private Integer quantityAllocated;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
