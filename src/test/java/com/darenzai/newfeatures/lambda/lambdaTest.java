package com.darenzai.newfeatures.lambda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class lambdaTest {

    @Test
    public void test1() throws  Exception{
        // 无参无返回值
        Runnable runnable = ()-> System.out.println("runnable 运行");
        runnable.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
    }
    @Test
    public void test02(){
        // 需要一个参数 无返回值
        //Consumer<String> consumer = (x)-> System.out.println("Runnable 运行");
        Consumer<String> consumer = x-> System.out.println(x);
        consumer.accept("Hello Consumer");

    }

    @Test
    public void test03(){
        Comparator<Integer> com =(x,y)->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
        System.out.println(com.compare(4,2));

    }

    @Test
    public void test04(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("新线程run方法执行了");
//            }
//        }).start();
        // lambda表达式
        new Thread(()->{
            System.out.println("你知道吗我在你身边");
        }).start();
//        printNum(new IntPredicate() {
//            @Override
//            public boolean test(int value) {
//                return value%2==0;
//            }
//        });

        printNum((int value)-> {
            return value%2==0;
        });

        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        foreachArr(value -> System.out.println(value));
    }
    @Test
    public static void printNum(IntPredicate predicate){
        int [] arr = {1,2,3,4,5};
        for(int i:arr){
            if(predicate.test(i)){
                System.out.println(i);
            }
        }
    }

    public static void foreachArr(IntConsumer consumer){
        int [] arr = {1,23,4,5,6,7,8,9,10};
        for(int i: arr){
            consumer.accept(i);
        }
    }
}
