package ru.itis.jlab.repositories.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.jlab.model.EdgeCurrency;
import ru.itis.jlab.repositories.EdgeCurrencyRepository;
import ru.itis.jlab.services.modelServices.BankService;
import ru.itis.jlab.services.modelServices.CurrencyService;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class EdgeCurrencyRepositoryJdbcTemplateImpl implements EdgeCurrencyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    BankService bankService;

    @Autowired
    CurrencyService currencyService;
    private RowMapper<EdgeCurrency> currencyRowMapper = (row, rowNumber) ->
            EdgeCurrency.builder()
                    .id(row.getLong("id"))
                    .bank(bankService.find(row.getLong("bank_id")).get())
                    .CurrencyFrom(currencyService.find(row.getLong("currency_from_id")).get())
                    .CurrencyTo(currencyService.find(row.getLong("currency_to_id")).get())
                    .costByOne(row.getDouble("cost_by_one"))
                    .logCostByOne(row.getDouble("log_cost_by_one"))
                    .urlFromData(row.getString("url_from_data"))
                    .parsingXPath(row.getString("parsing_xpath"))
                    .build();


    private String SQL_SELECT_BY_ID = "select * from edge_currency where id=?";

    @Override
    public Optional<EdgeCurrency> find(Long id) {
        try {
            EdgeCurrency edgeCurrency = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, currencyRowMapper);
            return Optional.ofNullable(edgeCurrency);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private String SQL_SELECT_ALL = "select * from edge_currency";

    @Override
    public List<EdgeCurrency> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, currencyRowMapper);
    }

    private String SQL_INSERT = "INSERT INTO edge_currency (bank_id,currency_from_id,currency_to_id,url_from_data,parsing_xpath,cost_by_one,reverse,log_cost_by_one) values (?,?,?,?,?,?,?,?)";

    @Override
    public void save(EdgeCurrency entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setLong(1, entity.getBank().getId());
            statement.setLong(2, entity.getCurrencyFrom().getId());
            statement.setLong(3, entity.getCurrencyTo().getId());
            statement.setString(4, entity.getUrlFromData());
            statement.setString(5, entity.getParsingXPath());
            statement.setDouble(6, entity.getCostByOne());
            statement.setBoolean(7, entity.getReverse());
            statement.setDouble(8, entity.getLogCostByOne());
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
    public void update(EdgeCurrency entity) {

    }

    private String SQL_SELECT_BY_BANK_ID_AND_CURRENCY_NAMES = "select * from edge_currency where bank_id=? and currency_from_id=?  and currency_to_id=?";

    @Override
    public Optional<EdgeCurrency> findByBankIdAndCurrencyNames(long bankId, long idFromCurrency, long idToCurrency) {
        try {
            EdgeCurrency edgeCurrency = jdbcTemplate.queryForObject(SQL_SELECT_BY_BANK_ID_AND_CURRENCY_NAMES, new Object[]{bankId, idFromCurrency, idToCurrency}, currencyRowMapper);
            return Optional.ofNullable(edgeCurrency);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private String SQL_SELECT_BY_BANK_ID = "select * from edge_currency where bankid=?";

    @Override
    public List<EdgeCurrency> findAllByBankId(long bankId) {
        return jdbcTemplate.query(
                SQL_SELECT_BY_BANK_ID,
                currencyRowMapper
        );
    }

    private String SQL_SELECT_BY_CURRENCIES_ID = "select * from edge_currency where currency_from_id=? and currency_to_id=?";

    @Override
    public List<EdgeCurrency> findByCurrenciesId(long idCurrencyFrom, long idCurrencyTo) {
        return jdbcTemplate.query(SQL_SELECT_BY_CURRENCIES_ID, new Object[]{idCurrencyFrom, idCurrencyTo}, currencyRowMapper);
    }
}
