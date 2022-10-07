
![Logo](https://store.mega.com/_modules/hopex.rest.api/icon.png)

# Rest Api

- Table of Contents

1. [About The Project](##About-The-Project)
2. [Installation](##Installation)
3. [Prerequisites](###Prerequisites)
4. [API Reference](##API-Reference)
5. [Features](##Features)
8. [License](##License)
9. [Acknowledgements](##Acknowledgements)
10. [Used By](##Used-By)
11. [Authors](##Authors)
12. [Badges](##Badges)
13. [FAQ](##FAQ)
14. [Feedback](##Feedback)

## About The Project
The Api contains the java files to call some public Api's. The data ae stored using csv and json files. The Api project has a generic program that works for all inputs and create the required files from th obtained data.

## Installation

The project doesnot need any installation , by adding this project to the IDE's workspace and adding all the required jar files given below to the class path the project will serve its purpose.

### Prerequisites 
The Project has some Prerequisites, jar files / libraries must be installed or added to the dependency’s. Following are required Jar files to add to the classPath for different features,
1. [pdfunit-java-2016.05.jar](http://www.pdfunit.com/en/download/)
5. [Underscore-1.81.jar](https://mavenlibs.com/jar/file/com.github.javadev/underscore)
6. [opencsv-1.7.jar](https://jar-download.com/?search_box=opencsv-1.7)
7. [jflat-core-1.3.8.jar](https://jar-download.com/?search_box=JFlat)
8. [commans.io.2.11.0.jar](https://mvnrepository.com/artifact/commons-io/commons-io/2.11.0)
9. [itextpdf-5.1.0.jar](https://mvnrepository.com/artifact/com.itextpdf/itextpdf/5.1.0)

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

The Project Features-Api has codes for different applications like,

- Calling Api
The project contains programs that url's of some public Api's to get thier data.

- Getting response 
Api's response is obtained in the code for the given request method, here we are getting for GET request. 

- Creating Csv File
The data from the Api's Call method is written to the csv file.

- Creating Json File
The Api's data used to create the Json file.




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



## Feedback

If you have any feedback, please reach out to me at LokanathK@gmail.com.com


