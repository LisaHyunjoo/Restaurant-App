package dao;

import domain.Restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RestaurantDao {
    void addRestaurant(Restaurant restaurant);

    Restaurant extractRestaurantFromResultSet(ResultSet resultSet) throws SQLException;
    Restaurant retrieveRestaurantByName(String restaurants_name);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurant_id);
    Restaurant getRestaurantById(int restaurant_id);
    List<Restaurant> getAllRestaurant();
}
