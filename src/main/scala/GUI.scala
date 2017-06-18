import javafx.scene.paint.Color
import javafx.scene.text.Font

import scalafx.event.ActionEvent
import org.json4s.JValue
import org.json4s.jackson.JsonMethods.parse

import scala.io.Source
import scala.util.Random
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, Label}
import scalafx.scene.image.{Image, ImageView}


object GUI {
  var points = 0;
  var good = 0;
  var bad = 0;

  def main(args: Array[String]): Unit = {
    println("LOL!")
    app.main(args)
  }

  val app = new JFXApp {

    var matchId = randomMatchId() //Losowanie interesującego nas meczu
    var json: JValue = connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
    var championList = getChampionsList(json)
    var realWinner = winner(json)
    championList.foreach(u => println(u.image + "    " + u.name))
    stage = new JFXApp.PrimaryStage {
      title.value = "LoL"
      width = 800
      height = 700
      scene = new Scene {
        fill = Color.rgb(22, 2, 63)


        val pointsL = new Label("Points: " + points)
        pointsL.setFont(new Font("TimesRoman", 31))
        pointsL.setTextFill(Color.RED)
        pointsL.layoutX = 600
        pointsL.layoutY = 530

        val goodL = new Label("GOOD: " + good)
        goodL.setFont(new Font("TimesRoman", 21))
        goodL.setTextFill(Color.RED)
        goodL.layoutX = 600
        goodL.layoutY = 500

        val badL = new Label("BAD: " + bad)
        badL.setFont(new Font("TimesRoman", 21))
        badL.setTextFill(Color.RED)
        badL.layoutX = 600
        badL.layoutY = 470

        val buttonRed = new Button("Blue Team WIN !")
        buttonRed.layoutX = 150
        buttonRed.layoutY = 610
        buttonRed.setTextFill(Color.BLUE)
        buttonRed.onAction = (e: ActionEvent) => {
          disable(false)
          win_check(realWinner, "Blue")
          pointsL.text = "Points: " + points
          goodL.text = "GOOD: " + good
          badL.text = "BAD: " + bad
          matchId = randomMatchId() //Losowanie interesującego nas meczu
          var json: JValue = connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
          championList = getChampionsList(json)
          realWinner = winner(json)
          championList.foreach(u => println(u.image + "    " + u.name))
          disable(true)
          refresh()

        }

        val buttonBlue = new Button("Purple Team WIN !")
        buttonBlue.layoutX = 350
        buttonBlue.layoutY = 610
        buttonBlue.setTextFill(Color.PURPLE)
        buttonBlue.onAction = (e: ActionEvent) => {
          disable(false)
          win_check(realWinner, "Purple")
          pointsL.text = "Points: " + points
          goodL.text = "GOOD: " + good
          badL.text = "BAD: " + bad
          matchId = randomMatchId() //Losowanie interesującego nas meczu
          var json: JValue = connectionMatch(matchId) //Bierze jsonStringa z http://developer.riotgames.com/ i konwertuje do JValue
          championList = getChampionsList(json)
          realWinner = winner(json)
          championList.foreach(u => println(u.image + "    " + u.name))
          disable(true)
          refresh()

        }

        val redT = new Label("Blue Team")
        redT.setFont(new Font("TimesRoman", 34))
        redT.setTextFill(Color.BLUE)
        redT.layoutX = 100
        redT.layoutY = 50

        val blueT = new Label("Purple Team")
        blueT.setFont(new Font("TimesRoman", 34))
        blueT.setTextFill(Color.PURPLE)
        blueT.layoutX = 300
        blueT.layoutY = 50

        val ver = new ImageView(new Image("https://static.comicvine.com/uploads/original/11111/111119495/3299555-kickass12.png"))
        ver.setFitHeight(100)
        ver.setFitWidth(100)
        ver.layoutX = 250
        ver.layoutY = 300

        val loading = new ImageView(new Image("http://www.lettersmarket.com/uploads/lettersmarket/blog/loaders/common_gray/ajax_loader_gray_512.gif"))
        loading.setFitHeight(200)
        loading.setFitWidth(200)
        loading.layoutX = 250
        loading.layoutY = 300
        loading.visible = false;


        val red1L = new Label(championList(0).name)
        red1L.setFont(new Font("TimesRoman", 19))
        red1L.setTextFill(Color.AQUA)
        red1L.layoutX = 10
        red1L.layoutY = 130


        val red1 = new ImageView(new Image(championList(0).image))
        red1.setFitHeight(100)
        red1.setFitWidth(100)
        red1.layoutX = 150
        red1.layoutY = 100

        val red2L = new Label(championList(1).name)
        red2L.setFont(new Font("TimesRoman", 19))
        red2L.setTextFill(Color.AQUA)
        red2L.layoutX = 10
        red2L.layoutY = 230

        val red2 = new ImageView(new Image(championList(1).image))
        red2.setFitHeight(100)
        red2.setFitWidth(100)
        red2.layoutX = 150
        red2.layoutY = 200

        val red3L = new Label(championList(2).name)
        red3L.setFont(new Font("TimesRoman", 19))
        red3L.setTextFill(Color.AQUA)
        red3L.layoutX = 10
        red3L.layoutY = 330

        val red3 = new ImageView(new Image(championList(2).image))
        red3.setFitHeight(100)
        red3.setFitWidth(100)
        red3.layoutX = 150
        red3.layoutY = 300

        val red4L = new Label(championList(3).name)
        red4L.setFont(new Font("TimesRoman", 19))
        red4L.setTextFill(Color.AQUA)
        red4L.layoutX = 10
        red4L.layoutY = 430

        val red4 = new ImageView(new Image(championList(3).image))
        red4.setFitHeight(100)
        red4.setFitWidth(100)
        red4.layoutX = 150
        red4.layoutY = 400

        val red5L = new Label(championList(4).name)
        red5L.setFont(new Font("TimesRoman", 19))
        red5L.setTextFill(Color.AQUA)
        red5L.layoutX = 10
        red5L.layoutY = 530

        val red5 = new ImageView(new Image(championList(4).image))
        red5.setFitHeight(100)
        red5.setFitWidth(100)
        red5.layoutX = 150
        red5.layoutY = 500

        val blue1L = new Label(championList(5).name)
        blue1L.setFont(new Font("TimesRoman", 19))
        blue1L.setTextFill(Color.AQUA)
        blue1L.layoutX = 460
        blue1L.layoutY = 130

        val blue1 = new ImageView(new Image(championList(5).image))
        blue1.setFitHeight(100)
        blue1.setFitWidth(100)
        blue1.layoutX = 350
        blue1.layoutY = 100

        val blue2L = new Label(championList(6).name)
        blue2L.setFont(new Font("TimesRoman", 19))
        blue2L.setTextFill(Color.AQUA)
        blue2L.layoutX = 460
        blue2L.layoutY = 230

        val blue2 = new ImageView(new Image(championList(6).image))
        blue2.setFitHeight(100)
        blue2.setFitWidth(100)
        blue2.layoutX = 350
        blue2.layoutY = 200

        val blue3L = new Label(championList(7).name)
        blue3L.setFont(new Font("TimesRoman", 19))
        blue3L.setTextFill(Color.AQUA)
        blue3L.layoutX = 460
        blue3L.layoutY = 330

        val blue3 = new ImageView(new Image(championList(7).image))
        blue3.setFitHeight(100)
        blue3.setFitWidth(100)
        blue3.layoutX = 350
        blue3.layoutY = 300

        val blue4L = new Label(championList(8).name)
        blue4L.setFont(new Font("TimesRoman", 19))
        blue4L.setTextFill(Color.AQUA)
        blue4L.layoutX = 460
        blue4L.layoutY = 430

        val blue4 = new ImageView(new Image(championList(8).image))
        blue4.setFitHeight(100)
        blue4.setFitWidth(100)
        blue4.layoutX = 350
        blue4.layoutY = 400

        val blue5L = new Label(championList(9).name)
        blue5L.setFont(new Font("TimesRoman", 19))
        blue5L.setTextFill(Color.AQUA)
        blue5L.layoutX = 460
        blue5L.layoutY = 530

        val blue5 = new ImageView(new Image(championList(9).image))
        blue5.setFitHeight(100)
        blue5.setFitWidth(100)
        blue5.layoutX = 350
        blue5.layoutY = 500

        private def refresh() {

          red1.image = new Image(championList(0).image)
          red2.image = new Image(championList(1).image)
          red3.image = new Image(championList(2).image)
          red4.image = new Image(championList(3).image)
          red5.image = new Image(championList(4).image)

          blue1.image = new Image(championList(5).image)
          blue2.image = new Image(championList(6).image)
          blue3.image = new Image(championList(7).image)
          blue4.image = new Image(championList(8).image)
          blue5.image = new Image(championList(9).image)


          red1L.text = championList(0).name
          red2L.text = championList(1).name
          red3L.text = championList(2).name
          red4L.text = championList(3).name
          red5L.text = championList(4).name

          blue1L.text = championList(5).name
          blue2L.text = championList(6).name
          blue3L.text = championList(7).name
          blue4L.text = championList(8).name
          blue5L.text = championList(9).name

        }

        private def disable(value: Boolean) {

          if (value == true) {
            loading.visible = false;

          } else {

            loading.visible = true;
          }

          red1.visible = value;
          red2.visible = value;
          red3.visible = value;
          red4.visible = value;
          red5.visible = value;

          blue1.visible = value;
          blue2.visible = value;
          blue3.visible = value;
          blue4.visible = value;
          blue5.visible = value;


          red1L.visible = value;
          red2L.visible = value;
          red3L.visible = value;
          red4L.visible = value;
          red5L.visible = value;

          blue1L.visible = value;
          blue2L.visible = value;
          blue3L.visible = value;
          blue4L.visible = value;
          blue5L.visible = value;
          buttonBlue.visible = value;
          buttonRed.visible = value;
          ver.visible = value;
        }


        content = List(badL, goodL, loading, pointsL, buttonBlue, buttonRed, blueT, redT, ver, red1, red2, red3, red4, red5, blue1, blue2, blue3, blue4, blue5, red1L, red2L, red3L, red4L, red5L, blue1L, blue2L, blue3L, blue4L, blue5L)

      }

    }
  }

