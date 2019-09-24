package cn.spark.study.core

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf


object Text {
  def main(args:Array[String]){
    val conf=new SparkConf().setAppName("Text").setMaster("local")
    val sc=new SparkContext(conf)
    mapTest()
   def mapTest(){
      //原来rdd
      val arr=Array(1,2,3,4,5,6)
      //新的rdd
      val arrRDD=sc.parallelize(arr,2)
      val result=arrRDD.map(x=>x*4)//map为转换操作，还需要一个行动操作来启动
      result.foreach(x=>print(x+","))
      
      val result2=arrRDD.map(x=>x*x)
      result2.foreach(x=>print(x+","))
      
      val arr3=Array(22,33,56,78,91,20,62)
      val arrRDD3=sc.parallelize(arr3,2)
      val result3=arrRDD3.filter(x=>x%2==0)
      result3.foreach(x=>print(x+","))
      
      val arr4=Array(13,24,31,44,57,68,75,82,91,104)
      val arrRDD4=sc.parallelize(arr4,2)
      val result4=arrRDD4.groupBy(x=>x%2==0)
      result4.foreach(flag=>{
        println(flag._1);
        flag._2.foreach(x=>print(x+"\t"))
        println()
        println("-"*35)
      })
    }
  }
}