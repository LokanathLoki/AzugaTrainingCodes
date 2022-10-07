
![Logo](https://ubiq.co/tech-blog/wp-content/uploads/2020/08/install-zip-unzip-627x410.png)

# Zip & Unzip - Mail

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
Zip directory contains the java code sfor zipping the reports obtained from the api call and formatters, and to unzip it and it also has the code to send the zip file through the mail.

## Installation

The project doesnot need any installation , by adding this project to the IDE's workspace and adding all the required jar files given below to the class path the project will surve its purpose.

### Prerequisites 
The Project has some Prerequisites, jar files / libraries must be installed or added to the dependency’s. Following are required Jar files to add to the classPath for different features,

1. [javax.mail.jar](https://jar-download.com/artifacts/com.sun.mail/javax.mail/1.6.1/source-code)
2. [activation-1.1.1.jar](https://jar-download.com/artifacts/javax.activation/activation/1.1.1/source-code)
3. [zip4j-2.11.2.jar](https://mvnrepository.com/artifact/net.lingala.zip4j/zip4j/2.11.2)

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

- Zip And UnZip
The reports created from the Api calls and Formatters were zipped, unzipped and sent through the mail programatically using java language.

- Mail
the zip file of the reports can be shared through the mail to the given recipent .


## Roadmap
 
 The flow of tasks during training,
 
- REST API

    -   --  [ Ghibli API ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/Api)
    -   --  [ Museum API ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/Api)

- FILES GENERATION

    -   --  [ PDF FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/formats)
    -   --  [ HTML FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/formats)
    -   --  [ XML FILE](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/formats)
- CREATION OF CHARTS

    -   --  [ BAR GRAPH ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)
    -   --  [ LINE CHART ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)
    -   --  [ PIE CHART](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/charts)
    -   

- ZIP AND Unzip

    -   --  [ ZIP ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/zip)
    -   --  [ UNZIP ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/zip)
    -   --  [ MAIL ](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/features/zip)




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

#### 1. Why I am unable to send the mail?

To send the mail from program, need to activate the two step verification and add that key to the program.



## Feedback

If you have any feedback, please reach out to me at LokanathK@gmail.com.com


