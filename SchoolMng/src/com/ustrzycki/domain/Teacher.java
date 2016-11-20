package com.ustrzycki.domain;
import java.time.LocalDate;

public class Teacher {

	private Person person = null;
	private String graduationInfo = null;

	
	
	public void setBuilder(TeacherBuilder builder) {
		this.person = builder.person;
		this.graduationInfo = builder.graduationInfo;
	}

	/////////////// Teacher-specific methods ///////////////

	private Person getPerson() {
		return person;
	}

	public String getGraduationInfo() {
		return graduationInfo;
	}

	///////////// delegation - Person methods //////////////

	public String getFirstName() {
		return person.getFirstName();
	}

	public String getLastName() {
		return person.getLastName();
	}

	public String getPesel() {
		return person.getPesel();
	}

	public Gender getGender() {
		return person.getGender();
	}

	public String getSimpleAddress() {
		return person.getSimpleAddress();
	}

	public LocalDate getDateOfBirth() {
		return person.getDateOfBirth();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		Teacher other = (Teacher) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Teacher [person=" + person + ", degree=" + graduationInfo + "]";
	}

	public static class TeacherBuilder extends AddableToDb{

		private Person person = null;
		private String graduationInfo = null;

		public TeacherBuilder(Person person) {

			this.person = person;
		}

		public TeacherBuilder graduationInfo(String info) {
			this.graduationInfo = info;
			return this;
		}

		public Teacher build() {
			Teacher teacher = new Teacher();
			teacher.setBuilder(this);

			// constraints on the initialization of the
			// required parameters of a Teacher object

			if (teacher.getPerson() == null)
				throw new IllegalStateException(
						"The Person variable of the TeacherBuilder class has not been initialized!");

			return teacher;
		}

	}

}
