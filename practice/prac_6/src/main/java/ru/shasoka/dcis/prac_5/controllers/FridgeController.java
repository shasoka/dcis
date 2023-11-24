package ru.shasoka.dcis.prac_5.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shasoka.dcis.prac_5.models.Fridge;
import ru.shasoka.dcis.prac_5.services.FridgesService;

/**
 * Контроллер для управления холодильниками.
 */
@Controller
@RequestMapping("/fridges")
public class FridgeController {

    private final FridgesService fridgesService;

    @Autowired
    public FridgeController(FridgesService fridgesService) {
        this.fridgesService = fridgesService;
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
            return "fridges/add";
        }

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
            return "fridges/edit";
        }
        fridgesService.update(id, fridge);
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
        fridgesService.delete(id);
        return "redirect:/fridges";
    }
}
