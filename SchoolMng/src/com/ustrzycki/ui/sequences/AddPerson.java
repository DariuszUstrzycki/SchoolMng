package com.ustrzycki.ui.sequences;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.ustrzycki.domain.Gender;
import com.ustrzycki.domain.Person;
import com.ustrzycki.ui.ConsoleUI;



public class AddPerson extends AbstractAddSequence {
	
	private Person.PersonBuilder personBuilder = new Person.PersonBuilder();
	private Person person;

	public AddPerson(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
	}
	
	// Overridden methods ------------------------------------------------------------------------------------
	
	@Override
	protected void matchPromptAction() {
		promptList.add("Enter the first name: ");
		actionsList.add(new RunnableInput(() -> inputFirstName(), true)); // string

		promptList.add("Enter the last name: ");
		actionsList.add(new RunnableInput(() -> inputLasttName(), true)); // string

		promptList.add("Enter the PESEL:");
		actionsList.add(new RunnableInput(() -> inputPESEL(), true)); // string

		promptList.add("Enter the gender - F (female) or M (male):");
		actionsList.add(new RunnableInput(() -> inputGender(), true)); // enum 

		promptList.add("Enter the date of birth in the format YYYY-MM-DD"
				+ "\n(press ENTER if you don't want to set this value now):");
		actionsList.add(new RunnableInput(() -> inputDateOfBirth(), true)); // Date
		//-------- end person methods ----------//
		
		promptList.add("");
		actionsList.add(new RunnableInput(() -> createPerson(), false)); // NO INPUT 
		
	}

	@Override
	public void addToDb() {
		
	}
	
	// Methods ------------------------------------------------------------------------------------

	private final void inputFirstName() {
		personBuilder.firstName(getInputValue());
	}

	private final void inputLasttName() {
		personBuilder.lastName(getInputValue());
	}

	private final void inputPESEL() {
		personBuilder.pesel(getInputValue());
	}

	private final void inputGender() {
		if (getInputValue().equalsIgnoreCase("F"))
			personBuilder.gender(Gender.FEMALE);
		else if (getInputValue().equalsIgnoreCase("M"))
			personBuilder.gender(Gender.MALE);
		else
			throw new IllegalArgumentException("Only F or M is possible");
	}
	
	private final void createPerson() {
		person = personBuilder.build();
	}


	private final void inputDateOfBirth() {

		try {
			
			if ("".equals(getInputValue())) {
				// ok for an optional parameter
			} else{
				String[] yearMonthDay = getInputValue().split("-");
				LocalDate date = LocalDate.of(Integer.parseInt(yearMonthDay[0]), 
											Integer.parseInt(yearMonthDay[1]),
											Integer.parseInt(yearMonthDay[2]));
				personBuilder.dateOfBirth(date);
			} 

		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Incorrect format.");

		} catch (DateTimeException e) {
			throw new IllegalArgumentException(
					"Incorrect format. A day/month/year is out of range, or the day of month is invalid\n"
							+ "for the month/year.");
		}

	}

	final Person getPerson() {
		return person;
	}





	










}
