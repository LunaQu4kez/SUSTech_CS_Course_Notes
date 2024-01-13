#!/bin/bash

javac -cp src:lib/algs4.jar ./src/*.java -d bin

PARAS="-cp bin:lib/algs4.jar"

java $PARAS TestStack

java $PARAS TestResizingArrayStack

java $PARAS TestQueue