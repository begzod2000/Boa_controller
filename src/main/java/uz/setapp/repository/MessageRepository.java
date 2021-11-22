package uz.setapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.setapp.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Message findByCode(Integer code);
}
