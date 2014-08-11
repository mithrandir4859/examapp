package ua.domain.exam.data;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Adevi on 8/8/2014.
 */
@Entity
public class Question implements Serializable{

    public static final long DEFAULT_TIME_LIMIT = 19_000;

    private Long id;
    private String text;
    private List<Answer> answers;
    private Answer correctAnswer;
    private Exam exam;

    // Time in seconds given to solve this Question
    private Long timeLimit = DEFAULT_TIME_LIMIT;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "correctAnswer")
    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @ManyToOne @JsonIgnore
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
