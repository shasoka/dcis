package ru.shasoka.dcis.prac_5.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shasoka.dcis.prac_5.models.Fridge;
import ru.shasoka.dcis.prac_5.repositories.FridgesRepository;

import java.util.List;

/**
 * Сервис для работы с холодильниками.
 */
@Service
@Transactional
public class FridgesService {

    private final FridgesRepository fridgeRepository;

    @Autowired
    public FridgesService(FridgesRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    /**
     * Получает все холодильники.
     *
     * @return список холодильников.
     */
    public List<Fridge> findAll() {
        return fridgeRepository.findAll();
    }

    /**
     * Находит холодильник по идентификатору.
     *
     * @param id идентификатор холодильника.
     * @return найденный холодильник или null, если не найден.
     */
    public Fridge findOne(int id) {
        return fridgeRepository.findById(id).orElse(null);
    }

    /**
     * Сохраняет новый холодильник.
     *
     * @param fridge объект холодильника.
     */
    @Transactional
    public void save(Fridge fridge) {
        fridgeRepository.save(fridge);
    }

    /**
     * Обновляет информацию о холодильнике.
     *
     * @param id     идентификатор холодильника.
     * @param fridge объект холодильника.
     */
    @Transactional
    public void update(int id, Fridge fridge) {
        fridge.setId(id);
        fridgeRepository.save(fridge);
    }

    /**
     * Удаляет холодильник по идентификатору.
     *
     * @param id идентификатор холодильника.
     */
    @Transactional
    public void delete(int id) {
        fridgeRepository.deleteById(id);
    }

    /**
     * Фильтрует холодильники по цене, равной или большей заданной.
     *
     * @param minCost минимальная цена.
     * @return список отфильтрованных холодильников.
     */
    public List<Fridge> filterByPrice(float minCost) {
        return fridgeRepository.findByCostGreaterThanEqual(minCost);
    }
}
