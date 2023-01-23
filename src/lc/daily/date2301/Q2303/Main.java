package lc.daily.date2301.Q2303;

class Solution {
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0.0;
        int preLevel = 0;
        for (int[] bracket : brackets) {
            int upper = bracket[0], percent = bracket[1];
            if (upper <= income){
                tax += (upper - preLevel) * (percent / 100.0);
                preLevel = upper;
            }else{
                tax += (income - preLevel) * (percent / 100.0);
                break;
            }
        }
        return tax;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] brackets = {
                {1,0},
                {4,25},
                {5,50}
        };
        System.out.println(solution.calculateTax(brackets, 2));
    }
}
