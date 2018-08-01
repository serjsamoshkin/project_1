package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.SimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingTour extends SimpleTour {

    private List<String> brands;
    {
        brands = new ArrayList<>();
    }

    public ShoppingTour(TourType type, LocalDate date) {
        super(type, date);
    }
}
