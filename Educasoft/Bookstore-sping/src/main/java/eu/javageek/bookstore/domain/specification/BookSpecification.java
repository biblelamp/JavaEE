package eu.javageek.bookstore.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.domain.Book_;
import eu.javageek.bookstore.domain.Genre;
import eu.javageek.bookstore.domain.Author;

public class BookSpecification {

	private BookSpecification() {
		super();
	}

	/**
	 * get {@link Specification} to get all {@link Book} with specified ({@link Author})
	 * @param author
	 * @return
	 */
	public static Specification<Book> getBookByAuthor(final Author author) {
		return new Specification<Book>() {

			@Override
			public Predicate toPredicate(final Root<Book> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {

				query.where(cb.equal(root.get(Book_.authorOfBook), author));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}

	/**
	 * get {@link Specification} to get all {@link Book} with specified ({@link Genre})
	 * @param genre
	 * @return
	 */
	public static Specification<Book> getBookByGenre(final Genre genre) {
		return new Specification<Book>() {

			@Override
			public Predicate toPredicate(final Root<Book> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {

				Join<Book, Genre> bookGenre = root.join(Book_.genre);
				query.where(bookGenre.in(genre));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}
}
