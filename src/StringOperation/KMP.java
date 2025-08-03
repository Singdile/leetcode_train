package StringOperation;

/**
 * 地址：https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 */
/

public class KMP {
    public static void main(String[] args) {
        String str="abc";
        String substr="abc";
    }

    public int getIndex(String str,String substr) {
        //计算next数组
        int[] nextArr = getNext(substr);

        //进行遍历
        int i = 0;
        int j = 0;

        while (i < str.length()) {
            while (j > 0 && str.charAt(i) != substr.charAt(j)) {
                j = nextArr[j - 1];  //匹配失败，找到前面一位的最大相等的前后缀的长度
            }

            //匹配成功
            if (str.charAt(i) == substr.charAt(j)) {
                i++;
                j++;
                if (j == substr.length()) { return i - j;}
            }

            //匹配失败，并且 j== 0 时，为防止陷入死循环
            if (j == 0 && str.charAt(i) != substr.charAt(j)) {
                i++;
            }
        }

        return -1;
    }


    //计算next数组，每一个表示从[0,i]当前位置的字符串的最长相等的前后缀的长度
    public int[] getNext(String substr){
        int[] nextArr = new int[substr.length()];
        nextArr[0] = 0; //第一个位置，前后缀长度为 0

        int i = 1; //i是表示需要进行计算字符串的位置[0,i],同时也表示最长的后缀的末尾位置
        int j = 0; //j是表示前一个字串[0,i-1]的最长相等前后缀的前缀位置的后面一位，也就是本次计算字符串的可能最长前缀的末尾位置

        for(;i<substr.length();i++){
            while (j > 0 && substr.charAt(i) != substr.charAt(j)){
                //理解为nextArr的套娃使用
                //当 j 位置不匹配，则缩小前缀范围，再次比较
                j =  nextArr[j - 1];
            }

            if (substr.charAt(i) == substr.charAt(j)){
                j++; //最长相等前后缀长度为j+1
            }

            //记录最长长度，并且j此时指向最长前缀的末尾位置的后面一位，用于下一次判断
            //如果 j == 0,就是没有相等的前后缀，则长度也就是 0
            nextArr[i] = j;
        }
        return nextArr;
    }
}


