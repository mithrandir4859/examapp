package ua.domain.exam.attempt;

import ua.domain.exam.data.Answer;
import ua.domain.exam.data.Exam;
import ua.domain.exam.data.Question;
import ua.domain.user.User;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Adevi on 8/9/2014.
 * For every attempt to pass an exam the instance of this class is created.
 * And it is modified in process of answering questions, as well as
 * its representation in the database (for the case if user accidentally lost
 * internet connection).
 * After user completes an exam, instance of this class is no longer needed.
 */
@Entity
public class ExamAttempt {

    private Long id;
    private User user;
    private Exam exam;
    private Map<Integer, AnsweredQuestion> answeredQuestions;
    private Long lastSubmission, deadline, started;
    private Integer currentQuestionNum;

    public ExamAttempt() {
    }

    public ExamAttempt(Exam exam, User user) {
        this.user = user;
        this.exam = exam;
        this.currentQuestionNum = 0;
        deadline = started = System.currentTimeMillis();
        randomlyChoseQuestions();
        lastSubmission = System.currentTimeMillis();
    }

    private void randomlyChoseQuestions() {
        List<Question> questions = exam.getQuestions();
        Collections.shuffle(questions);
        answeredQuestions = new HashMap<>(exam.getSize());
        for (int i = 0; i < exam.getSize(); i++) {
            deadline += questions.get(i).getTimeLimit();
            AnsweredQuestion aQuestion = new AnsweredQuestion();
            aQuestion.setQuestion(questions.get(i));
            aQuestion.setExamAttempt(this);
            aQuestion.setSequenceNumber(i);
            answeredQuestions.put(i, aQuestion);
        }
    }

    public AnsweredQuestion submitAndGet(Integer previousQuestionNum, Long answerId){
        AnsweredQuestion answeredQuestion = answeredQuestions.get(currentQuestionNum);
        if (deadlineMissed(answeredQuestion))
            return getNext(answeredQuestion);
        else if (previousQuestionNum != currentQuestionNum) {
            return answeredQuestion;
        } else {
            answeredQuestion.setGivenAnswer(answerId);
            return getNext(answeredQuestion);
        }
    }

    public ExamStatistics finishAttempt(){
        ExamStatistics examStatistics = new ExamStatistics();
        examStatistics.setExam(exam);
        examStatistics.setUser(user);
        examStatistics.setEnded(lastSubmission);
        examStatistics.setStarted(started);
        examStatistics.setDeadline(deadline);
        int correctAnswers = 0;
        for (AnsweredQuestion as: answeredQuestions.values()) {
            Long givenAnswer = as.getGivenAnswer();
            Long correctAnswer = as.getQuestion().getCorrectAnswer().getId();
            if (correctAnswer.equals(givenAnswer))
                correctAnswers++;
        }
        examStatistics.setCorrectAnswers(correctAnswers);
        return examStatistics;
    }

    private boolean deadlineMissed(AnsweredQuestion answeredQuestion){
        return System.currentTimeMillis() - lastSubmission >= answeredQuestion.getQuestion().getTimeLimit();
    }

    private AnsweredQuestion getNext(AnsweredQuestion answeredQuestion){
        lastSubmission = System.currentTimeMillis();
        if (++currentQuestionNum == exam.getSize())
            return null; // we have no more questions in test
        else
            return answeredQuestions.get(currentQuestionNum);
    }



    @OneToMany(mappedBy = "examAttempt", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = "sequenceNumber")
    public Map<Integer, AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Map<Integer, AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Long getLastSubmission() {
        return lastSubmission;
    }

    public void setLastSubmission(Long lastSubmission) {
        this.lastSubmission = lastSubmission;
    }

    public Integer getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public void setCurrentQuestionNum(Integer currentQuestion) {
        this.currentQuestionNum = currentQuestion;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Long getStarted() {
        return started;
    }

    public void setStarted(Long started) {
        this.started = started;
    }
}
