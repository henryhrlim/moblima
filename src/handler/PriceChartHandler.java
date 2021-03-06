package handler;

import entity.PriceChart;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PriceChartHandler extends DataHandler {

    private List<PriceChart> priceChartList;

    public PriceChartHandler() {

    }


    public void retrieve() {
        loadData("PriceChart");
    }


    public void create(PriceChart pc) {
        loadData("PriceChart");
        if (this.priceChartList == null)
            this.priceChartList = new ArrayList<PriceChart>();
        if (pc != null)
            this.priceChartList.add(pc);
        saveData("PriceChart");
    }


    public boolean update(PriceChart pc) {
        loadData("PriceChart");
        for (int i = 0; i < this.priceChartList.size(); i++) {
            PriceChart priceChart = this.priceChartList.get(i);
            if (priceChart.getPriceChartID() == pc.getPriceChartID()) {
                this.priceChartList.set(i, pc);
                saveData("PriceChart");
                return true;
            }
        }

        return false;
    }

    protected void readCSV(FileReader csvFile) {
        this.priceChartList = new ArrayList<PriceChart>();

        List<String> priceChartID = new ArrayList<String>();
        List<String> movieType = new ArrayList<String>();
        List<String> cinemaType = new ArrayList<String>();
        List<String> price = new ArrayList<String>();
        List<String> day = new ArrayList<String>();
        List<String> age = new ArrayList<String>();

        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(csvFile);
            String[] data;
            while ((line = br.readLine()) != null) {

                data = line.split(csvSplitBy);
                priceChartID.add(data[0]);
                movieType.add(data[1]);
                cinemaType.add(data[2]);
                price.add(data[3]);
                day.add(data[4]);
                age.add(data[5]);
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

        for (int i = 0; i < priceChartID.size(); i++) {
            PriceChart pc = new PriceChart(Integer.valueOf(priceChartID.get(i)), cinemaType.get(i), age.get(i), day.get(i), movieType.get(i), Double.valueOf(price.get(i)));
            this.priceChartList.add(pc);
        }
    }

    protected void saveDataToCSV(String to) {
        try (FileWriter file = new FileWriter(to)) {
            for (PriceChart pc : priceChartList) {
                file.append(pc.getPriceChartID() + "," + pc.getMovieType() + "," + pc.getCinemaType() + "," + pc.getPrice() + "," + pc.getDay() + "," + pc.getAge() + "\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<PriceChart> getPriceChartList() {
        return priceChartList;
    }

}
