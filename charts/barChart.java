package com.azuga.training.charts;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class barChart extends the ApplicationFrame class of java.Awt ,which is used for Window events
 * the barChart is used to interpretate the bar chart for the rottenTomatoes score of the different movies
 */
public class barChart extends ApplicationFrame {
    private static final Logger loki = Logger.getLogger(barChart.class);
    static List<String> lines = new ArrayList<>();
    static List<String> runTime = new ArrayList<>();
    static List<Integer> rtScore = new ArrayList<>();

    /**
     * the constructor is created for the class barChart with  arguments applicationTitle and chartTitle
     * that are used as title for window  and the bar-Chart created
     * @param applicationTitle - A string value of title is created as application title for the window
     * @param chartTitle       - A string value of title is given for the barchart title
     */
    public barChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        loki.info("enters to barChart constructor");
        // creating the barChart using JfreeChart class
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Movie Name",
                "Rt_Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        loki.info(" Created bar-chart for movieName Vs Rt-score");

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(760, 367));
        setContentPane(chartPanel);
        loki.info("chart panel created for barChart");
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * CategoryDataset class which extends to the class data.general.dataset is used for this method
     * @return - method returns the dataset which is used to create charts
     */
    public CategoryDataset createDataset() {
        loki.info("moveName and rt-score added to dataset");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         for(int i=0;i<rtScore.size();i++) {
        dataset.addValue(rtScore.get(i), runTime.get(i),"");
         }
        loki.debug(" dataset values - "+dataset);
        loki.info("bar-chart's Dataset Created");
        return dataset;
    }

    //public  void BarChart() {
    public static void main(String[] args) {
        
        String[] data;

        //bufferReader is used to read the content from the csv file of the api
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/Desktop/ghibli.csv"))) {
            loki.info("Reading Api data from /Users/azuga/Desktop/ghibli.csv");
            String currentLine;
            if (reader.readLine()==null) loki.error("Error due to empty input /Users/azuga/Desktop/ghibli.csv");
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            loki.info("Csv data is appended to list");
            loki.debug("Csv data in list "+lines);
        } catch (IOException e) {
            loki.error("error at reading  from /Users/azuga/Desktop/ghibli.csv"+e);
            e.printStackTrace();
        }
        for (int i = 1; i < lines.size(); i++) {
            data = lines.get(i).split(",");
            Integer rt = Integer.parseInt(data[9].replaceAll(" ", ""));
            runTime.add(data[1]);
            rtScore.add(rt);
        }
        loki.info("MovieNames,Rt_score are added to lists");
        loki.debug("movieNames stored in list "+runTime);
        loki.debug("Rt-score  stored in list "+rtScore);

        //barChart constructor is created with the arguments of applicationTitle and chartTitle
        barChart chart = new barChart("Rating for the Movies",
                "Movies with their Rt_Score");
        chart.pack();
        loki.info("bar-chart is created successfully");
        chart.setVisible(true);
    }
}


