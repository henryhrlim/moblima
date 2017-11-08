package objects;
public class Cinema{
    private string cinemaCode;
    private string cinemaType;

    public Cinema(){

    }

    public Cinema(String cinemaType, String cinemaCode){
        this.cinemaCode = cinemaCode;
        this.cinemaType = cinemaType;
    }

    public string getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(string cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public string getCinemaType() {
        return cinemaType;
    }

    public void setCinemaType(string cinemaType) {
        this.cinemaType = cinemaType;
    }
}