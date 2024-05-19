package bankapp.methods;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
@Getter
public class ConnectionToDataBase {

    private static String url = "jdbc:postgresql://localhost:5678/bankdb?user=user&password=pass";

    /* метод для подключения к БД, принимает текст запроса */
    public static String requestForDB(String theRequest) {
        String value1 = null;
        String value2 = null;
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(theRequest);
            while(resultSet.next()) {
                value1 = resultSet.getString(1);
                value2 = resultSet.getString(2);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            log.error("Произошла ошибка : " + e);
        }
        return value1 + " : " + value2;
    }
}