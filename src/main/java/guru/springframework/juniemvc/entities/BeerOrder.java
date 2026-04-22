package guru.springframework.juniemvc.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BeerOrder extends BaseEntity {

    private String customerRef;

    @Column(precision = 19, scale = 2)
    private BigDecimal paymentAmount;

    private String status;

    @Builder.Default
    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BeerOrderLine> beerOrderLines = new HashSet<>();

    public void addBeerOrderLine(BeerOrderLine line) {
        if (beerOrderLines == null) {
            beerOrderLines = new HashSet<>();
        }
        beerOrderLines.add(line);
        line.setBeerOrder(this);
    }

    public void removeBeerOrderLine(BeerOrderLine line) {
        beerOrderLines.remove(line);
        line.setBeerOrder(null);
    }
}
