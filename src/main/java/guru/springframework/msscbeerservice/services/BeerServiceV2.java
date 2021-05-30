package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface BeerServiceV2 {

    BeerDto getBeerById(UUID beerId);

    BeerDto addNewBeer(BeerDto dto);

    BeerDto updateBeer(UUID beerId, BeerDto dto);
}
