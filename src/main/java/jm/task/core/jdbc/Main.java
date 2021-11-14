package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.ArrayList;
import jm.task.core.jdbc.util.Util;



public class Main {
    private static final UserService userService = new UserServiceImpl();

    private static final User user1 = new User("Роман", "Зуев", (byte) 35);
    private static final User user2 = new User("Никита", "Власов", (byte) 27);
    private static final User user3 = new User("Елена", "Демидова", (byte) 22);
    private static final User user4 = new User("Александр", "Катаев", (byte) 54);
    //private static final User user5 = new User("Владимир", "Гольцер", (byte) 23);
    //private static final User user6 = new User("Ксения", "Степаненко", (byte) 18);
    //private static final User user7 = new User("Михаил", "Шмидт", (byte) 21);

    public static ArrayList<User> user = new ArrayList<>();

    public static void main(String[] args) throws Exception{

        user.add(user1);
        user.add(user2);
        user.add(user3);
        user.add(user4);
        //user.add(user5);
        //user.add(user6);
        //user.add(user7);

        userService.createUsersTable();

        for(int i=0; i<user.size(); i++){
            userService.saveUser(user.get(i).getName(), user.get(i).getLastName(), user.get(i).getAge());
        }

        userService.removeUserById(4);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnection();
    }
}









