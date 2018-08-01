package ua.testing.project1.entity.tour;

import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.tour.AbstractExcursionTour;

import java.time.LocalDate;

public class CruiseTour extends AbstractExcursionTour {

    private String shipName;
    {
        shipName = "";
    }

    public CruiseTour(LocalDate date) {
        super(TourType.CRUISE, date);
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
