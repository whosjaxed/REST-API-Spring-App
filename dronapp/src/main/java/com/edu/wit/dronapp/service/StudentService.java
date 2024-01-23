package com.edu.wit.dronapp.service;

import com.edu.wit.dronapp.Exception.StudentAlreadyExistException;
import com.edu.wit.dronapp.Exception.StudentNotFoundException;
import com.edu.wit.dronapp.model.Student;
import com.edu.wit.dronapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExist(student.getEmail())){
            throw new StudentAlreadyExistException(student.getEmail()+ " already exist");
        }
        return studentRepository.save(student);
    }

    private boolean studentAlreadyExist(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Student not found"));

    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with this id: " + id + " not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with this id: " + id + " not found");
        }
        studentRepository.deleteById(id);
    }
}
