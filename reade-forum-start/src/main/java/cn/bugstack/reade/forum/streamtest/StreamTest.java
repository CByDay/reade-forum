package cn.bugstack.reade.forum.streamtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhd
 * @date 2023/4/9
 * @description: 练习 stream 流
 */
@Component
public class StreamTest {

    public static void main(String[] args) {

        // 需求：现在需要打印所有年龄小于18的人的名字，并且要去重
        List<Author> authorList = getAuthors();
//        authorList.stream()//把集合转换成流
//                .distinct() //去重的方法
//                .filter(new Predicate<Author>() {   //过滤
//                    @Override
//                    public boolean test(Author author) {
//                        return author.getAge() < 18;
//                    }
//                })
//                .forEach(new Consumer<Author>() {   //循环
//                    @Override
//                    public void accept(Author author) {
//                        System.out.println(author.getName());
//                    }
//                });
//
//        authorList.stream()//把集合转换成流
//                .distinct() //去重的方法
//                .filter(author -> author.getAge() < 18) //过滤
//                .forEach(author -> System.out.println(author.getName())); //终结操作 forEach
//
//        getAuthors().stream().map(new Function<Author, String>() {
//            @Override
//            public String apply(Author author) {
//                return author.getName();
//            }
//        }).forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

//        getAuthors().stream()
//                .map(new Function<Author, Integer>() {
//                    @Override
//                    public Integer apply(Author author) {
//                        return author.getAge();
//                    }
//                })
//                .map(new Function<Integer, Integer>() {
//
//                    @Override
//                    public Integer apply(Integer integer) {
//                        return integer +10;
//                    }
//                })
//                .forEach(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) {
//                        System.out.println(integer);
//                    }
//                });

//        getAuthors().stream()
//                .distinct()
//                .forEach(author -> System.out.println(author.getName()));

//        getAuthors().stream()
//                .distinct()
//                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
//                .skip(1)
//                .forEach(author -> System.out.println(author.getName()));

//        getAuthors().stream()
//                .flatMap(new Function<Author, Stream<Book>>() {
//                    @Override
//                    public Stream<Book> apply(Author author) {     //flatMap 下的 new Function<Author, Stream<Book>> 这里是 传入Author 返回一个 Book 的 Stream() 流
//                        return author.getBooks().stream();
//                    }
//                })
//                .distinct()
//                .forEach(new Consumer<Book>() {
//                    @Override
//                    public void accept(Book book) {
//                        System.out.println(book.getName());
//                    }
//                });

//        getAuthors().stream()
//                .flatMap(author -> author.getBooks().stream())
//                .distinct()
//                .forEach(book -> System.out.println(book.getName()));

//        getAuthors().stream()
//                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
//                .distinct()
//                .flatMap((Function<Book, Stream<String>>) book -> Arrays.stream(book.getCategory().split(",")))
//                .distinct()
//                .forEach(category -> System.out.println(category));

//        final var count = getAuthors().stream()
//                .flatMap(author -> author.getBooks().stream())
//                .distinct()
//                .count();
//        System.out.println(count);

//        final var max = getAuthors().stream()
//                .flatMap(author -> author.getBooks().stream())
//                .max((o1, o2) -> o1.getScore() - o2.getScore());
//        System.out.println(max.get().getScore());

//        final var collect = getAuthors().stream()
//                .map(author -> author.getName())
//                .collect(Collectors.toList());
//        System.out.println(collect);


//        final var bookList = getAuthors().stream()
//                .flatMap(author -> author.getBooks().stream())
//                .map(book -> book.getName())
//                .collect(Collectors.toSet());
//        System.out.println(bookList);

//        Map<String, List<Book>> map = getAuthors().stream()
//                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks(),
//                        (v1, v2) -> v2));
//
//        System.out.println(map);

//        final var res = getAuthors().stream()
//                .noneMatch(author -> author.getAge() > 100);
//        System.out.println(res);

//        final var first = getAuthors().stream()
//                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
//                .findFirst();
//        first.ifPresent(author -> System.out.println(author.getName()));

//        final var sum = getAuthors().stream()
//                .distinct()
//                .map(author -> author.getAge())
//                .reduce((result, element) -> result + element);
//        System.out.println(sum);

//        final var reduce = getAuthors().stream()
//                .map(author -> author.getAge())
//                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
//        System.out.println(reduce);
//        final var sum = getAuthors().stream()
//                .distinct()
//                .map(author -> author.getAge())
//                .reduce((result, element) -> result + element);
//        System.out.println(sum);


//        List<Author> authors  = getAuthors();
//        getAuthors().stream().filter(((Predicate<Author>) author -> author.getAge() > 17).or(author -> author.getName().length() <2)).forEach(author -> System.out.println(author.getName()));

//        List<Author> authors = getAuthors();
//        getAuthors().stream().filter(((Predicate<Author>) author -> author.getAge() < 18).negate())
//                .forEach(author -> System.out.println(author.getName()));
//
//        List<Author> authors = getAuthors();
//        authors.stream()
//                .map(Author::getName)
//                .map(StringBuilder::new)
//                .map(stringBuilder -> stringBuilder.append("三更").toString())
//                .forEach(System.out::println);

    }

    private static List<Author> getAuthors() {
        Author author1 = new Author(1l, "蒙多", 33, "祖安", null);
        Author author2 = new Author(2l, "亚索", 15, "班德尔城", null);
        Author author3 = new Author(3l, "德玛西亚", 14, "暗影岛", null);
        Author author4 = new Author(3l, "德玛西亚", 14, "暗影岛", null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "飞速的旋转", "哲学,爱情", 88, "用一把刀划分了爱情"));
        books1.add(new Book(2L, "一个人不能在同一个地方跌倒两次", "个人成长,爱情", 99, "讲述了如何从失败中走出来"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维领域世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维领域世界的尽头"));
        books2.add(new Book(4L, "吹或者不吹", "爱情,个人传记", 56, "一个哲学家的"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂"));

        author1.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author1, author2, author3, author4));
        return authorList;
    }


    @Autowired
    TestValue testValue;
    public void testValue() throws Exception {
        testValue.afterPropertiesSet();
    }
}
