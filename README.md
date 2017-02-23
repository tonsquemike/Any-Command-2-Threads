# Any-Command-2-Threads
Java library for run any process in parallel. Multiplataform (Only tested on OSX, Windows & Linux)
## Synopsis
Java Library for run any JAR executable or command without know Java Threads and without modify your exiting projects
Easiest way to run commands in parallell.

## Code Example

```
 //configs -> list of argument for the same program
 String [] arguments = new String[configs.size()];
 arguments = (String[])configs.toArray(arguments);
 runHilos r = new runHilos(arguments, "java -jar "+JAR, Runtime.getRuntime().availableProcessors());
 r.ejecuta();//run command java -jar JAR with the list of arguments
```

## Motivation

I used to write code in C, but now I'm only write code in Java, I needed to run in parallel my old projects written in C.
I like process optimization but I don't have time for parallelize all my projects

## Build

You can clone this repository and compile the sources using any Java IDE or compile from command line with:
javac -d bin -sourcepath src -cp src/com/example/Application.java

##Installation

You only need to download the release file: 00001_Threads_LIB.jar and then add generated jar file to your Java project.


## Attribution Requirements

As an open source project, attribution is critical from a legal, practical and motivational perspective in my opinion.

## Contributors
Miguel Ángel García Calderón
Please report bugs to tonsquemike@outlook.com 
Please write your ideas to tonsquemike@outlook.com
## License

Code licensed under the MIT License: http://opensource.org/licenses/MIT
