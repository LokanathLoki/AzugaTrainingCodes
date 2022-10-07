
![Logo](https://media.istockphoto.com/vectors/finance-and-business-symbol-in-black-white-vector-id165765990?k=20&m=165765990&s=612x612&w=0&h=H9IRmRfxNIp7QkS4MIsm1rIZXOQTXt_FFBR6Z-EfkCE=)

# Charts Creation 

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
charts is a feature that creates the barChart, Line chart and Pie chart for the data in obtained from the Api call;

## Installation

The project doesnot need any installation , by adding this project to the IDE's workspace and adding all the required jar files given below to the class path the project will surve its purpose.

### Prerequisites 
The Project has some Prerequisites, jar files / libraries must be installed or added to the dependency’s. Following are required Jar files to add to the classPath for different features,
1. [jfreechart-1.5.3.jar](https://search.maven.org/artifact/org.jfree/jfreechart/1.5.3/jar)
2.  [opencsv-1.7.jar](https://jar-download.com/?search_box=opencsv-1.7)

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

The Project Charts has codes for different applications like,
- Creating BarCharts
The project contains programs thatcreates the bar chart for the data obtained from the Api.

- Creating LineChart
Line chart is created for the data read from the Api' calls Csv file.

- Creating PieChart
the data in th csv file is used to create the Pie Chart of the Api

## Roadmap
 
 The flow of tasks during training,
 
- REST API

    -   --  [ Ghibli API ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/Api)
    -   --  [ Museum API ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/Api)

- CREATION OF CHARTS

    -   --  [ BAR GRAPH ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)
    -   --  [ LINE CHART ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)
    -   --  [ PIE CHART](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)



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


