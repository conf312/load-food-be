package com.load.food.web;

import com.load.food.domain.food.Food;
import com.load.food.message.RestMessage;
import com.load.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/list")
    public ResponseEntity<RestMessage> findAll(Food.Request request,
       @RequestParam(required = false, defaultValue = "0") Integer page,
       @RequestParam(required = false, defaultValue = "8") Integer pageSize) {

        RestMessage restMessage = null;

        if (pageSize > 50)
            restMessage = new RestMessage(HttpStatus.OK, null, "pageSize 50 limit.");
        else
            restMessage = new RestMessage(HttpStatus.OK, foodService.findAll(request, page, pageSize));

        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(restMessage);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<RestMessage> findById(@PathVariable Long id) {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, foodService.findById(id)));
    }

    @GetMapping("/class/list")
    public ResponseEntity<RestMessage> findClassNameList() {
        return ResponseEntity.ok()
            .headers(new HttpHeaders())
            .body(new RestMessage(HttpStatus.OK, foodService.findClassNameList()));
    }

}
