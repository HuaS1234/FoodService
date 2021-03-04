package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Food Service that performs business logic operations regarding food
 */
@Service
public class FoodService {
    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    //check if food exists
    public Boolean isFoodAvailableAtMealTime(String foodName, MealTime mealTime) {
        return foodRepository.getFood(mealTime).stream()
                .filter(food -> food.getName().equals(foodName))
                .findFirst()
                .isPresent();
    }

    //add to repository
    public String addFood(String foodName, Integer calories, MealTime mealTime) {
        String log = foodRepository.addFood(mealTime, new FoodData(foodName, calories));
        return "/Adding to the repository..." + log;
    }

    //get
    public List<FoodData> getFoodRepository(MealTime mealTime) {
        return foodRepository.getFood(mealTime);
    }

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
}
