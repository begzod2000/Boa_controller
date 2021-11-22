package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.Xodimlar;
@Repository
public interface XodimlarRepository extends JpaRepository<Xodimlar, Long> {
}
