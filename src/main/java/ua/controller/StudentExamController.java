package ua.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.domain.exam.attempt.AnsweredQuestion;
import ua.domain.exam.attempt.ExamAttempt;
import ua.domain.exam.data.Exam;
import ua.domain.exam.data.Question;
import ua.repository.UserDao;
import ua.service.ExamService;

import java.security.Principal;
import java.util.List;
import static ua.util.Util.currentUser;

/**
 * Created by Adevi on 8/9/2014.
 */
@Controller
@RequestMapping("student")
public class StudentExamController {

    @Autowired private ExamService examService;

    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String getExamList(Principal principal, Model model){
        model.addAttribute(examService.loadAvailableExams(currentUser()));
        return "examList";
    }

    @RequestMapping(value = "/exam/{examId}", method = RequestMethod.GET)
    public String getExamStartPage(Model model, @PathVariable("examId") Long examId){
        Exam exam = examService.load(examId);
        if (exam == null)
            return "redirect:/student/examError";
        model.addAttribute(exam);
        return "examStartPage";
    }

    @RequestMapping(value = "/exam/{examId}", method = RequestMethod.POST)
    public String doStartExam(@PathVariable("examId") Long examId){
        try {
            examService.startExam(examId, currentUser());
            return "redirect:/student/examTaking";
        } catch (IllegalStateException e){
            return "redirect:/student/examContinue";
        } catch (IllegalArgumentException e){
            return "redirect:/student/examError";
        }
    }

    @RequestMapping(value = "/examTaking", method = RequestMethod.GET)
    public String getExamTakingPage(Model model){
        model.addAttribute(examService.getCurrentExam(currentUser()));
        return "examTaking";
    }

    @RequestMapping(value = "/next", method = RequestMethod.POST)
    public @ResponseBody
    AnsweredQuestion process(
            @RequestParam(value = "answerId", required = false) Long answerId,
            @RequestParam(value = "questionNum", required = false) Integer questionNum){
        return examService.submitAndGet(answerId, questionNum, currentUser());
    }

    @RequestMapping(value = "/examStatistics", method = RequestMethod.GET)
    public String getExamStatistics(Model model){
        model.addAttribute(examService.getExamStatistics(currentUser()));
        return "examStatistics";
    }

}
