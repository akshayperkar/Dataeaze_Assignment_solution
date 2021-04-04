
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

file3=file1.unionAll(file2)

spark.sql("SELECT Industry_Vertical, COUNT(Startup_Name) startup_count FROM topfive GROUP BY Industry_Vertical order by startup_count DESC LIMIT 5").show()

