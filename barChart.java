package com.azuga.training.Api;

/*
 * Copyright (c) 2022.  - All Rights Reserved
 *  * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 *  * is strictly prohibited-
 *  * @Author -LokanathK.
 */

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
    static List<String> lines = new ArrayList<String>();
    static List<String> runTime = new ArrayList<String>();
    static List<Integer> rtScore = new ArrayList<Integer>();

    /**
     * the constructor is created for the class barChart with  arguments applicationTitle and chartTitle
     * that are used as title for window  and the bar-Chart created
     * @param applicationTitle - A string value of title is created as application title for the window
     * @param chartTitle       - A string value of title is given for the barchart title
     */
    public barChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        // creating the barChart using JfreeChart class
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Movie Name",
                "Rt_Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(760, 367));
        setContentPane(chartPanel);
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * CategoryDataset class which extends to the class data.general.dataset is used for this method
     * @return - method returns the dataset which is used to create charts
     */
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         for(int i=0;i<rtScore.size();i++) {
        dataset.addValue(rtScore.get(i), runTime.get(i),"");
         }
        return dataset;
    }

    public static void main(String[] args) {
        String[] data;

        //bufferReader is used to read the content from the csv file of the api
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/Desktop/ghibli.csv"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < lines.size(); i++) {
            data = lines.get(i).split(",");
            Integer rt = Integer.parseInt(data[9].replaceAll(" ", ""));
            runTime.add(data[1]);
            rtScore.add(rt);
        }

        //barChart constructor is created with the arguments of applicationTitle and chartTitle
        barChart chart = new barChart("Rating for the Movies",
                "best movie?");
        chart.pack();
        chart.setVisible(true);
    }
}


