package ua.testing.project1.entity.voucher;



import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.entity.voucher.consts.Meal;
import ua.testing.project1.entity.voucher.consts.Transport;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entity class.
 * Represents a bought/reserved Voucher of specific {@code Tour}
 */
public class Voucher {

    private final Tour tour;

    private final Meal mealType;
    private final Transport transportType;
    private final int duration;

    public Voucher(Tour tour, Meal mealType, Transport transportType, int duration) {
        this.tour = tour;
        this.mealType = mealType;
        this.transportType = transportType;
        this.duration = duration;
    }

    public Tour getTour() {
        return tour;
    }

    public Meal getMealType() {
        return mealType;
    }

    public Transport getTransportType() {
        return transportType;
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getPlace() {
        return tour.getPlaces();
    }

    public TourType getType() {
        return tour.getType();
    }

    public LocalDate getDate() {
        return tour.getDate();
    }

    public long getTourId() {
        return tour.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return getDuration() == voucher.getDuration() &&
                Objects.equals(getTour(), voucher.getTour()) &&
                getMealType() == voucher.getMealType() &&
                getTransportType() == voucher.getTransportType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTour(), getMealType(), getTransportType(), getDuration());
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "tour=" + tour +
                ", mealType=" + mealType +
                ", transportType=" + transportType +
                ", duration=" + duration +
                '}';
    }
}


