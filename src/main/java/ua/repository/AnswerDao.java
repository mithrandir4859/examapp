package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.domain.exam.data.Answer;

/**
 * Created by Adevi on 8/8/2014.
 */
@Repository
    public interface AnswerDao extends JpaRepository<Answer, Long> {
}
