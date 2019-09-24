package cn.spark.study.core.parallelize

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object ParallelizeTest {
  def main(args:Array[String]){
    //1、创建SparkConf对象
    val conf=new SparkConf().setAppName("ParallelizeTest").setMaster("local")
    //2、创建SparkContext对象
    val sc=new SparkContext(conf)
    //3、创建初始RDD
    val arr=Array(1,2,3,4,5,6,7,8,9,10)
    val numberRDD=sc.parallelize(arr,4)//arr表示集合名称，4表示RDD分区数,至少要给2-4个分区数
    //4、完成1到10的累加
    val sum=numberRDD.reduce(_+_)
    println("1到10的累加和为："+sum)
  }
}