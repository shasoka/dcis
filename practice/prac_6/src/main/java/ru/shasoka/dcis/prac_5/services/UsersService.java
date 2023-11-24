package ru.shasoka.dcis.prac_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shasoka.dcis.prac_5.repositories.UsersRepository;
import ru.shasoka.dcis.prac_5.models.User;

/**
 * Сервис для работы с пользователями магазина.
 */
@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для ShopUserService.
     *
     * @param usersRepository Репозиторий пользователей магазина.
     * @param passwordEncoder    Кодировщик паролей.
     */
    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Проверяет наличие пользователя с указанным именем.
     *
     * @param username Имя пользователя для проверки.
     * @return true, если пользователь существует, в противном случае false.
     */
    public boolean hasUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param user Модель пользователя магазина.
     */
    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getUsername().equals("admin")) {
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }
        usersRepository.save(user);
    }

}
