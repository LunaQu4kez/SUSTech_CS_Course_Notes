#!/bin/bash

javac -cp lib/algs4.jar src/Main.java -d ./bin

fault="false"
for i in {1..100}; do
    input="./data/${i}.in"
    output="./data/${i}.out"
    echo $input
    java -cp ./bin/:algs4.jar Main < $input > tmp.txt
    if ! cmp -s $output tmp.txt; then :
        fault="true"
        break;
    fi
done

if [ "$fault" = "true" ]; then
    echo "$name Fail"
else :
    echo "$name Pass"
fi
