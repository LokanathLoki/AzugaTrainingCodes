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
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class LineChart extends the ApplicationFrame class of java.Awt ,which is used for Window events
 * the LineChart is used to interpretate the line chart for the runTime of the different movies
 */
public class LineChart extends ApplicationFrame {

    static List<String> lines = new ArrayList<String>();
    static List<String> movie = new ArrayList<String>();
    static List<Integer> runTime = new ArrayList<Integer>();

    /**
     * the constructor is created for the class LineChart with  arguments applicationTitle and chartTitle
     * that are used as title for window  and the LineChart created
     * @param applicationTitle - A string value of title is created as application title for the window
     * @param chartTitle       - A string value of title is given for the Linechart title
     */
    public LineChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        // creating the lineChart using JfreeChart class
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Movie Titles", "Movie RunTime",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, true);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1960, 567));
        setContentPane(chartPanel);
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * DefaultCategoryDataset class which implement data.general.dataset
     * @return - method returns the dataset which is used to create charts
     */
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<10;i++) {
            dataset.addValue(runTime.get(i), "RunTime in Minutes", movie.get(i));
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
            Integer rt = Integer.parseInt(data[8].replaceAll(" ", ""));
            runTime.add(rt);
            movie.add(data[1]);
        }

        //LineChart constructor is created with the arguments of applicationTitle and chartTitle
        LineChart chart = new LineChart(
                "RunTime Vs title",
                "Run-Time of various movies");
        chart.pack();
        chart.setVisible(true);
    }


}