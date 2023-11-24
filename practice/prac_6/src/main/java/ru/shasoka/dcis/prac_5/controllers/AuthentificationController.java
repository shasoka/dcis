package ru.shasoka.dcis.prac_5.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shasoka.dcis.prac_5.models.User;
import ru.shasoka.dcis.prac_5.services.UsersService;
import ru.shasoka.dcis.prac_5.validators.UserValidator;

/**
 * Контроллер аутентификации и регистрации пользователей.
 */
@RequestMapping("/auth")
@Controller
public class AuthentificationController {

    private final UserValidator userValidator;

    private final UsersService usersService;

    /**
     * Конструктор для AuthController.
     *
     * @param userValidator Валидатор пользователя.
     * @param usersService   Сервис пользователя магазина.
     */
    @Autowired
    public AuthentificationController(UserValidator userValidator, UsersService usersService) {
        this.userValidator = userValidator;
        this.usersService = usersService;
    }

    /**
     * Обработчик GET-запроса для страницы входа.
     *
     * @return Имя представления страницы входа.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    /**
     * Обработчик GET-запроса для страницы регистрации.
     *
     * @param user Модель пользователя магазина.
     * @return Имя представления страницы регистрации.
     */
    @GetMapping("/register")
    public String regPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    /**
     * Обработчик POST-запроса для обработки регистрации пользователя.
     *
     * @param user       Модель пользователя магазина.
     * @param bindingResult  Результаты связывания данных и валидации.
     * @return Имя представления страницы регистрации, если есть ошибки, в противном случае перенаправление на страницу входа.
     */
    @PostMapping("/process_reg")
    public String doReg(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        } else {
            usersService.register(user);
            return "redirect:/auth/login";
        }
    }

}
