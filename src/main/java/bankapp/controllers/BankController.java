package bankapp.controllers;

import bankapp.methods.BankMethods;
import bankapp.methods.ConnectionToDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankController {

    private static ConnectionToDataBase connectionToDataBase;
    private static BankMethods bankMethods;

    @GetMapping("/getBalance/{id:\\d++}")
    public static String getBalance(@PathVariable int id) {
        log.info("Запрошен баланс по id : " + id);
        return BankMethods.getBalance(id);
    }

    @PostMapping("/putMoney/{id:\\d++}/{money:\\d++}")
    public static String putMoney(@PathVariable int id, @PathVariable double money) {
        log.info("Пополнение счета по id : " + id + ", на сумму : " + money);
        return BankMethods.putMoney(id, money);
    }

    @PostMapping("/getMoney/{id:\\d++}/{money:\\d++}")
    public static String getMoney(@PathVariable int id, @PathVariable double money) {
        log.info("Снятие со счета по id : " + id + ", на сумму : " + money);
        return BankMethods.getMoney(id, money);
    }
}