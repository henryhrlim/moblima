package handler;

import entity.Holiday;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HolidayHandler extends DataHandler {
    private List<Holiday> holidayList;

    public HolidayHandler() {

    }


    public void create(Holiday h) {
        loadData("Holiday");
        if (this.holidayList == null)
            this.holidayList = new ArrayList<Holiday>();
        if (h != null)
            this.holidayList.add(h);
        saveData("Holiday");
    }


    public void retrieve() {
        loadData("Holiday");
    }


    public void update(Holiday h) {
        loadData("Holiday");
        for (int i = 0; i < this.holidayList.size(); i++) {
            Holiday holiday = this.holidayList.get(i);
            if (holiday.getDate().equals(h.getDate())) {
                this.holidayList.set(i, h);
                saveData("Holiday");
                return;
            } else if (holiday.getHolidayName().equals(h.getHolidayName())) {
                this.holidayList.set(i, h);
                saveData("Holiday");
                return;
            }
        }
    }


    public void delete(Holiday h) {
        loadData("Holiday");
        for (int i = 0; i < this.holidayList.size(); i++) {
            Holiday holiday = this.holidayList.get(i);
            if (holiday.getDate().equals(h.getDate())) {
                this.holidayList.remove(i);
                saveData("Holiday");
                return;
            }
        }
    }

    protected void readCSV(FileReader csvFile) {
        this.holidayList = new ArrayList<Holiday>();

        List<String> date = new ArrayList<String>();
        List<String> holidayName = new ArrayList<String>();

        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(csvFile);
            String[] data;
            while ((line = br.readLine()) != null) {

                data = line.split(csvSplitBy);
                date.add(data[0]);
                holidayName.add(data[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < date.size(); i++) {
            Holiday h = new Holiday(date.get(i), holidayName.get(i));
            this.holidayList.add(h);
        }
    }

    protected void saveDataToCSV(String to) {
        try (FileWriter file = new FileWriter(to)) {
            for (Holiday h : holidayList) {
                file.append(h.getDate() + "," + h.getHolidayName() + "\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Holiday> getHolidayList() {
        return holidayList;
    }

}
