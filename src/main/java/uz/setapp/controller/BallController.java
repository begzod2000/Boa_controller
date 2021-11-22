package uz.setapp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import uz.setapp.controller.models.Response;
import uz.setapp.entity.Ball;
import uz.setapp.entity.Reyting;
import uz.setapp.payload.ReqFilter;
import uz.setapp.entity.Xodimlar;
import uz.setapp.payload.ReqBall;
import uz.setapp.repository.BallRepository;
import uz.setapp.repository.MessageRepository;
import uz.setapp.repository.ReytingRepository;
import uz.setapp.repository.XodimlarRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/ballXodim")
public class BallController {
    private final XodimlarRepository xodimlarRepository;
    private final BallRepository ballRepository;
    private final MessageRepository messageRepository;
    private final JdbcTemplate jdbcTemplate;
    private final ReytingRepository repository;

    @Autowired
    public BallController(XodimlarRepository xodimlarRepository, BallRepository ballRepository, MessageRepository messageRepository, JdbcTemplate jdbcTemplate, ReytingRepository repository) {
        this.xodimlarRepository = xodimlarRepository;
        this.ballRepository = ballRepository;
        this.messageRepository = messageRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
    }

    @PostMapping("add")
    public HttpEntity<?> add(@RequestBody ReqBall reqBall) {
        Response response = new Response();
        Optional<Xodimlar> byId = xodimlarRepository.findById(reqBall.getXodimId());
        Optional<Reyting> byXodimlarId = repository.findByXodimlarId(reqBall.getXodimId());
        if (byXodimlarId.isPresent()) {
            if (byId.isPresent()) {
                Ball ball = new Ball();
                ball.setBall1(reqBall.getBall1());
                ball.setBall2(reqBall.getBall2());
                ball.setBall3(reqBall.getBall3());
                ball.setBall4(reqBall.getBall4());
                ball.setBall5(reqBall.getBall5());
                ball.setBall6(reqBall.getBall6());
                ball.setBall7(reqBall.getBall7());
                ball.setBall8(reqBall.getBall8());
                ball.setBall9(reqBall.getBall9());
                ball.setBall10(reqBall.getBall10());
                Reyting reyting = byXodimlarId.get();
                reyting.setAllBall(reqBall.getBall1()+ reyting.getBall2()+ reqBall.getBall3()+ reqBall.getBall4()+ reqBall.getBall5()+ reqBall.getBall6()+ reqBall.getBall7()+ reqBall.getBall7()+ reqBall.getBall8()+ reqBall.getBall9()+ reqBall.getBall10());
                ball.setXodimlar(byId.get());
                ballRepository.save(ball);
                response.setMessage(messageRepository.findByCode(0));
            } else {
                response.setMessage(messageRepository.findByCode(101));
            }
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("list")
    public HttpEntity<?> list() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from  ball");
        response.setData(maps);
        response.setMessage(messageRepository.findByCode(0));
        return ResponseEntity.ok(response);
    }

    @PostMapping("filter")
    public HttpEntity<?> filter(@RequestBody ReqFilter reqFilter) {
        Response response = new Response();
        int year = reqFilter.getOrderDate().getYear();
        int month = reqFilter.getOrderDate().getMonth();
        Date date2 = new Date();
        date2.setMonth(month);
        date2.setYear(year);
        date2.setDate(1);
        Date date1 = new Date();
        date1.setYear(year);
        date1.setDate(30);
        date1.setMonth(month);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from ball where ball.xodimlar_id = ? and ball.created_date between ? and ?", reqFilter.getXodimId(), date2, date1);
        response.setMessage(messageRepository.findByCode(0));
        response.setData(maps);
        return ResponseEntity.ok(response);

    }
}
