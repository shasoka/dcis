package ru.shasoka.dcis.prac_5.controllers;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shasoka.dcis.prac_5.exceptions.create.FridgeNotCreatedException;
import ru.shasoka.dcis.prac_5.exceptions.delete.FridgeNotDeletedException;
import ru.shasoka.dcis.prac_5.exceptions.find.FridgeNotFoundException;
import ru.shasoka.dcis.prac_5.exceptions.update.FridgeNotUpdatedException;
import ru.shasoka.dcis.prac_5.models.Fridge;
import ru.shasoka.dcis.prac_5.services.FridgesService;
import ru.shasoka.dcis.prac_5.utilities.ErrorHandler;

@RestController
@RequestMapping("/fridges/api")
public class FridgeRestController extends ErrorHandler<FridgeNotCreatedException, FridgeNotFoundException,
        FridgeNotUpdatedException, FridgeNotDeletedException> {

    private final FridgesService service;

    @Autowired
    public FridgeRestController(FridgesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Fridge>> getAll(@RequestParam(name = "cost", required = false) Float cost) {
        if (cost != null) {
            return new ResponseEntity<>(service.filterByPrice(cost), HttpStatus.OK);
        }
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fridge> show(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Fridge fridge, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new FridgeNotCreatedException(getError(bindingResult));
        }
        service.save(fridge);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid Fridge fridge, BindingResult bindingResult,
                                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            throw new FridgeNotUpdatedException(getError(bindingResult));
        }
        try {
            service.update(id, fridge);
        } catch (FridgeNotFoundException e) {
            throw new FridgeNotUpdatedException(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        try {
            service.delete(id);
        } catch (FridgeNotFoundException e) {
            throw new FridgeNotDeletedException(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll() {
        service.wipe();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> fillExample() {
        service.fillExample();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String getError(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error:
                errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }

}
