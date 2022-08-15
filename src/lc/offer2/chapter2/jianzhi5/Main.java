package lc.offer2.chapter2.jianzhi5;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
*/

class Solution {
    public String replaceSpace(String s) {
        if (s.length()==0){
            return s;
        }
        // 法1
//        return s.replaceAll(" ", "%20"); // 太低效

        // 法2
        StringBuilder sb = new StringBuilder(); // 线程安全
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i)!=' '){
                sb.append(s.charAt(i));
            }else {
                sb.append("%20");
            }
        }
        return sb.toString();

        // 法3
//        char[] chars = s.toCharArray();
//        int count = 0;
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i]==' '){
//                count++;
//            }
//        }
//        char[] result = new char[chars.length + count*2];
//        for(int i = 0, j = 0; i < chars.length; i++){
//            if (chars[i] != ' '){
//                result[j++] = chars[i];
//            }else{
//                result[j++] = '%';
//                result[j++] = '2';
//                result[j++] = '0';
//            }
//        }
//        return String.valueOf(result);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "We are happy.";
        System.out.println(solution.replaceSpace(s));
    }
}
