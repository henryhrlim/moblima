package dataAccess;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * DataHandler
 * This is an abstract class.
 * @version 1.0
 * @since 10/10/2015
 */

public abstract class DataHandler {
    /**
     * The File object file.
     */
    protected File file;

    /**
     * A constant variable. The filepath of the project.
     */
    private static final String FILEPATH = System.getProperty("user.dir");

    /**
     * A constant variable. The filepath within the project.
     */
    private static final String FILENAME = "/src/storage/";

    /**
     * A constant variable. The complete filepath to the data files.
     */
    protected final String filePath = FILEPATH+FILENAME;


    final protected void readFile(String from) {
        try{
            FileReader data = new FileReader(filePath+from+".csv");
            readCSV(data);
        } catch (IOException e){

        }
    }

    /**
     * To write to JSON file.
     * @param to This is the name of the JSON file.
     */
    final protected void writeFile(String to) {
        String fileName = filePath+to+".csv";
        saveDataToCSV(fileName);
    }

    /**
     * To set the file object.
     * @param path This is the name of the JSON file.
     */
    protected void setFilePath(String path) {
        file = new File(path);
    }


    protected abstract void readCSV(FileReader data);

    protected abstract void saveDataToCSV(String to);

    protected abstract void readJSON(JSONArray arr);

    /**
     * This is a abstract method to be implemented by the child classes.
     * This method will format the data into a JSONArray object and return it.
     * @return JSONArray object.
     */
    protected abstract JSONArray saveDataToJSON();

    /**
     * Call the readFile method.
     * @param from This is the name of the JSON file.
     */
    protected void loadData(String from) {
        readFile(from);
    }

    /**
     * Call the writeFile method.
     * @param to This is the name of the JSON file.
     */
    protected void saveData(String to) {
        writeFile(to);
    }
}