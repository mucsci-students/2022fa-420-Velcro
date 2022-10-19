package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.velcro.Controller.*;
import main.java.velcro.Model.*;
import main.java.velcro.View.*;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

class Surface extends JPanel {

	public Frame thisFrame;
	public Instance thisInstance;

	public Memento thisMemento;

	public List<ZEllipse> pntLst = new ArrayList<ZEllipse>();
	public List<ZLine> lnLst = new ArrayList<ZLine>();
	public List<String> relLst = new ArrayList<String>();
	public IteratorList arr = new IteratorList();
	public Graphics pubG;
	public int keycode = -1;
	
	private Point2D source;
	private String sourceName;

	public Surface(Instance input) {
		thisInstance = input;
	}

	

	public void setFrame(Frame input2) {
		thisFrame = input2;
		thisMemento = thisFrame.thisMemento;
		initUI(thisInstance, input2);
	}

	public ZEllipse getEll(String name) {
		if (arr.size() > 0) {
			IteratorList.Iterator first = arr.getIterator();
			for (first.first(); !first.isDone(); first.next())
				if (first.currentValue().className.equals(name))
					return first.currentValue();
		}
		return null;
	}

	class ZLine extends Line2D.Float {
		String type;
		String source;
		String destination;

		public void setType(String input) {
			this.type = input;
		}

		public ZLine(Point2D input1, Point2D input2, String source, String destination) {
			super(input1, input2);
			this.type = "";
			this.source = source;
			this.destination = destination;
		}

		public Point2D getCenter() {
			return new Point2D.Double((x1 + x2) / 2, (y1 + y2) / 2);
		}
	}

	public void initUI(Instance input, Frame input2) {
		thisFrame = input2;
		MovingAdapter ma = new MovingAdapter();
		addMouseMotionListener(ma);
		addMouseListener(ma);
		addMouseWheelListener(new ScaleHandler());
		thisMemento.add(input);
	}

	protected Point2D center(Rectangle2D bounds) {
		return new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
	}

	protected double angleBetween(Shape from, Shape to) {
		return angleBetween(center(from.getBounds2D()), center(to.getBounds2D()));
	}

	protected double angleBetween(Point2D from, Point2D to) {
		double x = from.getX();
		double y = from.getY();
		double deltaX = to.getX() - x;
		double deltaY = to.getY() - y;
		double rotation = -Math.atan2(deltaX, deltaY);
		rotation = Math.toRadians(Math.toDegrees(rotation) + 180);

		return rotation;
	}

	protected Point2D getPointOnCircle(Shape shape, double radians) {
		Rectangle2D bounds = shape.getBounds();
		Point2D point = center(bounds);
		return getPointOnCircle(point, radians, Math.max(bounds.getWidth(), bounds.getHeight()) / 2d);
	}

	protected Point2D getPointOnCircle(Point2D center, double radians, double radius) {

		double x = center.getX();
		double y = center.getY();

		radians = radians - Math.toRadians(90.0);
		// Calculate the outer point of the line
		double xPosy = Math.round((float) (x + Math.cos(radians) * radius));
		double yPosy = Math.round((float) (y + Math.sin(radians) * radius));

		return new Point2D.Double(xPosy, yPosy);

	}

	private ZLine addLine(ZEllipse ell1, ZEllipse ell2, Graphics2D g2d, String type, String source,
			String destination) {
		if (ell1 == null || ell2 == null || ell1.equals(ell2))
			return null;
		// Draw relationship line
		double from = angleBetween(ell1, ell2);
		double to = angleBetween(ell2, ell1);
		Point2D pointFrom = getPointOnCircle(ell1, from);
		Point2D pointTo = getPointOnCircle(ell2, to);
		// Draw relationship type
		g2d.setColor(Color.darkGray);
		ZLine line = new ZLine(pointFrom, pointTo, source, destination);
		g2d.drawString(type, (int) line.getCenter().getX() - 40, (int) line.getCenter().getY());
		g2d.setColor(Color.RED);
		// Draw arrowheads
		ArrowHead arrowHead = new ArrowHead();
		AffineTransform at = AffineTransform
				.getTranslateInstance(pointTo.getX() - (arrowHead.getBounds2D().getWidth() / 2d), pointTo.getY());
		at.rotate(from, arrowHead.getBounds2D().getCenterX(), 0);
		arrowHead.transform(at);
		g2d.draw(arrowHead);
		return line;
	}

