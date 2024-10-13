import javax.swing.*;
import java.awt.*;

public class GameLogic {
	
	private static final int SIZE = 3;
	private enum Player {X, O}
	private Player currentPlayer = Player.X;
	private GameGUI gui;
	
	public GameLogic(GameGUI gui) {
		this.gui = gui;
	}
	
	
	
	public void handleButtonClick (JButton buttonClicked) {
		if (buttonClicked.getText().isEmpty()) {
			buttonClicked.setText(currentPlayer == Player.X ? "X" : "O");
			buttonClicked.setForeground(Color.WHITE);
			currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
			checkForWin();
		}
	}
	
	private void checkForWin() { // function checking for win

		if (checkRowsAndColumns() || checkDiagonals()) {
			return;
		}

		if (isBoardFull()) {
			showDrawMessage();
		}
	}

	private boolean checkRowsAndColumns() {
		JButton buttons[][] = gui.getButtons();
		
		for (int i = 0; i < SIZE; i++) {
			
			if (checkLine(buttons[i][0], buttons[i][1], buttons[i][2])) {
				showWinMessage(buttons[i][0].getText());
				return true;

			} else if (checkLine(buttons[0][i], buttons[1][i], buttons[2][i])) {
				showWinMessage(buttons[0][i].getText());
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals() {
		JButton buttons[][] = gui.getButtons();
		
		if (checkLine(buttons[0][0], buttons[1][1], buttons[2][2])
				|| checkLine(buttons[0][2], buttons[1][1], buttons[2][0])) {

			showWinMessage(buttons[1][1].getText());
			return true;
		}

		return false;
	}

	private boolean checkLine(JButton b1, JButton b2, JButton b3) {
		String text = b1.getText();
		return !text.isEmpty() && text.equals(b2.getText()) && text.equals(b3.getText());
	}
	
	
	private void resetBoard() {
		JButton buttons[][] = gui.getButtons();
		
		for (int row = 0; row < SIZE; row++) {

			for (int col = 0; col < SIZE; col++) {
				buttons[row][col].setText("");
				buttons[row][col].setForeground(Color.WHITE);
			}
		}
		currentPlayer = Player.X;
	}

	private boolean isBoardFull() {
		JButton buttons[][] = gui.getButtons();
		
		for (int row = 0; row < SIZE; row++) {

			for (int col = 0; col < SIZE; col++) {
				if (buttons[row][col].getText().isEmpty())
					return false;
			}
		}
		return true;
	}
	
	private void showEndMessage(String message) {
		int response = JOptionPane.showConfirmDialog(gui, message, "Game over", JOptionPane.YES_NO_OPTION);

		if (response == JOptionPane.YES_OPTION)
			resetBoard();
		else {
			System.exit(0);
		}
	}

	private void showWinMessage(String winner) {
		showEndMessage(winner + " wins!");
	}

	private void showDrawMessage() {
		showEndMessage("It's a draw!");
	}

}
