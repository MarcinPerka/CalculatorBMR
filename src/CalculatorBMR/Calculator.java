package CalculatorBMR;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Calculator {
	private Person p;
	private double bmr, tdee;
	private String[][] macrosValue = new String[3][4];
	private String[] diets = new String[] { "Moderate Carb 30/35/35", "Lower Carb 40/40/20", "Higher Carb 30/20/50" };

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

	public void calculateStats(int index) {
		if (index == 0) {
			calculateHarrisBMR();
		} else {
			calculateMifflinBMR();
		}
		calculateTDEE();
		calculateMacronutrients();

	}

	public double calculateHarrisBMR() {
		if (p.getGender() == Gender.MALE) {
			bmr = 66.5 + 13.75 * p.getWeight() + 5.003 * p.getHeight() - 6.755 * p.getAge();
		} else {
			bmr = 655.1 + 9.563 * p.getWeight() + 1.850 * p.getHeight() - 4.676 * p.getAge();
		}
		return bmr;

	}

	public double calculateMifflinBMR() {
		bmr = 10.0 * p.getWeight() + 6.25 * p.getHeight() - 5.0 * p.getAge();
		if (p.getGender() == Gender.MALE) {
			bmr += 5;
		} else {
			bmr -= 161;
		}
		return bmr;
	}

	public double calculateTDEE() {
		tdee = bmr * getHashActivity(p.getActivity());
		return tdee;

	}

	public static double getHashActivity(String activity) {
		return hashActivity.get(activity);
	}

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
