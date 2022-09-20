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
    static int firstDec = 0;
    static int secDec = 0;
    static int thirdDec = 0;
    static int fourthDec = 0;
    static int fifthDec = 0;
    static List<String> lines = new ArrayList<String>();
    static List<Integer> years = new ArrayList<Integer>();

    /**
     * the constructor is created for the class PieChart with an argument title that is used to set the
     * title for the PieChart created
     * @param title - String parameter of title is given to the PieChart constructor as the title for the piechart
     */
    public PieChart(String title) {
        super(title);

        // Create dataset
        PieDataset dataset = createDataset();

        // Creating Pie-chart using JfreeChart class
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Respresnting the number of films released in the decade",
                dataset,
                true,
                true,
                false);

        //Format Label for the chart created
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                " {0}  : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Creating Panel for the chart
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    /**
     * createDataset is created to set the dataset required for creating the chart
     * PieDataset class which extends other classes like comparable,org.dataset are used to create the method
     * @return - method returns the dataset which is used to create charts
     */
    private PieDataset createDataset() {
        System.out.println(firstDec);
        DefaultPieDataset dataset=new DefaultPieDataset();
        //for(int i=0;i<10;i++) {
            /*dataset.setValue(runTime.get(i),rtScore.get(i));*/
        dataset.setValue("1985 - 1995",firstDec);
        dataset.setValue("1996 - 2005",secDec);
        dataset.setValue("2006 - 2015",thirdDec);
        dataset.setValue("2016 - 2025",fourthDec);
       // }
        return dataset;
    }


    public static void main(String[] args) {

    //SwingUtillities is used for positioning and orienting components on the screen
        SwingUtilities.invokeLater(() -> {
            PieChart example = new PieChart("Pie Chart");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);

    //WindowConstants are used to control the window-closing operation
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });

        String[] data;

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
            System.out.println(data[7]);
            Integer year = Integer.parseInt(data[7]);
            years.add(year);
        }
        for(int y : years) {
            if(y>=1985&&y<=1995) {
                firstDec += 1;
            } else if(y>=1996&&y<=2005) {
                secDec += 1;
            } else if(y>=2006&&y<=2015) {
                thirdDec += 1;
            } else if (y>=2016&&y<=2025) {
                fourthDec+=1;
            }
        }
    }
}