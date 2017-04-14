package org.hicham.View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JDesktopPane;

import org.hicham.Model.StatisticsQueries;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class StatisticsView extends JDesktopPane{
	final JFXPanel fxPanel = new JFXPanel();

	public StatisticsView(){
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				BarChart<String, Number> chart = getChart();
				fxPanel.setScene(new Scene(chart));
			}
		});
		fxPanel.setBounds(30, 100, 700, 400);
		this.add(fxPanel);



	}

	public BarChart<String, Number> getChart() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis,
				yAxis);
		bc.setTitle("Patient Par Mois");
		xAxis.setLabel("Mois");
		yAxis.setLabel("Nombre du Patient");
		
		StatisticsQueries statisticsQueries= new StatisticsQueries();
		Map<Integer,Integer>statMap=statisticsQueries.getRendezVousByMonth(10);
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>(statMap);
		for (Integer i: map.keySet()) {
		   XYChart.Series series = new XYChart.Series();
           String month = new DateFormatSymbols().getMonths()[i];
           series.setName(month);

           series.getData().add(new XYChart.Data(month,map.get(i)));
   		   bc.getData().add(series);
		}

		/*XYChart.Series series1 = new XYChart.Series();
		series1.setName("2003");
		series1.getData().add(new XYChart.Data("austria", 25601.34));
		series1.getData().add(new XYChart.Data("brazil", 20148.82));
		series1.getData().add(new XYChart.Data("france", 10000));
		series1.getData().add(new XYChart.Data("italy", 35407.15));
		series1.getData().add(new XYChart.Data("usa", 12000));

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("2004");
		series2.getData().add(new XYChart.Data("austria", 57401.85));
		series2.getData().add(new XYChart.Data("brazil", 41941.19));
		series2.getData().add(new XYChart.Data("france", 45263.37));
		series2.getData().add(new XYChart.Data("italy", 117320.16));
		series2.getData().add(new XYChart.Data("usa", 14845.27));

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("2005");
		series3.getData().add(new XYChart.Data("austria", 45000.65));
		series3.getData().add(new XYChart.Data("brazil", 44835.76));
		series3.getData().add(new XYChart.Data("france", 18722.18));
		series3.getData().add(new XYChart.Data("italy", 17557.31));
		series3.getData().add(new XYChart.Data("usa", 92633.68));

		bc.getData().addAll(series1, series2, series3);*/
		return bc;
	}

	public void addStatisticsViewAction(ActionListener listener){

	}
}
