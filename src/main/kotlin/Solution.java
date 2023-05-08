import java.util.List;
import java.util.Map;

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

    static Map<Integer, Integer>  sMap = Map.of(0, 0, 1, 1, 6, 9, 8, 8, 9, 6);
    ///易混淆数
    ////这个题就数字位数提取，并且替换为主
    public boolean confusingNumber(int n) {
        int temp = n;
        int newNum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            if (!sMap.containsKey(digit)) {
                return false;
            }
            newNum = newNum * 10 + sMap.get(digit);
            temp /= 10;
        }
        return newNum != n;
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public String stringShift(String s, int[][] shift) {
        int total = 0;
        for (int[] ints : shift) {
            int direction = ints[0];
            int amount = ints[1];
            if (direction == 0) {
                total -= amount;
            } else {
                total += amount;
            }
        }

        if (total == 0) {
            return s;
        }

        if (total < 0) {
            total = -total;
            total %= s.length();
            return s.substring(total) + s.substring(0, total);
        } else {
            total %= s.length();
            return s.substring(s.length() - total) + s.substring(0, s.length() - total);
        }

    }

    ///相隔1的编辑距离
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(t.length() - s.length()) > 1) {
            return false;
        }

        int i = 0;
        int difNum = 0;
        boolean result = false;
        if (s.length() == t.length()) {
            while (s.length() > i) {
                if (s.charAt(i) != t.charAt(i)) {
                    difNum ++;
                }
                i++;
            }
        } else {
            String longStr;
            String shortStr;
            if (s.length() > t.length()) {
                longStr = s;
                shortStr = t;
            } else {
                longStr = t;
                shortStr = s;
            }

            while (shortStr.length() > i) {
                if (longStr.charAt(i + difNum) != shortStr.charAt(i)) {
                    difNum ++;
                    if (difNum > 1) {
                        break;
                    }
                } else {
                    i++;
                }
            }

            //这种情况长短字符串前面的数值都一样，只是末尾的字符不一样
            if (difNum == 0) {
                difNum++;
            }
        }
        result = difNum == 1;
        return result;
    }

    /// 反转字符串中的单词 II
    public void reverseWords(char[] s) {
        if (s.length == 0 && s.length == 1) {
            return;
        }

        int begin = 0;
        int end = 0;
        for (int i = 0; i < s.length; i++) {
            if (i == s.length - 1) {
                end = i;
                reversalCharStr(s, begin, end);
            } else if (s[i] == ' ') {
                end = i - 1;
                reversalCharStr(s, begin, end);
                if (s.length > i + 1) {
                    begin = i + 1;
                }
            }
        }
    }

    public void reversalCharStr(char[] s, int begin, int end) {
        char temp;
        while (begin < end) {
           temp = s[begin];
           s[begin] = s[end];
           s[end] = temp;
           begin++;
           end--;
        }
    }

    /**
     * 主要用好和理解 String indexOf(cj, i) String indexOf(cj)
     */
    public int shortestWay(String source, String target) {
        int j = 0;
        int result = 0;
        while (j < target.length()) {
            int preIndex = j;
            for (int i = 0; i < source.length(); i++) {
                char tempChar = target.charAt(j);
                i = source.indexOf(tempChar, i);
                if (i == -1) {
                    break;
                } else {
                    j++;
                }
                if (j >= target.length()) {
                    break;
                }
            }
            if (preIndex == j) {
                return -1;
            }
            result++;
        }
        return result;
    }

}
