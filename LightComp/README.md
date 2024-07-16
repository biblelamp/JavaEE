# From Spring Boot 2 to 3
Migrate Application From Spring Boot 2 to Spring Boot 3

[Migrate Application From Spring Boot 2 to Spring Boot 3](https://www.baeldung.com/spring-boot-3-migration)

1. Unable to make protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException accessible: module java.base does not "opens java.lang" to unnamed module @46d21ee0

Encountered the same issue, as a workaround you can set the following VM option
```
--add-opens java.base/java.lang=ALL-UNNAMED
```

Unable to create SAAJ meta-factory: Provider SAAJMetaFactoryImpl not found
https://www.studytonight.com/post/solved-unable-to-create-saaj-metafactory-provider-saajmetafactoryimpl-not-found

How to use QueryBuilder and Search String in hibernate search 6?
https://stackoverflow.com/questions/71219881/how-to-use-querybuilder-and-search-string-in-hibernate-search-6

Query DSL migration reference
https://docs.jboss.org/hibernate/search/6.0/migration/html_single/#queries-reference

Guide to Lucene Analyzers
https://www.baeldung.com/lucene-analyzers

Remove Accents and Diacritics From a String in Java (hibernate search 7 solved this problem)
https://www.baeldung.com/java-remove-accents-from-text

Hibernate Search 6 With Spring Boot
https://www.mindbowser.com/hibernate-search-6-with-spring-boot/

8.3.2024

How to use QueryBuilder and Search String in hibernate search 6?
https://stackoverflow.com/questions/71219881/how-to-use-querybuilder-and-search-string-in-hibernate-search-6

14.3.2024

Hibernate Search 6.0.11.Final: Migration Guide from 5.11
https://docs.jboss.org/hibernate/search/6.0/migration/html_single/#searching

{!} org.apache.lucene.search.Query ⇒ SearchPredicate
https://docs.jboss.org/hibernate/stable/search/reference/en-US/html_single/#query-predicate

Remaining classes:
- * GeDescItemCondition
- * GtDescItemCondition
- * LeDescItemCondition
- * LtDescItemCondition
- * IntervalDescItemCondition
- * NotIntervalDescItemCondition
- * EqIntervalDescItemCondition
- * IntersectDescItemCondition
- * SubsetDescItemCondition
- * SelectedValuesDescItemEnumCondition
- * UnselectedValuesDescItemEnumCondition

4.7.2024

Merging LiquiBase changesets
https://stackoverflow.com/questions/47770546/merging-liquibase-changesets

Liquibase doc: generate-changelog
https://docs.liquibase.com/commands/inspection/generate-changelog.html

12.7.2024

Swagger in Spring Boot 3.x
https://springdoc.org/#migrating-from-springfox

18.7.2024

Error Handling for REST with Spring
https://www.baeldung.com/exception-handling-for-rest-with-spring