	public boolean missAllLines(List<ZLine> arr, int count, double x, double y) {
		if (count >= arr.size())
			return true;
		if (arr.get(count).ptLineDist(x, y) < .5)
			return false;
		return missAllLines(arr, count + 1, x, y);
	}

	public ZLine lineItHit(List<ZLine> arr, double x, double y) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).ptLineDist(x, y) < .5)
				return arr.get(i);
		return null;
	}

	public boolean missAllLines2(List<ZLine> arr, int count, double x, double y) {
		if (count >= arr.size())
			return true;
		if (arr.get(count).getBounds2D().contains(x, y))
			return false;
		return missAllLines(arr, count + 1, x, y);
	}

	public ZLine lineItHit2(List<ZLine> arr, double x, double y) {
		for (int i = 0; i < arr.size(); i++)
			if (arr.get(i).getBounds2D().contains(x, y))
				return arr.get(i);
		return null;
	}

	public void loadInstance(Instance input) {
		thisInstance = input;
		repaint();
	}

	public void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Font font = new Font("Serif", Font.BOLD, 20);
		g2d.setFont(font);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		arr.clear();
		for (int i = 0; i < thisInstance.classList.size(); i++) {
			arr.add(new ZEllipse(thisInstance.classList.get(i).point.x, thisInstance.classList.get(i).point.y, 80, 80,
					thisInstance.classList.get(i).getName(), thisInstance));
		}

		
		IteratorList.Iterator iter = arr.getIterator();
		for (iter.first(); !iter.isDone(); iter.next()) {
			g2d.setPaint(new Color(0, 0, 0));
			if (iter.currentValue() != null && iter.currentValue().className != null) {
				g2d.drawString(iter.currentValue().className, (int) iter.currentValue().getX() + 10,
						(int) iter.currentValue().getY() + 100);

			}
			if (thisInstance.highlight != null) {
				if (iter.currentValue().className.equals(thisInstance.highlight.getName())) {
					g2d.setPaint(new Color(255, 0, 0));
					g2d.draw(new ZRectangle((float) iter.currentValue().getX(), (float) iter.currentValue().getY(), 80,
							80));
				}
			}

			g2d.setPaint(new Color(0, 0, 200));
			g2d.fill(iter.currentValue());
		}

		lnLst.clear();
		for (int i = 0; i < thisInstance.classList.size(); i++) {
			for (int j = 0; j < thisInstance.classList.get(i).relationshipList.size(); j++) {
				ZEllipse source = getEll(thisInstance.classList.get(i).relationshipList.get(j).getSource());
				ZEllipse destination = getEll(thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				String type = thisInstance.classList.get(i).relationshipList.get(j).getType();
				g2d.setColor(Color.RED);
				ZLine line = addLine(source, destination, g2d, type,
						thisInstance.classList.get(i).relationshipList.get(j).getSource(),
						thisInstance.classList.get(i).relationshipList.get(j).getDestination());
				if (source == null || destination == null || source.equals(destination))
					continue;
				g2d.draw(line);
				lnLst.add(line);
			}
		}

		if (source != null) {
			g2d.setPaint(new Color(255, 0, 0));
			g2d.drawString("Source", (int) source.getX() + 15, (int) source.getY() + 47);
		}
		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {
		pubG = g;
		super.paintComponent(g);

		doDrawing(g);
	}

	public class ArrowHead extends Path2D.Double {

		public ArrowHead() {
			int size = 10;
			moveTo(0, size);
			lineTo(size / 2, 0);
			lineTo(size, size);
		}

	}

	class ZRectangle extends Rectangle2D.Float {

		public ZRectangle(float x, float y, float width, float height) {

			setRect(x, y, width, height);
		}

		public boolean isHit(float x, float y) {

			return getBounds2D().contains(x, y);
		}

		public void addX(float x) {

			this.x += x;
		}

		public void addY(float y) {

			this.y += y;
		}

		public void addWidth(float w) {

			this.width += w;
		}

		public void addHeight(float h) {

			this.height += h;
		}

	}

	
	
	class MovingAdapter extends MouseAdapter {

		private int x;
		private int y;

		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			doMove(e);
		}

		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				if (whatItHit(arr) != null) {
					thisMemento.add(thisInstance);
					ZEllipse hit = whatItHit(arr);
					String newName;
					try {
						newName = JOptionPane.showInputDialog("Please enter a new class name!", hit.className);
						if (newName != null) {
							Classes changed = thisInstance.getClass(hit.className);
							changed.rename(hit.className, newName);
							thisFrame.classModel.removeElement(hit.className);
							hit.className = newName;
							thisFrame.classModel.addElement(newName);
						}
					} catch (NullPointerException e2) {
					}
					repaint();
					return;
				}

			} else if (e.getButton() == MouseEvent.BUTTON3 || e.isShiftDown() ) {
				if (!missAllLines(lnLst, 0, (double) x, (double) y)) {
					thisMemento.add(thisInstance);
					ZLine line = lineItHit(lnLst, x, y);
					if (JOptionPane.showConfirmDialog(null, "Delete Relationship?", "Delete Relationship",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						thisInstance.getClass(line.source).removeRelationship(line.source, line.destination);
						thisInstance.getClass(line.destination).removeRelationship(line.source, line.destination);
						lnLst.remove(line);
						repaint();
					} else {
						return;
					}

					return;
				}

				if (whatItHit(arr) == null) {
					thisMemento.add(thisInstance);
					ZEllipse newEllipse = new ZEllipse(x - 40, y - 40, 80, 80, "", thisInstance);
					if (newEllipse.className != null && !newEllipse.className.equals("")) {
						arr.add(newEllipse);

						thisInstance.addClass(newEllipse.className);
						thisInstance.getClass(newEllipse.className).setLocation(x - 40, y - 40);
						thisFrame.classModel.addElement(newEllipse.className);
					}
					repaint();
				} else {
					ZEllipse hit = whatItHit(arr);
					thisMemento.add(thisInstance);
					pntLst.add(hit);
					if (source == null) {
						source = hit.center();
						sourceName = hit.className;
					} else {
						Object[] types = { "Aggregation", "Composition", "Inheritance", "Realization" };
						String n = "";
						n = (String) JOptionPane.showInputDialog(null, "Relationship Type?", "Set Relationship Type",
								JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
						if (n == null || n.equals("")) {
						} else {
							thisInstance.getClass(hit.className).addRelationship(sourceName, hit.className, n);
							thisInstance.getClass(sourceName).addRelationship(sourceName, hit.className, n);
						}
						source = null;
					}
					repaint();
				}
				return;
			} else if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
				if (whatItHit(arr) == null) {
					return;
				}
				thisMemento.add(thisInstance);
				ZEllipse hit = whatItHit(arr);
				if (thisInstance.getClass(hit.className) != null) {
					thisInstance.setHighlight(thisInstance.getClass(hit.className));
					thisFrame.comboBox.setSelectedItem(thisInstance.getClass(hit.className).getName());
				}

				Classes thisClass;
				if (thisInstance.getClass((String) thisFrame.comboBox.getSelectedItem()) != null) {
					thisFrame.fieldModel.removeAllElements();
					thisClass = thisInstance.getClass((String) thisFrame.comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.fieldList.size(); i++) {
						thisFrame.fieldModel.addElement(thisClass.fieldList.get(i).getName());
					}
				}

				if (thisInstance.getClass((String) thisFrame.comboBox.getSelectedItem()) != null) {
					thisFrame.relationshipsModel.removeAllElements();
					thisClass = thisInstance.getClass((String) thisFrame.comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.relationshipList.size(); i++) {

						if (!thisClass.relationshipList.get(i).getDestination().equals(thisClass.getName())) {
							thisFrame.relationshipsModel.addElement(thisClass.relationshipList.get(i).getDestination());
						}
					}
				}
				repaint();
			}

		}

		public ZEllipse whatItHit(IteratorList arr) {
			IteratorList.Iterator iter = arr.getIterator();
			for (iter.first(); !iter.isDone(); iter.next()) {
				if (iter.currentValue().isHit(x, y)) {
					return iter.currentValue();
				}
			}
			return null;
		}

		private void doMove(MouseEvent e) {

			int dx = e.getX() - x;
			int dy = e.getY() - y;

			IteratorList.Iterator iter = arr.getIterator();
			for (iter.first(); !iter.isDone(); iter.next()) {
				ZEllipse thisOne = iter.currentValue();
				if (thisOne.isHit(x, y)) {
					thisOne.addX(dx);
					thisOne.addY(dy);
					repaint();
				}
			}

			x += dx;
			y += dy;
		}
	}

	class ScaleHandler implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {

			doScale(e);
		}

		private void doScale(MouseWheelEvent e) {

			int x = e.getX();
			int y = e.getY();

			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
				IteratorList.Iterator iter = arr.getIterator();
				for (iter.first(); !iter.isDone(); iter.next()) {
					ZEllipse thisOne = iter.currentValue();
					float amount = e.getWheelRotation() * 5f;
					if (thisOne.isHit(x, y)) {
						thisOne.addWidth(amount);
						thisOne.addHeight(amount);
						repaint();
					}
				}

			}
		}
	}
}

