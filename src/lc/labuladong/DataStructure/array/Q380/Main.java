package lc.labuladong.DataStructure.array.Q380;

import java.util.*;

class RandomizedSet {
    private final List<Integer> nums;
    private final Map<Integer,Integer> valToIdx;
    private final Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        valToIdx = new HashMap<>();
        random = new Random();
    }

    /**
     * 如果 val 不存在集合中，则插入并返回 true，否则直接返回 false */
    public boolean insert(int val) {
        if (valToIdx.containsKey(val)) return false;
        valToIdx.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /**
     * 如果 val 在集合中，则删除并返回 true，否则直接返回 false */
    public boolean remove(int val) {
        if (!valToIdx.containsKey(val)) return false;
        int idx = valToIdx.get(val);
        int lastIdx = nums.size() - 1;
        int last = nums.get(lastIdx);
        // 把最后一个元素移动到idx位置
        nums.set(idx, last);
        valToIdx.put(last, idx);
        // 删除元素
        nums.remove(lastIdx);
        valToIdx.remove(val);
        return true;
    }

    /**
     * 从集合中等概率地随机获得一个元素 */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}

public class Main {
}
