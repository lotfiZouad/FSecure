package eventAnalyze;


import java.util.function.LongConsumer;

public class EventActivityTime implements LongConsumer{
    private long firstEvent = Long.MAX_VALUE;
    private long lastEvent = 0;


    @Override
    public void accept(long value) {
        firstEvent = Math.min(firstEvent,value);
        lastEvent = Math.max(lastEvent,value);
    }

    public void combine(EventActivityTime other){
        firstEvent = Math.min(firstEvent,other.firstEvent);
        lastEvent = Math.max(lastEvent,other.lastEvent);
    }

    public long getDiff(){
        return lastEvent - firstEvent;
    }

}
