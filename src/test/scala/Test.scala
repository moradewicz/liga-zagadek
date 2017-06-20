/**
  * Created by HP on 2017-06-18.
  */

import Connection.{championsIds, connectionMatch, getChampionsList, randomMatchId}
import org.json4s.JsonAST.JValue
import org.scalatest._

class Test extends FunSuite {


  test("randomMatchId();") {

    val matchId = randomMatchId()
    assert(matchId > 1000)
  }
  test("championsIds();") {
    val matchId = randomMatchId()
    val json: JValue = connectionMatch(matchId)

    assert(json != null)
  }

  test("getChampionsList();") {
    val matchId=randomMatchId()  //Losowanie interesującego nas meczu
    val json:JValue=connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
    val championList = getChampionsList(json)

    assert(championList != null)
  }


  test("Champion not exsit") {
    val matchId=randomMatchId()  //Losowanie interesującego nas meczu
    val json:JValue=connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
    val cha =   ChampionMap
    val championIdList: List[Int] = championsIds(json)
    var championList: List[Champion] = List()
    championIdList.foreach(x =>  assert(cha.mapa.exists(_._1 == x)))


  }


}