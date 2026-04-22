package guru.springframework.juniemvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BeerOrderLine extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "beer_order_id")
    private BeerOrder beerOrder;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private Integer orderQuantity;
    private Integer quantityAllocated;
    private String status;
}
