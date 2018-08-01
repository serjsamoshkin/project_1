package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.ExcursionTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcurtionTour extends ExcursionTour {

    private List<String> exhibitions;
    {
        exhibitions = new ArrayList<>();
    }

    public ExcurtionTour(String place, TourType type, LocalDate date) {
        super(place, type, date);
    }

    public void addExhibition(String exhibition){
        exhibitions.add(exhibition);
    }

}
