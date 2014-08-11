package ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domain.exam.attempt.AnsweredQuestion;
import ua.domain.exam.attempt.ExamAttempt;
import ua.domain.exam.attempt.ExamStatistics;
import ua.domain.exam.data.Exam;
import ua.domain.exam.data.Question;
import ua.domain.user.User;
import ua.repository.AnsweredQuestionDao;
import ua.repository.ExamAttemptDao;
import ua.repository.ExamDao;
import ua.repository.ExamStatisticsDao;

import java.util.List;

/**
 * Created by Adevi on 8/9/2014.
 */
@Service
public class ExamService {
    @Autowired private ExamDao examDao;
    @Autowired private ExamAttemptDao examAttemptDao;
    @Autowired private AnsweredQuestionDao answeredQuestionDao;
    @Autowired private ExamStatisticsDao examStatisticsDao;

    @Transactional
    public List<Exam> save( List<Exam> exams) {
        return examDao.save(exams);
    }

    @Transactional
    public Exam load(Long id) {
        return examDao.findOne(id);
    }

    @Transactional
    public List<Exam> loadAvailableExams(User user){
        return examDao.loadAvailableExams(user);
    }

    @Transactional
    public void startExam(Long examId, User user){
        if (examIsGoingOn(user))
            throw new IllegalStateException("Some exam is already in process, finish it first.");
        Exam examForStart = examDao.findOne(examId);
        if (examForStart == null)
            throw new IllegalArgumentException("Exam with given id doesn't exist");
        ExamAttempt examAttempt = new ExamAttempt(examForStart, user);
        examAttemptDao.save(examAttempt);
    }

    @Transactional public Exam getCurrentExam(User user){
        ExamAttempt examAttempt = examAttemptDao.find(user);
        if (examAttempt == null)
            throw new IllegalStateException("No exam in process");
        return examAttempt.getExam();
    }

    @Transactional public AnsweredQuestion submitAndGet(Long answerId, Integer questionNum, User user){
        ExamAttempt examAttempt = examAttemptDao.find(user);
        AnsweredQuestion answeredQuestion = examAttempt.submitAndGet(questionNum, answerId);
        if (answeredQuestion != null) {
            examAttemptDao.save(examAttempt);
            return answeredQuestion;
        } else{
            examStatisticsDao.save(examAttempt.finishAttempt());
            examAttemptDao.delete(examAttempt);
            return null;
        }
    }

    @Transactional public List<ExamStatistics> getExamStatistics(User user){
        return examStatisticsDao.find(user);
    }

    private boolean examIsGoingOn(User user){
        return examAttemptDao.find(user) != null;
    }

}
