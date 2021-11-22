package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.Role;
import uz.setapp.entity.enums.RoleName;


import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByName(RoleName roleName);


}
