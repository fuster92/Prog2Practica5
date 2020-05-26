#!/usr/bin/bash
for (( i=0;i<$1;i++ ))
do
 java -jar ./Practica5.jar >> test.csv
done