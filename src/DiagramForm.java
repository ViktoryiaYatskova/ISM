import javax.swing.*;

/**
 * Created by Viktoria on 28.02.2015.
 */

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.StyleManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a simple Chart using QuickChart
 */
public class DiagramForm extends SequenceForm {
    private JButton backButton;
    private JPanel panel;
    private JPanel diagramPanel;
    private Chart chart;

    public DiagramForm(double[] xData, final JFrame parent){
        super(parent);

        double[] yData = new double[xData.length];
        yData[0] = 0;
        
        for(int i = 1; i < xData.length; i++)
            yData[i] = xData[i-1];

        // Create Chart
        //chart = QuickChart.getChart(, , "Y[i+1]", , );
        ChartBuilder chartBuilder = new ChartBuilder();
        chartBuilder.chartType(StyleManager.ChartType.Scatter);
        chart = new Chart(chartBuilder);
        chart.setChartTitle("Diagram");
        chart.setXAxisTitle("X[i]");
        chart.setYAxisTitle("X[i+1]");
        chart.addSeries("y(x)", xData, yData);

        // Show it
        add(panel);
        setBounds(100, 10, 970, 600);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBackBtn();
            }
        });
    }
    
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics = diagramPanel.getGraphics();
        chart.paint((Graphics2D)graphics);
    }
}