#!/bin/bash


FILE="../testing/d10_s50.in"

#clear
#echo -------AV------- > output.txt
#echo '****************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputAV.txt >> output.txt
#echo '****************' >> output.txt
#echo -------AE------- >> output.txt
#echo '****************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputAE.txt >> output.txt
#echo '***************' >> output.txt
#echo -------N------- >> output.txt
#echo '***************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputN.txt >> output.txt
#echo '****************' >> output.txt
#echo -------RV------- >> output.txt
#echo '****************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputRV.txt >> output.txt
#echo '****************' >> output.txt
#echo -------RE------- >> output.txt
#echo '****************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputRE.txt >> output.txt
#echo '***************' >> output.txt
echo -------25------- > output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s25.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------50------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s50.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------75------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s75.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------100------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s100.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------250------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s250.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------500------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s500.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------750------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s750.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------1000------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s1000.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
echo -------2500------- >> output.txt
echo '***************' >> output.txt
java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f ../testing/d75_s2500.in vert.out edge.out neigh.out dist.out < inputS.txt >> output.txt
#echo '***************' >> output.txt
#echo -------V------- >> output.txt
#echo '***************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputV.txt >> output.txt
#echo '***************' >> output.txt
#echo -------E------- >> output.txt
#echo '***************' >> output.txt
#java -cp .:jopt-simple-5.0.2.jar:sample.jar GraphTester adjlist -f $FILE vert.out edge.out neigh.out dist.out < inputE.txt >> output.txt
#echo '***************' >> output.txt


