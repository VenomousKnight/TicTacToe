import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645793213727554194L;
	final int HEIGHT = 800;
	final int WIDTH = 600;
	Image icon = new ImageIcon("C:\\Users\\user\\Downloads\\Webp.net-resizeimage (2).png").getImage();
	
	JPanel bPane = new JPanel();
	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	JPanel gridPanel = new JPanel();
	
	JButton[][] buttons = new JButton[3][3];
	JButton restart = new JButton("restart");
	
	JPanel paneX = new JPanel();
	JLabel  labelX= new JLabel();
	JPanel paneO = new JPanel();
	JLabel labelO= new JLabel();
	
	
	String playerX = "X";
	String playerY = "O";
	
	int pointsX = 0;
	int pointsO = 0;
	
	
	String currentPlayer = playerX;
	boolean gameOver = false;
	
	int turns = 0;
	
	
	TicTacToe(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(HEIGHT, WIDTH);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setTitle("Tic Tac Toe");
		this.setIconImage(icon);
		
		label.setText("Tic Tac Toe");
		label.setFont(new Font("Arial", Font.BOLD, 50));
		label.setBackground(Color.LIGHT_GRAY);
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		//label.setVerticalTextPosition(JLabel.CENTER);
		
		panel.setPreferredSize(new Dimension(50 , 50));
		panel.setLayout(new BorderLayout());
		panel.add(label);
		
		gridPanel.setLayout(new GridLayout(3,3));
		gridPanel.setBackground(Color.gray);
		
		for(int r = 0; r <3; r++) {
			for(int c = 0; c < 3; c++) {
				JButton button = new JButton();
				buttons[r][c] = button;
				
				button.setBackground(Color.DARK_GRAY);
				button.setForeground(Color.white);
				button.setFocusable(false);
				button.setFont(new Font("Ariel", Font.BOLD, 100));
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(gameOver)return;
						
						JButton button = (JButton) e.getSource();
						
						if(button.getText()== "") {
							button.setText(currentPlayer);
							turns++;
							
							checkWinner();
							
							if(!gameOver) {
								currentPlayer = currentPlayer == playerX? playerY : playerX;
								label.setText(currentPlayer + "'s turn");
							}
							
						} 
					}
					
				});
				gridPanel.add(button);
			}
		}
		
		
		restart.setFocusable(false);
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				
				for(int i =0; i < 3; i++) {
					for(int j = 0; j < 3; j ++) {
						buttons[i][j].setText("");
						buttons[i][j].setBackground(Color.DARK_GRAY);
						buttons[i][j].setForeground(Color.white);
					}
				}
				
				turns = 0;
				label.setText(currentPlayer+ "'s turn");
				
				gameOver = false;				
				
			}
			
		});
		
		labelX.setText("X = "+ pointsX + " points ");
		labelO.setText("O = "+ pointsO + " points ");
		
		paneX.add(labelX);
		paneO.add(labelO);
		
		bPane.setLayout(new FlowLayout());
		bPane.setPreferredSize(new Dimension(50, 50));
		bPane.add(restart);
		
		
		
		this.add(bPane, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.NORTH);
		this.add(paneO, BorderLayout.EAST);
		this.add(paneX, BorderLayout.WEST);
		this.add(gridPanel, BorderLayout.CENTER);
		this.setVisible(true);

	}
	
	void checkWinner() {

		for(int r = 0; r < 3; r++) {
			if(buttons[r][0].getText() == "")continue;
			
			if(buttons[r][0].getText() == buttons[r][1].getText() &&
			   buttons[r][1].getText() == buttons[r][2].getText()) {
				gameOver = true;

				for(int i = 0; i < 3; i++) {
					setWinner(buttons[r][i]);
				}
			
				if(currentPlayer == playerX) {
					currentPlayer = playerY;
					pointsX++;
					labelX.setText("X = "+ pointsX + " points ");
				}
				else if(currentPlayer == playerY) {
					currentPlayer = playerX;
					pointsO++;
					labelO.setText("O = "+ pointsO + " points ");
				}
				return;
			}
			
			
			
		}
		
		
		
		for(int c = 0; c < 3; c++) {
			if(buttons[0][c].getText() == "")continue;
			
			if(buttons[0][c].getText() == buttons[1][c].getText() &&
			   buttons[1][c].getText() == buttons[2][c].getText()) {
				gameOver = true;
				
				
				for(int i = 0; i < 3; i++) {
					setWinner(buttons[i][c]);
				}
				if(currentPlayer == playerX) {
					currentPlayer = playerY;
					pointsX++;
					labelX.setText("X = "+ pointsX + " points ");
				}
				else if(currentPlayer == playerY) {
					currentPlayer = playerX;
					pointsO++;
					labelO.setText("O = "+ pointsO + " points ");
				}
				return;
			}
			
		}
		
		if(buttons[0][0].getText() == buttons[1][1].getText() && 
				   buttons[2][2].getText() == buttons[1][1].getText() && buttons[0][0].getText() != "" ) {
					label.setText(currentPlayer + " wins");
					
					for(int i = 0; i < 3; i++) {
						setWinner(buttons[i][i]);
					}
					if(currentPlayer == playerX) {
						currentPlayer = playerY;
						pointsX++;
						labelX.setText("X = "+ pointsX + " points ");
					}
					else if(currentPlayer == playerY) {
						currentPlayer = playerX;
						pointsO++;
						labelO.setText("O = "+ pointsO + " points ");
					}
					gameOver = true;
					return;
				}
		if(buttons[0][2].getText() == buttons[1][1].getText() && 
				   buttons[2][0].getText() == buttons[1][1].getText() && buttons[0][2].getText() != "") {
					label.setText(currentPlayer + " wins");
					
					int x = 0;
					int y = 2;
					for(int i = 0; i < 3; i++) {
						setWinner(buttons[x][y]);
						x++;
						y--;
					}
					if(currentPlayer == playerX) {
						currentPlayer = playerY;
						pointsX++;
						labelX.setText("X = "+ pointsX + " points ");
					}
					else if(currentPlayer == playerY) {
						currentPlayer = playerX;
						pointsO++;
						labelO.setText("O = "+ pointsO + " points ");
					}
					gameOver = true;
					return;
				}
		
	
		
		if(turns == 9){
			
			gameOver = true;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++)
					setDraw(buttons[i][j]);
			}
			return;
		}
	}
	
	void setWinner(JButton b) {
		b.setForeground(Color.green);
		b.setBackground(Color.black);
		label.setText(currentPlayer + " wins");
		
	}
	
	
	void setDraw(JButton b) {
		b.setForeground(Color.yellow);
		b.setBackground(Color.black);
		label.setText("IT'S A TIE!");
		
		
	}
}
