package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.exceptions.NotFoundException;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImplV2 implements BeerServiceV2 {
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow());
    }

    @Override
    public BeerDto addNewBeer(BeerDto dto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(dto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto dto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow();
        beer.setBeerName(dto.getBeerName());
        beer.setBeerStyle(dto.getBeerStyle().name());
        beer.setPrice(dto.getPrice());
        beer.setUpc(dto.getUpc());
        beer.setQuantityToBrew(dto.getQuantityOnHand());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
