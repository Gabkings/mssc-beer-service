package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerServiceV2;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/beer")
@RequiredArgsConstructor
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>( beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto dto){
        return new ResponseEntity<>(beerService.addNewBeer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeerDetails(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto dto){
        return new ResponseEntity<>(beerService.updateBeer(beerId, dto), HttpStatus.NO_CONTENT);
    }


}
