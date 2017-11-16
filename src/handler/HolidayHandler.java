package handler;

import entity.PriceChart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HolidayHandler extends DataHandler {
    private List<PriceChart.Holiday> holidayList;

    public HolidayHandler() {

    }


    public void create(PriceChart.Holiday h) {
        loadData("Holiday");
        if (this.holidayList == null)
            this.holidayList = new ArrayList<PriceChart.Holiday>();
        if (h != null)
            this.holidayList.add(h);
        saveData("Holiday");
    }


    public void retrieve() {
        loadData("Holiday");
    }


    public void update(PriceChart.Holiday h) {
        loadData("Holiday");
        for (int i = 0; i < this.holidayList.size(); i++) {
            PriceChart.Holiday holiday = this.holidayList.get(i);
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


    public void delete(PriceChart.Holiday h) {
        loadData("Holiday");
        for (int i = 0; i < this.holidayList.size(); i++) {
            PriceChart.Holiday holiday = this.holidayList.get(i);
            if (holiday.getDate().equals(h.getDate())) {
                this.holidayList.remove(i);
                saveData("Holiday");
                return;
            }
        }
    }

    protected void readCSV(FileReader csvFile) {
        this.holidayList = new ArrayList<PriceChart.Holiday>();

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
            PriceChart.Holiday h = new PriceChart.Holiday(date.get(i), holidayName.get(i));
            this.holidayList.add(h);
        }
    }

    protected void saveDataToCSV(String to) {
        try (FileWriter file = new FileWriter(to)) {
            for (PriceChart.Holiday h : holidayList) {
                file.append(h.getDate() + "," + h.getHolidayName() + "\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<PriceChart.Holiday> getHolidayList() {
        return holidayList;
    }

}
