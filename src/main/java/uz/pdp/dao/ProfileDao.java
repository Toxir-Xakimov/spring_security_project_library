package uz.pdp.dao;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Profile;

import java.util.Optional;

@Repository
@Component
public class ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    public ProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Profile profile) {
        jdbcTemplate.update("insert into test_users (full_name, username, user_role, password) values (?, ?, ?, ?)", profile.getFullname(), profile.getUsername(), profile.getRole().toString(), profile.getPassword());
    }


    public Optional<Profile> findByUsername(String username) {
        String sql = "select * from test_users where username = ?;";

        try {
            Profile profile = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Profile.class), username);
            return Optional.of(profile);
        } catch ( EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
