package handler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


abstract class DataHandler {

    private static final String FILEPATH = System.getProperty("user.dir");

    private static final String FILENAME = "/src/data/";

    private final String filePath = FILEPATH + FILENAME;

    private File file;

    private void readFile(String from) {
        try {
            FileReader data = new FileReader(filePath + from + ".csv");
            readCSV(data);
        } catch (IOException e) {

        }
    }


    private void writeFile(String to) {
        String fileName = filePath + to + ".csv";
        saveDataToCSV(fileName);
    }


    protected void setFilePath(String path) {
        file = new File(path);
    }


    protected abstract void readCSV(FileReader data);

    protected abstract void saveDataToCSV(String to);


    void loadData(String from) {
        readFile(from);
    }


    void saveData(String to) {
        writeFile(to);
    }
}