public class MovingScalingEx extends JFrame {

	public Memento thisMemento = new Memento();
	public Instance thisInstance;
	public static int panelHeight = 800;
	public static int panelWidth = 1200;
	public int keycode = -1;

	public void resize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		panelHeight = (int) Math.min(screenSize.getHeight() - 50, panelHeight);
		panelWidth = (int) Math.min(screenSize.getWidth() - 300, panelWidth);
	}

	public MovingScalingEx(Instance input) {

		thisInstance = input;
	}

	public void setFrame(Frame input2) {
		initUI(thisInstance, input2);
	}

	public void initUI(Instance input, Frame input2) {

		Surface surface = new Surface(input);
		surface.setFrame(input2);
		input2.setSurface(surface, this);
		getContentPane().add(surface);

		setTitle("Test Graphics");
		setSize(panelWidth, panelHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				Instance thisInstance = new Instance();
				thisInstance.addClass("class1");
				thisInstance.getClass("class1").addField("field1", "String");
				thisInstance.addClass("class2");
				thisInstance.addClass("class3");
				thisInstance.addClass("class4");
				List<Parameters> params = new ArrayList<Parameters>();
				params.add(new Parameters("param1", "float"));
				thisInstance.getClass("class1").setLocation(40, 40);
				thisInstance.getClass("class2").setLocation(80, 140);
				thisInstance.getClass("class3").setLocation(140, 240);
				thisInstance.getClass("class4").setLocation(540, 540);
				thisInstance.getClass("class3").addMethod("method1", "int", params);
				thisInstance.getClass("class3").addRelationship("class3", "class2", "Aggregation");
				thisInstance.getClass("class2").addRelationship("class3", "class2", "Aggregation");
				thisInstance.getClass("class2").addRelationship("class1", "class2", "Aggregation");
				thisInstance.getClass("class1").addRelationship("class1", "class2", "Aggregation");
				// thisInstance.setHighlight(thisInstance.getClass("class2"));

				MovingScalingEx ex = new MovingScalingEx(thisInstance);
				ex.resize();
				Frame menuFrame = new Frame(thisInstance, ex);
				ex.setFrame(menuFrame);
				ex.setVisible(true);
				menuFrame.setSize(300, 900);
				menuFrame.setVisible(true);
			}
		});
	}
}

