package handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.Messaging.SyncScopeHelper;

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
        String csvFile = filePath+from+".csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
            }
            readCSV(data);
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
    }

    /**
     * To write to JSON file.
     * @param to This is the name of the JSON file.
     */
    final protected void writeFile(String to) {
        String[] data = saveDataToCSV();
        try {
            FileWriter file = new FileWriter(filePath+to+".csv");
            file.write(data);
            file.flush();
            file.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    /**
     * To set the file object.
     * @param path This is the name of the JSON file.
     */
    protected void setFilePath(String path) {
        file = new File(path);
    }

    /**
     * This is a abstract method to be implemented by the child classes.
     * This method will format the arr JSONArray object.
     * @param arr This is a JSONArray object.
     */
    protected abstract void readCSV(String[] data);

    /**
     * This is a abstract method to be implemented by the child classes.
     * This method will format the data into a JSONArray object and return it.
     * @return JSONArray object.
     */
    protected abstract JSONArray saveDataToCSV();

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