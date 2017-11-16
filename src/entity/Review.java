package entity;


public class Review {
    private String feedback;
    private int rating;


    public Review() {

    }


    public Review(String feedback, int rating) {
        this.feedback = feedback;
        this.rating = rating;
    }


    public String getFeedback() {
        return feedback;
    }


    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


}