class Frame extends JFrame {
	Instance thisInstance;
	public JComboBox comboBox;
	MovingScalingEx thisObject;
	public Memento thisMemento;
	public JComboBox comboBox_1_1_1;
	public JComboBox comboBox_1;
	public JComboBox comboBox_1_1;
	public Surface thisSurface;
	public MovingScalingEx mainClass;
	public static int panelHeight = 800;
	public static int panelWidth = 300;

	public DefaultComboBoxModel<String> classModel;
	public DefaultComboBoxModel<String> fieldModel;
	public DefaultComboBoxModel<String> fieldTypeModel;
	public DefaultComboBoxModel<String> relationshipsModel;
	public DefaultComboBoxModel<String> relationshipsTypeModel;
	public DefaultComboBoxModel<String> methodsModel;
	public DefaultComboBoxModel<String> methodsTypeModel;
	public DefaultComboBoxModel<String> paramsModel;
	public DefaultComboBoxModel<String> paramsTypeModel;

	public Frame(Instance input, MovingScalingEx input2) {
		super("Control Frame");
		thisMemento = input2.thisMemento;
		thisInstance = input;
		thisObject = input2;
		// initialization code...
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
		for (int i = 0; i < thisInstance.classList.size(); i++) {
			classModel.addElement(thisInstance.classList.get(i).getName());
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double sizeChangeRatio = 1 - ((double) (panelHeight - screenSize.getHeight()) / panelHeight);

		if (sizeChangeRatio < 0)
			sizeChangeRatio = 1;
		int resizeHeight = (int) (43 * (sizeChangeRatio));

		setSize(panelWidth, panelHeight);

		getContentPane().setLayout(null);

		comboBox = new JComboBox<String>(classModel);
		comboBox.putClientProperty("i", 0);
		comboBox.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox.getClientProperty("i")), 234,
				resizeHeight);
		comboBox.setModel(classModel);

