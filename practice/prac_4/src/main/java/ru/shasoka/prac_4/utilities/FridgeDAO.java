package ru.shasoka.prac_4.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shasoka.prac_4.entity.Fridge;

import javax.sql.DataSource;
import java.util.List;

/** Класс доступа к данным. */
@Component
public class FridgeDAO {

    /** JDBC Template */
    private final JdbcTemplate jdbcTemplate;

    /** Конструктор класса. */
    @Autowired
    public FridgeDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Метод, выполняющий sql-запрос, который выполняет подсчет количества строк в таблице.
     *
     * @return число строк в таблице.
     */
    public int selectCount() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM fridges", Integer.class);
        return count;
    }

    /**
     * Метод, выполняющий sql-запрос, который возвращает выборку всех id в таблице.
     *
     * @return список id в таблице.
     */
    public List<Integer> selectIDs() {
        return jdbcTemplate.queryForList("SELECT id FROM fridges", Integer.class);
    }

    /**
     * Метод, выполняющий sql-запрос, который возвращает все записи из таблицы.
     *
     * @return список записей в таблице, приведенных к Fridge.
     */
    public List<Fridge> selectAll() {
        return jdbcTemplate.query("SELECT * FROM fridges", new BeanPropertyRowMapper<>(Fridge.class));
    }

    /**
     * Метод, выполняющий sql-запрос, возвращающий запись из таблицы по ее id.
     *
     * @return найденная запись или null, если она не была найдена.
     */
    public Fridge selectOne(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM fridges WHERE id = ?",
                    new BeanPropertyRowMapper<>(Fridge.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /** Метод, выполняющий sql-запрос, который выполняет вставку строк в таблицу. */
    public void insert(Fridge furniture) {
        jdbcTemplate.update("INSERT INTO fridges (model, prod, country_prod, cost, volume) VALUES (?,?,?,?,?)",
                furniture.getModel(),
                furniture.getProd(),
                furniture.getCountry_prod(),
                furniture.getCost(),
                furniture.getVolume());
    }

    /** Метод, выполняющий sql-запрос, который выполняет обновление строк в таблице. */
    public void update(Fridge furniture) {
        jdbcTemplate.update("UPDATE fridges SET model = ?, prod = ?, country_prod = ?, cost = ?, volume = ? where id = ?",
                furniture.getModel(),
                furniture.getProd(),
                furniture.getCountry_prod(),
                furniture.getCost(),
                furniture.getVolume(),
                furniture.getId());
    }

    /** Метод, выполняющий sql-запрос, который выполняет удаление строк из таблицы. */
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM fridges WHERE id = ?", id);
    }

    /**
     * Метод, выполняющий sql-запрос, который возвращает строки, удовлетворяющие условию сравнения.
     *
     * @return найденные строки таблицы, приведенные к Fridge.
     */
    public List<Fridge> selectCostBound(float costBound) {
        return jdbcTemplate.query("SELECT * FROM fridges WHERE cost >= ?", new BeanPropertyRowMapper<>(Fridge.class), costBound);
    }

}
