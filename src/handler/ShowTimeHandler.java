package handler;

import entity.Seat;
import entity.ShowTime;
import entity.Tickets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ShowTimeHandler
 *
 * @version 1.0
 * @since 10/10/2015
 */


public class ShowTimeHandler extends DataHandler {
    /**
     * The list of ShowTime.
     */
    private List<ShowTime> showTimeList;

    public ShowTimeHandler() {

    }

    /**
     * Gets the showTimeList
     *
     * @return showTimeList.
     */
    public List<ShowTime> getShowTimeList() {
        return showTimeList;
    }

    /**
     * Writes a new ShowTime into the JSON file.
     * The ShowTime object st's variables all must be set.
     *
     * @param st This is the object of ShowTime.
     */
    public void create(ShowTime st) {
        loadData("ShowTime");
        if (this.showTimeList == null)
            this.showTimeList = new ArrayList<ShowTime>();
        if (st != null)
            this.showTimeList.add(st);
        saveData("ShowTime");
    }

    /**
     * Call loadData method from parent class.
     * Retrieve data from JSON file and save in the showTimeList.
     */
    public void retrieve() {
        loadData("ShowTime");
        if (this.showTimeList == null)
            this.showTimeList = new ArrayList<ShowTime>();
    }


    /**
     * This method update the ShowTime object in the JSON file.
     * It will update data by the showTimeID as the index.
     * If updated return true, else return false.
     * The ShowTime object st's variables all must be set.
     *
     * @param st This is the object of ShowTime.
     * @return boolean
     */
    public boolean updateST(ShowTime st) {
        loadData("ShowTime");
        for (int i = 0; i < this.showTimeList.size(); i++) {
            ShowTime showtime = this.showTimeList.get(i);
            if (st.getShowTimeID() == showtime.getShowTimeID()) {
                this.showTimeList.set(i, st);
                saveData("ShowTime");
                return true;
            }
        }
        return false;
    }

    /**
     * This method update the seat in ShowTime object in the JSON file.
     * It will update data by the showTimeID as the index.
     * If updated return true, else return false.
     * The Tickets object ticket's variables all must be set.
     *
     * @param showTimeID This is ShowTime's showTimeID.
     * @param row        This is ShowTime's row.
     * @param column     This is ShowTime's column.
     * @param ticket     This is Tickets's ticket.
     * @return boolean
     */
    public boolean updateSeat(int showTimeID, String row, int column,
                              Tickets ticket) {
        loadData("ShowTime");
        for (int i = 0; i < this.showTimeList.size(); i++) {
            ShowTime st = this.showTimeList.get(i);
            if (st.getShowTimeID() == showTimeID) {
                List<Seat> seatList = st.getSeats();
                for (int x = 0; x < seatList.size(); x++) {
                    Seat s = seatList.get(x);
                    if (s.getRow().equals(row) && s.getColumn() == column) {
                        s.setStatus(true);
                        s.setTicket(ticket);
                        seatList.set(x, s);
                        st.setSeats(seatList);
                        this.showTimeList.set(i, st);
                        saveData("ShowTime");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method update the ShowTime object in the JSON file.
     * It will delete data by the showTimeID.
     * If deleted return true, else return false.
     *
     * @param showTimeID This is the ShowTime's showTimeID.
     * @return boolean
     */
    public boolean delete(ShowTime st) {
        loadData("ShowTime");
        for (int i = 0; i < this.showTimeList.size(); i++) {
            ShowTime showtime = this.showTimeList.get(i);
            if (showtime.getShowTimeID() == st.getShowTimeID()) {
                this.showTimeList.remove(i);
                saveData("ShowTime");
                return true;
            }
        }
        return false;
    }

    protected void readCSV(FileReader csvFile) {
        int k = 0;
        this.showTimeList = new ArrayList<ShowTime>();

        List<String> date = new ArrayList<String>();
        List<String> showTimeID = new ArrayList<String>();
        List<String> cinemaCode = new ArrayList<String>();
        List<String> movieID = new ArrayList<String>();
        List<String> time = new ArrayList<String>();
        List<String> day = new ArrayList<String>();
        List<String> row = new ArrayList<String>();
        List<String> column = new ArrayList<String>();
        List<String> seatType = new ArrayList<String>();
        List<String> status = new ArrayList<String>();
        List<String> ticketID = new ArrayList<String>();
        List<String> age = new ArrayList<String>();
        List<Seat> seats = null;

        BufferedReader br = null;
        BufferedReader seatsBr = null;
        String line;
        String seatsLine;
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(csvFile);
            seatsBr = new BufferedReader(new FileReader("src/data/Seats.csv"));
            String[] data;
            while ((line = br.readLine()) != null) {
                data = line.split(csvSplitBy);
                date.add(data[0]);
                showTimeID.add(data[1]);
                cinemaCode.add(data[2]);
                movieID.add(data[3]);
                time.add(data[4]);
                day.add(data[5]);
                while ((seatsLine = seatsBr.readLine()) != null) {
                    String[] seatsData;
                    seatsData = seatsLine.split(csvSplitBy);
                    seatType.add(seatsData[1]);
                    ticketID.add(seatsData[2]);
                    age.add(seatsData[3]);
                    column.add(seatsData[4]);
                    row.add(seatsData[5]);
                    status.add(seatsData[6]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    seatsBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < date.size(); i++) {
            seats = new ArrayList<Seat>();
            for (int j = 0; j < 48; j++) {
                Tickets t = new Tickets(Integer.valueOf(ticketID.get(k)), age.get(k));
                Seat s = new Seat(row.get(k), Integer.valueOf(column.get(k)), seatType.get(k), Boolean.valueOf(status.get(k)), t);
                seats.add(s);
                k++;
            }
            ShowTime st = new ShowTime(Integer.valueOf(showTimeID.get(i)), time.get(i), day.get(i), date.get(i), Integer.valueOf(movieID.get(i)), seats, cinemaCode.get(i));
            this.showTimeList.add(st);
        }
    }

    ;

    protected void saveDataToCSV(String to) {
        int i = 0;
        try {
            FileWriter showTimeFile = new FileWriter(to);
            FileWriter seatsFile = new FileWriter("src/data/Seats.csv");
            for (ShowTime st : showTimeList) {
                showTimeFile.append(st.getDate() + "," + st.getShowTimeID() + "," + st.getCinemaCode() + "," + st.getMovieID() + "," + st.getTime() + "," + st.getDay() + "\n");
                i++;
                for (Seat s : st.getSeats()) {
                    if (st.getShowTimeID() == i)
                        seatsFile.append(st.getShowTimeID() + "," + s.getSeatType() + "," + s.getTicket().getTicketID() + "," + s.getTicket().getAge() + "," + s.getColumn() + "," + s.getRow() + "," + s.getStatus() + "\n");
                }
            }
            showTimeFile.flush();
            seatsFile.flush();
            showTimeFile.close();
            seatsFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;
}
