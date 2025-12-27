package com.ecommerce.backend.services;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ecommerce.backend.dto.PokemonGiftDto;
import com.ecommerce.backend.dto.response.PokeApiResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokeApiService {

    private final WebClient webClient;
    private final Random random = new Random();

    public PokeApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<PokemonGiftDto> getRandomPokemonGifts(int amount) {
        List<Integer> randomIds = IntStream.range(0, amount)
                .map(i -> random.nextInt(151) + 1)
                .boxed()
                .toList();

        return Flux.fromIterable(randomIds)
                .flatMap(this::fetchPokemonInfo)
                .collectList()
                .block();
    }

    private Mono<PokemonGiftDto> fetchPokemonInfo(Integer id) {
        return webClient.get()
                .uri("/pokemon/{id}", id)
                .retrieve()
                .bodyToMono(PokeApiResponse.class)
                .map(response -> new PokemonGiftDto(
                        response.id(),
                        response.name(),
                        response.sprites() != null ? response.sprites().frontDefault() : null))
                .onErrorResume(e -> Mono.empty());
    }
}