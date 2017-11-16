package controller;

import entity.PriceChart;
import handler.HolidayHandler;

import java.util.ArrayList;
import java.util.List;


class HolidayController {

    public void createHoliday(PriceChart.Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.create(h);
    }

    public List<PriceChart.Holiday> retrieveHolidayList() {
        HolidayHandler handler = new HolidayHandler();
        handler.retrieve();
        List<PriceChart.Holiday> hList = handler.getHolidayList();
        if (hList == null)
            hList = new ArrayList<PriceChart.Holiday>();
        return hList;
    }


    public void updateHoliday(PriceChart.Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.update(h);
    }


    public void deleteHoliday(PriceChart.Holiday h) {
        HolidayHandler handler = new HolidayHandler();
        handler.delete(h);
    }


    public boolean isHoliday(String date) {
        HolidayHandler handler = new HolidayHandler();
        handler.retrieve();
        List<PriceChart.Holiday> holidayList = handler.getHolidayList();
        for (PriceChart.Holiday h : holidayList) {
            if (h.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
