package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.domain.exam.attempt.ExamAttempt;
import ua.domain.exam.attempt.ExamStatistics;
import ua.domain.user.User;

import java.util.List;

/**
 * Created by Adevi on 8/9/2014.
 */
@Repository
public interface ExamStatisticsDao extends JpaRepository<ExamStatistics, Long> {
    @Query("SELECT es FROM ExamStatistics es WHERE es.user = :user")
    List<ExamStatistics> find(@Param("user") User user);
}
