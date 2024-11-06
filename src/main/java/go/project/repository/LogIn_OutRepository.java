package go.project.repository;


import go.project.entity.LogIn_Out;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogIn_OutRepository extends JpaRepository<LogIn_Out, Long> {

}