		if (thisInstance.highlight != null) {
			for (int i = 0; i < thisInstance.classList.size(); i++) {
				if (thisInstance.highlight.equals(thisInstance.classList.get(i))) {
					comboBox.setSelectedItem(thisInstance.classList.get(i).getName());
				}
			}
		}
		Observer comboObserver = new Observer(comboBox, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisInstance.setHighlight(thisInstance.getClass((String) comboBox.getSelectedItem()));
				thisObject.repaint();

				Classes thisClass;
				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					fieldModel.removeAllElements();
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.fieldList.size(); i++) {
						fieldModel.addElement(thisClass.fieldList.get(i).getName());
					}
				}

				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					relationshipsModel.removeAllElements();
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.relationshipList.size(); i++) {

						if (!thisClass.relationshipList.get(i).getDestination().equals(thisClass.getName())) {
							relationshipsModel.addElement(thisClass.relationshipList.get(i).getDestination());
						}
					}
				}

				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					methodsModel.removeAllElements();
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					for (int i = 0; i < thisClass.methodList.size(); i++) {
						methodsModel.addElement(thisClass.methodList.get(i).getName());
					}
				}
			}
		});
		getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("Class");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 0, 234, 27);
		getContentPane().add(lblNewLabel);

		comboBox_1 = new JComboBox(relationshipsModel);
		comboBox_1.putClientProperty("i", 3);
		comboBox_1.setBounds(22, (int) (27+(resizeHeight+27)*(int) comboBox_1.getClientProperty("i")), 172, resizeHeight);

		Observer combo_1Observer = new Observer(comboBox_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Classes thisClass;
				Relationships thisRelationship;
				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					relationshipsTypeModel.removeAllElements();
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					if (thisClass.getRelationship(thisClass.getName(), (String) comboBox_1.getSelectedItem()) != null) {
						thisRelationship = thisClass.getRelationship(thisClass.getName(),
								(String) comboBox_1.getSelectedItem());
						relationshipsTypeModel.addElement(thisRelationship.getType());
					}
				}
			}
		});
		getContentPane().add(comboBox_1);

		JLabel lblRelationshipDestination = new JLabel("Relationship Destination");
		lblRelationshipDestination.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationshipDestination.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRelationshipDestination.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1.getClientProperty("i")),
				234, 27);
		getContentPane().add(lblRelationshipDestination);

		comboBox_1_1.putClientProperty("i", 4);
		comboBox_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1.getClientProperty("i")), 234,
				resizeHeight);
		getContentPane().add(comboBox_1_1);

		comboBox_1_1_1 = new JComboBox(fieldModel);
		comboBox_1_1_1.putClientProperty("i", 1);
		comboBox_1_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1.getClientProperty("i")),
				172, resizeHeight);
		Observer combo_1_1_1Observer = new Observer(comboBox_1_1_1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Fields thisField;
				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					fieldTypeModel.removeAllElements();
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					if (thisClass.getField((String) comboBox_1_1_1.getSelectedItem()) != null) {
						thisField = thisClass.getField((String) comboBox_1_1_1.getSelectedItem());
						fieldTypeModel.addElement(thisField.getType());
					}
				}
			}
		});
		getContentPane().add(comboBox_1_1_1);

		JComboBox comboBox_1_1_2 = new JComboBox(methodsModel);
		comboBox_1_1_2.putClientProperty("i", 5);
		comboBox_1_1_2.setBounds(22, (int) (27+(resizeHeight+27)*(int) comboBox_1_1_2.getClientProperty("i")), 172, resizeHeight);
		Observer combo_1_1_2Observer = new Observer(comboBox_1_1_2, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Methods thisMethod;
				methodsTypeModel.removeAllElements();
				paramsModel.removeAllElements();
				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					if (thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem()) != null) {
						thisMethod = thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem());
						methodsTypeModel.addElement(thisMethod.getType());
						if (thisMethod.paramList != null) {
							for (int i = 0; i < thisMethod.paramList.size(); i++) {
								paramsModel.addElement(thisMethod.paramList.get(i).getName());
							}
						}
					}

				}
			}
		});

		getContentPane().add(comboBox_1_1_2);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethod.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMethod.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2.getClientProperty("i")), 234, 27);
		getContentPane().add(lblMethod);

		JComboBox comboBox_1_1_2_1 = new JComboBox(paramsModel);
		comboBox_1_1_2_1.putClientProperty("i", 7);
		comboBox_1_1_2_1.setBounds(22, (int) (27+(resizeHeight+27)*(int) comboBox_1_1_2_1.getClientProperty("i")), 172, resizeHeight);
		Observer combo_1_1_2_1Observer = new Observer(comboBox_1_1_2_1,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Classes thisClass;
				Parameters thisParam;
				Methods thisMethod;
				paramsTypeModel.removeAllElements();
				if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
					thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
					if (thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem()) != null) {
						thisMethod = thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem());
						if (thisMethod.getParam((String) comboBox_1_1_2_1.getSelectedItem()) != null) {
							thisParam = thisMethod.getParam((String) comboBox_1_1_2_1.getSelectedItem());
							paramsTypeModel.addElement(thisParam.getType());
						}
					}

				}
			}
		});
		getContentPane().add(comboBox_1_1_2_1);

		JLabel lblParameter = new JLabel("Parameter");
		lblParameter.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameter.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblParameter.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_1.getClientProperty("i")), 234,
				27);
		getContentPane().add(lblParameter);

		JComboBox comboBox_1_1_2_2 = new JComboBox(methodsTypeModel);
		comboBox_1_1_2_2.putClientProperty("i", 6);
		comboBox_1_1_2_2.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_2.getClientProperty("i")),
				234, resizeHeight);
		getContentPane().add(comboBox_1_1_2_2);

		JLabel lblMethodType = new JLabel("Method Type");
		lblMethodType.setHorizontalAlignment(SwingConstants.CENTER);
		lblMethodType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMethodType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_2.getClientProperty("i")), 234,
				27);
		getContentPane().add(lblMethodType);

		JComboBox comboBox_1_1_1_1 = new JComboBox(fieldTypeModel);
		comboBox_1_1_1_1.putClientProperty("i", 2);
		comboBox_1_1_1_1.setBounds(22, (int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_1_1.getClientProperty("i")),
				234, resizeHeight);
		getContentPane().add(comboBox_1_1_1_1);

		JLabel lblFieldType = new JLabel("Field Type");
		lblFieldType.setHorizontalAlignment(SwingConstants.CENTER);
		lblFieldType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFieldType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_1_1.getClientProperty("i")), 234,
				27);
		getContentPane().add(lblFieldType);

		JLabel lblField = new JLabel("Field");
		lblField.setHorizontalAlignment(SwingConstants.CENTER);
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblField.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_1.getClientProperty("i")), 234, 27);
		getContentPane().add(lblField);

		JLabel lblRelationshipType = new JLabel("Relationship Type");
		lblRelationshipType.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelationshipType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRelationshipType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1.getClientProperty("i")), 234,
				27);
		getContentPane().add(lblRelationshipType);

		JComboBox comboBox_1_1_2_1_1 = new JComboBox(paramsTypeModel);
		comboBox_1_1_2_1_1.putClientProperty("i", 8);
		comboBox_1_1_2_1_1.setBounds(22,
				(int) (27 + (resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")), 234, resizeHeight);
		getContentPane().add(comboBox_1_1_2_1_1);

		JLabel lblParameterType = new JLabel("Parameter Type");
		lblParameterType.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameterType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblParameterType.setBounds(22, (int) ((resizeHeight + 27) * (int) comboBox_1_1_2_1_1.getClientProperty("i")),
				234, 27);
		getContentPane().add(lblParameterType);
        JButton btnNewButton = new JButton("Add");
        btnNewButton.setBounds(204, (int) (27+(resizeHeight+27)*(int) comboBox_1_1_1.getClientProperty("i")), 51, resizeHeight);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
        Observer btnNewObserver = new Observer(btnNewButton, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = "Please enter the field name and type.";
        		JTextField fieldName = new JTextField("Field Name");
        		fieldName.addFocusListener((FocusListener) new FocusListener() {
        		    public void focusGained(FocusEvent e) {
        		    	fieldName.setText("");
        		    }

        		    public void focusLost(FocusEvent e) {
        		    }
        		});
        		
        		
				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));

        		int result = JOptionPane.showOptionDialog(null, new Object[] {message, fieldName, type},
        			      "Add Field", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        		if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null && !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
    				thisMemento.add(thisInstance);
        			if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
    					Classes thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
    					thisClass.addField(fieldName.getText(), (String) type.getSelectedItem());
    					fieldModel.addElement(fieldName.getText());
    					repaint();
        			}
        		}
        	}
        });
        getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Add");
        btnNewButton_1.setBounds(205, (int) (27+(resizeHeight+27)*(int) comboBox_1_1_2.getClientProperty("i")), 51, resizeHeight);
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        Observer btnNew_1Observer = new Observer(btnNewButton_1, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = "Please enter the method name and type.";
        		JTextField fieldName = new JTextField("Method Name");
        		fieldName.addFocusListener((FocusListener) new FocusListener() {
        		    public void focusGained(FocusEvent e) {
        		    	fieldName.setText("");
        		    }

        		    public void focusLost(FocusEvent e) {
        		    }
        		});

				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));

        		int result = JOptionPane.showOptionDialog(null, new Object[] {message, fieldName, type},
        			      "Add Method", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        		if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null && !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
    				thisMemento.add(thisInstance);
        			if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
    					Classes thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
    					thisClass.addMethod(fieldName.getText(), (String) type.getSelectedItem(), new ArrayList<Parameters>());
    					methodsModel.addElement(fieldName.getText());
    					repaint();
        			}
        		}
        	}
        });
        getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Add");
        btnNewButton_2.setBounds(204, (int) (27+(resizeHeight+27)*(int) comboBox_1_1_2_1.getClientProperty("i")), 52, resizeHeight);
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        Observer btnNewObserver_2 = new Observer(btnNewButton_2, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = "Please enter the parameter name and type.";
        		JTextField fieldName = new JTextField("Parameter Name");
        		fieldName.addFocusListener((FocusListener) new FocusListener() {
        		    public void focusGained(FocusEvent e) {
        		    	fieldName.setText("");
        		    }

        		    public void focusLost(FocusEvent e) {
        		    }
        		});


				String[] types = { "int", "float", "String", "void", "double" };
				JComboBox type = new JComboBox(new DefaultComboBoxModel<String>(types));

				int result = JOptionPane.showOptionDialog(null, new Object[] { message, fieldName, type },
						"Add Parameter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.OK_OPTION && fieldName.getText() != null && type.getSelectedItem() != null
						&& !fieldName.getText().equals("") && !((String) type.getSelectedItem()).equals("")) {
					thisMemento.add(thisInstance);
        			 if (thisInstance.getClass((String) comboBox.getSelectedItem()) != null) {
    					Classes thisClass = thisInstance.getClass((String) comboBox.getSelectedItem());
    					if (thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem()) != null) {
    						Methods thisMethod = thisClass.getMethod((String) comboBox_1_1_2.getSelectedItem());
    						thisMethod.addParam(fieldName.getText(), (String) type.getSelectedItem());
    						paramsModel.addElement(fieldName.getText());
    					repaint();
    					} else {
    						JOptionPane.showInternalMessageDialog(null, "A method must exist prior to adding a parameter.",
    		        				"Adding Parameter", JOptionPane.ERROR_MESSAGE);
    					}
        			}
        		}
        	}
        });
        getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Add");
        btnNewButton_3.setBounds(204, (int) (27+(resizeHeight+27)*(int) comboBox_1.getClientProperty("i")), 51, resizeHeight);
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        Observer btnNewObserver_3 = new Observer(btnNewButton_3, new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		JOptionPane.showInternalMessageDialog(null, "To add a relationship, right-click on the source class, then right-click on the destination class, then choose the relationship type.",
        				"Adding Relationships", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        getContentPane().add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("All Class Contents");
        btnNewButton_4.setBounds(22, (int) (10+resizeHeight+27+(resizeHeight+27)*(int) comboBox_1_1_2_1_1.getClientProperty("i")), 119, resizeHeight/2);
        getContentPane().add(btnNewButton_4);
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
        Observer btnNewObserver_4 = new Observer(btnNewButton_4, new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			thisInstance.showContents();
    		}
    	});
        
        JButton btnNewButton_5 = new JButton("Undo");
        btnNewButton_5.setBounds(22, (int) (15+resizeHeight+54+(resizeHeight+27)*(int) comboBox_1_1_2_1_1.getClientProperty("i")), 119, resizeHeight/2);
        btnNewButton_5.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Instance newInstance = thisMemento.undo(thisSurface.thisInstance);
    			if (newInstance != null) {
    				thisSurface.thisInstance = copyInstance(newInstance);
    				thisInstance = copyInstance(newInstance);	
        			thisSurface.revalidate();
        			thisSurface.repaint();
    			}
    		}});
        getContentPane().add(btnNewButton_5);
        
        JButton btnNewButton_6 = new JButton("Redo");
        btnNewButton_6.setBounds(155, (int) (15+resizeHeight+54+(resizeHeight+27)*(int) comboBox_1_1_2_1_1.getClientProperty("i")), 119, resizeHeight/2);
        Observer btnNewObserver_6 = new Observer(btnNewButton_6, new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Instance newInstance = thisMemento.redo();
    			if (newInstance != null) {
    				thisSurface.thisInstance = copyInstance(newInstance);
    				thisInstance = copyInstance(newInstance);
    				thisSurface.revalidate();
    				thisSurface.repaint();
    			}
    		}});
        getContentPane().add(btnNewButton_6);
        
		JSeparator separator = new JSeparator();
		separator.setBounds(0, (int) (5+resizeHeight+27+(resizeHeight+27)*(int) comboBox_1_1_2_1_1.getClientProperty("i")), 284, 2);

		getContentPane().add(separator);

		setVisible(true);
	}

	public Instance copyInstance(Instance input) {
		Instance fakeInstance = new Instance();

		for (int i = 0; i < input.classList.size(); i++) {
			fakeInstance.addClass(input.classList.get(i).getName());
			fakeInstance.classList.get(i).setLocation(input.classList.get(i).point.x, input.classList.get(i).point.y);
			for (int j = 0; j < input.classList.get(i).fieldList.size(); j++) {
				fakeInstance.classList.get(i).addField(input.classList.get(i).fieldList.get(j).getName(),
						input.classList.get(i).fieldList.get(j).getType());
			}
			for (int j = 0; j < input.classList.get(i).relationshipList.size(); j++) {
				fakeInstance.classList.get(i).addRelationship(
						input.classList.get(i).relationshipList.get(j).getSource(),
						input.classList.get(i).relationshipList.get(j).getDestination(),
						input.classList.get(i).relationshipList.get(j).getType());
			}
			for (int j = 0; j < input.classList.get(i).methodList.size(); j++) {
				List<Parameters> newParamList = new ArrayList<Parameters>();
				for (int k = 0; k < input.classList.get(i).methodList.get(j).paramList.size(); k++) {
					newParamList.add(new Parameters(input.classList.get(i).methodList.get(j).paramList.get(k).getName(),
							input.classList.get(i).methodList.get(j).paramList.get(k).getType()));
				}
				fakeInstance.classList.get(i).addMethod(input.classList.get(i).methodList.get(j).getName(),
						input.classList.get(i).methodList.get(j).getType(), newParamList);
			}
		}
		fakeInstance.setHighlight(input.highlight);

		return fakeInstance;
	}

	public void setSurface(Surface input, MovingScalingEx input2) {
		this.thisSurface = input;
		this.mainClass = input2;
	}

}

