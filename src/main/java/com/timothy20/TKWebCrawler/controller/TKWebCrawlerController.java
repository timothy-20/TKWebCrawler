package com.timothy20.TKWebCrawler.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Controller
public class TKWebCrawlerController {
    public TKWebCrawlerController() {

    }

    @PostMapping(value = "/receive_target_url")
    public ResponseEntity<Map<String, Object>> receiveTargetURL(@RequestBody String targetURL) {
        ResponseEntity<Map<String, Object>> response;

        try {
            response = new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception exception) {
            HashMap<String, Object> body = new HashMap<>(1);

            body.put("reason", exception.getMessage());

            response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        } finally {
        }

        return response;
    }

}
