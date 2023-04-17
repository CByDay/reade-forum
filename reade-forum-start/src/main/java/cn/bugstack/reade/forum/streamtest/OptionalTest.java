package cn.bugstack.reade.forum.streamtest;

import java.util.List;
import java.util.Optional;

/**
 * @author zhd
 * @date 2023/4/10
 * @description:
 */
public class OptionalTest {

    public static void main(String[] args) {
//        Author author = getAuthor();
//
//        Optional<Author> authorOptional = Optional.ofNullable(author);
//        try {
//            Author author1 = authorOptional.orElseThrow((Supplier<Throwable>) () -> new RuntimeException("author 为空"));
//            System.out.println(author1.getName());
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//
//        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));


//        Optional<Author> authorOptional = getAuthorOptional();
//        authorOptional.isPresent();
//        authorOptional.filter(author -> author.getAge() > 18).ifPresent(author -> System.out.println(author.getName()));

        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
        Optional<List<Book>> books = authorOptional.map(author -> author.getBooks());
        books.ifPresent(books1 -> books1.forEach(book -> System.out.println(book.getName())));
    }

    private static Author getAuthor() {
        Author author1 = new Author(1l, "蒙多", 33, "祖安", null);
        return author1;
    }

    private static Optional<Author> getAuthorOptional() {
        Author author1 = new Author(1l, "蒙多", 33, "祖安", null);
        return Optional.ofNullable(author1);
    }


}
