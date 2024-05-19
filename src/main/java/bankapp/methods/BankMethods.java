package bankapp.methods;

import bankapp.entities.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Getter
public class BankMethods {

    @Autowired
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String url = "jdbc:postgresql://localhost:5678/bankdb?user=user&password=pass";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /* получение инфо о балансе по id */
    public static String getBalance(int id)  {
        Person person = new Person(1, 1.1);
        String personToJson = null;
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM bank_user WHERE id='" + id + "'"
            );
            while(resultSet.next()) {
                person.setId(resultSet.getInt(1));
                person.setBalance(resultSet.getDouble(2));
            }
            personToJson = objectMapper.writeValueAsString(person);
            connection.close();
            statement.close();
        } catch (SQLException | JsonProcessingException e) {
            log.error("Произошла ошибка : " + e);
        }
        return personToJson;
    }

    /* пополнение баланса по id + количество(money) */
    public static String putMoney(int id, double money) {
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "UPDATE bank_user SET balance=balance+'" + money + "' WHERE id='" + id + "';"
            );
            connection.close();
            statement.close();
        } catch (SQLException e) {
            log.error("Произошла ошибка : " + e);
        }
        return "+";
    }

    /* снятие средств с баланса по id + количество(money) */
    public static String takeMoney(int id, double money) {
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "UPDATE bank_user SET balance=balance-'" + money + "' WHERE id='" + id + "';"
            );
            connection.close();
            statement.close();
        } catch (SQLException e) {
            log.error("Произошла ошибка : " + e);
        }
        return "-";
    }
}