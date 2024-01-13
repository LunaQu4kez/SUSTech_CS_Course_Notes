

javac -cp src;lib\algs4.jar src\examples\*.java -d bin

set PARAS=-cp bin;lib\algs4.jar

java %PARAS% examples.TestStdInStdOut <  resources\1.txt
java %PARAS% examples.TestStdDraw
java %PARAS% examples.TestStdArrayIO <  resources\2.txt
java %PARAS% examples.TestStdRandom
java %PARAS% examples.TestStdStats

java %PARAS% examples.TestBinarySearch
java %PARAS% examples.TestInsertion
java %PARAS% examples.TestMerge
java %PARAS% examples.TestRedBlackBST
java %PARAS% examples.TestDFS

pause