package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Pretend repository implementation
 */
@Component //@Repository works as well.
public class FoodRepository {
    private Map<MealTime, List<FoodData>> foodDatabase = new EnumMap<>(MealTime.class);

    public List<FoodData> getFood(MealTime mealTime) {
        return foodDatabase.getOrDefault(mealTime, new ArrayList<>());
    }

    public String addFood(MealTime mealTime, FoodData food) {
        List<FoodData> list = new ArrayList<>();
        if (foodDatabase.containsKey(mealTime)) list = foodDatabase.get(mealTime);
        int size = list.size();
        list.add(food);
        foodDatabase.put(mealTime, list);

        //confirm
        if (foodDatabase.get(mealTime).size() == 0 || size + 1 != foodDatabase.get(mealTime).size()) return  "/Not Completed";
        else return "/Completed";
    }
}
