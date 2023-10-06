package service;

import connection.JDBConnection;
import dao.RestaurantDao;
import domain.Restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public void addRestaurant(Restaurant restaurant) {
        final String ADD_RESTAURANT_SQL = """
                INSERT INTO restaurants(restaurant_id,restaurants_name,restaurants_address,restaurants_rate,restaurants_phoneNumber)
                VALUES(?,?,?,?,?)
                """;
        try (Connection conn = JDBConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(ADD_RESTAURANT_SQL);
            preparedStatement.setInt(1, restaurant.getRestaurantID());
            preparedStatement.setString(2, restaurant.getRestaurantName());
            preparedStatement.setString(3, restaurant.getRestaurantAddress());
            preparedStatement.setInt(4, restaurant.getRestaurantRate());
            preparedStatement.setInt(5, restaurant.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

    }

    @Override
    public void deleteRestaurant(int restaurant_id) {

    }

    @Override
    public Restaurant getRestauranttById(int restaurant_id) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        final String GET_ALL_RESTAURANTS_SQL = "SELECT * FROM restaurants";
        List<Restaurant> restaurants = new ArrayList<>();
        try (Connection connection = JDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESTAURANTS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Restaurant restaurant = extractRestaurantFromResultSet(resultSet);
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public static boolean restaurantExists(int restaurantId) {
        String query = "SELECT COUNT(*) FROM restaurants WHERE restaurant_id = ?";

        try (Connection connection = JDBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, restaurantId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception as needed in your application
        }

        return false;
    }


    public Restaurant extractRestaurantFromResultSet(ResultSet resultSet) throws SQLException {
        int restaurantId = resultSet.getInt("restaurant_id");
        String restaurantName = resultSet.getString("restaurants_name");
        String restaurantAddress = resultSet.getString("restaurants_address");
        int restaurantRate = resultSet.getInt("restaurants_rate");
        int restaurantPhoneNumber = resultSet.getInt("restaurants_phoneNumber");

//        return new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantRate, restaurantPhoneNumber);
        Restaurant r = new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantRate, restaurantPhoneNumber);
        return r;
    }


    @Override
    public Restaurant retrieveRestaurantByName(String rName) {
//        final String GET_RESTAURANT_BY_NAME_SQL = "SELECT * FROM restaurants WHERE restaurants_name = ?";
//        try (Connection connection = JDBConnection.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_NAME_SQL);
//
//            preparedStatement.setString(1, rName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//            while (resultSet.next()) {
//                int restaurantId = resultSet.getInt("restaurant_id");
//                String restaurantName = resultSet.getString("restaurants_name");
//                String restaurantAddress = resultSet.getString("restaurants_address");
//                int restaurantRate = resultSet.getInt("restaurants_rate");
//                int restaurantPhoneNumber = resultSet.getInt("restaurants_phoneNumber");
//                Restaurant r = new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantRate, restaurantPhoneNumber);
//                return r;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
        final String GET_RESTAURANT_BY_NAME_SQL = "SELECT * FROM restaurants WHERE restaurants_name = ?";

        try(Connection connection = JDBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_BY_NAME_SQL)){
            preparedStatement.setString(1, rName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return extractRestaurantFromResultSet(resultSet);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
