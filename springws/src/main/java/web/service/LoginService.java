package web.service;

public class LoginService {

    public static boolean login(String username, String password, String dob) {

        return "ahsan".equals(username)
            && "ahsan_pass".equals(password)
            && dob != null
            && !dob.isEmpty();
    }
}