  def printGame(championList: List[Champion]) = {
    for (i <- 0 to 9) {
      if (i == 0)
        println("-------BLUE---------")
      if (i == 5)
        println("-------PURPLE---------")
      println(championList(i).name)
    }
    println("_______________")
    println("Zgadnij zwycięzcę!")
  }

  private def getChampionsList(json: JValue): List[Champion] = {
    val championIdList: List[Int] = championsIds(json)
    var championList: List[Champion] = List()
    championIdList.foreach(x => championList = championList.::(new Champion(x)))
    return championList
  }

  private def connectionMatch(matchId: Long): JValue = {
    var connectionString = createConnectionString
    var answer = "status"
    do {
      try {
        answer = Source.fromURL(connectionString).mkString
      }
      catch {
        case a: Exception => {
          connectionString = createConnectionString
        } //Nowe id gry
      }
    }
    while (answer.contains("status")) //status - że nie ma takiej gry, lub nie moze połączyć, bo za dużo razy bralismy dane w krotkim czasie
    val json: JValue = parse(answer)
    return json
  }

  private def championsIds(json: JValue): List[Int] = {
    val players = json \ "participants"
    val champIds = players \ "championId"
    var myList: List[Int] = List()
    for (a <- 0 to 9) {
      var id = champIds(a).values.toString.toInt
      myList = myList.::(id)
    }
    return myList
  }

