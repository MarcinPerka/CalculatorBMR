package CalculatorBMR;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;


/**
 * Frame which shows results(BMR, TDEE and macros) with some basic descriptions.
 */
public class StatsFrame extends JFrame{

    private String[] columnNames = {"Diet","Protein",
            "Fats",
            "Carbs"};
    private JTable table;
    private JLabel lblBmr, lblTdee, lblMacros;
    private double bmr, tdee;
    private Object[][] macrosValue;
    private JPanel panelMacros;
    private JPanel panelCalories;

    public StatsFrame(double bmr, double tdee, String [][] macrosValue){
        this.bmr=bmr;
        this.tdee=tdee;
        this.macrosValue=macrosValue;
        this.setTitle("Stats");
        this.setForeground(Color.WHITE);
        this.setBounds(400, 400, 400, 320);
        this.setVisible(true);
        this.setLayout(new GridLayout(2,1));
        this.setResizable(false);


        initialize();
    }

    private void initialize() {
        Font lblFont = new Font("Sans Serif", Font.PLAIN, 12);

        panelCalories= new JPanel();
        panelCalories.setLayout(new GridLayout(3,1));
        this.add(panelCalories, BorderLayout.CENTER);

        lblBmr = new JLabel("<html><b>BMR: "+Math.round(bmr)+" calories</b><br/>Basal Metabolic Rate is the number of " +
                "calories you would burn if you stayed in bed all day.</html>");
        lblBmr.setFont(lblFont);
        panelCalories.add(lblBmr);

        lblTdee = new JLabel("<html><b>TDEE: "+Math.round(tdee)+" calories</b><br/>Total Daily Energy Expenditure, " +
                "a measure of how many calories you burn per day.</html>");

        lblTdee.setFont(lblFont);
        panelCalories.add(lblTdee);

        lblMacros = new JLabel("<html><b>Macronutrients</b>:<br/>Values from table reflect your maintenance calories of " +
                Math.round(tdee) + " calories per day.</html>");
        lblMacros.setFont(lblFont);
        panelCalories.add(lblMacros);

        panelMacros=new JPanel();
        panelMacros.setLayout(new GridLayout(3,4));
        this.add(panelMacros,BorderLayout.SOUTH);

        table = new JTable(macrosValue, columnNames){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setLayout(new BorderLayout());
        table.getTableHeader().setFont(new Font("Sans Serif", Font.BOLD, 12));
        panelMacros.add(table.getTableHeader(), BorderLayout.PAGE_START);
        panelMacros.add(table, BorderLayout.CENTER);
        setTableDesign(table);

    }


    public static void setTableDesign(JTable table){
        int[] widths = {205,65,65,65};
        TableColumnModel columnModel = table.getColumnModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER );
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
            else break;
        }

    }
}
