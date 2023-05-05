import java.util.List;

///数组列表中的最大距离
public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0;
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int min = arrays.get(0).get(0);
        for (int i = 1; i < arrays.size(); i++) {
            res = Math.max(res, Math.max(arrays.get(i).get(0) - max, (arrays.get(i).get(arrays.get(i).size() - 1) - min)));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }
        return res;
    }

    ///摆动排序
    ///属于数学抽象
    /*
    这个坑在于替换如何保证之前已排序好的规则不变
     */
    public void wiggleSort(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i ++) {
            if (less) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            less = !less;
        }
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
