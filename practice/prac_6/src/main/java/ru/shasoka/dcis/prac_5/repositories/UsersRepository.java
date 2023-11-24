package ru.shasoka.dcis.prac_5.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shasoka.dcis.prac_5.models.User;

/**
 * Репозиторий для работы с пользователями магазина.
 */
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    /**
     * Поиск пользователя по имени пользователя.
     *
     * @param username Имя пользователя для поиска.
     * @return Optional с найденным пользователем или пустой, если пользователь не найден.
     */
    Optional<User> findByUsername(String username);
}