class ZEllipse extends Ellipse2D.Float {

	public String className;
	public List<ZEllipse> destinations;
	public Classes thisClass;

	public ZEllipse(float x, float y, float width, float height, String name, Instance thisInstance) {
		destinations = new ArrayList<ZEllipse>();
		setFrame(x, y, width, height);
		if (name == null || name.equals("")) {
			String input = JOptionPane.showInputDialog("Please enter a class name!");
			if (input == null || input.equals("")) {
				return;
			}
			this.className = input;
		} else {
			this.className = name;
		}
		thisClass = thisInstance.getClass(name);
	}
	
	public Point2D center() {
		return new Point2D.Float(this.x, this.y);
	}

	public boolean isHit(float x, float y) {

		return getBounds2D().contains(x, y);
	}

	public void addX(float x) {

		this.x += x;
		if (this.x < 0) {
			this.x = 0;
		}
		if (this.x >= MovingScalingEx.panelWidth - 100) {
			this.x = MovingScalingEx.panelWidth - 100;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	public void addY(float y) {

		this.y += y;
		if (this.y >= MovingScalingEx.panelHeight - 150) {
			this.y = MovingScalingEx.panelHeight - 150;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		thisClass.setLocation((int) this.x, (int) this.y);
	}

	public void addWidth(float w) {

		this.width += w;
	}

	public void addHeight(float h) {

		this.height += h;
	}

	public void setName(String input) {
		this.className = input;
	}

}
