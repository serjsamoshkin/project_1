package ua.testing.project1.db.util;

import ua.testing.project1.db.ToursBase;
import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Used to set demo data to the DB emulator
 */
public class DbInit {

    public static void initTours(){

        String[] places = {"Prague", "Paris", "Barcelona", "Turkey", "Greece"};

        ArrayList<LocalDate> dates = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            dates.add(LocalDate.of(2018, 8, i));
        }

        for (String place:
             places) {
            for (LocalDate date:
                 dates) {
                for (TourType type:
                        TourType.values()) {
                    Tour tour = Tour.tourOf(type, date);
                    tour.addPlace(place);
                    ToursBase.add(tour);
                }
            }
        }
    }
}
