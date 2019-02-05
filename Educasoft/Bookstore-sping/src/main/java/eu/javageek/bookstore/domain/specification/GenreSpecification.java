package eu.javageek.bookstore.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

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

				Subquery<Author> sub = query.subquery(Author.class);
				Root<Book> subRoot = sub.from(Book.class);

				sub.select(subRoot.get(Book_.authorOfBook));
				sub.where(cb.equal(subRoot.get(Book_.authorOfBook), author));

				Join<Genre, Book> genreBook = root.join(Genre_.books);
				query.where(genreBook.get(Book_.authorOfBook).in(sub.getSelection()));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}

	/**
	 * get {@link Specification} to get all {@link Genre} with define at least N ({@link Book})
	 * @param genre
	 * @return
	 */
	public static Specification<Genre> getGenreWhitcDefineAtLeastNBook(final Long nBook) {
		return new Specification<Genre>() {

			@Override
			public Predicate toPredicate(final Root<Genre> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {

				Subquery<Long> sub = query.subquery(Long.class);
				Root<Book> subRoot = sub.from(Book.class);

				Join<Book, Genre> subGenres = subRoot.join(Book_.genre);

				sub.select(cb.count(subRoot.get(Book_.id)));
				sub.where(cb.equal(root.get(Genre_.id), subGenres.get(Genre_.id)));

				query.where(cb.greaterThanOrEqualTo(sub, nBook));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}
}
