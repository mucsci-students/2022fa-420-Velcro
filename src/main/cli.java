package main.java;

import main.java.velcro.Model.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class cli {

	// max line size 
	int MAXLINE = 1024;
	// max args on a command line 
	int MAXARGS = 128;
	
	public static void printPrompt() {
		System.out.print("> ");
	}
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		String user;
		
		while (true) {
			System.out.print("Open in GUI mode? (yes/no): ");
			user = in.next();
			if (user == "yes") {
				app.main(null);
				return;
			}	
			if (user == "no")
				break;
			if (user != "yes" || user != "no")
				System.out.println("Response not recognized. Answer \"yes\" or \"no\"."); 
		}
		
		StringTokenizer inator = new StringTokenizer(user);
		String command;
		String param1;
		String param2;
		String param3;
		while (true) {
			printPrompt();
			user = in.next();
			command = inator.nextToken();
			param1 = inator.nextToken();
			param2 = inator.nextToken();
			param3 = inator.nextToken();
			switch (command) {
				case "help":
					//TODO helptext
					break;
				case "addClass":
					//TODO
					break;
				case "deleteClass":
					//TODO
					break;
				case "renameClass":
					Classes classObj = new Classes(param1);
					classObj.rename(param1, param2);
					break;
				case "":
					//TODO
					break;
				case "":
					//TODO
					break;
				case "":
					//TODO
					break;
				case "addRelation":
					//TODO
					break;
				case "deleteRelation":
					//TODO
					break;
				case "save":
					//TODO
					break;
				case "load":
					//TODO
					break;
				case "exit":
					return;
					
			}
		}
	}
}
