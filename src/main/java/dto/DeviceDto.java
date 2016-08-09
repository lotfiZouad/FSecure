package dto;


public class DeviceDto {
    private final String deviceId;
    private final String model;
    private final Long timezoneOffset;


    public DeviceDto(String deviceId, String model, Long timezoneOffset) {
        this.deviceId = deviceId;
        this.model = model;
        this.timezoneOffset = timezoneOffset;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getModel() {
        return model;
    }

    public Long getTimezoneOffset() {
        return timezoneOffset;
    }
}

