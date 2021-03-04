package com.udacity.jdnd.course1exercises.lesson2.exercise1;

/**
 * Maps to form object used to submit a new food.
 */
public class FoodForm {
    private String foodName;
    private Integer calories;
    private MealTime mealTime;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime;
    }
}
