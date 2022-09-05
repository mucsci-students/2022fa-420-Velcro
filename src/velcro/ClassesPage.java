package velcro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ClassesPage {

	JFrame classPage = new JFrame();
	private JTextField textField;
	private JTextField txtuseOnlyFor;

	ClassesPage() {
		classPage.setTitle("Velcro CSCI 420 :: Classes");
		classPage.setBounds(100, 100, 700, 500);
		classPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		classPage.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(54, 119, 255, 51);
		classPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtuseOnlyFor = new JTextField();
		txtuseOnlyFor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtuseOnlyFor.setColumns(10);
		txtuseOnlyFor.setBounds(369, 119, 255, 51);
		classPage.getContentPane().add(txtuseOnlyFor);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(54, 247, 159, 69);
		classPage.getContentPane().add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(265, 247, 159, 69);
		classPage.getContentPane().add(btnDelete);
		
		JButton btnRename = new JButton("Rename");
		btnRename.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRename.setBounds(472, 247, 159, 69);
		classPage.getContentPane().add(btnRename);
		
		JLabel lblNewLabel = new JLabel("Class Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(129, 63, 115, 45);
		classPage.getContentPane().add(lblNewLabel);
		
		JLabel lblReplaceClassName = new JLabel("Rename Class Name");
		lblReplaceClassName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReplaceClassName.setBounds(411, 63, 159, 45);
		classPage.getContentPane().add(lblReplaceClassName);
		
		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHomepage.setBounds(265, 359, 159, 69);
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classPage.dispose();
				LandingPage window = new LandingPage();
				window.homepage.setVisible(true);
			}});
		classPage.getContentPane().add(btnHomepage);
		
		JButton btnClassList = new JButton("Class List");
		btnClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClassList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnClassList.setBounds(54, 359, 159, 69);
		classPage.getContentPane().add(btnClassList);
		
		JButton btnListContents = new JButton("List Contents");
		btnListContents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListContents.setBounds(472, 359, 159, 69);
		classPage.getContentPane().add(btnListContents);
		
		classPage.setVisible(true);
		
		
	}
}
