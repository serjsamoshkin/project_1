package ua.testing.project1.model.tour;

import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;

import java.time.LocalDate;

public abstract class SimpleTour extends Tour {

    public SimpleTour(TourType type, LocalDate date) {
        super(type, date);
    }

    @Override
    void addPlace(String place) {
        if (getPlaces().size() > 0){
            throw new UnsupportedOperationException("Only one place can be added in this tour");
        }
        setPlace(place);
    }
}
