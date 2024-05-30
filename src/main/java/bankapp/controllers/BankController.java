package bankapp.controllers;

import bankapp.entity.Person;
import bankapp.service.BankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/getBalance/{id:\\d++}")
    public Optional<Person> getBalance(@PathVariable int id) {
        log.info("Был запрошен баланс по id : " + id );
        return bankService.getBalance(id);
    }

    @PostMapping("/putMoney/{id:\\d++}/{money:\\d++}")
    public Object putMoney(@PathVariable int id, @PathVariable double money) {
        log.info("Баланс id : " + id + ", был пополнен на сумму : " + money);
        return bankService.putMoney(id, money);
    }

    @PostMapping("/takeMoney/{id:\\d++}/{money:\\d++}")
    public Object takeMoney(@PathVariable int id, @PathVariable double money) {
        log.info("С баланса id : " + id + ", было снято : " + money);
        return bankService.takeMoney(id, money);
    }

    @GetMapping("/getOperationList/{id:\\d++}/{startDate}/{endDate}")
    public String getOperationList(@PathVariable int id, @PathVariable Date startDate, @PathVariable Date endDate) {
        return bankService.getOperationList(id, startDate, endDate);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }
}