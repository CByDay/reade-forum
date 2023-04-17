package cn.bugstack.reade.forum.streamtest;

/**
 * @author zhd
 * @date 2023/4/10
 * @description:
 */
public class ClassMethod {

    interface UseString{
     String  use(String str,int start, int length);
    }

    public static String subAuthorName(String str, UseString useString){
        int start = 0;
        int length = 1;
        return useString.use(str,start,length);
    }

    public static void main(String[] args) {
        subAuthorName("三更草堂", String::substring);
    }
}

