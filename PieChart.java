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
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * class PieChart extends the Jframe class of java.Awt ,which is used to create top-levelWindow
 * the PieChart is used to interpretate the pie chart for the number of films released in four decades
 * from 1985 to 2025
 */
public class PieChart extends JFrame {
    static int firstDec,secDec,thirdDec,fourthDec = 0;
    static List<String> lines = new ArrayList<>();
    static List<Integer> years = new ArrayList<>();
    static final Logger log = Logger.getLogger(PieChart.class);
    /**
     * the constructor is created for the class PieChart with an argument title that is used to set the
     * title for the PieChart created
     * @param title - String parameter of title is given to the PieChart constructor as the title for the piechart
     */
    public PieChart(String title) {
        super(title);

        // Create dataset
        PieDataset<String> dataset = createDataset();
        log.warn("Raw use of paramatrized class options for PieDataset");

        // Creating Pie-chart using JfreeChart class
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Respresnting the number of films released in the decade",
                dataset,
                true,
                true,
                false);
        log.info("creating pieChart with title ,dataset and other fields");
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                " {0}  : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        log.info("Pie section label for pieChart is generated using label Generator");
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        log.warn("raw use of parameterized class PiePlot");

        // Creating Panel for the chart
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        log.info("chartPanel is created for the PieChart");
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * PieDataset class which extends other classes like comparable,org.dataset are used to create the method
     * @return - method returns the dataset which is used to create charts
     */
    private PieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset=new DefaultPieDataset<>();
        log.warn("raw use of parameterized class PieDataset");
        log.info("DataSet with required data values is invoked for the Piechart");
        //for(int i=0;i<10;i++) {
            /*dataset.setValue(runTime.get(i),rtScore.get(i));*/
        dataset.setValue("1985 - 1995",firstDec);
        log.warn("Unchecked call to 'setValue");
        dataset.setValue("1996 - 2005",secDec);
        dataset.setValue("2006 - 2015",thirdDec);
        dataset.setValue("2016 - 2025",fourthDec);
       // }
        log.debug("dataset that is created for PieChart"+" "+dataset);
        return dataset;
    }


    public static void main(String[] args) {

    //SwingUtillities is used for positioning and orienting components on the screen
        SwingUtilities.invokeLater(() -> {
            log.info("swingUtilities is invoked for window settings to visualize the PieChart");
            PieChart example = new PieChart("Pie Chart");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);

    //WindowConstants are used to control the window-closing operation
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });

        String[] data;

        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/azuga/Desktop/ghibli.csv"))) {
           log.info("data is reading from the given file ghibli.csv file");
            String currentLine;
            if(reader.readLine() == null) log.fatal("error due to empty input file ghibli.csv");
            while ((currentLine = reader.readLine()) != null) {
                lines.add(currentLine);
            }
            log.debug("the content read from file added to list "+lines);
        } catch (IOException e) {
            log.error("exception occurs in ghibli.csv file reading");
            e.printStackTrace();
        }
        for (int i = 1; i < lines.size(); i++) {
            data = lines.get(i).split(",");
            System.out.println(data[7]);
            Integer year = Integer.parseInt(data[7]);
            years.add(year);
        }
        log.debug("years that are read from the csv is appended to list "+years);
        for(int y : years) {
            if(y>=1985&&y<=1995) {
                firstDec += 1;
                log.debug("number of films released in 1stDec"+" "+firstDec);
            } else if(y>=1996&&y<=2005) {
                secDec += 1;
                log.debug("number of films released in 2ndDec"+" "+secDec);
            } else if(y>=2006&&y<=2015) {
                thirdDec += 1;
                log.debug("number of films released in 3rdDec"+" "+thirdDec);
            } else if (y>=2016&&y<=2025) {
                fourthDec+=1;
                log.debug("number of films released in 4thDec"+" "+fourthDec);
            }
        }
        log.info("data of films released in decades are stored in respective variables");
    }
}