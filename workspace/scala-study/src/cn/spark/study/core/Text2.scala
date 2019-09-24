package cn.spark.study.core

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Text2 {
   def main(args:Array[String]){
   // groupByKeyTest()
    sortByKeyTest()
   }
   def groupByKeyTest(){  
      val conf=new SparkConf().setAppName("Text2").setMaster("local")
      val sc=new SparkContext(conf)
      //tuple(元组)可以是不同的数据类型
      val scoreList=Array(Tuple2("class1",90),Tuple2("class2",80),Tuple2("class1",75),Tuple2("class2",90))
      val scoreRDD=sc.parallelize(scoreList,2)
      val groupedSC=scoreRDD.groupByKey()
      groupedSC.foreach(group=>{
        println(group._1);
        group._2.foreach(x=>print(x+"\t"))
        println()
        println("-"*35)
      })
    }
    def sortByKeyTest(){
      val conf=new SparkConf().setAppName("Text2").setMaster("local")
      val sc=new SparkContext(conf)
      //tuple(元组)可以是不同的数据类型
      val scoreList=Array(Tuple2(80,"Jack"),Tuple2(60,"Tom"),Tuple2(100,"Daisy"),Tuple2(70,"Mike"))
      val scRDD=sc.parallelize(scoreList,2)
      val sortedSC=scRDD.sortByKey(false)
      sortedSC.foreach(score=>println(score._1+":"+score._2))
 
}
   }