package thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:xie-super
 * Time:2024/5/9
 * Name:IntelliJ IDEA
 */
public class Main {
    private static boolean help(String pattern, String str){
        String[] splitStr = str.split(" ");
        int len = splitStr.length;
        Map<Character, String> mp = new HashMap<>();
        if(pattern.length()!=splitStr.length)return false;
        for(int i = 0; i<splitStr.length; i++){
            if(!mp.containsKey(pattern.charAt(i))){
                mp.put(pattern.charAt(i), splitStr[i]);
            }else{
                if(!mp.get(pattern.charAt(i)).equals(splitStr[i]))return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String pattern = "abbac";
        String str = "北京 杭州 杭州 北京 上海";
        if(help(pattern, str)){
            System.out.println("匹配");
        }else{
            System.out.println("不匹配");
        }


    }
}
