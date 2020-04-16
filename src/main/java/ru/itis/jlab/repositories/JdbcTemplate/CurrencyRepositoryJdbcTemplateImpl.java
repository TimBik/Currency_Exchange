package ru.itis.jlab.repositories.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.model.Currency;
import ru.itis.jlab.repositories.CurrencyRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CurrencyRepositoryJdbcTemplateImpl implements CurrencyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Currency> currencyRowMapper = (row, rowNumber) ->
            Currency.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .approximateCost(row.getDouble("approximate_cost"))
                    .build();

    private String SQL_FIND = "select * from currency where id=?";

    @Override
    public Optional<Currency> find(Long aLong) {
        try {
            Currency currency = jdbcTemplate.queryForObject(SQL_FIND, new Object[]{aLong}, currencyRowMapper);
            return Optional.of(currency);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private String SQL_SELECT_ALL = "select * from currency";

    @Override
    public List<Currency> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, currencyRowMapper);
    }

    private String SQL_INSERT = "INSERT INTO currency (name,approximate_cost) values (?,?)";

    @Override
    public void save(Currency entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getApproximateCost());
            return statement;
        }, keyHolder);
        //вылетает ошибка, keyHolder = null
        //TO DO разобраться почему/исправить
        //entity.setId((Long) keyHolder.getKey());
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Currency entity) {

    }

    private String SQL_SELECT_BY_CURRENCY_NAME = "select * from currency where name=?";

    @Override
    public Optional<Currency> findByName(String nameCurrency) {
        try {
            Currency currency = jdbcTemplate.queryForObject(SQL_SELECT_BY_CURRENCY_NAME, new Object[]{nameCurrency}, currencyRowMapper);
            return Optional.of(currency);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
