#!/usr/bin/bash
#$1 = numero de ejecuciones
#$2 = longitud del vector
#$3 = nombre del archivo de salida
for (( i=0;i<$1;i++ ))
do
 java -jar ./Practica5.jar 1 $2 300 >> $3.csv
done
