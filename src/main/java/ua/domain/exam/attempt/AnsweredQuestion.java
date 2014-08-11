package ua.domain.exam.attempt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.domain.exam.data.Answer;
import ua.domain.exam.data.Question;

import javax.persistence.*;

/**
 * Created by Adevi on 8/9/2014.
 */
@Entity
@Table(
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"examAttempt_id", "question_id"})
)
public class AnsweredQuestion {
    private ExamAttempt examAttempt;
    private Question question;
    private Long givenAnswer;
    private Long id;
    private Integer sequenceNumber;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne @JsonIgnore
    public ExamAttempt getExamAttempt() {
        return examAttempt;
    }

    public void setExamAttempt(ExamAttempt examAttempt) {
        this.examAttempt = examAttempt;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @JsonIgnore
    public Long getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(Long givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
}
