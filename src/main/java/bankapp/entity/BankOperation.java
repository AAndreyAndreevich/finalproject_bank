package bankapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bank_operation")
@Component
public class BankOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_person")
    private int idPerson;
    @Column(name = "cur_operation")
    private String curOperation;
    @Column(name = "money")
    private double money;
    @Column(name = "date_operation")
    private LocalDate dateOperation;

    public BankOperation(int idPerson, String curOperation, double money, LocalDate dateOperation) {
        this.idPerson = idPerson;
        this.curOperation = curOperation;
        this.money = money;
        this.dateOperation = dateOperation;
    }

    public BankOperation() {
    }

    @Override
    public String toString() {
        return "BankOperation{" +
                "id=" + id +
                ", idPerson=" + idPerson +
                ", curOperation='" + curOperation + '\'' +
                ", money=" + money +
                ", dateOperation=" + dateOperation +
                '}';
    }
}