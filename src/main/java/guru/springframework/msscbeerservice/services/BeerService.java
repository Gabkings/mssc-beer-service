package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<BeerDto> getAllBeers();

    BeerDto getBeerById(UUID beerId);

    BeerDto saveBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
