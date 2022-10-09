import java.awt.EventQueue;

import javax.swing.JFrame;

import velcro.Controller.*;
import velcro.Model.*;
import velcro.View.*;

public class app {
	public JFrame homepage;
	public static void main(String[] args) {

		Instance thisInstance = new Instance();
		try {
			Controller window = new Controller(thisInstance, "LandingPage");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
