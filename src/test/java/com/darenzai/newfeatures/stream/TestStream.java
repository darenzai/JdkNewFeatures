package com.darenzai.newfeatures.stream;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.darenzai.newfeatures.stream.*;

@SuppressWarnings("ALL")
    public class TestStream {
        public static void main(String[] args) {
            List<Author> authors = getAuthors();
            Stream<Author> stream = authors.stream();
            test02();

            //test0(authors);


        }

        private static void test02(){
            Integer [] arr = {1,2,3,4};
            Stream<Integer> stream = Arrays.stream(arr);
            stream.distinct()
                    .filter((integer)-> integer>2)
                    .forEach(integer -> {
                        System.out.println(integer);
                    });

        }

    private static void test0(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()<18;
                    }
                })
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });
    }

    // 初始化一些数据
    private static List<Author> getAuthors() {
        Author author1 = new Author(1L, "杨杰炜", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "yjw", "my introduction 2", 19, null);
        Author author3 = new Author(3L, "yjw", "my introduction 3", 14, null);
        Author author4 = new Author(4L, "wdt", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "wtf", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "高效", "书名2", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "书名3", 83D, "这是简介哦"));

        books2.add(new Book(5L, "天啊", "书名4", 65D, "这是简介哦"));
        books2.add(new Book(6L, "高效", "书名5", 89D, "这是简介哦"));

        books3.add(new Book(7L, "久啊", "书名6", 45D, "这是简介哦"));
        books3.add(new Book(8L, "高效", "书名7", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "书名8", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5));
    }

    private static void test() {
        List<Product> productsList = new ArrayList<>();
        //Adding Products
        productsList.add(new Product(1, "HP Laptop", 25000d));
        productsList.add(new Product(2, "Dell Laptop", 30000d));
        productsList.add(new Product(3, "Lenovo Laptop", 28000d));
        productsList.add(new Product(4, "Sony Laptop", 28000d));
        productsList.add(new Product(5, "Apple Laptop", 90000d));
        // This is more compact approach for filtering data
        Double totalPrice = productsList.stream()
                .map(product->product.getPrice())
                // 第一个参数是累加类型 第二是累加表达式 累加到sum
                .reduce(0.0D, new BinaryOperator<Double>() {
                    @Override
                    public Double apply(Double total, Double price) {
                        return total + price;
                    }
                });
        System.out.println(totalPrice);
        // More precise code
        Double totalPrice2 = productsList.stream()
                .map(product -> product.getPrice())
                // 简单理解就是上一个map方法之后只拿到price值，这里全加起来
                .reduce(0.0D, Double::sum);
        System.out.println(totalPrice2);

        System.out.println("======");
        // 过滤，获取数据，收集成list
        List<Double> productPriceList2 =productsList.stream()
                .filter(p -> p.getPrice() > 30000)
                .map(p->p.getPrice())
                .collect(Collectors.toList());
        System.out.println(productPriceList2);

        // 常规操作
        long count = productsList.stream().filter(p -> p.getPrice() < 30000)
                .map(p -> p.getName())
                .distinct()
                .count();
        System.out.println(count);

        Set<Double> productPriceListSet = productsList.stream().filter(p -> p.getPrice() > 30000)
                .map(p -> p.getPrice())
                .distinct().collect(Collectors.toSet());
        System.out.println(productPriceListSet);

        productsList.stream().filter(p -> p.getPrice() < 30000)
                .map(p -> p.getName())
                .distinct().forEach(System.out::println);
    }


}
