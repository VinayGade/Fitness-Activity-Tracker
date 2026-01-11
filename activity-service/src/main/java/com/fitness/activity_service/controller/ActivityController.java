package com.fitness.activity_service.controller;

import com.fitness.activity_service.dto.ActivityRequest;
import com.fitness.activity_service.dto.ActivityResponse;
import com.fitness.activity_service.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    /*
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }
     */

    @PostMapping("/")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest /*,
    @RequestHeader("X-User-ID") String userId */){
        /*
        if(userId!=null){
            activityRequest.setUserId(userId);
        }*/
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<ActivityResponse>> getAll(){
        List<ActivityResponse> activities = activityService.getAll();
        return ResponseEntity.ok(activities);
    }
}
