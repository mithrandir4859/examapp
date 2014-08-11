package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.domain.exam.attempt.AnsweredQuestion;
import ua.domain.user.User;

/**
 * Created by Adevi on 8/10/2014.
 */
public interface AnsweredQuestionDao extends JpaRepository<AnsweredQuestion, Long>{
    @Query("SELECT aq FROM AnsweredQuestion aq WHERE aq.sequenceNumber = :number AND aq.examAttempt.user = :user")
    AnsweredQuestion find(@Param("user")User user, @Param("number")Integer number);
}
