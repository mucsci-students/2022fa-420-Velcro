/**
 * Filename: Observer.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Observer class. Basically acts as a wrapper for ActionListener, to satisfy requirements.
 * 
 */


package velcro.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class Observer implements ActionListener {
	
	ActionListener action;
	
	public Observer(JComboBox input, ActionListener e) {
		action = e;
		input.addActionListener(action);
	}
	
	public Observer(JButton input, ActionListener e) {
		action = e;
		input.addActionListener(action);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		action.actionPerformed(e);
	}
}