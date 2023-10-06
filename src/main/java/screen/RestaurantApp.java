package screen;

import dao.RestaurantDao;
import domain.Restaurant;
import service.RestaurantDaoImpl;

import java.util.InputMismatchException;
import java.util.List;

public class RestaurantApp {
    private static final RestaurantDao restaurantDao = new RestaurantDaoImpl();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice;
            try {
                choice = UserInput.getIntInput();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addRestaurant();
                    break;
                case 2:
                    displayAllRestaurants();
                    break;
                case 3:
                    getRestaurantByName();
                    break;
//                case 4:
//                    updateRestaurant();
//                    break;
                case 5:
//                    removeRestaurant();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private static void displayMenu() {
        System.out.println("\nWelcome to favorite restaurant application");
        System.out.println("1. Add restaurant");
        System.out.println("2. Display All restaurants");
        System.out.println("3. Get Restaurant ");
        System.out.println("4. Update restaurant ");
        System.out.println("5. Remove restaurant ");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addRestaurant() {

        System.out.print("Enter restaurant ID: ");
        int restaurantId = UserInput.getIntInput();// Consume newline character
//         Check if the restaurant ID already exists
        if (RestaurantDaoImpl.restaurantExists(restaurantId)) {
            System.out.println("Restaurant with ID " + restaurantId + " already exists.");
            return;
        }
        UserInput.getStringInput();
        System.out.print("Enter restaurant name: ");
        String restaurantName = UserInput.getStringInput();

        System.out.print("Enter restaurant address: ");
        String restaurantAddress = UserInput.getStringInput();

        System.out.print("Enter restaurant rate: ");
        int restaurantRate = UserInput.getIntInput();

        System.out.print("Enter restaurant phone number: ");
        int restaurantPhoneNumber = UserInput.getIntInput();


        Restaurant restaurant = new Restaurant(restaurantId, restaurantName, restaurantAddress, restaurantRate, restaurantPhoneNumber);
        restaurantDao.addRestaurant(restaurant);
        System.out.println("Restaurant added successfully.");
    }

    private static void displayAllRestaurants() {
        List<Restaurant> restaurants = restaurantDao.getAllRestaurant();
        System.out.println("\nAll Restaurants:");
        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }
    }

    private static void getRestaurantByName() {
        System.out.print("Enter restaurant Name: ");
        String restaurantName = UserInput.getStringInput();
        Restaurant r = restaurantDao.retrieveRestaurantByName(restaurantName);
        System.out.println(r);
    }

}