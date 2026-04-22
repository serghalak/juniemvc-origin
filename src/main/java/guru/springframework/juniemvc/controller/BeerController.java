package guru.springframework.juniemvc.controller;

import guru.springframework.juniemvc.model.BeerDTO;
import guru.springframework.juniemvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
class BeerController {

    private final BeerService beerService;

    @GetMapping
    ResponseEntity<List<BeerDTO>> listBeers() {
        return new ResponseEntity<>(beerService.listBeers(), HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId).orElseThrow(RuntimeException::new), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<BeerDTO> handlePost(@RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    ResponseEntity<Void> updateById(@PathVariable("beerId") Integer beerId, @RequestBody BeerDTO beer) {
        if (beerService.updateBeerById(beerId, beer).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    ResponseEntity<Void> deleteById(@PathVariable("beerId") Integer beerId) {
        if (!beerService.deleteById(beerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
