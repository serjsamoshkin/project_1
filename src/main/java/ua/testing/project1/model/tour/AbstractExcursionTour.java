package ua.testing.project1.model.tour;

import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;

import java.time.LocalDate;

public abstract class ExcursionTour extends Tour {
    public ExcursionTour(String place, TourType type, LocalDate date) {
        super(type, date);
    }

    @Override
    void addPlace(String place) {
        setPlace(place);
    }

}
