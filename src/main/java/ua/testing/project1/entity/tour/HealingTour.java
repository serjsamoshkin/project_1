package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.AbstractSimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HealingTour extends AbstractSimpleTour {

    private List<String> treatments;
    {
        treatments = new ArrayList<>();
    }

    public HealingTour(LocalDate date) {
        super(TourType.HEALING, date);
    }


    public void addTreatments(String treatment){
        treatments.add(treatment);
    }

}
