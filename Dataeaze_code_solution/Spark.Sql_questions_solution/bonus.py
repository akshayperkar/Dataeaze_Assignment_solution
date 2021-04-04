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
#top startup(by amount raised) from each city?
spark.sql("SELECT City, SUM(CAST(REGEXP_REPLACE(Amount_in_USD, '[^0-9]*', '') AS BIGINT)) total FROM topstartup WHERE City!=\"nan\" GROUP BY City ORDER BY total DESC").show()

