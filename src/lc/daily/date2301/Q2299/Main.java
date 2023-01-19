package lc.daily.date2301.Q2299;

class Solution {
    public boolean strongPasswordCheckerII(String password) {
        int n = password.length();
        if (n < 8) return false;
        boolean hasLowerCase = false, hasUpperCase = false, hasNum = false, hasSpChar = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (i < n - 1 && c == password.charAt(i+1)){
                return false;
            }
            if (!hasLowerCase && c >= 'a' && c <= 'z'){
                hasLowerCase = true;
            } else if (!hasUpperCase && c >= 'A' && c <= 'Z'){
                hasUpperCase = true;
            } else if (!hasNum && c >= '0' && c <= '9'){
                hasNum = true;
            } else if (!hasSpChar){
                switch (c){
                    case '!','@','#','$','%','^','&','*','(',')','-','+' -> hasSpChar = true;
                }
            }
        }
        return hasLowerCase && hasUpperCase && hasNum && hasSpChar;
    }
}

public class Main {
}
