# Byte Buddy Examples

Byte Buddy examples prepared for Motorola Tech Talks 2016 in Kraków.
The project consists of three Java Agents and definition of a new class in Byte Buddy syntax.

Agents included here are not intended for attach to a running VM. They can be only executed during virtual machine startup. Example of Java Agent capable of attaching to a running running VM is [here](https://github.com/jakubhalun/tt2016_byte_buddy_agent_demo).

## Execution

### New class definition example

```
java -jar byte-buddy-examples.jar
```

This executes code from [ClassCreationExample](https://github.com/jakubhalun/tt2016_byte_buddy_examples/blob/master/src/main/java/pl/halun/demo/bytebuddy/plain/examples/ClassCreationExample.java) class.

New class is defined using Byte Buddy syntax. It implements *Runnable* interface. Object of a new class is instantiated and executed in a separate thread.

### HelloWorldAgent

```
java -javaagent:byte-buddy-examples.jar="HelloWorldAgent" -jar any-application.jar
```

Executes agent that replaces *toString* implementation for all classes with the one that returns "Hello World" value. 

### TryCatchAgent

```
java -javaagent:byte-buddy-examples.jar="TryCatchAgent className methodName" -jar any-application.jar
```

Executes agent that encloses method invocation in a try-catch block, logs any caught exception and re-throws it.
Name of a class and name of a method must be provided, as the second and third agent parameter respectively.

### RegexSelectionLoggerAgent

```
java -javaagent:byte-buddy-examples.jar="RegexSelectionLoggerAgent classNameRegex methodNameRegex" -jar any-application.jar
```

Executes agent that adds logs to classes and methods selected using regular expressions.
Name of a class and name of a method must be provided, as the second and third agent parameter respectively.

