
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

from pyspark.sql.types import IntegerType
file3.createOrReplaceTempView('df4_table')
spark.sql("select  count(*) from df4_table where City == 'Pune' group by  InvestmentnType  having InvestmentnType ='Seed/ Angel Funding'").show()


