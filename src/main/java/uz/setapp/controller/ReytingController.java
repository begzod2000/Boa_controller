package uz.setapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.setapp.controller.models.Response;
import uz.setapp.entity.Reyting;
import uz.setapp.payload.ReqReyting;
import uz.setapp.repository.MessageRepository;
import uz.setapp.repository.ReytingRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/reyting")
public class ReytingController {
    private final ReytingRepository repository;
    private final MessageRepository messageRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReytingController(ReytingRepository repository, MessageRepository messageRepository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.messageRepository = messageRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public HttpEntity<?> addReyting(@RequestBody ReqReyting reqReyting) {
        Response response = new Response();
        Reyting reyting = new Reyting();
        reyting.setName(reqReyting.getName());
        reyting.setBall1(reqReyting.getBall1());
        reyting.setBall2(reqReyting.getBall2());
        reyting.setBall3(reqReyting.getBall3());
        reyting.setBall4(reqReyting.getBall4());
        reyting.setBall5(reqReyting.getBall5());
        reyting.setBall6(reqReyting.getBall6());
        reyting.setBall7(reqReyting.getBall7());
        reyting.setBall8(reqReyting.getBall8());
        reyting.setBall9(reqReyting.getBall9());
        reyting.setBall10(reqReyting.getBall10());
        Long ortacha = reqReyting.getBall1()+ reqReyting.getBall2()+ reqReyting.getBall3()+ reqReyting.getBall4()+ reqReyting.getBall5()+ reqReyting.getBall6()+
                reqReyting.getBall7()+ reqReyting.getBall8()+ reqReyting.getBall9()+ reqReyting.getBall10();
        reyting.setMiddle(ortacha);
        reyting.setFoiz(reqReyting.getFoiz());
        reyting.setHisob(reqReyting.getHisob());
        repository.save(reyting);
        response.setMessage(messageRepository.findByCode(0));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public HttpEntity<?> list(){
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from reyting");
        response.setMessage(messageRepository.findByCode(0));
        response.setData(maps);
        return  ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update/{reyId}")
    public HttpEntity<?> updateReyting(@PathVariable Long reyId, @RequestBody ReqReyting reqReyting) {
        Response response = new Response();
        Optional<Reyting> byId = repository.findById(reyId);
        if (byId.isPresent()) {
            Reyting reyting = byId.get();
            reyting.setBall1(reqReyting.getBall1());
            reyting.setBall2(reqReyting.getBall2());
            reyting.setBall3(reqReyting.getBall3());
            reyting.setBall4(reqReyting.getBall4());
            reyting.setBall5(reqReyting.getBall5());
            reyting.setBall6(reqReyting.getBall6());
            reyting.setBall7(reqReyting.getBall7());
            reyting.setBall8(reqReyting.getBall8());
            reyting.setBall9(reqReyting.getBall9());
            reyting.setBall10(reqReyting.getBall10());
            Long ortacha = reqReyting.getBall1()+ reqReyting.getBall2()+ reqReyting.getBall3()+ reqReyting.getBall4()+ reqReyting.getBall5()+ reqReyting.getBall6()+
                    reqReyting.getBall7()+ reqReyting.getBall8()+ reqReyting.getBall9()+ reqReyting.getBall10();
            reyting.setMiddle(ortacha/3);
            reyting.setFoiz(100L);
            reyting.setHisob(100L);
            repository.save(reyting);
            response.setMessage(messageRepository.findByCode(0));
        }else {
            response.setMessage(messageRepository.findByCode(1002));
        }
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete/{reyId}")
    public HttpEntity<?> delete(@PathVariable Long reyId){
        Response response = new Response();
        Optional<Reyting> byId = repository.findById(reyId);
        if (byId.isPresent()) {
            repository.delete(byId.get());
            response.setMessage(messageRepository.findByCode(0));
        }else {
            response.setMessage(messageRepository.findByCode(1002));
        }
        return ResponseEntity.ok(response);
    }
}
