import com.unwise.lab3.Student;
import com.unwise.lab3.StudentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    private StudentRepository studentRepository = new StudentRepository();

    @Test
    public void testCRUDOperations() {
        // Create
        Student newStudent = new Student();
        newStudent.setStudentId(1);
        newStudent.setStudentName("John Doe");
        studentRepository.saveStudent(newStudent);

        // Read
        Student retrievedStudent = studentRepository.findStudentById(1);
        assertNotNull(retrievedStudent);
        assertEquals("John Doe", retrievedStudent.getStudentName());

        // Update
        retrievedStudent.setStudentName("Updated Name");
        studentRepository.updateStudent(retrievedStudent);

        // Verify Update
        Student updatedStudent = studentRepository.findStudentById(1);
        assertNotNull(updatedStudent);
        assertEquals("Updated Name", updatedStudent.getStudentName());

        // Delete
        studentRepository.deleteStudent(1);

        // Verify Delete
        Student deletedStudent = studentRepository.findStudentById(1);
        assertNull(deletedStudent);
    }
}
