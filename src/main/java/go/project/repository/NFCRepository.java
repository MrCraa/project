package go.project.repository;


import go.project.entity.NFC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFCRepository extends JpaRepository<NFC, Integer> {
}
