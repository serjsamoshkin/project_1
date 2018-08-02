package ua.testing.project1.model.tour;

import ua.testing.project1.controller.TourController;
import ua.testing.project1.model.tour.Tour;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    private final static Map<String, TourType> keys;
    static {
        keys = new HashMap<>();
        for (int i = 0; i < values().length; i++) {
            keys.put(values()[i].toString(), values()[i]);
        }
    }
    static public boolean hasValue(String key){
        return keys.keySet().contains(key);
    }
    static public boolean containAll(Collection<String> col){
        return keys.keySet().containsAll(col);
    }
}
