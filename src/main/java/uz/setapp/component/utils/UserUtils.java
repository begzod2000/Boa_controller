package uz.setapp.component.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserUtils {
    private final JdbcTemplate jdbcTemplate;

    public UserUtils(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean checkSklad(Long sklad_id, Long user_id) {
        int count = jdbcTemplate.queryForObject("select count (*) from sklad_users s where s.sklad_id=? and s.users_id=?", new Object[]{sklad_id, user_id}, Integer.class);
        return count > 0;
    }
}
