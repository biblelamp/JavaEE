SELECT
	*
FROM
   book JOIN book_genre
ON
	book.id = book_genre.book_id && book_genre.genre_id = 1