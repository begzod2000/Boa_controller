package uz.setapp.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.setapp.controller.models.Response;
import uz.setapp.repository.MessageRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/xodim")
public class XodimlarController {
    private final JdbcTemplate jdbcTemplate;
    private final MessageRepository messageRepository;

    public XodimlarController(JdbcTemplate jdbcTemplate, MessageRepository messageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.messageRepository = messageRepository;
    }

    @GetMapping("list")
    public HttpEntity<?> list(){
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from xodimlar");
        response.setData(maps);
        response.setMessage(messageRepository.findByCode(0));
        return ResponseEntity.ok(response);
    }
}
