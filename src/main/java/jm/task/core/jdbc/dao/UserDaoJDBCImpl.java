package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connect = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement state = connect.createStatement()) {
            state.executeUpdate("CREATE TABLE IF NOT EXISTS test.users" +
                    "(id mediumint not null auto_increment," +
                    " name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))");
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement state = connect.createStatement()) {
            state.executeUpdate("Drop table if exists mybasetest.users");
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepareState = connect.prepareStatement(
                "INSERT INTO test.users(name, lastname, age) VALUES(?,?,?)")) {
            prepareState.setString(1, name);
            prepareState.setString(2, lastName);
            prepareState.setByte(3, age);
            prepareState.executeUpdate();
            System.out.println("User с именем – " + name + " успешно добавлен в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement state = connect.createStatement()) {
            state.executeUpdate("DELETE FROM test.users where id");
            System.out.println("User успешно удален из таблицы");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement state = connect.createStatement()) {
            ResultSet resultSet = state.executeQuery("SELECT id, name, lastName, age from test.users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement state = connect.createStatement()) {
            state.executeUpdate("DELETE FROM test.users");
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не получилось очистить таблицу");
        }
    }
}

