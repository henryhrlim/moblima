package controller;

import entity.Holiday;
import handler.HolidayHandler;

import java.util.ArrayList;
import java.util.List;


class HolidayController {

    public void createHoliday(Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.create(h);
    }

    public List<Holiday> retrieveHolidayList() {
        HolidayHandler handler = new HolidayHandler();
        handler.retrieve();
        List<Holiday> hList = handler.getHolidayList();
        if (hList == null)
            hList = new ArrayList<Holiday>();
        return hList;
    }


    public void updateHoliday(Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.update(h);
    }


    public void deleteHoliday(Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.delete(h);
    }


    public boolean isHoliday(String date) {
        HolidayHandler handler = new HolidayHandler();
        handler.retrieve();
        List<Holiday> holidayList = handler.getHolidayList();
        for (Holiday h : holidayList) {
            if (h.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
