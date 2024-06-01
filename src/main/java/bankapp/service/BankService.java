package bankapp.service;

import bankapp.entity.BankOperation;
import bankapp.entity.Person;
import bankapp.repository.BankOperationRepository;
import bankapp.repository.PersonRepository;
import lombok.AllArgsConstructor;
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

    public Object transferMoney(int idFrom, int idTo, double money) {
        double balanceIdFrom = personRepository.findById(idFrom).get().getBalance();
        double balanceIdTo = personRepository.findById(idTo).get().getBalance();
        if ((balanceIdFrom - money) < 0) {
            return "Указанная сумма : " + money + " превышает баланс : " + balanceIdFrom + ", укажите другое кол-во";
        } else {
            balanceIdFrom = balanceIdFrom - money;
            personRepository.findById(idFrom).get().setBalance(balanceIdFrom);
            personRepository.flush();
            balanceIdTo = balanceIdTo + money;
            personRepository.findById(idTo).get().setBalance(balanceIdTo);
            LocalDate localDate = LocalDate.now();
            BankOperation bankOperation = new BankOperation(idFrom, "перевод на id : " + idTo, money, localDate);
            bankOperationRepository.save(bankOperation);
            return "Успех!";
        }
    }

    public List<BankOperation> getOperationList(int idPerson, LocalDate startDate, LocalDate endDate) {
        List<BankOperation> bankOperations;
        if (startDate != null && endDate != null) {
            bankOperations = bankOperationRepository.findByDateOperationBetween(idPerson, startDate, endDate);
            bankOperations.forEach((bankOperation -> {
                bankOperation.setId(bankOperation.getId());
                bankOperation.setIdPerson(bankOperation.getIdPerson());
                bankOperation.setCurOperation(bankOperation.getCurOperation());
                bankOperation.setMoney(bankOperation.getMoney());
                bankOperation.setDateOperation(bankOperation.getDateOperation());
            }));
        } else if (startDate == null && endDate == null) {
            bankOperations = bankOperationRepository.findByIdPerson(idPerson);
            bankOperations.forEach((bankOperation -> {
                bankOperation.setId(bankOperation.getId());
                bankOperation.setIdPerson(bankOperation.getIdPerson());
                bankOperation.setCurOperation(bankOperation.getCurOperation());
                bankOperation.setMoney(bankOperation.getMoney());
                bankOperation.setDateOperation(bankOperation.getDateOperation());
            }));
        } else if (startDate != null) {
            bankOperations = bankOperationRepository.findByDateOperationAfter(idPerson, startDate);
            bankOperations.forEach((bankOperation -> {
                bankOperation.setId(bankOperation.getId());
                bankOperation.setIdPerson(bankOperation.getIdPerson());
                bankOperation.setCurOperation(bankOperation.getCurOperation());
                bankOperation.setMoney(bankOperation.getMoney());
                bankOperation.setDateOperation(bankOperation.getDateOperation());
            }));
        } else {
            bankOperations = bankOperationRepository.findByDateOperationBefore(idPerson, endDate);
            bankOperations.forEach((bankOperation -> {
                bankOperation.setId(bankOperation.getId());
                bankOperation.setIdPerson(bankOperation.getIdPerson());
                bankOperation.setCurOperation(bankOperation.getCurOperation());
                bankOperation.setMoney(bankOperation.getMoney());
                bankOperation.setDateOperation(bankOperation.getDateOperation());
            }));
        }
        return bankOperations;
    }
}