package com.godigit.team2.service.feedback;

import com.godigit.team2.entity.feedback.Feedback;
import org.json.JSONObject;

import java.util.List;

public interface FeebackService {
    public List<Feedback> getFeedbacksByProductId(int productId);
    public void SubmitFeedback(JSONObject feedback);
    public List<Feedback> getAllFeedbackByUserId(String userId);
}
