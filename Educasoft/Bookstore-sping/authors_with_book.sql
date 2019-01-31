SELECT
	DISTINCT author.name
FROM
   author JOIN book
ON
	author.id = book.author_of_book_id
