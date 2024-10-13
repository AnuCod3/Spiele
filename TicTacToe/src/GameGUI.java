import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame implements ActionListener{
	
	private static final int SIZE = 3;
	private JButton[][] buttons = new JButton[SIZE][SIZE];
	private GameLogic gameLogic = new GameLogic(this);

	

	public GameGUI() { // creating window and setting the settings
		setTitle("Tic Tac Toe");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel(new GridLayout(SIZE, SIZE, 5, 5));
		initializeButton(buttonPanel);
		add(buttonPanel);

		setVisible(true);
	}

	private void initializeButton(JPanel panel) { // creating/initializing the fields 3x3
		for (int row = 0; row < SIZE; row++) {

			for (int col = 0; col < SIZE; col++) {
				buttons[row][col] = new JButton("");
				buttons[row][col].setFont(new Font("Arial", Font.BOLD, 80));
				buttons[row][col].setBackground(Color.BLACK);
				buttons[row][col].setFocusPainted(false);
				buttons[row][col].setBorderPainted(true);
				buttons[row][col].addActionListener(this);
				panel.add(buttons[row][col]);

			}
		}
	}

	public void actionPerformed(ActionEvent e) { // setting field values by clicking and checking for win
		JButton buttonClicked = (JButton) e.getSource();
		gameLogic.handleButtonClick(buttonClicked);
	}
	
	public JButton[][] getButtons() {
		return buttons;
	}
	
}
