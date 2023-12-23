package toDo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToDoList extends JFrame implements ActionListener{

	public static void main(String[] args) {
		new ToDoList();

	}
	
	ImageIcon icon= new ImageIcon("icon.png");
	JLabel gif= new JLabel(new ImageIcon("gif.png"));
	JLabel titleLabel= new JLabel("My To - Do List");
	JLabel taskLabel= new JLabel();
	JPanel panel1= new JPanel();
	JPanel panel2= new JPanel();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> taskList = new JList<String>(listModel);
	JLayeredPane imageLayer= new JLayeredPane();
	JTextField inputText= new JTextField("Enter Your Task");
	JButton add= new JButton("ADD TASK");
	JButton delete= new JButton("DELETE TASK");
	JButton delAll= new JButton("DELETE ALL");
	JButton exit= new JButton("EXIT");
	
	ToDoList(){
		this.setVisible(true);
		this.setSize(600, 650);
		this.setTitle("TO-DO List");
		this.setLayout(null);
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(new Color(221,160,221));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		titleLabel.setBounds(0, 0,600,100);
		titleLabel.setVerticalTextPosition(JLabel.CENTER);
		titleLabel.setForeground(new Color(150,100,100));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Segoe Script",Font.BOLD,40));
		
		panel1.setBackground(new Color(221,160,221));
		panel1.setBounds(30,140, 270,400);
		panel1.setLayout(null);
		
		inputText.setBounds(10, 30, 200, 50);
		inputText.setBackground(Color.WHITE);
		inputText.setFont(new Font("Consolas",Font.PLAIN,18));
		inputText.setCaretColor(Color.GREEN);
		inputText.setForeground(Color.BLUE);
		
		add.setBounds(10, 100, 200, 50);
		add.setFont(new Font("Consolas",Font.PLAIN+ Font.BOLD,18));
		add.setBackground(new Color(114,11,107));
		add.setForeground(Color.WHITE);
		add.setFocusable(false);
		add.addActionListener(this);
		
		delete.setBounds(10, 170, 200, 50);
		delete.setFont(new Font("Consolas",Font.PLAIN+ Font.BOLD,18));
		delete.setBackground(new Color(114,11,107));
		delete.setForeground(Color.WHITE);
		delete.addActionListener(this);
		delete.setFocusable(false);
		
		delAll.setBounds(10, 240, 200, 50);
		delAll.setFont(new Font("Consolas",Font.PLAIN+ Font.BOLD,18));
		delAll.setBackground(new Color(114,11,107));
		delAll.setForeground(Color.WHITE);
		delAll.addActionListener(this);
		delAll.setFocusable(false);
		
		exit.setBounds(10, 310, 200, 50);
		exit.setFont(new Font("Consolas",Font.PLAIN+ Font.BOLD,18));
		exit.setBackground(new Color(114,11,107));
		exit.setForeground(Color.WHITE);
		exit.addActionListener(this);
		exit.setFocusable(false);
		
		gif.setBounds(450, 20, 100,200);
	
		taskLabel.setText("====Tasks====\n");
		taskLabel.setForeground(Color.black);
		taskLabel.setFont(new Font("Consolas",Font.ITALIC,30));
		taskLabel.setHorizontalTextPosition(JLabel.CENTER);
		taskLabel.setVerticalTextPosition(JLabel.CENTER);
		taskList.setBackground(new Color(221,160,221));
		taskList.setFont(new Font("Consolas",Font.PLAIN,18));
		
		listModel.addElement("\n\n");
		
		panel2.setBounds(300,200,270,400);
		panel2.setBackground(new Color(221,160,221));
		
		this.add(titleLabel,BorderLayout.NORTH);
		panel1.add(inputText);
		panel1.add(add);
		panel1.add(delete);
		panel1.add(delAll);
		panel1.add(exit);
		this.add(gif);
		this.add(panel1);
		this.add(panel2);
		panel2.add(taskLabel);
		panel2.add(taskList);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == add) {
			 String taskText = inputText.getText();
			 if (!taskText.isEmpty()) {
		            listModel.addElement("\n●  " +taskText);
		            inputText.setText("");
		        }
		 }else if (e.getSource() == delete) {
		        int selectedIndex = taskList.getSelectedIndex();
		        if (selectedIndex != -1) {
		            listModel.remove(selectedIndex);
		        }
		 }else if (e.getSource() == delAll) {
		        listModel.removeAllElements();
		 }else if (e.getSource() == exit) {
		        System.exit(0);
		    }
	}
}
