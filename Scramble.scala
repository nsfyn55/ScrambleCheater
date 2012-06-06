import scala.util.Random
import scala.collection.mutable.{Set => MSet}

object Scramble {

  def main(args: Array[String]){
    val s = MSet[Tuple2[Int,Int]]()
    val r = new Random
    val g = Array.tabulate(args(0).toInt, args(1).toInt)((w,h) => Character.toChars(r.nextInt(25)+97)(0))
   
    println("Grid Contents:\n%s".format(g.deep.mkString("\n")))
   
    walkGrid((0,0), "", g, s)
  }
  
  def walkGrid(currentPosition: Tuple2[Int,Int], 
               accumulator: String, 
               grid: Array[Array[Char]], 
               checkedSet: MSet[Tuple2[Int,Int]] ): Unit= {

    //add current position to checked Set
    val acc = accumulator + grid(currentPosition._2)(currentPosition._1)
    checkedSet.add(currentPosition)
    
    println(acc)

    //Walk South
    if( currentPosition._2+1 < grid.length && !checkedSet.contains((currentPosition._1,currentPosition._2+1)))
      walkGrid((currentPosition._1,currentPosition._2+1), acc, grid, checkedSet)

    //Walk East
    if( currentPosition._1+1 < grid(0).length && !checkedSet.contains((currentPosition._1+1,currentPosition._2)))
      walkGrid((currentPosition._1+1,currentPosition._2), acc, grid, checkedSet)

    //Walk West 
    if( currentPosition._1 != 0  && !checkedSet.contains((currentPosition._1-1,currentPosition._2)))
      walkGrid((currentPosition._1-1,currentPosition._2), acc, grid, checkedSet)
      
    //Walk North 
    if( currentPosition._2 != 0  && !checkedSet.contains((currentPosition._1,currentPosition._2-1)))
      walkGrid((currentPosition._1,currentPosition._2-1), acc, grid, checkedSet)
    
    checkedSet.remove(currentPosition)

  }    
}
