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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {

    private final RedisDao redisDao;

    /*
    * 일반 Key / Values CRUD
    * */
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

        String values = (String) redisDao.getValues("name"); // redis value 가져오기
        log.info("# 조회완료 ! key = name / value = {}", values);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public ResponseEntity deleteKey(){

        redisDao.deleteValues("name"); // redis value 가져오기
        log.info("# 삭제완료 !");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*
     * Hash Key / Values CRUD
     * */

    //
    @GetMapping("/save/hashops")
    public ResponseEntity setHashOps(){

        Map<String, String> like = new HashMap<>();
        like.put("like","10");
        like.put("view","200");
        like.put("token","tokenExample");

        redisDao.setHashOps("dhfif718@naver.com",like);

        Set<String> set = like.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            log.info("# 생성완료 ! key = dhfif718@naver.com / fields = [{} : {}]", next, like.get(next));
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/get/hashops")
    public ResponseEntity getHashOps(){

        String values1 = redisDao.getHashOps("dhfif718@naver.com", "like");
        String values2 = redisDao.getHashOps("dhfif718@naver.com", "view");
        String values3 = redisDao.getHashOps("dhfif718@naver.com", "token");

        log.info("# 생성완료 ! key = dhfif718@naver.com / fields = [like : {}]", values1);
        log.info("# 생성완료 ! key = dhfif718@naver.com / fields = [view : {}]", values2);
        log.info("# 생성완료 ! key = dhfif718@naver.com / fields = [token : {}]", values3);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/delete/hashops")
    public ResponseEntity deleteHashOps(){

        redisDao.deleteHashOps("dhfif718@naver.com", "like"); // redis value 가져오기
        redisDao.deleteHashOps("dhfif718@naver.com", "view"); // redis value 가져오기
        redisDao.deleteHashOps("dhfif718@naver.com", "token"); // redis value 가져오기

        log.info("# 삭제완료 !");

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
