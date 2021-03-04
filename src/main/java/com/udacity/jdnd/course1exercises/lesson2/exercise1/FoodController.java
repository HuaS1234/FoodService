package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for receiving requests.
 */
@RestController //combination of Controller + ResponseBody //Controller: receiving requests
@RequestMapping("/food")
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    public String getHomePage(FoodForm foodForm, Model model) {
        foodService.addFood(foodForm.getFoodName(), foodForm.getCalories(), foodForm.getMealTime());
        return "foodAdded";
    }

    public Boolean isFoodAvailableAtMealTime(@RequestBody String foodName) {
        MealTime mealTime = MealTime.BREAKFAST;
        return foodService.isFoodAvailableAtMealTime(foodName, mealTime);
    }
    @GetMapping
    public List<FoodData> getFoodRepository(@RequestBody int index) {
        try{
            return foodService.getFoodRepository(MealTime.values()[index]);
        }
        catch(Exception e) {
            return new ArrayList<>();
        }
    }
    @PostMapping
    public String addFood(@RequestBody FoodForm foodForm) {
        String log = foodService.addFood(foodForm.getFoodName(), foodForm.getCalories(), foodForm.getMealTime());
        return foodForm.getFoodName() + log;
    }

}
