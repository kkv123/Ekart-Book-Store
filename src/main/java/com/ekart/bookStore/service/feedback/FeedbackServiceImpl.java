package com.ekart.bookStore.service.feedback;

import com.ekart.bookStore.entity.feedback.Feedback;
import com.ekart.bookStore.repository.feedback.FeedbackRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeebackService {
    FeedbackRepo feedbackRepo;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    @Override
    public List<Feedback> getFeedbacksByProductId(int productId) {
        return feedbackRepo.findByProductId(productId);
    }

    @Override
    public List<Feedback> getAllFeedbackByUserId(String userId){
         return feedbackRepo.allFeedbackByUserId(userId);
    }


    @Override
    public void SubmitFeedback(JSONObject requestJson){
        Feedback feedback= new Feedback();
        feedback.setComment(requestJson.optString("comment"));
        feedback.setRating(requestJson.optInt("rating"));
        feedback.setUserId(requestJson.optString("userId"));
        feedback.setProductId(requestJson.optInt("productId"));
        feedbackRepo.save(feedback);
    }
}
