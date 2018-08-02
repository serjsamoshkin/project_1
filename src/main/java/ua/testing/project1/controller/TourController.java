package ua.testing.project1.controller;

import ua.testing.project1.db.ToursBase;
import ua.testing.project1.entity.voucher.Voucher;
import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.model.tour.TourType;
import ua.testing.project1.model.voucher.Meal;
import ua.testing.project1.model.voucher.Transport;
import ua.testing.project1.service.comparators.TourComparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Class handles MainServlet HTTP calls with urls "/tour", "/tour_open", "/tour_buy".
 * Contains logic for processing all Tour and Voucher operations in JSP (view) and business logic (controller)
 */
public class TourController {

    /**
     * Displays the list of active tours (Tours in database emulation).
     * Handles service operation that was selected by user on the page:
     * - type_opt - Contains TourType string representation.
     * Saves users choice to Map, serializes it to json and store in json_filter parameter.
     * impl: filters the list of Tours with selected users TourTypes
     * <p>
     * - sort_opt - Contains sorting option. It is the string representation of inner Sort Enum.
     * With this operation is processed json_filter parameter.
     * It can be stored in the type_opt operation handler or empty.
     * impl: Sort Tours list with {@link TourComparator}. Restore TourType selection from json_filter.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showTorsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<Tour> tours = ToursBase.getTours();

        Map<TourType, Boolean> typeMap = fillMap(new TreeMap<>(), TourType.values(), false);
        if (request.getParameterMap().containsKey("type_opt")) {
            List<String> type_opt = Arrays.asList(request.getParameterValues("type_opt"));
            if (!TourType.containAll(type_opt)) {
                response.sendRedirect("/");
                return;
            }

            type_opt.forEach(t -> {
                tours.removeIf(tour -> tour.getType().equals(TourType.valueOf(t)));
                typeMap.put(TourType.valueOf(t), true);
            });
        }

        Sort sortConst = Sort.values()[0]; // def val
        List<Tour> tourList = new ArrayList<>(tours);
        if (request.getParameterMap().containsKey("sort_opt")) {
            String sort_opt = request.getParameter("sort_opt");
            if (!Sort.hasValue(sort_opt)) {
                response.sendRedirect("/");
                return;
            }

            sortConst = Sort.valueOf(sort_opt);
            Comparator<Tour> comp = TourComparator.getComparator(sortConst);
            tourList.sort(comp);
        }

        request.setAttribute("type_map", typeMap);
        request.setAttribute("tours_list", tourList);

        Map<Sort, Boolean> sortMap = fillMap(new HashMap<>(), Sort.values(), false);
        sortMap.put(sortConst, true);
        request.setAttribute("sort_map", sortMap);

        request.getServletContext().getRequestDispatcher("/jsp/tours_list.jsp").forward(request, response);

    }

    /**
     * Displays page with Tour bean to add users extra options to create {@code Voucher}: to byu voucher or to reserved it.
     * Sets the Voucher's Meal and Transport enum constants to page as attributes for
     * creating <select></select> tags on page.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void openTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        long id;
        if (checkLongValue(strId)) {
            id = Long.valueOf(strId);
        } else {
            response.sendRedirect("/");
            return;
        }

        Set<Meal> mealSet = new HashSet<>(Arrays.asList(Meal.values()));
        Set<Transport> transportSet = new HashSet<>(Arrays.asList(Transport.values()));

        Optional<Tour> tour = ToursBase.getById(id);
        if (!tour.isPresent()) {
            // TODO залогировать
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("tour", tour.get());
        request.setAttribute("meal_set", mealSet);
        request.setAttribute("transport_set", transportSet);

        request.setAttribute("tour_id", id);

        request.getServletContext().getRequestDispatcher("/jsp/tour_edit.jsp").forward(request, response);

    }



    /**
     * Processes the purchase of a voucher with the selected user parameters.
     * It is necessary to send only as a POST request. Parameters are not checked in the method.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void byuTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        String meal_opt = request.getParameter("meal_opt");
        String transport_opt = request.getParameter("transport_opt");

        Meal meal;
        if (meal_opt == null) {
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        } else {
            meal = Meal.valueOf(meal_opt);
        }

        Transport transport;
        if (transport_opt == null) {
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        } else {
            transport = Transport.valueOf(transport_opt);
        }

        long id;
        if (checkLongValue(strId)) {
            id = Long.valueOf(strId);
        } else {
            response.sendRedirect("/");
            return;
        }
        Optional<Tour> tour = ToursBase.getById(id);
        if (!tour.isPresent()) {
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }

        String durationStr = request.getParameter("duration");
        int duration;
        if (checkLongValue(durationStr)) {
            duration = Integer.valueOf(durationStr);
        }else {
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }
        Voucher voucher = new Voucher(tour.get(), meal, transport, duration);

        request.setAttribute("voucher", voucher);

        request.getServletContext().getRequestDispatcher("/jsp/tour_buy_confirm.jsp").forward(request, response);

    }

    private boolean checkLongValue(String str) {
        if (!str.matches("^[1-9]+$")){
            return false;
        }
        try {
            Long.valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean checkIntValue(String str) {
        if (!str.matches("^[1-9]+$")){
            return false;
        }
        try {
            Integer.valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private <K, V> Map<K, V> fillMap(Map<K, V> map, K[] keys, V defVal) {
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], defVal);
        }
        return map;
    }

    /**
     * Inner Enum is stored sorting parameters for displaying to users and process their choice.
     */
    public enum Sort {
        DATE,
        PLACE,
        TYPE,;
        private static Map<String, Sort> keys;

        static {
            keys = new HashMap<>();
            for (int i = 0; i < values().length; i++) {
                keys.put(values()[i].toString(), values()[i]);
            }
        }

        static public boolean hasValue(String key) {
            return keys.keySet().contains(key);
        }

        static public boolean containAll(Collection<Sort> col) {
            return keys.values().containsAll(col);
        }
    }

}
