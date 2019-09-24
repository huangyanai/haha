package cn.spark.study.core

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount2 {
  def main(args:Array[String]){
    val conf=new SparkConf()
             .setAppName("WordCount")
             .setMaster("local")   
    val sc=new SparkContext(conf)
    val lines=sc.textFile("F://spark/bigbigworld.txt",2)
    //根据初始RDD(lines),调用flatMap算子分隔出每一行的单词，保存在变量words中
    val words=lines.flatMap(line => line.split(" "))
    //将单个单词变成键值对，每个单词计数为1,即变成(word,1)的形式，用map算子实现
    val pairs=words.map(word=>(word,1))
    //将单个单词按照key值(word)进行计算
    val wordCounts=pairs.reduceByKey(_+_)
    //键值对进行互换
    val countWords=wordCounts.map(wordCount=>(wordCount._2,wordCount._1))
    //按照值进行降序排序
    val sortedPairs=countWords.sortByKey(false)
    //键值对换回来
    val result=sortedPairs.map(sortedPair=>(sortedPair._2,sortedPair._1))
    //第五步，输出统计结果
    result.foreach{wordCount=>println(wordCount._1+": "+wordCount._2)
    }
  }
}