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
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDto> getAllBeers() {
        List<Beer> beers = (List<Beer>) beerRepository.findAll();

        List<BeerDto> beerDtoList = new ArrayList();
        beers.forEach(beer -> beerDtoList.add(beerMapper.beerToBeerDto(beer)));

        return beerDtoList;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);

        log.info("SERVER: getBeerById called!");
        return beerDto;
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        BeerDto savedBeerDto = beerMapper.beerToBeerDto(savedBeer);

        log.info("SERVER: saveNewBeer called!");
        return savedBeerDto;
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer existingBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        existingBeer.setBeerName(beerDto.getBeerName());
        existingBeer.setBeerStyle(beerDto.getBeerStyle().name());
        existingBeer.setPrice(beerDto.getPrice());
        existingBeer.setUpc(beerDto.getUpc());

        Beer updatedBeer = beerRepository.save(existingBeer);
        BeerDto updatedBeerDto = beerMapper.beerToBeerDto(updatedBeer);

        log.info("SERVER: updateBeerById called!");
        return updatedBeerDto;
    }
}
