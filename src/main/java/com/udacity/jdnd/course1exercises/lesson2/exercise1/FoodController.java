package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for receiving requests.
 */
//@ResponseBody //??ResponseBody doesn't support templates?????????
@Controller  //combination of Controller + ResponseBody //Controller: receiving requests
@RequestMapping//("/food")
public class FoodController {

    private FoodService foodService;
    private String message;

    public FoodController(FoodService foodService, String message) {
        this.message = message;
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
    @RequestMapping("/home")  //need @RequestMapping or @GetMapping to response to the HTTP request.
    public String getHomePage(Model m) {
        //return message; //return "hello there" which was defined as bean at the main class.
        m.addAttribute("welcomeMessage", Instant.now().toString()); //current timestamp
        m.addAttribute("welcomeMessage", "test1"); //replace welcomeMessage
        m.addAttribute("greetings", new String[]{"hi", "hello", "bye"});
        m.addAttribute("msgs", "testn");
        return "home";
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
