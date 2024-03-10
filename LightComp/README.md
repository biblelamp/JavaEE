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