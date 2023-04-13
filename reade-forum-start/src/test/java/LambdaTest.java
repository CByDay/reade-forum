import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @author zhd
 * @date 2023/4/9
 * @description:
 */
public class LambdaTest {


    /**
     * @description: 基本语法： (参数列表) -> { * 方法体 * };
     * @author: zhd
     * @date: 2023/4/9 15:21
     * @param:
     * @return: void
     **/
    @Test
    void testLambda01() {
        {
            I01 i01 = () -> {
                System.out.println("无返回值、无参数");
            };
            I02 i02 = (int a) -> {
                System.out.println("无返回值，单个参数。a=" + a);
            };
            I03 i03 = (int a, int b) -> {
                System.out.println("无返回值，多个参数。a=" + a + ",b=" + b);
            };
            I04 i04 = () -> {
                System.out.println("有返回值、无参数");
                return 4;
            };
            I05 i05 = (int a) -> {
                System.out.println("有返回值，单个参数。a=" + a);
                return 5;
            };
            I06 i06 = (int a, int b) -> {
                System.out.println("有返回值，多个参数。a=" + a + ",b=" + b);
                return 6;
            };
            i01.method();
            i02.method(5);
            i03.method(5, 10);
            System.out.println(i04.method());
            System.out.println(i05.method(5));
            System.out.println(i06.method(5, 10));
        }
    }

    interface I01 {
        void method();
    }

    interface I02 {
        void method(int a);
    }

    interface I03 {
        void method(int a, int b);
    }

    interface I04 {
        int method();
    }

    interface I05 {
        int method(int a);
    }

    interface I06 {
        int method(int a, int b);
    }

    @Test
    @DisplayName("testLambda02")
    void testLambda02() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("普通写法");
//            }
//        });
//
//        new Thread(() -> {
//            System.out.println("Lambda 表达式写法");
//        }).start();

        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i);

        int i1 = calculateNum((int a, int b) -> {
            return a + b;
        });

        System.out.println(i1);

    }

    public static int calculateNum(IntBinaryOperator intBinaryOperator) {
        int a = 10;
        int b = 20;
        return intBinaryOperator.applyAsInt(a, b);
    }

    @Test
    @DisplayName("testLambda03")
    void testLambda03() {
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 3 == 0;
            }
        });

        printNum((int val) -> {
            return val > 7;
        });
    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
                System.out.println("-------------------");
            }
        }
    }

    @Test
    @DisplayName("testLambda04")
    void testLambda04() {
        final var integer = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });
        System.out.println(integer);

        final var integer1 = typeConver((String str) -> {
            return Integer.valueOf(str);
        });

        System.out.println(integer1);

    }

    public static <R> R typeConver(Function<String, R> function) {
        String str = "1234";
        R result = function.apply(str);
        return result;
    }

    @Test
    @DisplayName("testLambda05")
    void testLambda05() {

       foreachArr(new IntConsumer() {
           @Override
           public void accept(int value) {

               System.out.println(value);
           }
       });

       foreachArr((int val) ->{
           System.out.println(val);
       });
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i :
                arr) {
            consumer.accept(i);
        }
    }
}
