package hibernate.controller.dto;

import hibernate.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String name;

    public static BookDTO getInstance(Book book) {
        return new BookDTO(book.getId(), book.getName());
    }
}
