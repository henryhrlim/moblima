package entity;


public class Cinema {
    private String cinemaCode;
    private String cinemaType;


    public Cinema() {

    }


    public Cinema(String cinemaType, String cinemaCode) {
        this.cinemaType = cinemaType;
        this.cinemaCode = cinemaCode;
    }


    public String getCinemaCode() {
        return cinemaCode;
    }


    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }


    public String getCinemaType() {
        return cinemaType;
    }


    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }


}
