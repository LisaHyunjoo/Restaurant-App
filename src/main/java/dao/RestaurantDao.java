package dao;

import domain.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void addRestaurant(Restaurant restaurant);
    Restaurant retrieveRestaurantByName(String restaurants_name);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurant_id);
    Restaurant getRestauranttById(int restaurant_id);
    List<Restaurant> getAllRestaurant();
}
