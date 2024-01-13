
javac -cp src;lib\algs4.jar src\*.java -d bin

set PARAS=-cp bin;lib\algs4.jar

java %PARAS% TestQuickSort

pause