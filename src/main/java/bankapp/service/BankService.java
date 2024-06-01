package bankapp.service;

import bankapp.entity.BankOperation;
import bankapp.entity.Person;
import bankapp.repository.BankOperationRepository;
import bankapp.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate localDate = LocalDate.now();
        BankOperation bankOperation = new BankOperation(id, "пополнение", money, localDate);
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
            LocalDate localDate = LocalDate.now();
            BankOperation bankOperation = new BankOperation(id, "снятие", money, localDate);
            bankOperationRepository.save(bankOperation);
            return personRepository.findById(id);
        }
    }

    @SneakyThrows
    public List<BankOperation> getOperationList(int idPerson, LocalDate startDate, LocalDate endDate) {
        List<BankOperation> bankOperations = bankOperationRepository.findByDateOperationBetween(idPerson, startDate, endDate);
        bankOperations.forEach((bankOperation -> {
            bankOperation.setId(bankOperation.getId());
            bankOperation.setIdPerson(bankOperation.getIdPerson());
            bankOperation.setCurOperation(bankOperation.getCurOperation());
            bankOperation.setMoney(bankOperation.getMoney());
            bankOperation.setDateOperation(bankOperation.getDateOperation());
        }));
        return bankOperations;
    }
}