package by.htp.library.dao.impl;

import static by.htp.library.dao.util.MySqlPropertyManager.getLogin;
import static by.htp.library.dao.util.MySqlPropertyManager.getPass;
import static by.htp.library.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.library.dao.AuthorDao;
import by.htp.library.entity.Author;

public class AuthorDaoImpl implements AuthorDao {
	private static final String SELECT_AUTOR_BY_ID = "SELECT * FROM author WHERE id_author = ?";
	private static final String INSERT_AUTOR = "INSERT INTO author(name, surname, birthdate) VALUES (?, ?, ?)";
	private static final String UPDATE_AUTOR = "UPDATE author SET name = ?, surname= ?, birthdate = ? WHERE name = ? AND surname= ? AND birthdate = ?";
	private static final String DELETE_AUTOR = "DELETE FROM author WHERE name = ? AND surname = ? AND birthdate = ?";
	private static final String SELECT_AUTORS = "SELECT * FROM author";

	@Override
	public Author readById(int id) {
		Author autor = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_AUTOR_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//autor = buildAuthor(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autor;
	}



	private Author buildAutor(ResultSet rs) throws SQLException {
		Author autor = new Author();
		autor.setSurname(rs.getString("surname"));
		autor.setName(rs.getString("name"));
		autor.setBirthdate(rs.getDate("birthdate"));
		return autor;
	}

	@Override
	public List<Author> getAuthors() {
		List<Author> autors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(SELECT_AUTORS);
			while(result.next()) {
				Author tempAutor = buildAutor(result);
				autors.add(tempAutor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autors;
	}



	@Override
	public void addAuthor(Author autor) {
		String name = autor.getName();
		String surname = autor.getSurname();
		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = formatForDate.format(autor.getBirthdate());
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_AUTOR);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, birthdate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void deliteAuthor(Author autor) {
		String name = autor.getName();
		String surname = autor.getSurname();
		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = formatForDate.format(autor.getBirthdate());
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_AUTOR);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setString(3, birthdate);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void updateAuthor(Author autorCurrent, Author autorNew) {
		String nameCurrent = autorCurrent.getName();
		String nameNew = autorNew.getName();
		String surnameCurrent = autorCurrent.getSurname();
		String surnameNew = autorNew.getSurname();
		SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBirthdateCurrent = autorCurrent.getBirthdate();
		String birthdateCurrent = formatForDate.format(dateBirthdateCurrent);
		Date dateBirthdateNew = autorCurrent.getBirthdate();
		String birthdateNew = formatForDate.format(dateBirthdateNew);
		try (Connection conn = DriverManager.getConnection(getUrl(), getLogin(), getPass())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_AUTOR);
			ps.setString(1, nameNew);
			ps.setString(2, surnameNew);
			ps.setString(3, birthdateNew);
			ps.setString(4, nameCurrent);
			ps.setString(5, surnameCurrent);
			ps.setString(6, birthdateCurrent);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}