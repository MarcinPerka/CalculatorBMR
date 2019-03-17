package CalculatorBMR;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;


/**
 * A class whose object is responsible for performing BMR, TDEE calculations and macronutrient consumption
 */
public class Calculator {
	private Person p;
	private double bmr, tdee;
	private String[][] macrosValue = new String[3][4]; // An array of macronutrients for three different diets
	private String[] diets = new String[] { "Moderate Carb 30/35/35", "Lower Carb 40/40/20", "Higher Carb 30/20/50" }; //Table's titles

	/** Defined map with possible person's activity and assigned coefficients used to calculate BMR and TDEE **/
	 private static final Map<String, Double> hashActivity;
	static {
		hashActivity = new HashMap<>();
		hashActivity.put("Lack of physical activity", 1.2);
		hashActivity.put("Low physical activity", 1.375);
		hashActivity.put("Average physical activity", 1.55);
		hashActivity.put("High physical activity", 1.725);
		hashActivity.put("Very High physical activity", 1.9);

	}

	public Calculator(Person p) {
		this.p = p;

	}

	/**
	 *
	 * @param index - the type of method that should be used to calculate the BMR (Mifflin / Harris), and then calculates Tdee and macros
	 *
	 */
	public void calculateStats(int index) {
		if (index == 0) {
			calculateHarrisBMR();
		} else {
			calculateMifflinBMR();
		}
		calculateTDEE();
		calculateMacronutrients();

	}

	/**
	 *
	 * @return BMR calculated with Harris method.
	 *
	 */
	public double calculateHarrisBMR() {
		if (p.getGender() == Gender.MALE) {
			bmr = 66.5 + 13.75 * p.getWeight() + 5.003 * p.getHeight() - 6.755 * p.getAge();
		} else {
			bmr = 655.1 + 9.563 * p.getWeight() + 1.850 * p.getHeight() - 4.676 * p.getAge();
		}
		return bmr;

	}


	/**
	 *
	 * @return BMR calculated with Mifflin method
	 * For men: (10 x w) + (6.25 x h) - (5 x a) + 5
	 * For women:(10 x w) + (6.25 x h) - (5 x a) - 161
	 * Where:
	 * w = weight in kg (1 pound = 0.45359237 kilograms)
	 * h = height in cm (1 inch = 2.54 centimeters)
	 * a = age (in years)
	 */
	public double calculateMifflinBMR() {
		bmr = 10.0 * p.getWeight() + 6.25 * p.getHeight() - 5.0 * p.getAge();
		if (p.getGender() == Gender.MALE) {
			bmr += 5;
		} else {
			bmr -= 161;
		}
		return bmr;
	}

	/**
	 *
	 * @return Tdee calculation
	 */
	public double calculateTDEE() {
		tdee = bmr * getHashActivity(p.getActivity());
		return tdee;

	}

	public static double getHashActivity(String activity) {
		return hashActivity.get(activity);
	}

	/**
	 *
	 * @return An array with the breakdown of proteins, fats and carbohydrates depending on the diet (Moderate Carb 30/35/35, Lower Carb 40/40/20, Higher Carb 30/20/50)
	 */
	public String[][] calculateMacronutrients() {
		double[][] proportions = new double[][] { { 0.3, 0.35, 0.35 }, { 0.4, 0.4, 0.2 }, { 0.3, 0.2, 0.5 } };
		int[] caloriesPerGram = new int[] { 4, 9, 4 };
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if(j==0)macrosValue[i][j]=diets[i];
				else macrosValue[i][j] =  Math.round(tdee * proportions[i][j-1] / caloriesPerGram[j-1])+" g";
			}
		}
		return macrosValue;
	}

	public double getBmr() {
		return bmr;
	}

	public void setBmr(double bmr) {
		this.bmr = bmr;
	}

	public double getTdee() {
		return tdee;
	}

	public void setTdee(double tdee) {
		this.tdee = tdee;
	}

	public String[][] getMacrosValue() {
		return macrosValue;
	}

	public void setMacrosValue(String[][] macrosValue) {
		this.macrosValue = macrosValue;
	}
}
