package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.AbstractExcursionTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcursionTour extends AbstractExcursionTour {

    private List<String> exhibitions;
    {
        exhibitions = new ArrayList<>();
    }

    public ExcursionTour(LocalDate date) {
        super(TourType.EXCURSION, date);
    }

    public void addExhibition(String exhibition){
        exhibitions.add(exhibition);
    }

}
