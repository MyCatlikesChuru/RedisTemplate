package com.spring.redis.conroller;

import com.spring.redis.common.RedisDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {

    private final RedisDao redisDao;

    @GetMapping("/save")
    public ResponseEntity saveKey(@RequestParam String name){


        redisDao.setValues("name",name); // key = name / value = 이재혁
        log.info("# 생성완료 ! key = name / value = {}",name);

        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/save/time")
    public ResponseEntity saveKeyTime(@RequestParam String name){


        redisDao.setValues("name",name, Duration.ofMillis(10000)); // 1초후 소멸
        log.info("# 생성완료 ! key = name / value = {} [소멸 시간 = 10초후]",name);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getKey(){

        String values = redisDao.getValues("name"); // redis value 가져오기
        log.info("# 조회완료 ! key = name / value = {}", values);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public ResponseEntity deleteKey(){

        redisDao.deleteValues("name"); // redis value 가져오기
        log.info("# 삭제완료 !");

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
