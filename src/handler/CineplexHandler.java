package handler;

import entity.Cineplex;
import entity.Movie;
import entity.ShowTime;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CineplexHandler extends DataHandler {

    private List<Cineplex> cineplexList;

    public CineplexHandler() {

    }

    public CineplexHandler(List<Cineplex> cineplexList) {
        this.cineplexList = cineplexList;
    }


    public void create(Cineplex c) {
        loadData("Cineplex");
        if (this.cineplexList == null)
            this.cineplexList = new ArrayList<Cineplex>();
        if (c != null)
            this.cineplexList.add(c);
        saveData("Cineplex");
    }


    public void retrieve() {
        loadData("Cineplex");
    }


    public boolean update(Cineplex c) {
        boolean result = false;
        loadData("Cineplex");

        for (int i = 0; i < this.cineplexList.size(); i++) {
            Cineplex cine = this.cineplexList.get(i);
            if (cine.getCineplexID() == c.getCineplexID()) {
                this.cineplexList.set(i, c);
                result = true;
                saveData("Cineplex");
                return result;
            }
        }
        return result;
    }


    public boolean delete(int cineplexID) {
        boolean result = false;
        loadData("Cineplex");
        for (int i = 0; i < this.cineplexList.size(); i++) {
            Cineplex cine = this.cineplexList.get(i);
            if (cine.getCineplexID() == cineplexID) {
                this.cineplexList.remove(i);
                result = true;
                saveData("Cineplex");
                return result;
            }
        }
        return result;
    }


    public List<Cineplex> getCineplexList() {
        return cineplexList;
    }


    public void setCineplexList(List<Cineplex> cineplexList) {
        this.cineplexList = cineplexList;
    }

    protected void readCSV(FileReader csvFile) {
        this.cineplexList = new ArrayList<Cineplex>();
        List<Integer> cineplexID = new ArrayList<>();
        List<String> location = new ArrayList<>();
        List<String> cineplexName = new ArrayList<>();
        List<List> cinemas = new ArrayList<>();
        List<List> movies = new ArrayList<>();
        List<List> showTime = new ArrayList<>();

        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(csvFile);
            String[] data = null;
            List<String> cinemaList;
            List<String> showTimeList;
            List<String> moviesList;
            while ((line = br.readLine()) != null) {
                data = line.split(csvSplitBy);

                showTimeList = new ArrayList<String>(Arrays.asList((data[0]).split("~")));
                showTime.add(showTimeList);
                cineplexID.add(Integer.valueOf(data[1]));
                location.add(data[2]);
                moviesList = new ArrayList<String>(Arrays.asList((data[3]).split("~")));
                movies.add(moviesList);
                cineplexName.add(data[4]);
                cinemaList = new ArrayList<String>(Arrays.asList((data[5]).split("~")));
                cinemas.add(cinemaList);
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
        for (int i = 0; i < cineplexID.size(); i++) {
            Cineplex cineplex;
            List<Cineplex.Cinema> cinemaList = new ArrayList<>();
            List<Movie> movieList = new ArrayList<>();
            List<ShowTime> showTimesList = new ArrayList<>();
            for (int j = 0; j < cinemas.get(i).size() - 1; j++) {
                String a = String.valueOf(cinemas.get(i).get(j));
                a = a.replace("\"", "");
                a = a.replace("~", "");
                String[] b = a.split("-");
                String cinemaType = b[0];
                String cinemaCode = b[1];
                Cineplex.Cinema c = new Cineplex.Cinema(cinemaType, cinemaCode);
                cinemaList.add(c);
            }
            for (int k = 0; k < movies.get(i).size() - 1; k++) {
                String d = String.valueOf(movies.get(i).get(k));
                d = d.replace("\"", "");
                Movie m = new Movie();
                m.setMovieID(Integer.valueOf(d));
                movieList.add(m);
            }
            for (int l = 0; l < showTime.get(i).size() - 1; l++) {
                String e = String.valueOf(showTime.get(i).get(l));
                e = e.replace("\"", "");
                ShowTime s = new ShowTime();
                s.setShowTimeID(Integer.valueOf(e));
                showTimesList.add(s);
            }
            cineplex = new Cineplex(Integer.valueOf(cineplexID.get(i)), location.get(i), cineplexName.get(i), cinemaList);
            cineplex.setMovie(movieList);
            cineplex.setShowTime(showTimesList);
            cineplexList.add(cineplex);
        }
    }

    protected void saveDataToCSV(String to) {
        try (FileWriter file = new FileWriter(to)) {
            for (Cineplex c : cineplexList) {
                List<ShowTime> showTimeList = c.getShowTime();
                file.append("\"");
                for (ShowTime showTime : showTimeList) {
                    file.append(String.valueOf(showTime.getShowTimeID()));
                    file.append("~");
                }
                file.append("\"," + c.getCineplexID() + "," + c.getLocation() + ",\"");
                List<Movie> movieList = c.getMovie();
                for (Movie movie : movieList) {
                    file.append(String.valueOf(movie.getMovieID()) + "~");
                }
                file.append("\"," + c.getCineplexName() + ",\"");
                List<Cineplex.Cinema> cinemaList = c.getCinemas();
                for (Cineplex.Cinema cinema : cinemaList) {
                    file.append(cinema.getCinemaType() + "-" + cinema.getCinemaCode() + "~");
                }
                file.append("\"\n");
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
