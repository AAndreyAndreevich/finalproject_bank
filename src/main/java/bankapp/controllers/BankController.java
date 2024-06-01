package bankapp.controllers;

import bankapp.entity.BankOperation;
import bankapp.entity.Person;
import bankapp.service.BankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/getBalance")
    public Optional<Person> getBalance(@RequestParam int id) {
        log.info("Был запрошен баланс по id : " + id );
        return bankService.getBalance(id);
    }

    @PostMapping("/putMoney")
    public Object putMoney(@RequestParam int id, @RequestParam double money) {
        log.info("Баланс id : " + id + ", был пополнен на сумму : " + money);
        return bankService.putMoney(id, money);
    }

    @PostMapping("/takeMoney")
    public Object takeMoney(@RequestParam int id, @RequestParam double money) {
        log.info("С баланса id : " + id + ", было снято : " + money);
        return bankService.takeMoney(id, money);
    }

    @PostMapping("/transferMoney")
    public Object transferMoney(@RequestParam int idFrom, @RequestParam int idTo, @RequestParam double money) {
        log.info("Id : " + idFrom + " сделал перевод на id : " + idTo + " в количестве : " + money);
        return bankService.transferMoney(idFrom, idTo, money);
    }

    @GetMapping("/getOperationList")
    public List<BankOperation> getOperationList(@RequestParam int idPerson, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        return bankService.getOperationList(idPerson, startDate, endDate);
    }
}