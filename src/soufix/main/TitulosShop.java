package soufix.main;

import java.util.ArrayList;
import java.util.List;
import soufix.client.Player;
import soufix.common.SocketManager;
import soufix.other.Titre;
import soufix.game.World;

public class TitulosShop {
  public static final List<Titre> titles = new ArrayList<>();
  private static String packet;

  public static void initPacket() {
    packet = getTitlesList();
  }

  public static void open(Player player) {
    if(player.getFight() == null) {
      SocketManager.send(player, "ECK0|1");
      SocketManager.send(player, "EL" + getTitlesList());
      player.sendMessage("Servicio de títulos - Precio: " + Config.getInstance().prix_titre + " kamas por título");
    }
  }

  private static String getTitlesList() {
    StringBuilder titles = new StringBuilder();
    for(Titre titre : World.Titre.values()) {
      if(titre != null) {
        // Formato: id,name,color,precio,stat,cantidad
        titles.append(titre.ID).append(",")
                .append(titre.Content).append(",")
                .append(titre.Color).append(",")
                .append(Config.getInstance().prix_titre).append(",") // Usar getInstance()
                .append("").append(",") // stat vacío
                .append("1"); // cantidad 1
        if(titles.length() > 0) titles.append(";");
      }
    }
    return titles.toString();
  }
}