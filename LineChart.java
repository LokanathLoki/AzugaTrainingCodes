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

    static List<String> lines = new ArrayList<>();
    static List<String> movie = new ArrayList<>();
    static List<Integer> runTime = new ArrayList<>();

    static  final Logger log = Logger.getLogger(LineChart.class);

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
                "Movie Titles", "Movie RunTime(in minutes)",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, true);
        log.info("lineChart is created with all the required fields");
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1960, 567));
        setContentPane(chartPanel);
        log.info("chartPanel is created with width and height");
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * DefaultCategoryDataset class which implement data.general.dataset
     * @return - method returns the dataset which is used to create charts
     */
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        log.info("dataset for line chart is invoked ");
        for(int i=0;i<10;i++) {
            dataset.addValue(runTime.get(i), "RunTime in Minutes", movie.get(i));
        }
        log.info("the MovieName and RunTime are added to the dataset for LineChart");
        return dataset;
    }

    public static void main(String[] args) {
        String[] data;

        //bufferReader is used to read the content from the csv file of the api
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/Desktop/ghibli.csv"))) {
            log.info("the data is read from the ghibli.csv file");
            String currentLine;
            if (reader.readLine() == null) log.fatal("error due to empty input file ghibli.csv");
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            log.error("error occurs while reading the data from ghibli.csv file");
            e.printStackTrace();
        }
        for (int i = 1; i < lines.size(); i++) {
            data = lines.get(i).split(",");
            Integer rt = Integer.parseInt(data[8].replaceAll(" ", ""));
            runTime.add(rt);
            movie.add(data[1]);
        }
        log.info("the data required is accessed and stored to the lists");
        log.debug("runTime of movies is stored in the list "+runTime);
        log.debug("Movie nmaes are stored in list "+movie);

        //LineChart constructor is created with the arguments of applicationTitle and chartTitle
        LineChart chart = new LineChart(
                "RunTime Vs title",
                "Run-Time of various movies");
        log.info("the LineChart is created with appTitle and chartTitle");
        chart.pack();
        chart.setVisible(true);
    }


}