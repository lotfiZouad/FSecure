package parser;


import org.json.simple.JSONObject;

import java.util.stream.Stream;

public interface DataReader {

    Stream<JSONObject> getJsonFromInputs(String input);
}
