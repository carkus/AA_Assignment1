#!/bin/bash


FILE="../testing/d75_s2500.in"

clear
echo -------AV------- > output.txt
echo '****************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputAV.txt >> output.txt
echo '****************' >> output.txt
echo -------AE------- >> output.txt
echo '****************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputAE.txt >> output.txt
echo '***************' >> output.txt
echo -------N------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputN.txt >> output.txt
echo '****************' >> output.txt
echo -------RV------- >> output.txt
echo '****************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputRV.txt >> output.txt
echo '****************' >> output.txt
echo -------RE------- >> output.txt
echo '****************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputRE.txt >> output.txt
echo '***************' >> output.txt
echo -------S------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo '***************' >> output.txt
echo -------V------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputV.txt >> output.txt
echo '***************' >> output.txt
echo -------E------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputE.txt >> output.txt
echo '***************' >> output.txt


