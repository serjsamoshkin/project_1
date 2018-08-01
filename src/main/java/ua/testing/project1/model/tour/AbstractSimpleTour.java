package ua.testing.project1.model.tour;

import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is realized addPlace and getPlaceRepresentation method of Tour base class.
 * AbstractExcursionTour represents Tours with one place to visit.
 */
public abstract class AbstractSimpleTour extends Tour {

    public AbstractSimpleTour(TourType type, LocalDate date) {
        super(type, date);
    }

    @Override
    public void addPlace(String place) {
        if (getPlaces().size() > 0){
            throw new UnsupportedOperationException("Only one place can be added in this tour");
        }
        setPlace(place);
    }

    @Override
    public String getPlaceRepresentation(){
        List<String> places = getPlaces();
        if (places.isEmpty()){
            return "";
        }else {
            return places.get(0);
        }
    }

}
