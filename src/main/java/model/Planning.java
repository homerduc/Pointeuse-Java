package model;

import java.time.LocalTime;

public class Planning {

    private LocalTime Start ;
    private LocalTime End;

    public Planning(){}

    public LocalTime getStart() {
        return Start;
    }

    public void setStart(LocalTime start) {
        Start = start;
    }

    public LocalTime getEnd() {
        return End;
    }

    public void setEnd(LocalTime end) {
        End = end;
    }

    public Planning(LocalTime start, LocalTime end) {
        Start = start;
        End = end;
    }
}
