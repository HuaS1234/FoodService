package com.udacity.jdnd.course1exercises.lesson2.exercise1;

import org.springframework.context.MessageSource;
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
    private MessageService messageService;

    public FoodController(FoodService foodService, String message, MessageService messageService) {
        this.message = message;
        this.foodService = foodService;
        this.messageService = messageService;
    }

    public String getHomePage(FoodForm foodForm, Model model) {
        foodService.addFood(foodForm.getFoodName(), foodForm.getCalories(), foodForm.getMealTime());
        return "foodAdded";
    }

    public Boolean isFoodAvailableAtMealTime(@RequestBody String foodName) {
        MealTime mealTime = MealTime.BREAKFAST;
        return foodService.isFoodAvailableAtMealTime(foodName, mealTime);
    }
    @GetMapping("/home")   //need @RequestMapping or @GetMapping to response to the HTTP request.
    public String getHomePage(@ModelAttribute("newMessage") MessageForm newMessage, Model m) {
//        return message; //return "hello there" which was defined as bean at the main class.
//        m.addAttribute("welcomeMessage", Instant.now().toString()); //current timestamp
//        m.addAttribute("greetings", new String[]{"hi", "hello", "bye"});

        m.addAttribute("greetings", this.messageService.getMessages());
        return "home";

    }
    @PostMapping("/home")
    public String postHomePage(@ModelAttribute("newMessage") MessageForm newMessage, Model m) {
        String str = newMessage.getText();
        messageService.addMessage(str);
        m.addAttribute("greetings", this.messageService.getMessages());
        return "home";
    }

    @RequestMapping("/home/simplehome")  //need @RequestMapping or @GetMapping to response to the HTTP request.
    public String getSimpleHomePage(Model m) {
        return "home";
    }

//    @GetMapping
//    public List<FoodData> getFoodRepository(@RequestBody int index) {
//        try{
//            return foodService.getFoodRepository(MealTime.values()[index]);
//        }
//        catch(Exception e) {
//            return new ArrayList<>();
//        }
//    }
    @PostMapping
    public String addFood(@RequestBody FoodForm foodForm) {
        String log = foodService.addFood(foodForm.getFoodName(), foodForm.getCalories(), foodForm.getMealTime());
        return foodForm.getFoodName() + log;
    }
}
