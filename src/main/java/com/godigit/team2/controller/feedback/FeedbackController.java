package com.godigit.team2.controller.feedback;

import com.godigit.team2.entity.feedback.Feedback;
import com.godigit.team2.service.feedback.FeedbackServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-kart/feedback")
public class FeedbackController {


    FeedbackServiceImpl feedbackService;

    @Autowired
    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/product/{product_id}")
    public List<Feedback> getFeedback(@PathVariable int product_id) {
        return feedbackService.getFeedbacksByProductId(product_id);
    }

    @GetMapping("user/{userId}")
    public List<Feedback> getFeedbackByUserId (@PathVariable String userId) {
        return feedbackService.getAllFeedbackByUserId(userId);
    }

    @PostMapping("/submit")
    public String submitFeedback(@RequestBody String request, @RequestParam String productId,@RequestParam String userId ){
        JSONObject requestJson= new JSONObject(request);
        requestJson.put("productId",productId);
        requestJson.put("userId",userId);
        feedbackService.SubmitFeedback(requestJson);
        return "Feedback submitted";
    }


}
