package com.ustrzycki.domain;
import java.time.LocalDate;

import com.ustrzycki.domain.Teacher.TeacherBuilder;

public class Student extends AddableToDb{

	private Person person = null;
	private boolean scholarship = false;

	public void setBuilder(StudentBuilder builder) {
		this.person = builder.person;
		this.scholarship = builder.scholarship;
	}

	/////////////// Student-specific methods ///////////////

	private Person getPerson() {
		return person;
	}

	public boolean hasScholarship() {
		return scholarship;
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
		Student other = (Student) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [person=" + person + ", getsScholarship=" + scholarship + "]";
	}

	public static class StudentBuilder {

		private Person person = null;
		private boolean scholarship = false;

		public StudentBuilder(Person person) {
			this.person = person;
		}

		public StudentBuilder scholarship(boolean hasScholarship) {
			this.scholarship = hasScholarship;
			return this;
		}

		public Student build() {

			Student student = new Student();
			student.setBuilder(this);

			// constraints on the initialization of the
			// required parameters of a Teacher object

			if (student.getPerson() == null)
				throw new IllegalStateException(
						"The Person variable of the StudentBuilder class has not been initialized!");

			return student;
		}

	}
}
