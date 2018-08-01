package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.ExcursionTour;

import java.time.LocalDate;

public class CruiseTour extends ExcursionTour {

    private String shipName;
    {
        shipName = "";
    }

    public CruiseTour(String place, TourType type, LocalDate date) {
        super(place, type, date);
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
