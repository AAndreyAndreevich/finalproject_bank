package bankapp.service;

import bankapp.entity.BankOperation;
import bankapp.entity.Person;
import bankapp.repository.BankOperationRepository;
import bankapp.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankService {

    private final PersonRepository personRepository;
    private final BankOperationRepository bankOperationRepository;

    public Optional<Person> getBalance(int id) {
        return personRepository.findById(id);
    }

    public Object putMoney(int id, double money) {
        double currentBalance = personRepository.findById(id).get().getBalance();
        currentBalance = currentBalance + money;
        personRepository.findById(id).get().setBalance(currentBalance);
        personRepository.flush();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(date);
        BankOperation bankOperation = new BankOperation(id, "пополнение", money, date);
        bankOperationRepository.save(bankOperation);
        return personRepository.findById(id);
    }

    public Object takeMoney(int id, double money) {
        double currentBalance = personRepository.findById(id).get().getBalance();
        if ((currentBalance - money) < 0) {
            return "Указанная сумма : " + money + " превышает баланс : " + currentBalance + ", укажите другое кол-во";
        } else {
            currentBalance = currentBalance - money;
            personRepository.findById(id).get().setBalance(currentBalance);
            personRepository.flush();
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.format(date);
            BankOperation bankOperation = new BankOperation(id, "снятие", money, date);
            bankOperationRepository.save(bankOperation);
            return personRepository.findById(id);
        }
    }

    public List<Object> getOperationList(int id, Date startDate, Date endDate) {
        //я не понимаю. ;)
        return null;
    }
}