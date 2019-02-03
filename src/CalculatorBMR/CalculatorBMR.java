package CalculatorBMR;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorBMR {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void createAndShowGui() {
		JFrame frame = new JFrame();
		JPanel calcPanel= new CalcPanel();
		frame.add(calcPanel);
		frame.setVisible(true);
		frame.setTitle("Calculator");
		frame.setResizable(false);
		frame.setForeground(Color.WHITE);
		frame.setBounds(100, 100, 290, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
