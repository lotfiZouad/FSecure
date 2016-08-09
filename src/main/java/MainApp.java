import dto.EventDto;
import eventAnalyze.EventStatsCalculator;
import org.json.simple.JSONObject;
import parser.DataReader;
import parser.DataReaderImpl;
import parser.EventParser;
import parser.EventParserImpl;

import java.util.stream.Stream;

public class MainApp {
    private DataReader reader = new DataReaderImpl();
    private EventParser eventParser = new EventParserImpl();
    private EventStatsCalculator calculator = new EventStatsCalculator();

    public void run(){
        Stream<JSONObject> jsonStream = reader.getJsonFromInputs("obfuscated_data");
        Stream<EventDto> events = eventParser.parseEvents(jsonStream);
        System.out.println(calculator.nbOfFirstTimeLaunchByProduct("product-a",events));
    }

    public static void main(String[] args) {
        new MainApp().run();
    }


}
