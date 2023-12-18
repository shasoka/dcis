package ru.shasoka.dcis.prac_8.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.shasoka.dcis.prac_8.exceptions.find.FridgeNotFoundException;
import ru.shasoka.dcis.prac_8.messages.MessageProducer;
import ru.shasoka.dcis.prac_8.models.Fridge;
import ru.shasoka.dcis.prac_8.services.FridgesService;

import java.util.stream.Collectors;

/**
 * Контроллер для управления холодильниками.
 */
@Controller
@RequestMapping("/fridges")
public class FridgeController {

    private final FridgesService fridgesService;

    private final MessageProducer msgProducer;

    @Autowired
    public FridgeController(FridgesService fridgesService, MessageProducer msgProducer) {
        this.fridgesService = fridgesService;
        this.msgProducer = msgProducer;
    }

    /**
     * Обрабатывает запрос на получение списка холодильников.
     *
     * @param cost  Цена для фильтрации.
     * @param model Объект модели.
     * @return Представление списка холодильников.
     */
    @GetMapping()
    public String index(@RequestParam(name = "cost", required = false) Float cost, Model model) {
        if (cost != null) {
            model.addAttribute("fridges", fridgesService.filterByPrice(cost));
        } else {
            model.addAttribute("fridges", fridgesService.findAll());
        }
        return "fridges/main";
    }

    /**
     * Обрабатывает запрос на редактирование информации о холодильнике.
     *
     * @param id    Идентификатор холодильника.
     * @param model Объект модели.
     * @return Представление для редактирования.
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("fridge", fridgesService.findOne(id));
        return "fridges/edit";
    }

    /**
     * Обрабатывает запрос на добавление нового холодильника.
     *
     * @param fridge Объект холодильника для добавления.
     * @return Представление для добавления холодильника.
     */
    @GetMapping("/add")
    public String addFridge(@ModelAttribute("fridge") Fridge fridge) {
        return "fridges/add";
    }

    /**
     * Обрабатывает запрос на создание нового холодильника.
     *
     * @param fridge        Объект холодильника для создания.
     * @param bindingResult Результаты валидации.
     * @return Представление для добавления холодильника или перенаправление на список холодильников.
     */
    @PostMapping()
    public String create(
            @ModelAttribute("fridge") @Valid Fridge fridge,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            msgProducer.sendMessage("\nErrors occured while adding new fridge: " + errors);
            return "fridges/add";
        }

        msgProducer.sendMessage("\nAdded new fridge: " + fridge);
        fridgesService.save(fridge);
        return "redirect:/fridges";
    }

    /**
     * Обрабатывает запрос на обновление информации о холодильнике.
     *
     * @param fridge        Объект холодильника для обновления.
     * @param bindingResult Результаты валидации.
     * @param id            Идентификатор холодильника.
     * @return Представление для редактирования или перенаправление на список холодильников.
     */
    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("fridge") @Valid Fridge fridge,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            msgProducer.sendMessage("\nErrors occured while editing fridge=" + id + ": " + errors);
            return "fridges/edit";
        }
        try {
            fridgesService.update(id, fridge);
            msgProducer.sendMessage("\nFridge=" + id + " changed to: " + fridge);
        } catch (FridgeNotFoundException e) {
            msgProducer.sendMessage("\nErrors occured while editing fridge=" + id + ": " + e.getMessage());
        }
        return "redirect:/fridges";
    }

    /**
     * Обрабатывает запрос на удаление холодильника.
     *
     * @param id Идентификатор холодильника.
     * @return Перенаправление на список холодильников.
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            fridgesService.delete(id);
            msgProducer.sendMessage("\nDeleted fridge=" + id);
        } catch (FridgeNotFoundException e) {
            msgProducer.sendMessage("\nErrors occured while deleting fridge=" + id + ": " + e.getMessage());
        }
        return "redirect:/fridges";
    }

    @PatchMapping("/{id}/buy")
    public String buy(@PathVariable("id") int id) {
        try {
            msgProducer.sendMessage("\nSuccessfuly bought fridge=" + id);
        } catch (FridgeNotFoundException e) {
            msgProducer.sendMessage("\nErrors occured while buying fridge=" + id + ": " + e.getMessage());
        }
        return "redirect:/fridges";
    }

}
