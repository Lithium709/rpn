# CLI RPN Calculator

Commandline reverse polish notation calculator

[http://en.wikipedia.org/wiki/Reverse_Polish_notation](http://en.wikipedia.org/wiki/Reverse_Polish_notation)

## Desciption

The calculator waits for user input and expects to receive strings containing whitespace
separated lists of numbers and operators.
 
Numbers are pushed on to the stack.  Operators operate on numbers that are on the stack.
 
Available operators are `+`, `-`, `*`, `/`

They operators perform addition, subtraction, multiplication and division respectively on the top two items from the stack. 
 
Operators pop their parameters off the stack, and push their results back onto the stack.
  
After processing an input string, the calculator displays the current contents of the stack as a space-separated list. Numbers are stored on the stack to at least 15 decimal places of precision, but displayed to 10 decimal places (or less if it causes no loss of precision).
 
If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
 
`operator <operator> (position: <pos>): insufficient parameters`

After displaying the warning, all further processing of the string terminates and the current state of the stack is displayed.

To quit the program symbol 'q' or end of input indicator  (EOF / Ctrl+D) can be used. 

## Requirements

- Implemented and tested using Java 8

- Tests require JUnit and Mockito

- Project dependencies and compiling managed by Maven

## Technical review

For this implementation I used my everyday tools.
The calculator is built on basis of Java 8 implementation of stack (Deque). All functionality is encapsulated in the class Calculator. It has two public methods
 - *getValuesStack()*  returns current values in the stack.
 - *eval(String)* evaluates input string. 
 
 For operations I created special enum Operator, which can be easily extended with new operations.
 To ensure correctness solid test coverage was implemented.

## Compile, Test, Run and Packaging

- Compile: `mvn compile`

- Test: `mvn test`

- Run: `mvn exec:java`

- Packaging: `mvn package`, compiled jar in *target/* folder