package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<List<BeerDto>> getAllBeers() {
        List<BeerDto> beerDtoList = beerService.getAllBeers();
        return new ResponseEntity(beerDtoList, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        BeerDto beerDto = beerService.getBeerById(beerId);
        return new ResponseEntity(beerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto) {
        BeerDto savedBeer = beerService.saveBeer(beerDto);
        return new ResponseEntity(savedBeer, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId,
                                                  @RequestBody BeerDto beerDto) {
        BeerDto updatedBeer = beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(updatedBeer, HttpStatus.NO_CONTENT);
    }
}
