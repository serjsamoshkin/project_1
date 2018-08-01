package ua.testing.project1.model.tour;

import ua.testing.project1.entity.tour.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Base class of {@code Tour} subclasses
 * Represents a {@code Tour} with fixed date and type of {@code Tour}.
 * Is used in a {@code Voucher} for storing external parameters like Place, TourType and Date.
 */
public abstract class Tour {

    private static volatile long idQueue = 0;

    public static final Optional<Tour> TOUR_NOT_FOUND = Optional.empty();


    private ArrayList<String> places = new ArrayList<>();
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

    /**
     * Return new unmodifiableList each time. Do not use to for comparison!
     * Use containsAll() to compare result List of the method.
     * @return
     */
    public List<String> getPlaces(){
        return Collections.unmodifiableList(places);
    }

    public abstract void addPlace(String place);

    /**
     * Add additional place to list without any controls.
     * Must be used in addPlace() method in {@code Tour} subclasses
     * @param place
     */
    protected final void setPlace(String place){
        places.add(place);
    }

    public abstract String getPlaceRepresentation();

    /**
     * Simple fabric method that creates and returns an instance of {@code Tour} subclass.
     * @param type of the Tour subclass that will be created
     * @param date of the Tour.
     * @return
     */
    public static Tour tourOf(TourType type, LocalDate date){

        switch (type){
            case CRUISE:
                return new CruiseTour(date);
            case HEALING:
                return new HealingTour(date);
            case SHOPPING:
                return new ShoppingTour(date);
            case EXCURSION:
                return new ExcursionTour(date);
            case RECREATION:
                return new RecriationTour(date);
            default:
                throw new UnsupportedOperationException(String.format("No type %s define in tourOf method in Tour class.", type));
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;
        Tour tour = (Tour) o;
        return places.containsAll(tour.places) &&
                getType() == tour.getType() &&
                Objects.equals(getDate(), tour.getDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlaces(), getType(), getDate());
    }
}
