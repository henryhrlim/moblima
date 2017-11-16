package entity;


public class TopSales {
    private int movieID;
    private String title;
    private double totalAmount;


    public TopSales(int movieID, double totalAmount, String title) {
        this.movieID = movieID;
        this.totalAmount = totalAmount;
        this.title = title;
    }


    public int getMovieID() {
        return movieID;
    }


    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


}
