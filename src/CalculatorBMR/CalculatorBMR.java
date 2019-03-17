package CalculatorBMR;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Marcin Perka
 * @version 1.0
 * @since 2019-03-17
 * App which caluclates BMR, TDEE and macronutrients to maintain the weight at the current level.
 * BMR - Basal Metabolic Rate
 * TDEE - Total Daily Energy Expenditure
 */
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

	/**
	 * Creates frame with panel which have basic options to choose: Age, Height, Weight, Activity, Gender, BMR formula
	 */
	private static void createAndShowGui() {
		JFrame frame = new JFrame();
		JPanel calcPanel= new CalcPanel();
		frame.add(calcPanel);
		frame.setVisible(true);
		frame.setTitle("Calculator");
		frame.setResizable(false);
		frame.setForeground(Color.WHITE);
		frame.setBounds(200, 200, 290, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
