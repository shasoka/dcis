package ru.shasoka.dcis.prac_8.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.shasoka.dcis.prac_8.models.Fridge;
import ru.shasoka.dcis.prac_8.services.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shasoka.dcis.prac_8.utilities.Logger.log;

@Component
public class Client implements CommandLineRunner {

  private final ClientService clientService;
  List<Integer> ids = new ArrayList<>();
  List<Float> costs = new ArrayList<>();


  @Autowired
  public Client(ClientService clientService) {
    this.clientService = clientService;
  }

  public void showAll() {
    Mono<List<Fridge>> fridgesMono = clientService.getAll();
    fridgesMono
        .doOnSuccess(fridges -> log("Your table:\n" + fridges.stream()
            .map(fridge -> {
              ids.add(fridge.getId());
              costs.add(fridge.getCost());
              return fridge.toString();
            })
            .collect(Collectors.joining("\n")), "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void showOne(int id) {
    Mono<Fridge> fridgeMono = clientService.getById(id);
    fridgeMono
        .doOnSuccess(fridge -> log("Fridge " + id + ": " + fridge, "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void showFilteredByCost(Float cost) {
    Mono<List<Fridge>> fridgeMono = clientService.getFilteredByCost(cost);
    fridgeMono
        .doOnSuccess(fridges -> log("Fridges with price gte " + cost + " :\n" + fridges.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n")), "Client"))
        .subscribeOn(Schedulers.boundedElastic())
        .block();
  }

  public void addFridge() {
    Fridge fridge = new Fridge("name_before", "prod_before", "country_before", 1000f,
            1);
    log("Adding new fridge: " + fridge, "Client");
    clientService.create(fridge);
  }

  public void updateFridge(int id) {
    Fridge fridge = new Fridge("name_after", "prod_after", "country_after", 5000f,
            100);
    log(String.format("Updating fridge %d: ", id) + fridge, "Client");
    clientService.update(id, fridge);
  }

  public void deleteFridge(int id) {
    log(String.format("Deleting fridge %d", id), "Client");
    clientService.delete(id);
  }

  public void wipeFridges() {
    log("Wiping fridges . . .", "Client");
    clientService.wipeAll();
  }

  public void fillByExample() {
    log("Seeding example data . . .", "Client");
    clientService.fillExample();
  }


  @Override
  public void run(String... args) {
    String allowRun = System.getProperty("allow.run");

    if (!(allowRun == null || "true".equals(allowRun))) {
      return;
    }
    log("CLIENT RUNNING\n", "Client");

    fillByExample();
    log("- - - - - - - - - -");

    showAll();
    log("- - - - - - - - - -");

    if (!ids.isEmpty()) {
      showOne(ids.get(0));
    } else {
      log("DB is empty.", "Client");
    }
    log("- - - - - - - - - -");

    if (!ids.isEmpty()) {
      showFilteredByCost(
          ((float) costs.stream().mapToDouble(Float::doubleValue).average().orElse(1000f)));
    } else {
      log("DB is empty.", "Client");
    }
    log("- - - - - - - - - -");

    addFridge();
    showAll();
    log("- - - - - - - - - -");

    if (!ids.isEmpty()) {
      updateFridge(ids.get(0));
    } else {
      log("DB is empty.", "Client");
    }
    showAll();
    log("- - - - - - - - - -");

    if (!ids.isEmpty()) {
      deleteFridge(ids.get(0));
    } else {
      log("DB is empty.", "Client");
    }
    showAll();
    log("- - - - - - - - - -");

    wipeFridges();
    showAll();
    log("- - - - - - - - - -");

    log("The example is over! Application works normally. Visit http://localhost:8080/");
  }


}
