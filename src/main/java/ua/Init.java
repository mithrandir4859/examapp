package ua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.domain.exam.attempt.ExamStatistics;
import ua.domain.user.User;
import ua.domain.user.UserRole;
import ua.domain.exam.attempt.AnsweredQuestion;
import ua.domain.exam.attempt.ExamAttempt;
import ua.domain.exam.data.Answer;
import ua.domain.exam.data.Exam;
import ua.domain.exam.data.Question;
import ua.service.ExamService;
import ua.service.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Adevi on 8/9/2014.
 */
@Component
public class Init {
    private Random random = new Random(19);
    private static final int
            ANSWERS_NUMBER = 5,
            QUESTION_NUMBER = 20,
            EXAM_NUMBER = 22,
            EXAM_SIZE = 6,
            MAX_NUM = 1100;

    private User user;
    private List<Exam> exams;

    {
        user = new User();
        user.setFirstname("Yurii");
        user.setLastname("Andrieiev");
        user.setLogin("login");
        user.setPassword("password");
        user.setUserRole(UserRole.STUDENT);
    }

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void hm() {
        exams = examService.save(exams = generateExams(EXAM_NUMBER));
        user = userService.save(user);
    }

    private List<Question> generateQuestions(int howMany, Exam exam){
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            int num1 = random.nextInt(MAX_NUM);
            int num0 = random.nextInt(MAX_NUM);
            int sum = num1 + num0;
            Question question = new Question();
            List<Answer> answers = new ArrayList<>();
            Answer correctAnswer = new Answer();
            correctAnswer.setQuestion(question);
            correctAnswer.setText(Integer.valueOf(sum).toString());
            answers.add(correctAnswer);
            for (int j = 1; j < ANSWERS_NUMBER; j++) {
                Answer answer = new Answer();
                answer.setQuestion(question);
                int potentialText;
                do {
                    potentialText = sum + random.nextInt() % 17 - i;
                } while (potentialText == sum);
                answer.setText(potentialText + "");
                answers.add(answer);
            }
            Collections.shuffle(answers);
            question.setAnswers(answers);
            question.setCorrectAnswer(correctAnswer);
            question.setText(num1 + " + " + num0 + " = ?");
            question.setExam(exam);
            questions.add(question);
        }
        return questions;
    }


    /*private List<Answer> generateAnswers(int howMany, Question question) {
        List<Answer> answers = new ArrayList<>(howMany);
        for (int i = 0; i < howMany; i++) {
            Answer answer = new Answer();
            answer.setText("Answer text " + random.nextInt(100500) + " " + i);
            answer.setQuestion(question);
            answers.add(answer);
        }
        return answers;
    }


    private List<Question> generateQuestions(int howMany, Exam exam) {
        List<Question> questions = new ArrayList<>(howMany);
        for (int i = 0; i < howMany; i++) {
            Question question = new Question();
            List<Answer> answers = generateAnswers(ANSWERS_NUMBER, question);
            question.setAnswers(answers);
            question.setCorrectAnswer(answers.get(random.nextInt(ANSWERS_NUMBER)));
            question.setText("Text of question here " + random.nextInt(666666) + " " + i);
            question.setExam(exam);
            questions.add(question);
        }
        return questions;
    }
    */
    private List<Exam> generateExams(int howMany) {
        List<Exam> exams = new ArrayList<>(howMany);
        for (int i = 0; i < howMany; i++) {
            Exam exam = new Exam();
            exam.setDescription("Exam description " + i);
            exam.setName("Arifmetika " + i);
            exam.setQuestions(generateQuestions(QUESTION_NUMBER, exam));
            exam.setSize(EXAM_SIZE);
            exams.add(exam);
        }
        return exams;
    }

}
