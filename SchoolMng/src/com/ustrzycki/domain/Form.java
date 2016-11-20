package com.ustrzycki.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Form extends AddableToDb{

	private String name = null;
	private List<Student> studentsList = new ArrayList<Student>();
	private Map<Subject, Teacher> subjectTeacherMap = new HashMap<>();
	private Teacher tutor = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {

		if ("".equals(name))
			throw new IllegalArgumentException("The name of a form cannot be empty!");
		
		name = name.replace(" ", "");

		this.name = name;
	}

	public List<Student> getListOfStudents() {
		return new ArrayList<Student>(studentsList);
	}

	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

	public boolean containsStudent(Student student) {

		if (studentsList.contains(student)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addStudent(Student student) {

		if (!studentsList.contains(student)) {
			studentsList.add(student);
			return true;
		} else {
			return false;
		}
	}

	public boolean addAllStudents(Collection<Student> col) {

		boolean collectionChanged = false;

		for (Student student : col) {

			if (!containsStudent(student)) {
				studentsList.add(student);
				collectionChanged = true;
			}
		}

		return collectionChanged;

	}

	public boolean deleteStudent(Student student) {

		if (studentsList.contains(student)) {
			studentsList.remove(student);
			return true;
		} else {
			return false;
		}
	}

	public int getNumberOfStudents() {
		return studentsList.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Form other = (Form) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Form [name=" + name + ", tutor=" + tutor + "]";
	}

	public Map<Subject, Teacher> getSubjectTeacherMap() {
		return new HashMap<Subject, Teacher>(subjectTeacherMap);
	}

	public void setSubjectTeacherMap(Map<Subject, Teacher> newMap) {
		this.subjectTeacherMap = new HashMap<Subject, Teacher>(newMap);
	}
	
	

	
}
