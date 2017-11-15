package entity;

/**
 * Cinema
 *
 * @version 1.0
 * @since 10/10/2015
 */

public class Cinema {
    private String cinemaCode;
    private String cinemaType;

    /**
     * Class Constructor
     */
    public Cinema() {

    }

    /**
     * Class Constructor
     *
     * @param cinemaType To pass a string of cinemaType
     * @param cinemaCode To pass a string of cinemaCode
     */
    public Cinema(String cinemaType, String cinemaCode) {
        this.cinemaType = cinemaType;
        this.cinemaCode = cinemaCode;
    }

    /**
     * Gets the cinemaCode
     *
     * @return cinemaCode.
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Set the cinemaCode
     *
     * @param cinemaCode To pass string of cinemaCode
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * Gets the cinemaType
     *
     * @return cinemaType.
     */
    public String getCinemaType() {
        return cinemaType;
    }

    /**
     * Set the cinemaType
     *
     * @param cinemaType To pass string of cinemaType
     */
    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }


}
