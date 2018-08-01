package ua.testing.project1.db;

import ua.testing.project1.model.tour.Tour;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The class is used to emulate database operations
 */
public class ToursBase {

    private static Set<Tour> base;
    static {
        base = new HashSet<>();
    }

    public static void add(Tour tour){
        base.add(tour);
    }

    public static Set<Tour> getTours(){

        return Collections.unmodifiableSet(base);

    }

    public static Optional<Tour> getById(Long id){
        for (Tour tour :
                base) {
            if (tour.getId() == id){
                return Optional.of(tour);
            }
        }
        return Tour.TOUR_NOT_FOUND;
    }
}
