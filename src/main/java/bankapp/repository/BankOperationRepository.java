package bankapp.repository;

import bankapp.entity.BankOperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BankOperationRepository extends JpaRepository<BankOperation, Integer> {

    @Query(value = "SELECT * FROM bank_operation WHERE id_person=:idPerson AND (date_operation>=:startDate AND date_operation <=:endDate)", nativeQuery = true)
    List<BankOperation> findByDateOperationBetween(@Param("idPerson") int idPerson, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

}