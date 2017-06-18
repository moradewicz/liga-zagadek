/**
  * Created by HP on 2017-06-18.
  */
import Connection.randomMatchId
import org.scalatest._

class Test extends FunSuite {


  test("randomMatchId();"){

    val matchId=randomMatchId()
    assert(matchId > 1000)
  }




}
