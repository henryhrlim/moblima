package controller;

import entity.PriceChart;
import handler.PriceChartHandler;

import java.util.ArrayList;
import java.util.List;


class PriceChartController {
    public double getTicketPrice(PriceChart pc) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.retrieve();
        List<PriceChart> pcList = handler.getPriceChartList();
        String cinemaType = pc.getCinemaType().toLowerCase();
        String age = pc.getAge().toLowerCase();
        String day = pc.getDay().toLowerCase();
        String movieType = pc.getMovieType().toLowerCase();
        double price = 0;
        if (cinemaType.equals("platinum")) {
            movieType = "Any";
            age = "Standard";
        }

        for (PriceChart pricechart : pcList) {
            String pcCinemaType = pricechart.getCinemaType().toLowerCase();
            String pcMovieType = pricechart.getMovieType().toLowerCase();
            String pcDay = pricechart.getDay().toLowerCase();
            String pcAge = pricechart.getAge().toLowerCase();
            if (pcCinemaType.equals(cinemaType)) {
                if (pcMovieType.equals(movieType)) {
                    if (pcDay.equals(day)) {
                        if (pcAge.equals(age)) {
                            price = pricechart.getPrice();
                        }
                    }
                }
            }
        }
        return price;
    }


    public void createPriceChart(PriceChart pc) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.create(pc);
    }

    public List<PriceChart> retrievePriceChartList() {
        PriceChartHandler handler = new PriceChartHandler();
        handler.retrieve();
        return handler.getPriceChartList();
    }


    public List<PriceChart> retrievePriceChartList(String ageGrp) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.retrieve();
        List<PriceChart> tempList = handler.getPriceChartList();
        List<PriceChart> priceChartList = new ArrayList<PriceChart>();
        for (PriceChart pcTemp : tempList) {
            if (pcTemp.getAge().equals(ageGrp)) {
                priceChartList.add(pcTemp);
            }
        }
        return priceChartList;
    }

    public List<PriceChart> retrievePriceChartListByCinemaType(String cineType) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.retrieve();
        List<PriceChart> tempList = handler.getPriceChartList();
        List<PriceChart> priceChartList = new ArrayList<PriceChart>();
        for (PriceChart pcTemp : tempList) {
            if (pcTemp.getCinemaType().equals(cineType)) {
                priceChartList.add(pcTemp);
            }
        }
        return priceChartList;
    }

    public List<PriceChart> retrievePriceChartList(String ageGrp, String cineType, String movType) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.retrieve();
        List<PriceChart> tempList = handler.getPriceChartList();
        List<PriceChart> priceChartList = new ArrayList<PriceChart>();
        for (PriceChart pcTemp : tempList) {
            if (pcTemp.getAge().equals(ageGrp) && pcTemp.getCinemaType().equals(cineType) && pcTemp.getMovieType().equals(movType)) {
                priceChartList.add(pcTemp);
            }
        }
        return priceChartList;
    }

    public void updatePriceChart(PriceChart pc) {
        PriceChartHandler handler = new PriceChartHandler();
        handler.update(pc);
    }


}
