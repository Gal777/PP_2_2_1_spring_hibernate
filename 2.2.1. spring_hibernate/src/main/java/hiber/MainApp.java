package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Ivan", "Ivanov", "Ivanov@mail.ru",
                               new Car("reno", 567)));
      userService.add(new User("Petr", "Petrov", "Petrov@mail.ru",
                               new Car("Mazda", 325)));
      userService.add(new User("Olga", "Sidorova", "Sidorova@mail.ru",
                               new Car("Lada", 777)));
      userService.add(new User("Elena", "Voronova", "Voronova@mail.ru",
                               new Car("UAZ", 555)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      User user = userService.findByUser("UAZ", 555);
      System.out.println(user);

      context.close();
   }
}
