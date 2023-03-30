# Project name

This application adds features to [Remember the Milk](https://www.rememberthemilk.com), such as adding tasks to a list from a CSV file.

## Disclaimer

This product uses the [Remember The Milk API](https://www.rememberthemilk.com/services/api/) but it's not endorsed or certified by Remember The Milk.

## Privacy policy

The Todo List Tools collects and processes data from the user's Remember the Milk account. Please read the [privacy policy](privacy-policy.md) for more information.

## Goals

Remember the Milk is a service for creating to-do lists. It provides advanced ways to filter and sort items on those to-do lists. I have used the service for many years, and I can highly recommend it. Despite all the praise I have for the service, it lacks some features. That is where this project comes in.

## Installation

You can choose to either build the app yourself or download a release and run the JAR directly.

Prerequisites for downloaded JAR:

* JRE version 8--10

### Build it yourself

1. Make sure you meet the following prerequisites:
   - JDK, version 8–10
   - Maven (to use my build file directly) or other build tool
2. Download or [clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) this repository.
3. Unzip the downloaded file, if applicable.
4. Open a terminal at the root directory of the repository.
5. If you're using Maven, execute `mvn package`. You will get three JAR files under the *target* subdirectory:
   - *todo-list-tools-{version}.jar* (contains the runnable app)
   - *todo-list-tools-{version}-javadoc.jar* (contains the Javadoc)
   - *todo-list-tools-{version}-sources.jar* (contains source files). If you're using some other build tool, please refer to the file `pom.xml` as a guide on what you should do.

### Download a release

1. Make sure you meet the following prerequisites:
   - JRE, version 8–10
2. Choose a [release](https://github.com/olivertwistor/todo-list-tools/releases). Click on the version number of your choice. The latest release is on top.
3. Under the release notes, you will see three different JAR files. For the runnable application, download *todo-list-tools-{version}.jar*.
4. Move the downloaded file to your desired directory.

## Usage

### Before you start

1. Make sure you meet the following prerequisites:
   - An [account on Remember The Milk](https://www.rememberthemilk.com/signup/).
2. [Apply for an API key and a shared secret.](https://www.rememberthemilk.com/services/api/)
3. Download or copy the contents of the file [app.sample.cfg](app.sample.cfg). Change the property values of `key` and `shared-secret` to the corresponding values you received from Remember The Milk in the previous step.
4. Build or download a runnable JAR file of this application.
5. Open a terminal and execute the following command: `java -jar {path/to/jar-file} {path/to/config-file}`. Example: `java -jar todo-list-tools-1.0.0.jar app.cfg`

Keep in mind that if you want to restrict read/write access to the configuration file, you also execute Java as a user that has read access to that file.

### Main menu

When the application starts, you will be greeted with a disclaimer and privacy policy. After that, the main menu appears. From here, you can do the following things:

* add Remember The Milk tasks from a CSV file
* obtain authentication for the application, to allow reading and writing data from and to your Remember The Milk account
* exit the application

If this is the first time you launch the application, you should obtain authentication first. This will get you an authentication token that will be written to your config file.

## Licenses

This project is distributed under an Apache License 2.0. For detailed terms, please read [LICENSE](LICENSE).

### Third-party

- [Apache HttpClient](https://hc.apache.org/httpcomponents-client-ga/index.html) by The Apache Software Foundation. APL 2.0.
- [Apache Log4J2](http://logging.apache.org/log4j/2.x/index.html) by The Apache Software Foundation. APL 2.0.
- [Dom4J](https://dom4j.github.io/) by MetaStuff Ltd. and Dom4j contributors. Custom license.
- [Ini4J](http://ini4j.sourceforge.net/index.html) by Ivan Szkiba. APL 2.0.
- [Java TUI](https://github.com/olivertwistor/java-tui) by Johan Nilsson. MIT License.
- [JetBrains Annotations](https://github.com/olivertwistor/java-tui) by JetBrains. APL 1.0.
- [JUnit 4](https://junit.org/junit4/) av The JUnit Team. EPL 1.0.
- [Pair](https://central.sonatype.com/artifact/ch.rfin.java-util/pair/1.1.0) by Christoffer Fink. MIT License.

Please refer to [3rd-party-licenses.txt](3rd-party-licenses.txt) for full license terms.
