package lc.labuladong.DataStructure.graph.Q277;

class People{
    private boolean[][] relationship;

    public People(boolean[][] relationship) {
        this.relationship = relationship;
    }

    public boolean knows(int i, int j){
        return relationship[i][j];
    }

    public int findCelebrity(int n){
        // 先假设 cand 是名人
        int cand = 0;
        for (int other = 1; other < n; other++) {
            if (!knows(other, cand) || knows(cand, other)){
                // cand 不可能是名人，排除
                // 假设 other 是名人
                cand = other;
            }
            // else
            // other不可能是名人，所以继续假设cand是名人
        }
        // 现在的 cand 是排除的最后结果，但不能保证一定是名人
        for (int other = 0; other < n; other++) {
            if (cand == other) continue;
            if (!knows(other,cand) || knows(cand, other)){
                return -1;
            }
        }
        return cand;
    }

}
public class Main {
}
