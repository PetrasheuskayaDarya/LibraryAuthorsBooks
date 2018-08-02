package by.htp.library.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Author {
	private String name;
	private String surname;
	private Date birthdate;
	
	public Author() {
		super();

	}

	public Author(String name, String surname, Date birthdate) {

		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Autor [name=" + name + ", surname=" + surname + ", birthdate="
				+ birthdate + "]";
	}

}