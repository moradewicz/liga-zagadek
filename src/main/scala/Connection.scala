/**
  * Created by Lenovo on 2017-05-21.
  */

//import connection.{answer, connectionString, json}
object Connection extends App{
  import org.json4s._
  import org.json4s.jackson.JsonMethods._

  import scala.io.Source
  import scala.util.Random






   def getChampionsList(json:JValue):List[Champion] = {
    val championIdList: List[Int] = championsIds(json)
    var championList: List[Champion] = List()
    championIdList.foreach(x => championList = championList.::(new Champion(x)))
    return championList
  }

   def connectionMatch(matchId:Long): JValue ={
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

   def championsIds(json:JValue):List[Int] = {
    val players = json \ "participants"
    val champIds=players \ "championId"
    var myList: List[Int] = List()
    for (a <- 0 to 9) {
      var id = champIds(a).values.toString.toInt
      myList = myList.::(id)
    }
    return myList
  }

   def winner(json:JValue): String ={
    val teams=json \\ "teams"
    val winnerLoser=teams \ "win"
    if(winnerLoser(0).values.equals("Win"))
      return "Blue"
      return "Purple"
  }

   def createConnectionString:String={
    val matchId=randomMatchId()
    val  connectionString = "https://eun1.api.riotgames.com/lol/match/v3/matches/"+matchId+"?api_key=bc394c90-2ab4-40a1-9ea7-72ab90670e03"
    connectionString
  }

   def championsIds(gameId:Long):List[Int] = {
    val json:JValue=connectionMatch(gameId)
    championsIds(json)
  }

  def randomMatchId(baseMatchId:Int=1715640189,variance:Int=10000) = {
    var x: Random = new Random()
    val matchId = 1715640189 - x.nextInt(variance)
    matchId
  }

}
