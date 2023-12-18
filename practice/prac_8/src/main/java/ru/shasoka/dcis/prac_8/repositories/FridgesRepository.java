package ru.shasoka.dcis.prac_8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.shasoka.dcis.prac_8.models.Fridge;

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

    String example = """
            INSERT INTO fridges (model, prod, country_prod, cost, volume)
            VALUES
                ('Samsung RS300', 'Samsung', 'South Korea', 1200.00, 500),
                ('Whirlpool WRB322', 'Whirlpool', 'United States', 1100.00, 450),
                ('Bosch B36CL80SNS', 'Bosch', 'Germany', 1500.00, 550),
                ('LG LSXS26366S', 'LG', 'South Korea', 1300.00, 600),
                ('Haier HRF15N3AGS', 'Haier', 'China', 800.00, 350),
                ('Electrolux EI23BC82SS', 'Electrolux', 'Sweden', 1400.00, 650),
                ('Miele KF1903SF', 'Miele', 'Germany', 2000.00, 700),
                ('KitchenAid KRFF507HPS', 'KitchenAid', 'United States', 1800.00, 600),
                ('GE GSS25GSHSS', 'GE Appliances', 'United States', 1000.00, 400),
                ('Sharp SJG2351FS', 'Sharp', 'Japan', 900.00, 350);
            """;

    /**
     * Вставка примеров холодильников в базу данных.
     */
    @Modifying
    @Query(value = example, nativeQuery = true)
    void insertExample();
}
