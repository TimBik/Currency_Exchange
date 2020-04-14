package ru.itis.jlab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.jlab.model.Role;
import ru.itis.jlab.model.State;
import ru.itis.jlab.model.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcTemplateImpl implements UserRepository {
    private String SQL_SELECT_BY_CODE = "select * from users where code=?";

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .confirmCode(row.getString("code"))
                    .login(row.getString("login"))
                    .mail(row.getString("mail"))
                    .hashPassword(row.getString("password"))
                    .state(State.valueOf(row.getString("state")))
                    .role(Role.valueOf(row.getString("role")))
                    .build();

    @Override
    public Optional<User> findByConfirmCode(String code) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CODE, new Object[]{code}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private String SQL_SELECT_BY_LOGIN = "select * from users where login=?";

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_LOGIN, new Object[]{login}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private String SQL_SELECT_BY_EMAIL = "select * from users where mail=?";

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long userId) {
    }

    @Override
    public Optional<User> find(Long aLong) {
        return Optional.empty();
    }

    private String SQL_SELECT_ALL = "select * from users";

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    //language=sql
    private static final String SQL_INSERT =
            "INSERT INTO users (login, password, mail, state, code,role) values (?,?,?,?,?,?)";

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getHashPassword());
            statement.setString(3, entity.getMail());
            statement.setString(4, entity.getState().toString());
            statement.setString(5, entity.getConfirmCode());
            statement.setString(6, entity.getRole().toString());
            return statement;
        }, keyHolder);
//        entity.setId((Long) keyHolder.getKey());

    }

    @Override
    public void delete(Long aLong) {

    }

    private final String SQL_UPDATE_STATE = "UPDATE users SET state = ? WHERE code=?";

    @Override
    public void update(User entity) {
        jdbcTemplate.update(SQL_UPDATE_STATE
                , new Object[]{entity.getState().toString(), entity.getConfirmCode()});
    }
}
