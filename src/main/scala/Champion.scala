/**
  * Created by Lenovo on 2017-05-25.
  */
class Champion(champName:String,champId:Int,champImage:String) {
  var name: String=champName
  var id: Int=champId
  var image: String=champImage

  def this(id:Int)= {
    this(ChampionMap.mapa(id),id,"http://ddragon.leagueoflegends.com/cdn/7.11.1/img/champion/"+ChampionMap.mapa(id).replaceAll("\\s", "").substring(0,1)+ChampionMap.mapa(id).replaceAll("\\s", "").replaceAll("'", "").substring(1)+".png")
  }

  override def toString = s"Champion($name, $id)"
}
