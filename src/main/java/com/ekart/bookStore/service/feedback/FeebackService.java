package com.ekart.bookStore.service.feedback;

import com.ekart.bookStore.entity.feedback.Feedback;
import org.json.JSONObject;

import java.util.List;

public interface FeebackService {
    public List<Feedback> getFeedbacksByProductId(int productId);
    public void SubmitFeedback(JSONObject feedback);
    public List<Feedback> getAllFeedbackByUserId(String userId);
}
