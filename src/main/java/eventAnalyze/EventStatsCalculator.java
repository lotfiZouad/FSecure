package eventAnalyze;


import dto.EventDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

public class EventStatsCalculator {


    public long nbOfLaunchByProduct(String source,Stream<EventDto> events){
        return events.filter(eventDto -> eventDto.getSource().equals(source) && eventDto.getType().equals("launch"))
               .count();
    }

    public long nbOfFirstTimeLaunchByProduct(String source,Stream<EventDto> events){
        return events.filter(eventDto -> eventDto.getSource().equals(source) && eventDto.getType().equals("launch") && Boolean.FALSE.equals(eventDto.isImprovement()))
                .count();
    }

    public Stream<String> duplicateEvents(Stream<EventDto> events){
        Set<String> diffIds = new LinkedHashSet<>();
        return events.map(EventDto::getId)
                .filter((s -> !diffIds.add(s))).distinct();

    }

    public int hourWithMinOfEvents(Stream<EventDto> events){
        return events.collect(Collectors.groupingBy(eventDto -> LocalDateTime.ofInstant(Instant.ofEpochSecond(eventDto.getTimeStamp()),ZoneId.of("UTC")).getHour(),Collectors.counting()))
                .entrySet()
                .stream()
                .min((o1, o2) -> Long.compare(o1.getValue(),o2.getValue()))
                .get()
                .getKey();
    }

    public String deviceWithLongestActivityTime(Stream<EventDto> events){
        Collector<Long,?,EventActivityTime> collector = Collector.of(EventActivityTime::new,
                EventActivityTime::accept,
                (eventActivityTime, eventActivityTime2) ->   {eventActivityTime.combine(eventActivityTime2);
                                                              return  eventActivityTime; });
        Map<String,EventActivityTime> eventTimes = events
                .collect(groupingBy(eventDto -> eventDto.getDevice().getDeviceId(), mapping(eventDto -> eventDto.getTimeStamp(),collector)));

        return eventTimes.entrySet().stream()
                .max((o1, o2) -> Long.compare(o1.getValue().getDiff(),o2.getValue().getDiff()))
                .get()
                .getKey();

    }
}


