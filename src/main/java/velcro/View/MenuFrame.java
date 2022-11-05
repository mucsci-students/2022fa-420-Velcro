package main.java.velcro.View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import main.java.velcro.main.*;
import main.java.velcro.Model.*;
import main.java.velcro.Controller.*;

// Main class.
public class MenuFrame extends JFrame {
	public static Instance thisInstance;
	public static JComboBox comboBox;
	public static DrawingGUI thisObject;
	public static Memento thisMemento;
	public static JComboBox comboBox_1_1_1;
	public static JComboBox comboBox_1;
	public static JComboBox comboBox_1_1;
	public static GUIFrame thisSurface;
	public static DrawingGUI mainClass;
	public static int panelHeight;
	public static int panelWidth = 300;
	public static JComboBox comboBox_1_1_2;
	public static JComboBox comboBox_1_1_2_1;
	public static JButton btnNewButton;
	public static JButton btnNewButton_1;
	public static JButton btnNewButton_2;
	public static JButton btnNewButton_3;
	public static JButton btnNewButton_4;
	public static JButton btnNewButton_5;
	public static JButton btnNewButton_6;
	public static JButton btnNewButton_7;
	public static JButton btnNewButton_8;
	public static JButton btnNewButton_9;
	public static JButton btnNewButton_10;
	public static JButton btnNewButton_11;
	public static JButton btnNewButton_12;
	public static JButton btnNewButton_13;
	public static JButton btnNewButton_14;
	public static JButton btnNewButton_15;
	public static JButton btnNewButton_16;
	public static MenuFrame thisFrame;

	
	public static DefaultComboBoxModel<String> classModel;
	public static DefaultComboBoxModel<String> fieldModel;
	public static DefaultComboBoxModel<String> fieldTypeModel;
	public static DefaultComboBoxModel<String> relationshipsModel;
	public static DefaultComboBoxModel<String> relationshipsTypeModel;
	public static DefaultComboBoxModel<String> methodsModel;
	public static DefaultComboBoxModel<String> methodsTypeModel;
	public static DefaultComboBoxModel<String> paramsModel;
	public static DefaultComboBoxModel<String> paramsTypeModel;

