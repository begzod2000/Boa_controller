package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.Ball;

@Repository
public interface BallRepository extends JpaRepository<Ball, Long> {
}
