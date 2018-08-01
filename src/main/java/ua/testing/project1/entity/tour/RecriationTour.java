package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.AbstractSimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecriationTour extends AbstractSimpleTour {

    private List<String> spaProcedures;
    {
        spaProcedures = new ArrayList<>();
    }

    public RecriationTour(LocalDate date) {
        super(TourType.RECREATION, date);
    }

    public void addSpaProcedure(String spaProcedure){
        spaProcedures.add(spaProcedure);
    }

}
