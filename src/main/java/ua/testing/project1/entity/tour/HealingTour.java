package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.SimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealingTour extends SimpleTour {

    private List<String> treatments;
    {
        treatments = new ArrayList<>();
    }

    public HealingTour(TourType type, LocalDate date) {
        super(type, date);
    }


    public void addTreatments(String treatment){
        treatments.add(treatment);
    }

}
