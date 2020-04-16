package ru.itis.jlab.repositories.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.jlab.model.Bank;
import ru.itis.jlab.repositories.BankRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class BankRepositoryJdbcTemplateImpl implements BankRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Bank> bankRowMapper = (row, rowNumber) ->
            Bank.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .build();

    private String SQL_FIND = "select * from bank where id=?";

    @Override
    public Optional<Bank> find(Long id) {
        try {
            Bank bank = jdbcTemplate.queryForObject(SQL_FIND, new Object[]{id}, bankRowMapper);
            return Optional.ofNullable(bank);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private String SQL_SELECT_ALL = "select * from bank";

    @Override
    public List<Bank> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, bankRowMapper);
    }

    private String SQL_INSERT = "INSERT INTO bank (name) values (?)";

    @Override
    public void save(Bank entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getName());
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
    public void update(Bank entity) {

    }

    private String SQL_SELECT_BY_BANK_NAME = "select * from bank where name=?";

    @Override
    public Optional<Bank> findByName(String name) {
        try {
            Bank bank = jdbcTemplate.queryForObject(SQL_SELECT_BY_BANK_NAME, new Object[]{name}, bankRowMapper);
            return Optional.ofNullable(bank);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
