package ua.testing.project1.model.tour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entity class.
 * Represents a Tour in a specific city with a fixed date and type of tour.
 * Is used in a {@code Voucher} for storing external parameters like Place, TourType and Date.
 */
public abstract class Tour {

    private static volatile long idQueue;
    static {
        idQueue = 0;
    }

    public static final Tour TOUR_NOT_FOUND = null;

//    public static final Tour TOUR_NOT_FOUND = new Tour(
//            "not found",
//            TourType.values()[0],
//            LocalDate.of(1,1,1));

    private ArrayList<String> places;
    private final TourType type;
    private final LocalDate date;
    private final long id;

    public Tour(TourType type, LocalDate date) {
        this.type = type;
        this.date = date;
        synchronized (Tour.class){
            if (Tour.idQueue == Long.MAX_VALUE){
                throw new IllegalArgumentException("Reached the MAX_VALUE limit for long idQueue value");
            }
            id = Tour.idQueue++;
        }
    }
    public TourType getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    abstract void addPlace(String place);

    protected final void setPlace(String place){
        places.add(place);
    }

    public List<String> getPlaces(){
        return Collections.unmodifiableList(places);
    }


}
