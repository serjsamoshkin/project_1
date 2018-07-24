package ua.testing.project1.model.entity;

import ua.testing.project1.model.tourTypes.TourType;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity class.
 * Represents a Tour in a specific city with a fixed date and type of tour.
 * Is used in a {@code Voucher} for storing external parameters like Place, TourType and Date.
 */
public class Tour {

    private static volatile long idQueue;
    static {
        idQueue = 0;
    }

    public static final Tour TOUR_NOT_FOUND = new Tour(
            "not found",
            TourType.EXCURSION,
            LocalDate.of(1,1,1));

    private final String place;
    private final TourType type;
    private final LocalDate date;
    private final long id;

    public Tour(String place, TourType type, LocalDate date) {
        this.place = place;
        this.type = type;
        this.date = date;
        synchronized (Tour.class){
            if (Tour.idQueue == Long.MAX_VALUE){
                throw new IllegalArgumentException("Reached the MAX_VALUE limit for long idQueue value");
            }
            id = Tour.idQueue++;
        }
    }

    public String getPlace() {
        return place;
    }

    public TourType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(getPlace(), tour.getPlace()) &&
                getType() == tour.getType() &&
                Objects.equals(getDate(), tour.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlace(), getType(), getDate());
    }

    @Override
    public String toString() {
        return "Tour{" +
                "place='" + place + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
