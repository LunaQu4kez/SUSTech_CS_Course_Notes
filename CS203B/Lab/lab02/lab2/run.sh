#!/bin/bash

javac -cp src:lib/algs4.jar ./src/*.java -d bin

PARAS="-cp bin:lib/algs4.jar"

java $PARAS GenData
java $PARAS Simple3SumTest
java $PARAS TestThreeSum
java $PARAS TestThreeSumFast
java $PARAS TestBinarySearch
java $PARAS BinaryRangeSearch

java $PARAS edu.princeton.cs.algs4.DoublingRatio