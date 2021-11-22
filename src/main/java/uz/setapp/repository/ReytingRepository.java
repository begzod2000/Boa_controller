package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.Reyting;

import java.util.Optional;

@Repository
public interface ReytingRepository extends JpaRepository<Reyting, Long> {
    Optional<Reyting> findByXodimlarId(Long xodimId);
}
