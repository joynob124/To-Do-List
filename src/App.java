import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App extends JFrame implements ActionListener{

	public static void main(String[] args) {
		new App();
	}

    class RoundedButton extends JButton {
        private Color bgColor;
        private Color hoverColor = new Color(150, 50, 150);

        public RoundedButton(String text, Color bg) {
            super(text);
            this.bgColor = bg;
            setFocusable(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Consolas", Font.BOLD, 18));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color fillColor = bgColor;
            if (getModel().isPressed()) {
                fillColor = bgColor.darker();
            } else if (getModel().isRollover()) {
                fillColor = hoverColor;
            }

            g2.setColor(fillColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            String text = getText();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 3;
            g2.drawString(text, x, y);
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 50);
        }
    }

	
	ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icon.jpg"));
    JLabel gif = new JLabel(new ImageIcon(getClass().getResource("/resources/girl.png")));
	JLabel titleLabel= new JLabel("My To - Do List");
	JLabel taskLabel= new JLabel();
	JPanel panel1= new JPanel();
	JPanel panel2= new JPanel();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> taskList = new JList<String>(listModel);
	JLayeredPane imageLayer= new JLayeredPane();
	JTextField inputText= new JTextField("Enter Your Task");
	JButton add= new RoundedButton("ADD TASK", new Color(114, 11, 107));
	JButton delete= new RoundedButton("DELETE TASK", new Color(114, 11, 107));
	JButton delAll= new RoundedButton("DELETE ALL", new Color(114, 11, 107));
	JButton exit= new RoundedButton("EXIT", new Color(114, 11, 107));
	
	App(){
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
		inputText.setForeground(Color.GRAY);
		
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


        loadTasks();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveTasks();
                System.exit(0);
            }
        }
        );
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String taskText = inputText.getText().trim();
            if (!taskText.isEmpty() && !taskText.equals("Enter Your Task")) {
                listModel.addElement("●  " + taskText);
                inputText.setText("");
                saveTasks();
            }
        }else if (e.getSource() == delete) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
                saveTasks();
        }
        }else if (e.getSource() == delAll) {
            listModel.removeAllElements();
            saveTasks();
        }else if (e.getSource() == exit) {
            System.exit(0);
        }
	}

    private void loadTasks() {
        File file = new File("tasks.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                listModel.clear();
                while (scanner.hasNextLine()) {
                    String task = scanner.nextLine().trim();
                    if (!task.isEmpty()) {
                        listModel.addElement("●  " + task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"))) {
            for (int i = 0; i < listModel.size(); i++) {
                String element = listModel.getElementAt(i).toString();
                if (element.startsWith("●  ")) {
                    writer.println(element.substring(3));
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}

