package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.domain.exam.data.Exam;
import ua.domain.user.User;

import java.util.List;

/**
 * Created by Adevi on 8/8/2014.
 */
@Repository
public interface ExamDao extends JpaRepository<Exam, Long>{
    @Query("SELECT e FROM Exam e WHERE e NOT IN (SELECT e.exam FROM ExamStatistics e WHERE e.user = :user)")
    List<Exam> loadAvailableExams(@Param("user") User user);
}
