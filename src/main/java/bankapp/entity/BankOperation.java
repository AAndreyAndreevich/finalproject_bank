package bankapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bank_operation")
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BankOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idPerson;

    private String curOperation;

    private double money;

    private Date dateOperation;

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