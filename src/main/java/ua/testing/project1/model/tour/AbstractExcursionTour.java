package ua.testing.project1.model.tour;

import java.time.LocalDate;

/**
 * This class is realized addPlace and getPlaceRepresentation method of Tour base class.
 * AbstractExcursionTour represents Tours with several places to visit.
 */
public abstract class AbstractExcursionTour extends Tour {
    public AbstractExcursionTour(TourType type, LocalDate date) {
        super(type, date);
    }

    @Override
    public void addPlace(String place) {
        setPlace(place);
    }

    public String getPlaceRepresentation(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String str:
                getPlaces()) {
            stringBuilder.append(str);
            stringBuilder.append(" - ");
        }

        if (stringBuilder.length() > 0){
            return stringBuilder.substring(0, stringBuilder.length()-3);
        }else {
            return stringBuilder.toString();
        }
    }

}
