package guru.springframework.juniemvc.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Builder
public record BeerOrderShipmentDTO(
        Integer id,
        Integer version,
        OffsetDateTime shipmentDate,
        String carrier,
        String trackingNumber,
        LocalDateTime createdDate,
        LocalDateTime updateDate
) {
}
