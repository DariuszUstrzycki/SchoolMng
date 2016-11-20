package com.ustrzycki.domain;
import java.time.LocalDate;

public class Person {

	private String firstName = null;
	private String lastName = null;
	private String pesel = null;
	private Gender gender = null;;
	private String simpleAddress = null;
	private LocalDate dateOfBirth = null;

	private Person(PersonBuilder builder) {

		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.pesel = builder.pesel;
		this.gender = builder.gender;
		this.simpleAddress = builder.simpleAddress;
		this.dateOfBirth = builder.dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPesel() {
		return pesel;
	}

	public Gender getGender() {
		return gender;
	}

	public String getSimpleAddress() {
		return simpleAddress;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel + ", gender=" + gender
				+ ", simpleAddress=" + simpleAddress + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		return true;
	}

	public static class PersonBuilder {

		private String firstName = null;
		private String lastName = null;
		private String pesel = null;
		private Gender gender = null;;
		private String simpleAddress = null;
		private LocalDate dateOfBirth = null;

		// no-args constructor to validate attributes in builder's setter
		// methods one by one as they are input from the command line
		public PersonBuilder() {
		}

		public PersonBuilder firstName(String first) {
			if ("".equals(first))
				throw new IllegalArgumentException("First name cannot be empty!");

			this.firstName = first;
			return this;
		}

		public PersonBuilder lastName(String last) {
			if ("".equals(last))
				throw new IllegalArgumentException("Last name cannot be empty!");

			this.lastName = last;
			return this;
		}

		public PersonBuilder pesel(String pesel) {

			if (!pesel.matches("\\d{11}"))
				throw new IllegalArgumentException("PESEL must contain 11 numbers!");

			this.pesel = pesel;
			return this;
		}

		public PersonBuilder gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public PersonBuilder address(String streetNoCityZip) {
			this.simpleAddress = streetNoCityZip;
			return this;
		}

		public PersonBuilder dateOfBirth(LocalDate birth) {

			LocalDate today = LocalDate.now();

			if (birth.isAfter(today) || birth.isEqual(today))
				throw new IllegalArgumentException("Date of birth must be earlier than today's date!");
			
			if (birth.isBefore(LocalDate.of(1900, 01, 01)))
				throw new IllegalArgumentException("It's not the date of birth of a living person!");

			this.dateOfBirth = birth;
			return this;
		}

		public Person build() {

			Person person = new Person(this);

			// the constraints on the initializtation of the required parameters of a Person object -
			// a consequence of using a no-args constructor
			if (person.getFirstName() == null || person.getLastName() == null || person.getGender() == null
					|| person.getPesel() == null)
				throw new IllegalStateException(
						"First name, last name, gender and pesel must be initialized to create a Person object!" +
						" firstName: " + (person.getFirstName()) +
						", lastName: " + (person.getLastName()) +
						", gender: "	+ (	person.getGender()) +
						", pesel: " + (person.getPesel()));
			return person;
		}

	}

}
