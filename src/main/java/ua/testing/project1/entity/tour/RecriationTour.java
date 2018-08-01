package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.SimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecriationTour extends SimpleTour {

    private List<String> spaProcedures;
    {
        spaProcedures = new ArrayList<>();
    }

    public RecriationTour(TourType type, LocalDate date) {
        super(type, date);
    }

    public void addSpaProcedure(String spaProcedure){
        spaProcedures.add(spaProcedure);
    }

}
