package guru.springframework.juniemvc.repositories;

import guru.springframework.juniemvc.entities.BeerOrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, Integer> {
    Page<BeerOrderLine> findAllByBeerOrderId(Integer beerOrderId, Pageable pageable);
}
