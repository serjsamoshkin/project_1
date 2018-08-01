package ua.testing.project1.model.tour;

import ua.testing.project1.model.tour.Tour;

/**
 * Contains TourType constants of {@code Tour} entity.
 * Used as keys in properties bundle. After adding new constants, you must add keys to the properties.
 * Used to send to JSP pages as attributes list/set.
 *
 * @see Tour
 */
public enum TourType {

    RECREATION,
    EXCURSION,
    HEALING,
    SHOPPING,
    CRUISE,
    ;
}
