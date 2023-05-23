# Tasks

In this document, we keep track of both a work breakdown structure and a network diagram.

## Work breakdown structure

| Task ID | Name | Priority | Depends upon |
| --- | --- | --- | --- |
| 1 | Change required Java version from 8 to 11 | 2 | 37 |
| 37 | Follow the project model | 1 |  |
| 46 | Translate the app into Swedish | 3 | 47 |
| 47 | Use java-tui for standard input | 1 | 37 |
| 48 | Show number of tasks and total estimated time per smart list and priority | 2 | 47 |
| 49 | Replace ch.rfin.pair in libs to Maven Central | 2 | 37 |
| 50 | javax.xml.bind is removed in JDK11+ https://stackoverflow.com/questions/48204141/replacements-for-deprecated-jpms-modules-with-java-ee-apis/48204154#48204154 | 1 | 1 |
| 51 | Remove dependency to JetBrains annotation | 2 | 37 |
| 52 | Pass strings and ints instead of objects | 3 | 37 |

## Network diagram

| Task ID | Name | Priority | Depends upon |
| --- | --- | --- | --- |
| 37 | Follow the project model | 1 |  |
| 47 | Use java-tui for standard input | 1 | 37 |
| 1 | Change required Java version from 8 to 11 | 2 | 37 |
| 50 | javax.xml.bind is removed in JDK11+ https://stackoverflow.com/questions/48204141/replacements-for-deprecated-jpms-modules-with-java-ee-apis/48204154#48204154 | 1 | 1 |
| 48 | Show number of tasks and total estimated time per smart list and priority | 2 | 47 |
| 49 | Replace ch.rfin.pair in libs to Maven Central | 2 | 37 |
| 51 | Remove dependency to JetBrains annotation | 2 | 37 |
| 46 | Translate the app into Swedish | 3 | 47 |
| 52 | Pass strings and ints instead of objects | 3 | 37 |