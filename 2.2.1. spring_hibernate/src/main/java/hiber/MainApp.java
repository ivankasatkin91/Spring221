package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        //Creating Users and Ca
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user3@mail.ru");

        Car car1 = new Car("Peugeot", 407);
        Car car2 = new Car("BMW", 5);
        Car car3 = new Car("Ford", 150);
        Car car4 = new Car("Chevrolet", 1500);

        // Adding Cars to DB and returning cars list
        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("car_Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }

        //Assigning cars to users
        user1.setCar(car2);
        user2.setCar(car1);
        user3.setCar(car4);
        user4.setCar(car3);

        //Adding Users to DB and returning users list
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        //Testing user return method by cars model & series
        System.out.println(carService.getUserByCar(new Car("Ford", 150)));

        context.close();
    }
}
