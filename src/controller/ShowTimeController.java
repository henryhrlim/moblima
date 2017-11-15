package controller;

import entity.ShowTime;
import handler.ShowTimeHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class: ShowTimeController
 * <p>
 * Class Methods:
 * - createShowTime(ShowTime st) : void
 * - retrieveShowTimeList() : List<ShowTime>
 * - retrieveShowTimeList(List<ShowTime> stList, int movieID): List<ShowTime>
 * - updateShowTimeList(ShowTime st) : void
 */
public class ShowTimeController {


    public void createShowTime(ShowTime st) {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.create(st);
    }

    public List<ShowTime> retrieveShowTimeList() {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.retrieve();
        List<ShowTime> stList = handler.getShowTimeList();
        if (stList == null)
            stList = new ArrayList<ShowTime>();
        return stList;
    }

    public List<ShowTime> retrieveShowTimeList(String cinemaCode) {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.retrieve();
        List<ShowTime> tempList = handler.getShowTimeList();
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        if (tempList == null)
            tempList = new ArrayList<ShowTime>();
        for (ShowTime st : tempList) {
            if (st.getCinemaCode().equals(cinemaCode))
                showTimeList.add(st);
        }
        return showTimeList;
    }


    public List<ShowTime> retrieveShowTimeList(String cinemaCode, int movieID) {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.retrieve();
        List<ShowTime> tempList = handler.getShowTimeList();
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        if (tempList == null)
            tempList = new ArrayList<ShowTime>();
        for (ShowTime st : tempList) {
            if (st.getCinemaCode().equals(cinemaCode) && st.getMovieID() == movieID)
                showTimeList.add(st);
        }
        return showTimeList;
    }


    public List<ShowTime> retrieveShowTimeList(List<ShowTime> stList, int movieID) {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.retrieve();
        List<ShowTime> tempList = handler.getShowTimeList();
        List<ShowTime> showTimeList = new ArrayList<ShowTime>();
        if (tempList == null)
            tempList = new ArrayList<ShowTime>();
        for (ShowTime st : tempList) {
            for (ShowTime showtime : stList) {
                if (showtime.getShowTimeID() == st.getShowTimeID() && st.getMovieID() == movieID) {
                    showTimeList.add(st);
                }
            }
        }
        return showTimeList;
    }

    public void updateShowTimeList(ShowTime st) {

        ShowTimeHandler handler = new ShowTimeHandler();
        handler.updateST(st);
    }

    public void removeShowtime(ShowTime st) {
        ShowTimeHandler handler = new ShowTimeHandler();
        handler.delete(st);
    }
}
