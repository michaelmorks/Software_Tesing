package web.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import web.service.LoginService;
import web.service.MathQuestionService;

@Controller
@RequestMapping("/")
public class RoutingServlet {

    @GetMapping("/")
    public String welcome() {
        return "view-welcome";
    }

    @GetMapping("/login")
    public String loginView() {
        return "view-login";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        String dob = request.getParameter("dob");

        if (LoginService.login(username, password, dob)) {
            return new RedirectView("/q1", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Incorrect credentials.");
            return new RedirectView("/login", true);
        }
    }

    @GetMapping("/q1")
    public String q1View() {
        return "view-q1";
    }

    @PostMapping("/q1")
    public RedirectView q1(HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");

        Double n1 = MathQuestionService.safeParse(number1);
        Double n2 = MathQuestionService.safeParse(number2);
        Double userAnswer = MathQuestionService.safeParse(resultUser);

        if (n1 == null || n2 == null || userAnswer == null) {
            redirectAttributes.addFlashAttribute("message", "Invalid input. Numbers only!");
            return new RedirectView("/q1", true);
        }

        double correct = MathQuestionService.q1Addition(number1, number2);
        if (Math.abs(correct - userAnswer) < 0.0001) {
            return new RedirectView("/q2", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
            return new RedirectView("/q1", true);
        }
    }

    @GetMapping("/q2")
    public String q2View() {
        return "view-q2";
    }

    @PostMapping("/q2")
    public RedirectView q2(HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");

        Double n1 = MathQuestionService.safeParse(number1);
        Double n2 = MathQuestionService.safeParse(number2);
        Double userAnswer = MathQuestionService.safeParse(resultUser);

        if (n1 == null || n2 == null || userAnswer == null) {
            redirectAttributes.addFlashAttribute("message", "Invalid input. Numbers only!");
            return new RedirectView("/q2", true);
        }

        double correct = MathQuestionService.q2Subtraction(number1, number2);
        if (Math.abs(correct - userAnswer) < 0.0001) {
            return new RedirectView("/q3", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
            return new RedirectView("/q2", true);
        }
    }

    @GetMapping("/q3")
    public String q3View() {
        return "view-q3";
    }

    @PostMapping("/q3")
    public RedirectView q3(HttpServletRequest request,
                           RedirectAttributes redirectAttributes) {
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String resultUser = request.getParameter("result");

        Double n1 = MathQuestionService.safeParse(number1);
        Double n2 = MathQuestionService.safeParse(number2);
        Double userAnswer = MathQuestionService.safeParse(resultUser);

        if (n1 == null || n2 == null || userAnswer == null) {
            redirectAttributes.addFlashAttribute("message", "Invalid input. Numbers only!");
            return new RedirectView("/q3", true);
        }

        double correct = MathQuestionService.q3Multiplication(number1, number2);
        if (Math.abs(correct - userAnswer) < 0.0001) {
            redirectAttributes.addFlashAttribute("message", "Well done! You completed all questions!");
            return new RedirectView("/q3", true);
        } else {
            redirectAttributes.addFlashAttribute("message", "Wrong answer, try again.");
            return new RedirectView("/q3", true);
        }
    }
}