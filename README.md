
![Logo](https://static.javatpoint.com/core/images/java-logo1.png)

# AzugaTrainingCodes

- Table of Contents

1. [About The Project](##About-The-Project)
2. [Installation](##Installation)
3. [Prerequisites](##Prerequisites)
4. [API Reference](##API-Reference)
5. [Features](##Features)
7. [Roadmap](##Roadmap)
8. [License](##License)
9. [Acknowledgements](##Acknowledgements)
10. [Used By](##Used-By)
11. [Authors](##Authors)
12. [Badges](##Badges)
13. [FAQ](##FAQ)
14. [Feedback](##Feedback)

## About The Project
The Project Azuga Training contains the well modularized java files for different functionalities . These codes were written in training with CodeOps as day to day tasks and are uploded to the Github repository,so that they can be used for future references and will be availble for any updates and any suggestions from the people for project upgradation. 

## Installation

The project doesnot need any installation , by adding this project to the IDE's workspace and adding all the required jar files given below to the class path the project will surve its purpose.

### Prerequisites 
The Project has some Prerequisites, jar files / libraries must be installed or added to the dependency’s. Following are required Jar files to add to the classPath for different features,
1. [pdfunit-java-2016.05.jar](http://www.pdfunit.com/en/download/)
2. [javax.mail.jar](https://jar-download.com/artifacts/com.sun.mail/javax.mail/1.6.1/source-code)
3. [activation-1.1.1.jar](https://jar-download.com/artifacts/javax.activation/activation/1.1.1/source-code)
4. [jfreechart-1.5.3.jar](https://search.maven.org/artifact/org.jfree/jfreechart/1.5.3/jar)
5. [Underscore-1.81.jar](https://mavenlibs.com/jar/file/com.github.javadev/underscore)
6. [opencsv-1.7.jar](https://jar-download.com/?search_box=opencsv-1.7)
7. [jflat-core-1.3.8.jar](https://jar-download.com/?search_box=JFlat)
8. [commans.io.2.11.0.jar](https://mvnrepository.com/artifact/commons-io/commons-io/2.11.0)
9. [itextpdf-5.1.0.jar](https://mvnrepository.com/artifact/com.itextpdf/itextpdf/5.1.0)
10. [zip4j-2.11.2.jar](https://mvnrepository.com/artifact/net.lingala.zip4j/zip4j/2.11.2)

- if any of them are not working try out the latest versions of jar file


## API Reference

#### Ghibli-Api (Get all films)

```http
 https://ghibliapi.herokuapp.com/#section/Studio-Ghibli-API
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

The above Api is used to get the data about the films in the Ghibli-Api

#### Museum-Api (Get all id's)

```http
  https://metmuseum.github.io/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |


Museum Api give the data about the artists and their arts.


## Features

The Project Azuga Training has codes for different applications like,
- Linux-commands
The project contains programs that mimics some of the basic Linux-Commands like cat, wc, head, tail, sort, ls etc

- Api-Calls
It is included with programs for major Api's GET methods to get the data from the Api's and use them to create the .csv and .json files. 

- File-Formatting
The data from the Api's Call method stored in .csv and .json file are formatted to the .xml, .pdf, .html formats.

- Charts Creation
The Api's data in the files are used to create the charts, i.e to visualize the properties of the Api. Bar chart, Pie Chart and Line Chart are created.

- OOps Concepts
OOps concepts like interface, methods, objects, classes, constructors, abstraction etc were also used in the java codes.

- Zip And UnZip
The reports created from the Api calls and Formatters were zipped, unzipped and sent through the mail programatically using java language.


## Roadmap
 
 The flow of tasks during training,
 
- LINUX COMMANDS 

    -   --  [ LINUX COMMANDS ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/main/linuxCommands)
- REST API

    -   --  [ Ghibli API ](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/features/ghibliApi.java)
    -   --  [ Museum API ](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/features/apiGenric.java)

- FILES GENERATION

    -   --  [ PDF FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/features/CSVtoPdf.java)
    -   --  [ HTML FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/features/CSVtoHtml.java)
    -   --  [ XML FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/features/JsonToXml.java)
- CREATION OF CHARTS

    -   --  [ BAR GRAPH ](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/charts/barChart.java)
    -   --  [ LINE CHART ](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/charts/LineChart.java)
    -   --  [ PIE CHART](https://github.com/LokanathLoki/AzugaTrainingCodes/blob/charts/PieChart.java)
- OOPS 

    -   --  [ REST API - OOPS ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/OOPS_RestApi)
    -   --  [ FORMATTER OOPS ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/Zip_Unzip)

- ZIP AND Unzip

    -   --  [ ZIP ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/OOPS_RestApi)
    -   --  [ UNZIP ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/Zip_Unzip)
    -   --  [ MAIL ](https://github.com/11suryasoni/JavaTraining/tree/feature/OOPS/Zip_Unzip)

- TESTING WITH JUNIT-5

    -   --  [ UNIT-TESTING ](https://github.com/11suryasoni/JavaTraining/tree/feature/JunitTesting)
   


## Used By

This project is used for validation by the following companies:

- Azuga Telematics
- CodeOps



## Acknowledgements

 - [Bealdung ](https://www.baeldung.com/java-tutorial)
 - [StackOver FLow](https://stackoverflow.com/)
 - [JavaTpoint](https://www.javatpoint.com/)



## Authors

- [@Lokanath](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/main)



## License

[MIT](https://choosealicense.com/licenses/mit/) License 

Distributed under the MIT License. See LICENSE.md for more information.


## Badges


[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
#

![Your Repository's Stats](https://github-readme-stats.vercel.app/api/top-langs/?username=LokanathLoki&theme=blue-green)
#
![Your Repository's Stats](https://github-readme-stats.vercel.app/api?username=LokanathLoki&show_icons=true)



## FAQ

#### 1. Why methods of Underscore class are not working?

You must add the jar file with version 1.5.3 given in above list

####  2. Why program is running long time for museum Api?

It has 4lakh objects ,so it takez time to acess them all. It is better to bound them from 10 to 15 using loops and random object.

#### 3. Why I am unable to send the mail?

To send the mail from program, need to activate the two step verification and add that key to the program.



## Feedback

If you have any feedback, please reach out to me at LokanathK@gmail.com.com


