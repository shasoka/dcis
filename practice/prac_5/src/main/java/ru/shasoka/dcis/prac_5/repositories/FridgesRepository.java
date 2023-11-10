package ru.shasoka.dcis.prac_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shasoka.dcis.prac_5.models.Fridge;

import java.util.List;

/**
 * Репозиторий для работы с холодильниками.
 */
@Repository
public interface FridgesRepository extends JpaRepository<Fridge, Integer> {

    /**
     * Поиск холодильников по цене, равной или большей заданной.
     *
     * @param cost  Цена холодильника, относительно которой производится поиск.
     * @return  Список холодильников, у которых цена равна или больше заданной.
     */
    List<Fridge> findByCostGreaterThanEqual(float cost);
}
