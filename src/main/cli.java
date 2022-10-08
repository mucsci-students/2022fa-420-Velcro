package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.velcro.Model.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.google.gson.Gson;

public class cli {
	
	public static void printPrompt() {
		System.out.print("> ");
	}
	
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String user = null;
		Instance thisInstance = new Instance();
		
		while (true) {
			System.out.print("Open in GUI mode? (yes/no): ");
			user = in.next();
			if (user.equals("yes")) {
				app.main(null);
				return;
			}	
			if (user.equals("no"))
				break;
			if (!user.equals("yes") || !user.equals("no"))
				System.out.println("Response not recognized. Answer \"yes\" or \"no\"."); 
		}
		if (user == "yes")
			return;
		
		String launchHelp = " -\t launchGUI (launches GUI; prompts user to save current state)\n";
		String addClassHelp = " -\t addClass <classname>\n";
		String deleteClassHelp = " -\t deleteClass <classname>\n";
		String renameClass = " -\t renameClass <classname> <newclassname>\n";
		String renameMethod = " -\t renameMethod <methodname>\n";
		String methodAddParam = " -\t methodAddParam <parametername> <parentclassname>\n";
		String methodRemoveParam = " -\t methodRemoveParam <parametername>\n";
		String methodClearParam = " -\t methodClearParam\n";
		String renameField = " -\t renameField <fieldname> <newfieldname>\n";
		String addRelation = " -\t addRelation <sourceclass> <destinationclass> <relationshiptype>\n";
		String deleteRelation = " -\t deleteRelation <sourceclass> <destinationclass> <relationshiptype>\n";
		String save = " -\t save <filename>\n";
		String load =  " -\t load <filename>\n";
		String exit = " -\t exit\n";

		while (true) {
			printPrompt();
			user = in.next();
			String param[] = user.split(" ");
			
			switch (param[0]) {
				case "launchGUI":
					System.out.print("Save? ");
					user = in.next();
					if (user.equals("yes")) {
						File newFiles = new File(param[0]);
						BufferedWriter writers = new BufferedWriter(new FileWriter(newFiles, true));
						Gson gsons = new Gson();
						writers.append(gsons.toJson((thisInstance)));
						writers.close();
						app.main(null);
						return;
					}
					else {
						app.main(null);
						return;
					}	
				case "help":
					System.out.println(launchHelp+addClassHelp+deleteClassHelp+renameClass+renameMethod+methodAddParam+methodRemoveParam+methodClearParam+renameField+addRelation+deleteRelation+save+load+exit);
					break;
				case "addClass":
					thisInstance.addClass(param[1]);
					break;
				case "deleteClass":
					thisInstance.removeClass(param[1]);
					break;
				case "renameClass":
					Classes classObj1 = new Classes(param[1]);
					classObj1.rename(param[1], param[2]);
					break;
				/**
				case "renameMethod":
					Methods methodObj1 = new Methods(param[1]);
					methodObj1.rename(param[1]);
					break;
				case "methodAddParam":
					Methods methodObj2 = new Methods(param[1]);
					methodObj2.addParam(param[1], param[2]);
					break;
				case "methodRemoveParam":
					Methods methodObj3 = new Methods(param[1]);
					methodObj3.removeParam(param[1]);
					break;
				case "methodClearParam":
					Methods methodObj4 = new Methods(param[1]);
					methodObj4.clearParam();
					break;
				*/
				case "renameField":
					Parameters paramObj = new Parameters(param[1], param[2]);
					paramObj.rename(param[1]);
					break;
				case "addRelation":
					Classes relObj1 = new Classes(param[1]);
					relObj1.addRelationship(param[1], param[2], param[3]);
					break;
				case "deleteRelation":
					Classes relObj2 = new Classes(param[1]);
					relObj2.removeRelationship(param[1], param[2], param[3]);
					break;
				case "save":
					File newFile = new File(param[1]);
					BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
					Gson gson1 = new Gson();
					writer.append(gson1.toJson((thisInstance)));
					writer.close();
					break;
				case "load":
					Gson gson2 = new Gson();
					thisInstance = gson2.fromJson(new FileReader(param[1]), Instance.class);
					break;
				case "exit":
					return;
					
			}
		}
	}
}
