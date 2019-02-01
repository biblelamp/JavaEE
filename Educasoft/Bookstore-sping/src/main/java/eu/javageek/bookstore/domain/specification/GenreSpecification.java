package eu.javageek.bookstore.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.domain.Book_;
import eu.javageek.bookstore.domain.Author;
import eu.javageek.bookstore.domain.Genre;
import eu.javageek.bookstore.domain.Genre_;

public class GenreSpecification {

	private GenreSpecification() {
		super();
	}

	/**
	 * get {@link Specification} to get all {@link Genre} with specified ({@link Author})
	 * @param author
	 * @return
	 */
	public static Specification<Genre> getGenreByAuthor(final Author author) {
		return new Specification<Genre>() {

			@Override
			public Predicate toPredicate(final Root<Genre> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {

				Join<Genre, Book> genreBook = root.join(Genre_.books);
				query.where(cb.equal(genreBook.get(Book_.authorOfBook), author));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}
}
