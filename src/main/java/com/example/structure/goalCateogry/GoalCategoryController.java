package com.example.structure.goalCateogry;

import com.example.structure.body.domain.Body;
import com.example.structure.body.dto.request.BodyRequest;
import com.example.structure.goalCateogry.domain.GoalCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal-categories")
public class GoalCategoryController {
    private final GoalCategoryService goalCategoryService;

    public GoalCategoryController(GoalCategoryService goalCategoryService) {
        this.goalCategoryService = goalCategoryService;
    }

    @PostMapping
    public ResponseEntity<GoalCategory> insert(@RequestBody GoalCategoryRequest goalCategoryRequest) {
        GoalCategory goalCategory = goalCategoryService.insert(goalCategoryRequest);
        return new ResponseEntity<>(goalCategory, HttpStatus.CREATED);
    }
}

