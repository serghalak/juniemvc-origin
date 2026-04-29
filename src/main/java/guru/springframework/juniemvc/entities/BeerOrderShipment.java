package guru.springframework.juniemvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrderShipment extends BaseEntity {

    @NotNull
    @ManyToOne
    private BeerOrder beerOrder;

    @NotNull
    private OffsetDateTime shipmentDate;

    private String carrier;

    private String trackingNumber;
}
