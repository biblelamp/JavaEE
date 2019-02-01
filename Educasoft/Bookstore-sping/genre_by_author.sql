SELECT
	DISTINCT genre.name
FROM
   genre JOIN book_genre
ON
	genre.id = book_genre.genre_id
	JOIN book
ON
	book_genre.book_id = book.id AND book.author_of_book_id in (5)