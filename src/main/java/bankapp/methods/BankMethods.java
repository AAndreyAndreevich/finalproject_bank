package bankapp.methods;

import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class BankMethods {

    private static ConnectionToDataBase connectionToDataBase;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /* получение баланса по id */
    public static String getBalance(int id) {
        return ConnectionToDataBase.requestForDB(
                "SELECT * FROM bank_user WHERE id='" + id + "';"
        );
    }

    /* пополнение баланса по id с количеством money */
    public static String putMoney(int id, double money) {
        return ConnectionToDataBase.requestForDB(
                "UPDATE bank_user SET balance=balance+'" + money + "' WHERE id='" + id + "';"
        );
    }

    /* снятие средств по id с количеством money */
    public static String getMoney(int id, double money) {
        return ConnectionToDataBase.requestForDB(
                "UPDATE bank_user SET balance=balance-'" + money + "' WHERE id='" + id + "';"
        );
    }
}