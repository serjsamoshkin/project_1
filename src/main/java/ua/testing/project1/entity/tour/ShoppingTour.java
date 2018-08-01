package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.AbstractSimpleTour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingTour extends AbstractSimpleTour {

    private List<String> brands;
    {
        brands = new ArrayList<>();
    }

    public ShoppingTour(LocalDate date) {
        super(TourType.SHOPPING, date);
    }
}
