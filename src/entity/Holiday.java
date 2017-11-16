package entity;


public class Holiday {
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
