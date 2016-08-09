package parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class DataReaderImpl implements DataReader {

    public Stream<JSONObject> getJsonFromInputs(String input) {
        Stream<String> jsons = null;
        try {
            jsons = Files.lines(Paths.get(input));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsons.map(this::stringToJson);

    }

    private JSONObject stringToJson(String line){
        try {
            return (JSONObject) new JSONParser().parse(line);
        } catch (ParseException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }



}
