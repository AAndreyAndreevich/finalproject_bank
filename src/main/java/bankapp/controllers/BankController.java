package bankapp.controllers;

import bankapp.methods.BankMethods;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BankController {

    private static BankMethods bankMethods;


    @GetMapping("/getBalance/{id:\\d++}")
    public static String getBalance(@PathVariable int id) {
        log.info("Запрошен баланс по id : " + id);
        return BankMethods.getBalance(id);
    }

    @PostMapping("/putMoney/{id:\\d++}/{money:\\d++}")
    public static String putMoney(@PathVariable int id, @PathVariable double money) {
        log.info("Пополнение баланса по id : " + id + ", на сумму : " + money);
        return BankMethods.putMoney(id, money);
    }

    @PostMapping("/takeMoney/{id:\\d++}/{money:\\d++}")
    public static String takeMoney(@PathVariable int id, @PathVariable double money) {
        log.info("Снятие средств по id : " + id + ", на сумму : " + money);
        return BankMethods.takeMoney(id, money);
    }
}