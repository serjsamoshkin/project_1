package ua.testing.project1.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.util.stream.Collectors;

/**
 * Class handles MainServlet HTTP calls with urls "/tour", "/tour_open", "/tour_buy".
 * Contains logic for processing all Tour and Voucher operations in JSP (view) and business logic (controller)
 */
public class TourController {

    /**
     * Displays the list of active tours (Tours in database emulation).
     * Handles service operation that was selected by user on the page:
     *  - type_opt - Contains TourType string representation.
     *      Saves users choice to Map, serializes it to json and store in json_filter parameter.
     *      impl: filters the list of Tours with selected users TourTypes
     *
     *  - sort_opt - Contains sorting option. It is the string representation of inner Sort Enum.
     *      With this operation is processed json_filter parameter.
     *      It can be stored in the type_opt operation handler or empty.
     *      impl: Sort Tours list with {@link TourComparator}. Restore TourType selection from json_filter.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showTorsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Set<Tour> tours = ToursBase.getTours();
        Sort sort = Sort.values()[0];


        Map<TourType, Boolean> typeMap = new TreeMap<>();
        for (TourType type : TourType.values()) {
            typeMap.put(type, false);
        }

        Map<Sort, Boolean> sortMap = new HashMap<>();
        for (Sort s : Sort.values()) {
            sortMap.put(s, false);
        }

        if (request.getParameterMap().containsKey("sort_opt")) {
            String sort_opt = request.getParameter("sort_opt");
            if (!sort_opt.isEmpty()) {
                try {
                    sort = Sort.valueOf(sort_opt);
                    sortMap.put(sort, true);
                }catch (IllegalArgumentException e){
                    // TODO залогировать
                    // Вероятно играется пользователь
                    request.getServletContext().getRequestDispatcher("/").forward(request, response);
                }
            }
            String json_filter= request.getParameter("json_filter");
            if (json_filter != null && !json_filter.isEmpty()) {
                Map<TourType, Object> retMap = new Gson().fromJson(request.getParameter("json_filter"),
                        new TypeToken<HashMap<TourType, Boolean>>() {}.getType()
                );
                if (retMap.containsKey(null)){
                    request.setAttribute("json_filter", null);
                }else {
                    retMap.keySet().forEach(v -> typeMap.put(v, true));
                }
            }

        } else if (request.getParameterMap().containsKey("type_opt")) {
            String[] type_opt = request.getParameterMap().get("type_opt");
            if (type_opt != null) {
                for (String aType_opt : type_opt) {
                    try {
                        typeMap.put(TourType.valueOf(aType_opt), true);
                    }catch (IllegalArgumentException e){
                        // TODO залогировать
                        // Вероятно играется пользователь
                        request.setAttribute("type_opt", null);
                        typeMap.replaceAll((k, v) -> false);
                    }
                }
            }
        }

        HashMap<TourType, Boolean> innerTypeMap = new HashMap<>(typeMap);
        innerTypeMap.values().removeAll(Collections.singleton(false));

        if (!innerTypeMap.isEmpty()) {
            tours = tours.stream()
                    .filter(t -> innerTypeMap.keySet().contains(t.getType()))
                    .collect(Collectors.toSet());

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            request.setAttribute("json_filter", gson.toJson(innerTypeMap));
        }

        List<Tour> tourList = new ArrayList<>(tours);

        Comparator<Tour> comp = TourComparator.getComparator(sort);
        tourList.sort(comp);

        request.setAttribute("type_map", typeMap);
        request.setAttribute("tours_list", tourList);

        sortMap.put(sort, true);
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
        long id = Long.valueOf(strId);

        Set<Meal> mealSet = new HashSet<>(Arrays.asList(Meal.values()));
        Set<Transport> transportSet = new HashSet<>(Arrays.asList(Transport.values()));

        Optional<Tour> tour = ToursBase.getById(id);
        if (!tour.isPresent()){
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }

        request.setAttribute("tour", tour.get());
        request.setAttribute("meal_set", mealSet);
        request.setAttribute("transport_set", transportSet);

        request.setAttribute("tour_id", id);

        request.getServletContext().getRequestDispatcher("/jsp/tour_edit.jsp").forward(request, response);

    }

    /**
     * Processes the purchase of a voucher with the selected user parameters
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
        String durationStr = request.getParameter("duration");
        int duration = Integer.valueOf(durationStr);

        Meal meal;
        Transport transport;

        if (meal_opt == null){
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }else {
            meal = Meal.valueOf(meal_opt);
        }

        if (transport_opt == null){
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }else {
            transport = Transport.valueOf(transport_opt);
        }

        long id = Long.valueOf(strId);
        Optional<Tour> tour = ToursBase.getById(id);
        if (!tour.isPresent()){
            // TODO залогировать
            request.getServletContext().getRequestDispatcher("/").forward(request, response);
            return;
        }

        Voucher voucher = new Voucher(tour.get(), meal, transport, duration);

        request.setAttribute("voucher", voucher);

        request.getServletContext().getRequestDispatcher("/jsp/tour_buy_confirm.jsp").forward(request, response);

    }

    /**
     * Inner Enum is stored sorting parameters for displaying to users and process their choice.
     */
    public enum Sort {
        DATE,
        PLACE,
        TYPE,;
    }

}
