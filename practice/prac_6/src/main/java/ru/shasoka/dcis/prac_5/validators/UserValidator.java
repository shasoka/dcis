package ru.shasoka.dcis.prac_5.validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.shasoka.dcis.prac_5.models.User;
import ru.shasoka.dcis.prac_5.services.UsersService;

/**
 * Валидатор для проверки уникальности имени пользователя.
 */
@Component
public class UserValidator implements Validator {

    private final UsersService userService;

    /**
     * Конструктор для UserValidator.
     *
     * @param userService Сервис пользователей магазина.
     */
    @Autowired
    public UserValidator(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.hasUsername(user.getUsername())) {
            errors.rejectValue("username", "", "This username already exists.");
        }

    }
}