	// Constructor and initial attributes.
	public MenuFrame(Instance input, DrawingGUI input2, int panelHeight) {
		super("Control Frame");
		this.panelHeight = panelHeight;
		thisFrame = this;
		thisMemento = input2.thisMemento;
		thisInstance = input;
		thisObject = input2;
		fieldModel = new DefaultComboBoxModel<String>();
		relationshipsModel = new DefaultComboBoxModel<String>();
		relationshipsTypeModel = new DefaultComboBoxModel<String>();
		fieldTypeModel = new DefaultComboBoxModel<String>();
		methodsTypeModel = new DefaultComboBoxModel<String>();
		methodsModel = new DefaultComboBoxModel<String>();
		paramsTypeModel = new DefaultComboBoxModel<String>();
		paramsModel = new DefaultComboBoxModel<String>();
		comboBox_1_1 = new JComboBox(relationshipsTypeModel);
		classModel = thisInstance.classModel;

		// Populate ClassModel model.
		MenuController.initClassModel(classModel);
		
		// Resize panel to fit laptops/smaller screens.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//double sizeChangeRatio = 1 - ((double) (panelHeight - screenSize.getHeight()) / panelHeight);
		double sizeChangeRatio = (screenSize.height/panelHeight);
		if (sizeChangeRatio < 0)
			sizeChangeRatio = 1;
		int resizeHeight = (int) (33 * (sizeChangeRatio));
		setSize(panelWidth, screenSize.height);

		getContentPane().setLayout(null);

		// Class combobox.
		comboBox = new JComboBox<String>(classModel);
		comboBox.putClientProperty("i", 0);
		comboBox.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox.getClientProperty("i")), 172,
				resizeHeight);
		comboBox.setModel(classModel);
		MenuController.setComboBoxListener();
		getContentPane().add(comboBox);

		// Class label.
		JLabel lblNewLabel = new JLabel("Class");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 0, 172, 27);
		getContentPane().add(lblNewLabel);

		// Relationship combobox.
		comboBox_1 = new JComboBox(relationshipsModel);
		comboBox_1.putClientProperty("i", 3);
		comboBox_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1.getClientProperty("i")), 172,
				resizeHeight);
		MenuController.setComboBox1Listener();
		getContentPane().add(comboBox_1);

		// Relationship destination label.
		JLabel lblRelationshipDestination = new JLabel("Relationship Destination");
		lblRelationshipDestination.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationshipDestination.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRelationshipDestination.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1.getClientProperty("i")),
				219, 27);
		getContentPane().add(lblRelationshipDestination);

		// Relationship type combobox.
		comboBox_1_1.putClientProperty("i", 4);
		comboBox_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1.getClientProperty("i")), 172,
				resizeHeight);
		getContentPane().add(comboBox_1_1);

		// Field combobox.
		comboBox_1_1_1 = new JComboBox(fieldModel);
		comboBox_1_1_1.putClientProperty("i", 1);
		comboBox_1_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1.getClientProperty("i")),
				172, resizeHeight);
		MenuController.setComboBox111Listener();
		getContentPane().add(comboBox_1_1_1);

		// Methods combobox.
		comboBox_1_1_2 = new JComboBox(methodsModel);
		comboBox_1_1_2.putClientProperty("i", 5);
		comboBox_1_1_2.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2.getClientProperty("i")),
				172, resizeHeight);
		MenuController.setComboBox112Listener();
		getContentPane().add(comboBox_1_1_2);

		// Methods label.
		JLabel lblMethod = new JLabel("Method");
		lblMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMethod.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2.getClientProperty("i")), 172, 27);
		getContentPane().add(lblMethod);

		// Parameters combobox.
		comboBox_1_1_2_1 = new JComboBox(paramsModel);
		comboBox_1_1_2_1.putClientProperty("i", 7);
		comboBox_1_1_2_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1.getClientProperty("i")),
				172, resizeHeight);
		MenuController.setComboBox1121Listener();
		getContentPane().add(comboBox_1_1_2_1);

		// Parameters label.
		JLabel lblParameter = new JLabel("Parameter");
		lblParameter.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameter.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblParameter.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_1.getClientProperty("i")), 172,
				27);
		getContentPane().add(lblParameter);

		// Methods type combobox.
		JComboBox comboBox_1_1_2_2 = new JComboBox(methodsTypeModel);
		comboBox_1_1_2_2.putClientProperty("i", 6);
		comboBox_1_1_2_2.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_2.getClientProperty("i")),
				172, resizeHeight);
		getContentPane().add(comboBox_1_1_2_2);

		// Methods label.
		JLabel lblMethodType = new JLabel("Method Type");
		lblMethodType.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethodType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMethodType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_2.getClientProperty("i")), 172,
				27);
		getContentPane().add(lblMethodType);

		// Field type combobox.
		JComboBox comboBox_1_1_1_1 = new JComboBox(fieldTypeModel);
		comboBox_1_1_1_1.putClientProperty("i", 2);
		comboBox_1_1_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1_1.getClientProperty("i")),
				172, resizeHeight);
		getContentPane().add(comboBox_1_1_1_1);

		// Field type label.
		JLabel lblFieldType = new JLabel("Field Type");
		lblFieldType.setHorizontalAlignment(SwingConstants.CENTER);
		lblFieldType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFieldType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_1_1.getClientProperty("i")), 172,
				27);
		getContentPane().add(lblFieldType);

		// Field label.
		JLabel lblField = new JLabel("Field");
		lblField.setHorizontalAlignment(SwingConstants.CENTER);
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblField.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_1.getClientProperty("i")), 172, 27);
		getContentPane().add(lblField);

		// Relationship type label.
		JLabel lblRelationshipType = new JLabel("Relationship Type");
		lblRelationshipType.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationshipType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRelationshipType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1.getClientProperty("i")), 172,
				27);
		getContentPane().add(lblRelationshipType);

		// Parameters type combobox.
		JComboBox comboBox_1_1_2_1_1 = new JComboBox(paramsTypeModel);
		comboBox_1_1_2_1_1.putClientProperty("i", 8);
		comboBox_1_1_2_1_1.setBounds(22,
				(int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")), 172, resizeHeight);
		getContentPane().add(comboBox_1_1_2_1_1);

		// Parameters type label.
		JLabel lblParameterType = new JLabel("Parameter Type");
		lblParameterType.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameterType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblParameterType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				172, 27);
		getContentPane().add(lblParameterType);

		// Add field button.
		btnNewButton = new JButton("Add");
		btnNewButton.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1.getClientProperty("i")), 60,
				resizeHeight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		MenuController.setBtnListener();
		getContentPane().add(btnNewButton);

		// Add method button.
		btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(205, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2.getClientProperty("i")),
				60, resizeHeight);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		MenuController.setBtn1Listener();
		getContentPane().add(btnNewButton_1);

		// Add parameter button.
		btnNewButton_2 = new JButton("Add");
		btnNewButton_2.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1.getClientProperty("i")),
				60, resizeHeight);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		MenuController.setBtn2Listener();
		getContentPane().add(btnNewButton_2);

		// Add relationship button.
		btnNewButton_3 = new JButton("Add");
		btnNewButton_3.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1.getClientProperty("i")), 60,
				resizeHeight);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		MenuController.setBtn3Listener();
		getContentPane().add(btnNewButton_3);

		// Show contents button.
		btnNewButton_4 = new JButton("All Class Contents");
		btnNewButton_4.setBounds(22,
				(int) (10 + resizeHeight + 27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn4Listener();
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getContentPane().add(btnNewButton_4);

		// Undo button.
		btnNewButton_5 = new JButton("Undo");
		btnNewButton_5.setBounds(22,
				(int) (15 + resizeHeight + 54 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn5Listener();
		getContentPane().add(btnNewButton_5);

		// Redo button.
		btnNewButton_6 = new JButton("Redo");
		btnNewButton_6.setBounds(155,
				(int) (15 + resizeHeight + 54 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn6Listener();
		getContentPane().add(btnNewButton_6);

		// Save button.
		btnNewButton_7 = new JButton("Save");
		btnNewButton_7.setBounds(22,
				(int) (20 + resizeHeight + 81 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn7Listener();
		getContentPane().add(btnNewButton_7);

		// Load button.
		btnNewButton_8 = new JButton("Load");
		btnNewButton_8.setBounds(155,
				(int) (20 + resizeHeight + 81 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn8Listener();
		getContentPane().add(btnNewButton_8);

		// Exit button.
		btnNewButton_9 = new JButton("Exit");
		btnNewButton_9.setBounds(155,
				(int) (25 + resizeHeight + 108 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn9Listener();
		getContentPane().add(btnNewButton_9);

		// Class contents button.
		btnNewButton_10 = new JButton("Class Contents");
		btnNewButton_10.setBounds(155,
				(int) (10 + resizeHeight + 27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		getContentPane().add(btnNewButton_10);
		MenuController.setBtn10Listener();
		btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 10));

		// Screenshot button.
		btnNewButton_11 = new JButton("Screenshot");
		btnNewButton_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_11.setBounds(22,
				(int) (25 + resizeHeight + 108 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				119, (int) (resizeHeight / 1.2));
		MenuController.setBtn11Listener();
		getContentPane().add(btnNewButton_11);

		JSeparator separator = new JSeparator();
		separator.setBounds(0,
				(int) (5 + resizeHeight + 27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				284, 2);
		getContentPane().add(separator);

		
		
		// Remove class button.
		btnNewButton_12 = new JButton("Remove");
		btnNewButton_12.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_12.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox.getClientProperty("i")), 60,
				resizeHeight);
		MenuController.setBtn12Listener();
		getContentPane().add(btnNewButton_12);
		
		// Remove field button.
		btnNewButton_13 = new JButton("Remove");
		btnNewButton_13.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_13.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1_1.getClientProperty("i")),
				60, resizeHeight);
		MenuController.setBtn13Listener();
		getContentPane().add(btnNewButton_13);
		
		// Remove relationship button.
		btnNewButton_14 = new JButton("Remove");
		btnNewButton_14.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_14.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1.getClientProperty("i")), 60,
				resizeHeight);
		MenuController.setBtn14Listener();
		getContentPane().add(btnNewButton_14);
		
		// Remove method button.
		btnNewButton_15 = new JButton("Remove");
		btnNewButton_15.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_15.setBounds(204, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_2.getClientProperty("i")),
				60, resizeHeight);
		MenuController.setBtn15Listener();
		getContentPane().add(btnNewButton_15);
		
		// Remove parameter button.
		btnNewButton_16 = new JButton("Remove");
		btnNewButton_16.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_16.setBounds(204,
				(int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")), 60, resizeHeight);
		MenuController.setBtn16Listener();
		getContentPane().add(btnNewButton_16);
		
		
		setVisible(true);
	}


	// Method making parent visible to scope.
	public void setSurface(GUIFrame input, DrawingGUI input2) {
		this.thisSurface = input;
		this.mainClass = input2;
	}
	
	// Pass method on to controller.
	public void firstLoad() {
		MenuController.firstLoad();
	}
	
	// Clears static requirement to access repaint from other classes.
	public static void repaintMe() {
		if(thisFrame == null) return;
		thisFrame.repaint();
	}
}
