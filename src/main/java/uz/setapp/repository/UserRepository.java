package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.User;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndActive(String username, boolean active);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneOrUsername(String phone, String username);
    Optional<User> findByIdAndActive(Long id, Boolean active);

//    @Query(value = "WITH r AS (SELECT au.id, au.username, au.phone FROM app_user au order by au.id)\n" +
//            "SELECT r.*,\n" +
//            "       CAST( (SELECT array_to_json(array_agg(s.id)) as sklads_id\n" +
//            "              FROM sklad s left join sklad_users su on s.id = su.sklad_id\n" +
//            "              WHERE su.users_id = r.id) AS varchar)\n" +
//            "FROM r;", nativeQuery = true)
//    List<User> findBySkladUsers();
}
