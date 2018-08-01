package ua.testing.project1.service.comparators;

import ua.testing.project1.controller.TourController;
import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Static class. Contains {@code TourComparator} for {@code Tour} objects.
 */
public class TourComparator {

    public static Comparator<Tour> getComparator(TourController.Sort op){
        Comparator<Tour> comp;
        switch (op) {
            case DATE:
                comp = TourComparator.getDateComparator();
                break;
            case TYPE:
                comp = TourComparator.getTypeComparator();
                break;
            case PLACE:
                comp = TourComparator.getPlaceComparator();
                break;
            default:
                // TODO сюда попадать не должны, залогировать.
                comp = TourComparator.getDateComparator();
                break;
        }
        return comp;
    }


    private static Comparator<Tour> getDateComparator() {
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

    private static Comparator<Tour> getPlaceComparator() {
        class Comp implements Comparator<Tour> {
            @Override
            public int compare(Tour o1, Tour o2) {
                String pl1 = o1.getPlaceRepresentation();
                String pl2 = o2.getPlaceRepresentation();

                return pl1.compareTo(pl2);
            }

            @Override
            public Comparator<Tour> reversed() {
                class CompRev implements Comparator<Tour> {
                    @Override
                    public int compare(Tour o1, Tour o2) {
                        String pl1 = o1.getPlaceRepresentation();
                        String pl2 = o2.getPlaceRepresentation();

                        return pl2.compareTo(pl1);
                    }
                }
                return new CompRev();
            }
        }
        return new Comp();
    }

    private static Comparator<Tour> getTypeComparator() {
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
