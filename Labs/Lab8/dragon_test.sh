#!/bin/bash
#Test your cowsay program.

OUTPUTFILE="output.txt"

print_and_run()
{
  COMMAND=$1
  echo ">$COMMAND"
  $COMMAND
}

print_and_run "javac Cowsay.java" > $OUTPUTFILE
print_and_run "java Cowsay Hello World!" >> $OUTPUTFILE
print_and_run "java Cowsay -n kitteh Moew-Moew!" >> $OUTPUTFILE
print_and_run "java Cowsay -l" >> $OUTPUTFILE
print_and_run "java Cowsay -n ninja Hello world!" >> $OUTPUTFILE
print_and_run "java Cowsay -n dragon Fiery RAWR" >> $OUTPUTFILE
print_and_run "java Cowsay -n ice-dragon Ice-cold RAWR" >> $OUTPUTFILE

less $OUTPUTFILE
