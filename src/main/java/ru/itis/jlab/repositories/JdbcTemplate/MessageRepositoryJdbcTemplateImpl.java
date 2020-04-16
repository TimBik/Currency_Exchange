package ru.itis.jlab.repositories.JdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.jlab.model.*;
import ru.itis.jlab.repositories.MessageRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryJdbcTemplateImpl implements MessageRepository {
    private String SQL_SELECT_ALL_BY_BANK_NAME = "select * from message where bank_name=?";

    private RowMapper<Message> messageRowMapper = (row, rowNumber) ->
            Message.builder()
                    .id(row.getLong("id"))
                    .bankName(row.getString("bank_name"))
                    .text(row.getString("text"))
                    .build();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> findAllByBankName(String bankName) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_BANK_NAME, new Object[]{bankName}, messageRowMapper);
    }

    @Override
    public Optional<Message> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    private String SQL_INSERT = "INSERT INTO message (bank_name,text) values (?,?)";

    @Override
    public void save(Message entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getBankName());
            statement.setString(2, entity.getText());
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
    public void update(Message entity) {

    }
}
