package com.ustrzycki.ui.sequences;

import com.ustrzycki.ui.ConsoleUI;
import com.ustrzycki.ui.Screen.RunnableInput;

public class ZZZ_UnavailableSequence extends AbstractSequence{
	
	public ZZZ_UnavailableSequence(ConsoleUI application, String menuName, String menuInstruction) {
		super(application, menuName, menuInstruction);
		
		promptList.add("");
		actionsList.add(new RunnableInput(() -> System.out.println("#######Option currently unavailable. "
															+ "Still working on it!!!#######\n"), false));
	}

}
