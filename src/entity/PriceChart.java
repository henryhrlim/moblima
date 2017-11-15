package entity;


/**
 * PriceChart
 *
 * @version 1.0
 * @since 10/10/2015
 */
public class PriceChart {
    private int priceChartID;
    private String cinemaType;
    private String age;
    private String day;
    private String movieType;
    private double price;

    /**
     * Class Constructor
     */
    public PriceChart() {

    }

    /**
     * Class Constructor
     *
     * @param priceChartID integer of priceChartID
     * @param cinemaType   string of cinemaType
     * @param age          string of age
     * @param day          string of day
     * @param movieType    string of movieType
     * @param price        double of price
     */
    public PriceChart(int priceChartID, String cinemaType, String age, String day, String movieType,
                      double price) {
        this.priceChartID = priceChartID;
        this.cinemaType = cinemaType;
        this.age = age;
        this.day = day;
        this.movieType = movieType;
        this.price = price;
    }

    /**
     * Gets the Cinema Type
     *
     * @return cinemaType.
     */
    public String getCinemaType() {
        return cinemaType;
    }

    /**
     * Set the Cinema Type
     *
     * @param cinemaType To pass string of cinemaType
     */
    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    /**
     * Gets the Age of the movie ("Student","Adult","Children","Senior Citizen")
     *
     * @return age.
     */
    public String getAge() {
        return age;
    }

    /**
     * Set the Age of the movie ("Student","Adult","Children","Senior Citizen")
     *
     * @param age To pass string of age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets the day of the movie
     *
     * @return day.
     */
    public String getDay() {
        return day;
    }

    /**
     * Set the day
     *
     * @param day To pass string of day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Gets the Movie Type
     *
     * @return movieType.
     */
    public String getMovieType() {
        return movieType;
    }


    /**
     * Set the Movie Type
     *
     * @param movieType To pass string of movieType
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    /**
     * Gets the price of movie
     *
     * @return price.
     */
    public double getPrice() {
        return price;
    }


    /**
     * Set the price of movie
     *
     * @param price To pass double of price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the PriceChartID
     *
     * @return priceChartID.
     */
    public int getPriceChartID() {
        return priceChartID;
    }

    /**
     * Set the priceChartID
     *
     * @param priceChartID To pass integer of priceChartID
     */
    public void setPriceChartID(int priceChartID) {
        this.priceChartID = priceChartID;
    }


}

