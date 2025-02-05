package com.NativeJPQLQuery.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.NativeJPQLQuery.entity.Student;
import jakarta.transaction.Transactional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    // Fetch students by name
    public List<Student> findByName(String name);
    
    // Insert student
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student (name, course, age) VALUES (:name, :course, :age)", nativeQuery = true)
    public void insertStudent(@Param("name") String name, @Param("course") String course, @Param("age") int age);

    

    // Fetch students by name and course
    @Query(value = "SELECT * FROM student WHERE name=?1 AND course=?2", nativeQuery = true)
    public List<Student> getByNameAndCourse(String name, String course);

    //fetch student by greaterthan 
    //@Query("SELECT s FROM Student s WHERE s.age > :age")
    //public List<Student> findByAgeGreaterThanJPQL(@Param("age") int age);

    // Fetch students by course (JPQL)
    @Query("SELECT s FROM Student s WHERE s.course = :course")
    List<Student> findByCourseJPQL(@Param("course") String course);

    // Update student
    @Modifying
    @Transactional
    @Query(value = "UPDATE Student SET name=?1, age=?2 WHERE course=?3")
    public void updateStudent(String name, int age, String course);

   
 
    
  

    // Delete student
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student WHERE name=?1", nativeQuery = true)
    public void deleteStudent(String name);
}
