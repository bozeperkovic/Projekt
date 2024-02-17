package ba.sum.fsre.studentpraksa.repositories;

import ba.sum.fsre.studentpraksa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
