#!/bin/bash

javac Cards.java

for i in {1..2}
do
    java Cards < data/$i.in > tmp.txt
    re=`diff data/$i.out tmp.txt`
    if [ -n "$re" ]; then
        echo "data $i fail"
        break
    else
        echo "data $i pass"
    fi
done

rm ./Cards.class ./tmp.txt
echo "end"
