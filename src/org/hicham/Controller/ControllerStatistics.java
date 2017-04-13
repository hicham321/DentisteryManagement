package org.hicham.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hicham.Model.StatisticsQueries;
import org.hicham.View.StatisticsView;

public class ControllerStatistics {
	
	StatisticsQueries statisticsQueries= new StatisticsQueries();
	StatisticsView statisticsView = new StatisticsView();
	
	public ControllerStatistics(StatisticsQueries statisticsQueries,StatisticsView statisticsView){
		this.statisticsView= statisticsView;
		this.statisticsQueries= statisticsQueries;
		this.statisticsView.addStatisticsViewAction(new StatisticsViewAction());
	}
	 class StatisticsViewAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}

}
