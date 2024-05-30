package bankapp.repository;

import bankapp.entity.BankOperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOperationRepository extends JpaRepository<BankOperation, Integer> {}