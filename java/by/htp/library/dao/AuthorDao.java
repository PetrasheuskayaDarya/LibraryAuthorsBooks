package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Author;

public interface AuthorDao {
	
	Author readById(int id);
	List<Author> getAuthors();
	void addAuthor(Author author);
	void deliteAuthor(Author author);
	void updateAuthor(Author authorCurrent, Author authorNew);
	
}
