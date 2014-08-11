package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.domain.exam.attempt.ExamAttempt;
import ua.domain.user.User;

/**
 * Created by Adevi on 8/9/2014.
 */
@Repository
public interface ExamAttemptDao extends JpaRepository<ExamAttempt, Long> {
    @Query("SELECT e FROM ExamAttempt e WHERE e.user = :user")
    ExamAttempt find(@Param("user") User user);
}
