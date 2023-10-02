package dao;

import domain.Restaurant;

import java.util.List;

public interface restaurantDao {
    void addRestaurant();
    String retrieveRestaurantByName(String name);
    void updateRestaurant(String name);
    void deleteRestaurant(String name);
    List<Restaurant> getAllRestaurant();
}
