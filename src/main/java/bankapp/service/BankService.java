package bankapp.service;

import bankapp.entity.Person;
import bankapp.repository.BankOperationRepository;
import bankapp.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return personRepository.findById(id);
    }

    public Object takeMoney(int id, double money) {
        double currentBalance = personRepository.findById(id).get().getBalance();
        if ((currentBalance - money) < 0) {
            return "Указанная сумма : " + money + " превышает баланс : " + currentBalance + ", укажите другое кол-во";
        }
        currentBalance = currentBalance - money;
        personRepository.findById(id).get().setBalance(currentBalance);
        personRepository.flush();
        return personRepository.findById(id);
    }

    public String getOperationList(int id, Date startDate, Date endDate) {
        return null;
    }
}