import controller.AdminController;
import model.Admin;
import view.View;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hi there :)");
        // comment
        AdminController adminController = new AdminController();
        adminController.setAdmin(Admin.getAdmin("admin01", "adminPass", "Admin", "admin01@gmail.com","09121112233",LocalDate.of(2005,7,10)));
        View.getView().showMenu();
    }
}
