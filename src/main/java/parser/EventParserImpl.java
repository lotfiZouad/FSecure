package parser;

import dto.DeviceDto;
import dto.EventDto;
import org.json.simple.JSONObject;

import java.util.stream.Stream;


public class EventParserImpl implements EventParser {

    private static final String EVENT_ID = "event_id";
    private static final String TYPE = "type";
    private static final String SOURCE = "source";
    private static final String DEVICE_ID = "device_id";
    private static final String MODEL = "model";
    private static final String TIME = "time";
    private static final String DEVICE = "device";
    private static final String CREATE_TIMESTAMP = "create_timestamp";
    public static final String TIMEZONE_OFFSET = "timezone_offset";

    @Override
    public Stream<EventDto> parseEvents(Stream<JSONObject> jsons){
        return jsons.map(this::parseEventJsonObject);
    }


    private EventDto parseEventJsonObject(JSONObject jsonEvent){
        String id = (String) jsonEvent.get(EVENT_ID);
        String type = (String) jsonEvent.get(TYPE);
        String source = (String) jsonEvent.get(SOURCE);
        JSONObject jsonDevice = (JSONObject) jsonEvent.get(DEVICE);
        DeviceDto deviceDto = parseDeviceJsonObject(jsonDevice);
        JSONObject jsonTime = (JSONObject) jsonEvent.get(TIME);
        long timestamp = getTimestamp(jsonTime);
        JSONObject consent = (JSONObject) jsonEvent.get("consent");
        Boolean improvement = (Boolean) consent.get("product_improvement");
        if(deviceDto.getTimezoneOffset()!=null)
            timestamp +=deviceDto.getTimezoneOffset();

        EventDto eventDto = new EventDto(id);
        eventDto.setType(type);
        eventDto.setTimeStamp(timestamp);
        eventDto.setSource(source);
        eventDto.setDevice(deviceDto);
        eventDto.setImprovement(improvement);

        return eventDto;
    }


    private DeviceDto parseDeviceJsonObject(JSONObject jsonDevice){
        String id = (String) jsonDevice.get(DEVICE_ID);
        String model = (String) jsonDevice.get(MODEL);
        Long offset = (Long) jsonDevice.get(TIMEZONE_OFFSET);

        return new DeviceDto(id,model, offset);
    }

    private long getTimestamp(JSONObject jsonTime){
        return (long) jsonTime.get(CREATE_TIMESTAMP);
    }

}
