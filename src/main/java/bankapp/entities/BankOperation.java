package bankapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankOperation {

    int id;

    int idPerson;

    String curOperation;

    double money;

    Date dateOperation;

}