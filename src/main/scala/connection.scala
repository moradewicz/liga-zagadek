/**
  * Created by Lenovo on 2017-05-21.
  */

//import connection.{answer, connectionString, json}

import java.util.NoSuchElementException

import connection.{championList, json}

import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._
object connection extends App{
  import org.json4s._
  import org.json4s.JsonDSL._
  import org.json4s.jackson.JsonMethods._
  import org.json4s.native.Serialization
  import org.json4s.scalap.{Failure, Success}
  import scala.util.Random
  import scala.io.Source


  //val lastMatchId=1715640189 // Id na sucho wpisane pewnej gry która na pewno istniała
  val matchId=randomMatchId()  //Losowanie interesującego nas meczu
  val json:JValue=connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
  val championList = getChampionsList(json)
  printGame(championList)
  val realWinner=winner(json) //Patrzy który zespół wygrał
  val guess = scala.io.StdIn.readLine() // zespół który obstawia użytkownik
  println(s"Zwycięzcą jest "+realWinner)
  if(guess.equalsIgnoreCase(realWinner))
    println("Zgadłeś!")
  else
  println("Niestety nie zgadłeś")
  print("koniec")


  def printGame(championList: List[Champion]) =   {
    for(i<-0 to 9) {
    if (i == 0)
      println("-------BLUE---------")
    if (i == 5)
      println("-------PURPLE---------")
    println(championList(i).name)
  }
    println("_______________")
    println("Zgadnij zwycięzcę!")
  }

  private def getChampionsList(json:JValue):List[Champion] = {
    val championIdList: List[Int] = championsIds(json)
    var championList: List[Champion] = List()
    championIdList.foreach(x => championList = championList.::(new Champion(x)))
    return championList
  }

  private def connectionMatch(matchId:Long): JValue ={
    var connectionString = createConnectionString
    var answer="status"
    do {
      try {
        answer = Source.fromURL(connectionString).mkString
      }
      catch {
        case a:Exception =>{connectionString = createConnectionString} //Nowe id gry
      }
    }
    while(answer.contains("status")) //status - że nie ma takiej gry, lub nie moze połączyć, bo za dużo razy bralismy dane w krotkim czasie
    val json:JValue = parse(answer)
    return json
  }

  private def championsIds(json:JValue):List[Int] = {
    val players = json \ "participants"
    val champIds=players \ "championId"
    var myList: List[Int] = List()
    for (a <- 0 to 9) {
      var id = champIds(a).values.toString.toInt
      myList = myList.::(id)
    }
    return myList
  }

  private def winner(json:JValue): String ={
    val teams=json \\ "teams"
    val winnerLoser=teams \ "win"
    if(winnerLoser(0).values.equals("Win"))
      return "Blue"
      return "Purple"
  }

  private def createConnectionString:String={
    val matchId=randomMatchId()
    val  connectionString = "https://eun1.api.riotgames.com/lol/match/v3/matches/"+matchId+"?api_key=bc394c90-2ab4-40a1-9ea7-72ab90670e03"
    connectionString
  }

  private def championsIds(gameId:Long):List[Int] = {
    val json:JValue=connectionMatch(gameId)
    championsIds(json)
  }

  private def randomMatchId(baseMatchId:Int=1715640189,variance:Int=10000) = {
    var x: Random = new Random()
    val matchId = 1715640189 - x.nextInt(variance)
    matchId
  }

}
