package ua.testing.project1.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.testing.project1.entity.voucher.Voucher;
import ua.testing.project1.model.tour.Tour;
import ua.testing.project1.entity.voucher.voucherTypes.MealType;
import ua.testing.project1.entity.voucher.voucherTypes.TransportType;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;

public class ModelTest {

    static Map<Long, Integer> tourIncrVals = new ConcurrentHashMap<>();

    @Test
    public void testModel() {

        LocalDate currentDate = LocalDate.now();

        // Hash and equals test
        Tour tour1 = new Tour("test", Tour.TourType.values()[0], currentDate);
        Tour tour2 = new Tour("test", Tour.TourType.values()[0], currentDate);
        Tour tour3 = new Tour("test", Tour.TourType.values()[0], currentDate);

        Tour tour4 = new Tour("test1", Tour.TourType.values()[0], currentDate);
        Tour tour5 = new Tour("test", Tour.TourType.values()[0],
                LocalDate.of(1, 1, 1));

        Assert.assertTrue("Tour equals error", tour1.equals(tour2));
        Assert.assertTrue("Tour equals error", tour1.equals(tour3));
        Assert.assertTrue("Tour equals error", tour2.equals(tour3));

        Assert.assertTrue("Tour equals error", tour2.equals(tour1));

        Assert.assertFalse("Tour equals error", tour1.equals(tour4));
        Assert.assertFalse("Tour equals error", tour4.equals(tour1));

        Assert.assertFalse("Tour equals error", tour1.equals(tour5));
        Assert.assertFalse("Tour equals error", tour5.equals(tour1));

        Assert.assertFalse("Tour equals error", tour2.equals(tour4));

        Set<Tour> tourHashSet = new HashSet<>();

        tourHashSet.add(tour1);
        tourHashSet.add(tour2);
        tourHashSet.add(tour3);

        Assert.assertTrue("Tour hash code error",
                tourHashSet.size() == 1);

        tourHashSet.add(tour4);
        tourHashSet.add(tour5);
        Assert.assertTrue("Tour hash code error",
                tourHashSet.size() == 3);


        // test of invariants
        Assert.assertNotNull("null value of TourType", tour1.getType());
        Assert.assertNotNull("null value of Date ", tour1.getDate());
        Assert.assertNotNull("null value of Place ", tour1.getPlace());

        ExecutorService exec = Executors.newCachedThreadPool();

        ExecutorService[] services = new ExecutorService[5];


        List<Callable<Object>> todo = new ArrayList<>(5);

        int loopVal = 1000000;

        todo.add(Executors.callable(new TourIncrementation(loopVal)));
        todo.add(Executors.callable(new TourIncrementation(loopVal)));
        todo.add(Executors.callable(new TourIncrementation(loopVal)));
        todo.add(Executors.callable(new TourIncrementation(loopVal)));
        todo.add(Executors.callable(new TourIncrementation(loopVal)));

        try {
            exec.invokeAll(todo);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }

        Long res = tourIncrVals.entrySet()
                .stream().filter(e -> e.getValue() > 1).count();

        Assert.assertTrue("There are errors in mechanism of generating 'id' value of Tour: several Tour obj with the same 'id'!", res == 0);

        Tour tour6 = new Tour("test", Tour.TourType.values()[0], currentDate);
        Assert.assertTrue("Errors in TourIncrementation mechanism",
                tour5.getId() + loopVal * todo.size() + 1 == tour6.getId());


        // Voucher invariants test

        Voucher voucher = new Voucher(tour6, MealType.values()[0], TransportType.values()[0], 10);

        Assert.assertTrue(voucher.getTour().equals(tour6));
        Assert.assertTrue(voucher.getMealType().equals(MealType.values()[0]));
        Assert.assertTrue(voucher.getTransportType().equals(TransportType.values()[0]));
        Assert.assertTrue(voucher.getDuration() == 10);
        Assert.assertTrue(voucher.getPlace().equals(tour6.getPlace()));
        Assert.assertTrue(voucher.getType().equals(tour6.getType()));
        Assert.assertTrue(voucher.getDate().equals(tour6.getDate()));
        Assert.assertTrue(voucher.getTourId() == tour6.getId());

        // In the application there is no service (model) logic to do other tests


    }

    class TourIncrementation implements Runnable {

        int loopVal;

        TourIncrementation(int loopVal) {
            this.loopVal = loopVal;
        }

        @Override
        public void run() {

            for (int i = 0; i < loopVal; i++) {
                Tour tour = new Tour("test", Tour.TourType.values()[0],
                        LocalDate.of(1, 1, 1));

                tourIncrVals.merge(tour.getId(), 1, this::sum);

            }
        }

        private int sum(int l, int r){
            return l + r;
        }
    }
}