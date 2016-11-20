package com.ustrzycki.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Screen implements MenuConstants{
	
	protected ConsoleUI app;
	protected Scanner scanner;
	protected String menuName;
	protected String menuInstruction;
	
	protected List<String> promptList = new ArrayList<>();
	protected List<RunnableInput> actionsList = new ArrayList<>();
			
	public Screen(ConsoleUI app, String menuName, String menuInstruction) {
		this.app = app;
		this.scanner = app.getScanner();
		this.menuName = menuName;
		this.menuInstruction = menuInstruction;
	}
	
	abstract public void showConsole();
	abstract public String generateText();
	
	
	public static class RunnableInput {
		private final Runnable action;
		private final boolean forInput;

		public RunnableInput(Runnable action, boolean forInput) {
			this.action = action;
			this.forInput = forInput;
		}

		public Runnable getRunnable() {
			return action;
		}

		public boolean isForInput() {
			return forInput;
		}
	}
	

}
