package parser;


import dto.EventDto;
import org.json.simple.JSONObject;

import java.util.stream.Stream;

public interface EventParser {
    Stream<EventDto> parseEvents(Stream<JSONObject> jsons);

}
