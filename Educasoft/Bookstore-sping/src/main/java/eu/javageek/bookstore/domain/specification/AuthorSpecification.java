package eu.javageek.bookstore.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.javageek.bookstore.domain.Author;
import eu.javageek.bookstore.domain.Author_;
import eu.javageek.bookstore.domain.Book;
import eu.javageek.bookstore.domain.Book_;

public class AuthorSpecification {

	private AuthorSpecification() {
		super();
	}

	/**
	 * get {@link Specification} to get all {@link Author} if author has at least one book\
	 * @param none
	 * @return
	 */
	public static Specification<Author> getAuthorWithBook() {
		return new Specification<Author>() {

			@Override
			public Predicate toPredicate(final Root<Author> root, final CriteriaQuery<?> query,
					final CriteriaBuilder cb) {

				Join<Author, Book> authorBook = root.join(Author_.books);
				query.where(root.in(authorBook.get(Book_.authorOfBook)));
				query.distinct(true);

				return query.getRestriction();
			}
		};
	}
}
