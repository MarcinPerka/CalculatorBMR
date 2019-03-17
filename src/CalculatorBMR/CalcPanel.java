package CalculatorBMR;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Panel which has basic options to choose: Age, Height, Weight, Activity, Gender, BMR formula
 */
public class CalcPanel extends JPanel implements ActionListener {

    private JLabel lblCalculate, lblGender, lblAge, lblWeight, lblHeight, lblAgeRange, lblWeightScale, lblHeightScale,
            lblActivity, lblFormula;
    private final ButtonGroup rbnGroup = new ButtonGroup();
    private JSpinner spinnerAge, spinnerWeight, spinnerHeight;
    private JRadioButton rbnFemale, rbnMale;
    private JButton btnCalculate;
    private JComboBox<String> cmbActivity, cmbFormula;

    public CalcPanel() {
        initialize();
    }

    private void initialize() {
        Font lblScaleFont = new Font("Sans Serif", Font.PLAIN, 8);
        this.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        this.setLayout(null);


        lblCalculate = new JLabel("Calculate the daily calories");
        lblCalculate.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalculate.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        lblCalculate.setBounds(17, 11, 250, 18);
        this.add(lblCalculate);

        lblGender = new JLabel("Gender:");
        lblGender.setBounds(17, 76, 46, 14);
        this.add(lblGender);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(17, 101, 46, 14);
        this.add(lblAge);

        lblAgeRange = new JLabel("(18-99)");
        lblAgeRange.setFont(lblScaleFont);
        lblAgeRange.setBounds(246, 102, 46, 14);
        this.add(lblAgeRange);

        lblWeight = new JLabel("Weight:");
        lblWeight.setBounds(17, 126, 46, 14);
        this.add(lblWeight);

        lblWeightScale = new JLabel("kg");
        lblWeightScale.setFont(lblScaleFont);
        lblWeightScale.setBounds(246, 127, 46, 14);
        this.add(lblWeightScale);

        lblHeight = new JLabel("Height:");
        lblHeight.setBounds(17, 151, 46, 14);
        this.add(lblHeight);

        lblHeightScale = new JLabel("cm");
        lblHeightScale.setFont(lblScaleFont);
        lblHeightScale.setBounds(246, 151, 46, 14);
        this.add(lblHeightScale);

        lblActivity = new JLabel("Choose how active life you have: ");
        lblActivity.setFont(new Font("Sans Serif", Font.PLAIN, 13));
        lblActivity.setBounds(17, 175, 250, 14);
        this.add(lblActivity);

        lblFormula = new JLabel("BMR eq.:");
        lblFormula.setBounds(17, 51, 74, 14);
        this.add(lblFormula);

        rbnFemale = new JRadioButton("F");
        rbnGroup.add(rbnFemale);
        rbnFemale.setSelected(true);
        rbnFemale.setFocusPainted(false);
        rbnFemale.setBounds(111, 70, 37, 23);
        this.add(rbnFemale);

        rbnMale = new JRadioButton("M");
        rbnGroup.add(rbnMale);
        rbnMale.setFocusPainted(false);
        rbnMale.setBounds(170, 70, 37, 23);
        this.add(rbnMale);

        spinnerAge = new JSpinner();
        spinnerAge.setModel(new SpinnerNumberModel(18, 18, 99, 1));
        spinnerAge.setBounds(101, 100, 138, 20);
        this.add(spinnerAge);

        spinnerWeight = new JSpinner();
        spinnerWeight.setModel(new SpinnerNumberModel(0, 0, 150, 1));
        spinnerWeight.setBounds(101, 123, 138, 20);
        this.add(spinnerWeight);

        spinnerHeight = new JSpinner();
        spinnerHeight.setModel(new SpinnerNumberModel(0, 0, 230, 1));
        spinnerHeight.setBounds(101, 148, 138, 20);
        this.add(spinnerHeight);

        cmbActivity = new JComboBox<String>(new String[]{"Lack of physical activity", "Low physical activity",
                "Average physical activity", "High physical activity", "Very High physical activity"});
        cmbActivity.setSelectedIndex(0);
        cmbActivity.setBounds(17, 190, 250, 20);
        this.add(cmbActivity);


        btnCalculate = new JButton("Calculate\r\n");
        btnCalculate.setFont(new Font("Sans Serif", Font.BOLD, 18));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setBackground(Color.BLUE);
        btnCalculate.setBounds(17, 221, 250, 40);
        btnCalculate.setFocusPainted(false);
        this.add(btnCalculate);

        cmbFormula = new JComboBox<String>(new String[]{"Harris Benedict", "Mifflin-St Jeor"});
        cmbFormula.setSelectedIndex(0);
        cmbFormula.setBounds(101, 49, 138, 20);
        this.add(cmbFormula);

        btnCalculate.addActionListener(this);
    }

    /**
     *
     *
     * After pressing the button, the parameters entered by the user will be assigned to variables, and then the BMR,
     * TDDE and macros for diets will be calculated, and then a frame with results will be displayed.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            int age = (int) spinnerAge.getValue();
            int height = (int) spinnerHeight.getValue();
            int weight = (int) spinnerWeight.getValue();
            Gender gender = rbnMale.isSelected() ? Gender.MALE : Gender.FEMALE;
            String activity = cmbActivity.getSelectedItem().toString();
            Person p = new Person(age, gender, weight, height, activity);
            Calculator calculator = new Calculator(p);
            calculator.calculateStats(cmbFormula.getSelectedIndex());
            StatsFrame statsFrame = new StatsFrame(calculator.getBmr(), calculator.getTdee(), calculator.getMacrosValue());


        }
    }

}
