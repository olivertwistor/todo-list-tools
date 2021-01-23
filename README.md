# Todo List Tools
[Remember the Milk][1] is a service for creating to-do lists. It provides advanced ways to filter and sort items on those to-do lists. I have used the service for many years, and I can highly recommend it. Despite all the praise I have for the service, it lacks some features. That is where this project comes in.

* [Disclaimer](#disclaimer)
* [Privacy policy](#privacy-policy)
* [Installation](#installation)
    * [Build it yourself](#build-it-yourself)
    * [Download a release](#download-a-release)
* [Usage](#usage)
    * [Before you start](#before-you-start)
    * [Main menu](#main-menu)
* [Licensing](#licensing)
    * [Third-party](#third-party)

## Disclaimer
This product uses the [Remember The Milk API][5] but it's not endorsed or certified by Remember The Milk.

## Privacy policy
The Todo List Tools collects and processes data from the user's Remember the Milk account. Please read the [privacy policy][2] for more information.

## Installation
You can choose to either build the app yourself or download a release and run the JAR directly.

Prerequisites for building:

* JDK 8 or later.
* Maven (to use my build file directly) or other build tool

Prerequisites for downloaded JAR:

* JRE 8 or later.

### Build it yourself

1. Download or [clone][14] this repository.
1. Unzip the downloaded file, if applicable. 
1. Open a terminal at the root directory of the repository.
1. If you're using Maven, execute `mvn package`. You will get four JAR files under the *target* subdirectory: `todo-list-tools-{version}.jar` (contains the runnable app), `todo-list-tools-{version}-javadoc.jar` (contains the Javadoc), `todo-list-tools-{version}-sources.jar` (contains source files) and `todo-list-tools-{version}-tests.jar` (contains test source files). If you're using some other build tool, please refer to the file `pom.xml` as a guide on what you should do.

### Download a release

1. Choose a [release][15]. Click on the version number of your choice. The latest release is on top.
1. Under the release notes, you will see four different JAR files. For the runnable application, download `todo-list-tools-{version}.jar`.
1. Move the downloaded file to your desired directory.

## Usage

#### Before you start

1. Make sure you have an [account on Remember The Milk][16].
1. Make sure you have an API key and a shared secret. Apply for those [here][17].
1. Download or copy the contents of the file *[app.sample.cfg][18]*. Change the property values of `key` and `shared-secret` to the corresponding values you received from Remember The Milk in the previous step.
1. Build or download a runnable JAR file of this application.
1. Open a terminal and execute the following command: `java -jar {path/to/jar-file} {path/to/config-file}`.

#### Main menu

When the application starts, you will be greeted with a disclaimer and privacy policy. After that, the main menu appears. From here, you can do the following things:

* add Remember The Milk tasks from a CSV file
* obtain authentication for the application, to allow reading and writing data from and to your Remember The Milk account
* exit the application

If this is the first time you launch the application, you should obtain authentication first. This will get you an authentication token that will be written to your config file.

## Licensing
This project is licensed under an Apache License 2.0. For detailed terms, please read [LICENSE][3].

### Third-party

| Library                     | Author(s)                             | License |
| --------------------------- | ------------------------------------- | ------- |
| [Apache HttpClient][12]     | The Apache Software Foundation        | APL 2.0 |
| [Apache Log4J2][13]         | The Apache Software Foundation        | APL 2.0 |
| [Dom4J][7]                  | MetaStuff Ltd. and Dom4j contributors | Custom  |
| [Ini4J][8]                  | Ivan Szkiba                           | APL 2.0 |
| [Java TUI][10]              | Johan Nilsson                         | MIT     |
| [JetBrains Annotations][11] | JetBrains                             | APL 1.0 |
| [JUnit 4][9]                | JUnit team                            | EPL 1.0 |
| Pair                        | [Christoffer Fink][4]                 | MIT     |

For all third-party licenses, please refer to [3rd-party-licenses.txt][6] for full license terms.


[1]: https://www.rememberthemilk.com
[2]: privacy-policy.md
[3]: LICENSE
[4]: https://github.com/finkn
[5]: https://www.rememberthemilk.com/services/api/
[6]: 3rd-party-licenses.txt
[7]: https://dom4j.github.io/
[8]: http://ini4j.sourceforge.net/index.html
[9]: https://junit.org/junit4/
[10]: https://github.com/olivertwistor/java-tui
[11]: https://github.com/JetBrains/java-annotations
[12]: https://hc.apache.org/httpcomponents-client-ga/index.html
[13]: http://logging.apache.org/log4j/2.x/index.html
[14]: https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository
[15]: https://github.com/olivertwistor/todo-list-tools/releases
[16]: https://www.rememberthemilk.com/signup/
[17]: https://www.rememberthemilk.com/services/api/ "Apply for API access to Remember The Milk."
[18]: https://github.com/olivertwistor/todo-list-tools/blob/release/1.0.0/app.sample.cfg
