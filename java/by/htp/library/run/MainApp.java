package by.htp.library.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import by.htp.library.dao.AuthorDao;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.AuthorDaoImpl;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.entity.Author;
import by.htp.library.entity.Book;

public class MainApp {

	public static void main(String[] args) throws ParseException {
		BookDao bookDao = new BookDaoImpl();
		Book book = bookDao.read(1);
		System.out.println(book);
		bookDao.list();
		System.out.println("add BookA");

		Book bookForAdd = new Book();
		bookForAdd.setIdAuthor(5);
		bookForAdd.setTitle("bookA");
		bookDao.add(bookForAdd);
		System.out.println("delite BookA ");

		Book bookForDel = new Book();
		bookForDel.setIdAuthor(5);
		bookForDel.setTitle("bookA");
		bookDao.delete(bookForDel);
		System.out.println("update BookS");

		Book bookForUpdate = new Book();
		bookForUpdate.setIdAuthor(5);
		bookForUpdate.setTitle("new_bookS");
		bookForUpdate.setIdBook(1);
		bookDao.update(bookForUpdate);

		System.out.println("get all Book(size)");

		List<Book> books = bookDao.list();
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
		}

		System.out.println("read Autor by id");

		AuthorDao autorDao = new AuthorDaoImpl();
		Author autor = autorDao.readById(3);
		System.out.println(autor);
		System.out.println("add Author");
		String target = "1500-02-20";
		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		Date birsdate = formatForDate.parse(target);
		Author autorForAdd = new Author("Mark", "Statenberg", birsdate);
		autorDao.addAuthor(autorForAdd);
		
		System.out.println("delite Author");
		
		String targetDel = "1500-02-20";
		Date birsdateDel = formatForDate.parse(targetDel);
		Author autorForDel = new Author("Kua", "Mia", birsdateDel);
		autorDao.deliteAuthor(autorForDel);
		
		System.out.println("update Author");

		String targetCurrent = "1985-10-10";
		Date birsdateCurrent = formatForDate.parse(targetCurrent);
		Author autorCurrent = new Author("name", "surname", birsdateCurrent);
		String targetNew = "1990-11-11";
		Date birsdateNew = formatForDate.parse(targetNew);
		Author autorNew = new Author("Karl", "Smith", birsdateNew);
		autorDao.updateAuthor(autorCurrent, autorNew);
		
		System.out.println("select Authors");

		List<Author> myAuthors = autorDao.getAuthors();
		for (int i = 0; i < myAuthors.size(); i++) {
			System.out.println(myAuthors.get(i));
		}
	}
}