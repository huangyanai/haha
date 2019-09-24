package cn.spark.study.core

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount {
  //第一步：写主方法main函数
  def main(args:Array[String]){
    //第二步：创建SparkConf对象，用于设置程序的配置信息
    //该对象的setAppName方法用来设置程序的名称
    //该对象的setMaster()方法可以设置连续主机的地址，如果是本地运行，那么设置为local
    val conf=new SparkConf()
             .setAppName("WordCount")
//             .setMaster("local")
    /*第三步，创建SparkContext对象，SparkContext是spark所有应用功能的入口，是spark应用中最重要
     * SparkContext的主要作用：初始化Spark应用程序所需的一些核心组件，也包括
     * 调度器(DAGScheduler,TaskScheduler),在Spark Master节点上进行注册等*/
    val sc=new SparkContext(conf)
    //第四步，Spark的核心编程
    //定义初始RDD,用SparkContext对象的textFile()方法可以指定初始RDD的文件来源
    val lines=sc.textFile("hdfs://node:9000/sparktext.txt",1)
    //根据初始RDD(lines),调用flatMap算子分隔出每一行的单词，保存在变量words中
    val words=lines.flatMap(line => line.split(" "))
    //将单个单词变成键值对，每个单词计数为1,即变成(word,1)的形式，用map算子实现
    val pairs=words.map(word=>(word,1))
    //将单个单词按照key值(word)进行计算
    val wordCounts=pairs.reduceByKey(_+_)
    //第五步，输出统计结果
    wordCounts.foreach{wordCount=>println(wordCount._1+" appeared: |"+wordCount._2+"times.")
      }
  }
}