  private def winner(json: JValue): String = {
    val teams = json \\ "teams"
    val winnerLoser = teams \ "win"
    if (winnerLoser(0).values.equals("Win"))
      return "Blue"
    return "Purple"
  }

  private def createConnectionString: String = {
    val matchId = randomMatchId()
    val connectionString = "https://eun1.api.riotgames.com/lol/match/v3/matches/" + matchId + "?api_key=bc394c90-2ab4-40a1-9ea7-72ab90670e03"
    connectionString
  }

  private def win_check(win: String, who: String): Unit = {

    if (win.equals(who)) {

      points += 100;
      good +=1;
      new Alert(AlertType.Information) {
        title = "You WON !"
        headerText = "+ 100 points"
        contentText = ":)"
      }.showAndWait()
    } else {
      points -= 100;
      bad +=1;
      new Alert(AlertType.Information) {
        title = "You LOST !"
        headerText = "- 100 points"
        contentText = ":("
      }.showAndWait()
    }
  }


  private def championsIds(gameId: Long): List[Int] = {
    val json: JValue = connectionMatch(gameId)
    championsIds(json)
  }

  private def randomMatchId(baseMatchId: Int = 1715640189, variance: Int = 10000) = {
    var x: Random = new Random()
    val matchId = 1715640189 - x.nextInt(variance)
    matchId
  }

}