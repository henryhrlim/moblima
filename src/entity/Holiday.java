package entity;

/**
 * Holiday
 *
 * @version 1.0
 * @since 10/10/2015
 */

public class Holiday {
    private String date;
    private String holidayName;


    /**
     * Class Constructor
     *
     * @param date        To pass the string of date
     * @param holidayName To pass the string of holiday name
     */
    public Holiday(String date, String holidayName) {
        this.date = date;
        this.holidayName = holidayName;
    }

    /**
     * Gets the date of holiday
     *
     * @return date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of holiday
     *
     * @param date To pass string of date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the name of the holiday
     *
     * @return holidayName.
     */
    public String getHolidayName() {
        return holidayName;
    }

    /**
     * Set the name of the holiday
     *
     * @param holidayName To pass string of holidayName
     */
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }


}
