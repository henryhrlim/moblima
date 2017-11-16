package entity;


public class PriceChart {
    private int priceChartID;
    private String cinemaType;
    private String age;
    private String day;
    private String movieType;
    private double price;


    public PriceChart() {

    }


    public PriceChart(int priceChartID, String cinemaType, String age, String day, String movieType,
                      double price) {
        this.priceChartID = priceChartID;
        this.cinemaType = cinemaType;
        this.age = age;
        this.day = day;
        this.movieType = movieType;
        this.price = price;
    }


    public String getCinemaType() {
        return cinemaType;
    }


    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }


    public String getAge() {
        return age;
    }


    public void setAge(String age) {
        this.age = age;
    }


    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }


    public String getMovieType() {
        return movieType;
    }


    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public int getPriceChartID() {
        return priceChartID;
    }


    public void setPriceChartID(int priceChartID) {
        this.priceChartID = priceChartID;
    }


    public static class Holiday {
        private String date;
        private String holidayName;


        public Holiday(String date, String holidayName) {
            this.date = date;
            this.holidayName = holidayName;
        }


        public String getDate() {
            return date;
        }


        public void setDate(String date) {
            this.date = date;
        }


        public String getHolidayName() {
            return holidayName;
        }


        public void setHolidayName(String holidayName) {
            this.holidayName = holidayName;
        }


    }
}

