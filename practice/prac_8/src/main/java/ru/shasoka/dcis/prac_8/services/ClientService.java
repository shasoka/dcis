package ru.shasoka.dcis.prac_8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.shasoka.dcis.prac_8.models.Fridge;

import java.util.List;

@Service
public class ClientService {

  private final WebClient webClient;

  @Autowired
  public ClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<List<Fridge>> getAll() {
    return webClient.get()
        .uri("/fridges/api")
        .retrieve()
        .bodyToFlux(Fridge.class)
        .collectList();
  }

  public Mono<Fridge> getById(int id) {
    return webClient.get()
        .uri("/fridges/api/" + id)
        .retrieve().bodyToMono(Fridge.class);
  }

  public Mono<List<Fridge>> getFilteredByCost(Float cost) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path("/fridges/api")
            .queryParam("cost", cost)
            .build())
        .retrieve()
        .bodyToFlux(Fridge.class)
        .collectList();
  }

  public void create(Fridge fridge) {
    webClient.post()
        .uri("/fridges/api")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(fridge), Fridge.class)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void update(int id, Fridge updatedFridge) {
    webClient.put()
        .uri("/fridges/api/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(updatedFridge))
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void delete(int id) {
    webClient.delete()
        .uri("/fridges/api/{id}", id)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void wipeAll() {
    webClient.delete()
        .uri("/fridges/api")
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  public void fillExample() {
    webClient.patch()
        .uri("/fridges/api")
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

}
