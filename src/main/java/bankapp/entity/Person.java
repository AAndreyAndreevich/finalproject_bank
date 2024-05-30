package bankapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
@Table(name = "bank_user")
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double balance;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}