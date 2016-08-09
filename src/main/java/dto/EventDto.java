package dto;


public class EventDto implements Comparable<EventDto> {
    private final String id;
    private String type;
    private long timeStamp;
    private DeviceDto device;
    private String source;
    private Boolean improvement;

    public EventDto(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public DeviceDto getDevice() {
        return device;
    }

    public void setDevice(DeviceDto device) {
        this.device = device;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isImprovement() {
        return improvement;
    }

    public void setImprovement(Boolean improvement) {
        this.improvement = improvement;
    }

    @Override
    public int compareTo(EventDto o) {
        return Long.compare(this.timeStamp,o.timeStamp);
    }
}
