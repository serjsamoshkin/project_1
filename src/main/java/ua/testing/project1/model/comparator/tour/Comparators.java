package ua.testing.project1.model.comparator.tour;

import ua.testing.project1.model.entity.Tour;
import ua.testing.project1.model.tourTypes.TourType;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Static class. Contains {@code Comparators} for {@code Tour} objects.
 */
public class Comparators {

    public static Comparator<Tour> getPlaceComparator(){
        class Comp implements Comparator<Tour> {
            @Override
            public int compare(Tour o1, Tour o2) {
                String place1 = o1.getPlace();
                String place2 = o2.getPlace();

                return place1.compareTo(place2);
            }

            @Override
            public Comparator<Tour> reversed() {
                class CompRev implements Comparator<Tour> {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        String place1 = o1.getPlace();
                        String place2 = o2.getPlace();

                        return place2.compareTo(place1);
                    }

                }
                return new CompRev();
            }
        }
        return new Comp();
    }

    public static Comparator<Tour> getDateComparator(){
        class Comp implements Comparator<Tour> {
            @Override
            public int compare(Tour o1, Tour o2) {
                LocalDate date1 = o1.getDate();
                LocalDate date2 = o2.getDate();

                return date1.compareTo(date2);
            }

            @Override
            public Comparator<Tour> reversed() {
                class CompRev implements Comparator<Tour> {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        LocalDate date1 = o1.getDate();
                        LocalDate date2 = o2.getDate();

                        return date2.compareTo(date1);
                    }
                                    }
                return new CompRev();
            }
        }
        return new Comp();
    }

    public static Comparator<Tour> getTypeComparator(){
        class Comp implements Comparator<Tour> {
            @Override
            public int compare(Tour o1, Tour o2) {
                TourType type1 = o1.getType();
                TourType type2 = o2.getType();

                return type1.compareTo(type2);
            }

            @Override
            public Comparator<Tour> reversed() {
                class CompRev implements Comparator<Tour> {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        TourType type1 = o1.getType();
                        TourType type2 = o2.getType();

                        return type2.compareTo(type1);
                    }
                }
                return new CompRev();
            }
        }
        return new Comp();
    }

}
