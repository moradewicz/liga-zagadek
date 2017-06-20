import javafx.scene.paint.Color
import javafx.scene.text.Font

import scalafx.scene.control.{Button, Label}

/**
  * Created by HP on 2017-06-20.
  */
class GuiFun {

  def label(points:String,size : Int, color: Color,x :Int, y:Int ):Label={
    val l = new Label("Points: " + points)
    l.setFont(new Font("TimesRoman", size))
    l.setTextFill(color)
    l.layoutX = x
    l.layoutY = y
return l
  }

  def button(a:String, color: Color,x :Int, y:Int ):Button={
    val b = new Button( a)
    b.setTextFill(color)
    b.layoutX = x
    b.layoutY = y
    return b
  }

}
