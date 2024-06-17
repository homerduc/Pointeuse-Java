package model;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * This class represents a Planning with start and end times.
 */
public class Planning implements Serializable {

    private LocalTime Start ;
    private LocalTime End;

    /**
     * Constructs a new Planning instance with default values.
     */
    public Planning(){}

    /**
     * Gets the start time of the planning.
     *
     * @return the start time
     */
    public LocalTime getStart() {
        return Start;
    }

    /**
     * Sets the start time of the planning.
     *
     * @param start the new start time
     */
    public void setStart(LocalTime start) {
        Start = start;
    }

    /**
     * Gets the end time of the planning.
     *
     * @return the end time
     */
    public LocalTime getEnd() {
        return End;
    }

    /**
     * Sets the end time of the planning.
     *
     * @param end the new end time
     */
    public void setEnd(LocalTime end) {
        End = end;
    }

    /**
     * Constructs a new Planning instance with specified start and end times.
     *
     * @param start the start time of the planning
     * @param end the end time of the planning
     */
    public Planning(LocalTime start, LocalTime end) {
        Start = start;
        End = end;
    }

    /**
     * Returns a string representation of the planning.
     *
     * @return a string representation of the planning
     */
    @Override
    public String toString() {
        return Start + " - "+End;
    }
}
