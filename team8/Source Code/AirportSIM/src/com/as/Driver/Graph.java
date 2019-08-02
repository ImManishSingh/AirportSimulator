package com.as.Driver;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class Graph {
	
	private static double[][] data;
	
	public Graph(double[][] data) {
		this.data = data;																//setting the value for display
		initialize();
	}
	
	
	private void initialize() {
		 JFrame frame = new JFrame("Charts");

         frame.setSize(600, 400);																// defining the size of graph GUI
         
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);									//close when cliked on close button on the top right corner 
        

         XYDataset ds = createDataset();
         JFreeChart chart = ChartFactory.createXYLineChart("Airport Simulator",										// defining x label and y label of the graph 
                 "Number of gates", "Average wait time/flight (mins)", ds, PlotOrientation.VERTICAL, true, true,
                 false);

         ChartPanel cp = new ChartPanel(chart);

         frame.getContentPane().add(cp);
         frame.setVisible(true);
	}
	
	private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        ds.addSeries("series1", data);

        return ds;
    }
}
