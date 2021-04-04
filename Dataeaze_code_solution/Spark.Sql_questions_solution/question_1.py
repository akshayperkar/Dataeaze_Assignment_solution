
import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.functions import *

spark = SparkSession.builder\
    .config("spark.sql.shuffle.partitions", "2")\
    .appName("que1")\
    .master("local[2]")\
    .getOrCreate()


file1=spark.read.csv("/home/sunbeam/Downloads/startup.csv",inferSchema=True,header=True)

file2=spark.read.parquet("/home/sunbeam/Downloads/consumerInternet.parquet",inferSchema=True,header=True)

file1.printSchema()
#check file 1 having how much pune city
# cnt = file1.filter(file1.City == 'Pune').count()
# print(cnt) 78

file2.printSchema()
# cnt = file2.filter(file2.City == 'Pune').count()
# print(cnt)  27
file3=file1.unionAll(file2)

cnt = file3.filter(file3.City == 'Pune').count()
print(cnt)

# 105
