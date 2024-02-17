package ba.sum.fsre.studentpraksa.services;

import ba.sum.fsre.studentpraksa.model.Student;
import ba.sum.fsre.studentpraksa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;
    public void save(Student m){
        studentRepo.save(m);
    }
    public List<Student> getAllstudent(){
        return studentRepo.findAll();
    }

    public Student getstudentById(int id){
        return studentRepo.findById(id).get();
    }
    public void deletestudentById(int id){
         studentRepo.deleteById(id);
    }
}
