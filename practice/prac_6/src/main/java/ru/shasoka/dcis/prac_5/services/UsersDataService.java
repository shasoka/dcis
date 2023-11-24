package ru.shasoka.dcis.prac_5.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shasoka.dcis.prac_5.models.User;
import ru.shasoka.dcis.prac_5.repositories.UsersRepository;
import ru.shasoka.dcis.prac_5.security.UserData;

/**
 * Сервис для загрузки пользовательских данных из репозитория пользователей магазина.
 */
@Service
public class UsersDataService implements UserDetailsService {

    private final UsersRepository usersRepository;

    /**
     * Конструктор для ShopUserDetailsService.
     *
     * @param usersRepository Репозиторий пользователей магазина.
     */
    @Autowired
    public UsersDataService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Получает пользователя по его имени
     *
     * @param username имя пользователя
     * @return ShopUserDetails для пользователя
     * @throws UsernameNotFoundException если пользователь не был найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserData(user.get());
    }

}
