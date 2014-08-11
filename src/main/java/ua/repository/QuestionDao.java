package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ua.domain.exam.data.Question;

/**
 * Created by Adevi on 8/8/2014.
 */
public interface QuestionDao extends JpaRepository<Question, Long> {
}
