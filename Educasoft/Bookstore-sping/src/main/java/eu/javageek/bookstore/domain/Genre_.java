package eu.javageek.bookstore.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Genre_ {

	public static volatile SingularAttribute<Genre, Integer> id;
	public static volatile SingularAttribute<Genre, String> name;
	public static volatile ListAttribute<Genre, Book> books;

}
