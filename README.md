![logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1s0sHXPi8hcp5FJuqSSePsBPltQZovQfaFr_5EcH_YOrxU9x13mAWzsC3QYHEBlJnF4s&usqp=CAU)
# AzugaTrainingCodes - features

Different features of the project were committed to this branch and updated till the final required result, then pushed to the main through pull requests. It has the directories for different features.

## Features Available 

The Project Azuga Training has codes for different applications like,
- Linux-commands

![commands](https://miro.medium.com/max/800/0*juRBP_UwEgIz-MQi.jpg)

The project contains programs that mimics some of the basic Linux-Commands like cat, wc, head, tail, sort, ls etc

- Api-Calls

![Api](https://accelpix.com/wp-content/uploads/2019/04/Rest-Api-200x200.png)

It is included with programs for major Api's GET methods to get the data from the Api's and use them to create the .csv and .json files. 

- File-Formatting

![formats](https://cdn.paperpile.com/guides/img/ris-to-excel-converter-400x200.png)

The data from the Api's Call method stored in .csv and .json file are formatted to the .xml, .pdf, .html formats.

- Charts Creation

![charts](https://cdn.formidableforms.com/wp-content/uploads/2017/02/Screen-Shot-2017-02-07-at-5.26.30-PM-400x200.png)

The Api's data in the files are used to create the charts, i.e to visualize the properties of the Api. Bar chart, Pie Chart and Line Chart are created.

- OOps Concepts

![oops](https://www.techiediaries.com/images/2019-7-29-object-oriented-programming-concepts.png)

OOps concepts like interface, methods, objects, classes, constructors, abstraction etc were also used in the java codes.

- Zip And UnZip

![zip & unzip](https://blog.aspose.com/2020/04/22/create-zip-archives-add-files-or-folders-to-zip-in-csharp-asp.net/images/Create-ZIP-in-C.jpg)

The reports created from the Api calls and Formatters were zipped, unzipped and sent through the mail programatically using java language.



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



## Acknowledgements

 - [Bealdung ](https://www.baeldung.com/java-tutorial)
 - [StackOver FLow](https://stackoverflow.com/)
 - [JavaTpoint](https://www.javatpoint.com/)


## Authors

- [@Lokanath](https://github.com/LokanathLoki/AzugaTrainingCodes/tree/main)


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


