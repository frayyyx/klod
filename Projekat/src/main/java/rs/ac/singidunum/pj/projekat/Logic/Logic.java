package rs.ac.singidunum.pj.projekat.Logic;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Logic {


    public double calculatePrice(Date arrival_at, Date departure_at, int beds, int meals) {

        long duration = departure_at.getTime() - arrival_at.getTime();

        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        if (diffInDays < 0) {
            return 0;
        }

        double priceBeds;
        double priceMeals = 700;

        if (beds == 1) {
            priceBeds = 2200;
        } else if (beds == 2) {
            priceBeds = 3300;
        } else if (beds == 3) {
            priceBeds = 4200;
        } else if (beds == 4) {
            priceBeds = 4900;
        } else if (beds == 5) {
            priceBeds = 5400;
        } else {
            priceBeds = 0;
        }

        return (diffInDays * (priceBeds + (meals * priceMeals)));

    }

}
