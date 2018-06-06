package server.model.players;
 
import server.util.Misc;
import server.Config;
import server.model.players.Client;
import server.model.players.PlayerHandler;
 
/**
 * Request Help
 * @author  Trisidia
 */
 
public class RequestHelp {
 
    public static boolean requestingHelp = false;
    public static String otherPlayer = "";
     
    public static void sendOnlineStaff(Client c) {
	c.updateRequired = true;
        String[][] Staff_Config = { {""+Config.OWNER+"", "28000", "28001"}, {""+Config.CO_OWNER+"", "28002", "28003"}, {""+Config.HEAD_ADMIN+"", "28004", "28005"}, {""+Config.ADMIN+"", "28006", "28007"}, {""+Config.HEAD_MOD+"", "28008", "28009"}, {""+Config.MOD1+"", "28010", "28011"}, {""+Config.MOD2+"", "28012", "28013"}, {""+Config.MOD3+"", "28014", "28015"}, {""+Config.MOD4+"", "28016", "28017"}  };
       c.updateRequired = true;
	   for (int i = 0; i < Staff_Config.length; i++) {
		c.updateRequired = true;
            c.getPA().sendFrame126(Staff_Config[i][0], Integer.parseInt(Staff_Config[i][1]));
            if(PlayerHandler.isPlayerOn(Staff_Config[i][0])) {
			c.updateRequired = true;
                c.getPA().sendFrame126("@gre@World 1", Integer.parseInt(Staff_Config[i][2]));
            }
        }
    }
     
    public static void setInterface(Client c) {
        if (!requestingHelp) {
            sendOnlineStaff(c);
           /// c.setSidebarInterface(14, 24999);
           /// c.getPA().sendFrame106(14);
        } else if (requestingHelp) {
            ///c.setSidebarInterface(14, 3213);
            ///c.getPA().sendFrame106(14);
            requestingHelp = false;
        }
    }
     
    public static void callForHelp(Client c) {
		if (System.currentTimeMillis() - c.lastRequest < 300000) {
            c.sendMessage("It has only been "+ getTimeLeft(c) +" seconds since your last request for help!");
            c.sendMessage("Please only request help from the staff every 5 Minutes!");
            if (!requestingHelp) {
                ///c.setSidebarInterface(14, 3213);
                ///c.getPA().sendFrame106(14);
            }
            return;
        }
        requestingHelp = true;
        otherPlayer = c.playerName;
        c.lastRequest = System.currentTimeMillis();
        setInterface(c);
        PlayerHandler.messageAllStaff("<col=2424816><shad=3932415>"+ Misc.optimizeText(getPlayer().playerName) +" needs help, their cords are: "+ playerCoords() +". <col=16777215><shad=14024897>Type ::teletohelp", true);
    }
     
    public static long getTimeLeft(Client c) {
        return (System.currentTimeMillis() - c.lastRequest) / 1000;
    }
 
    public static Client getPlayer() {
        return PlayerHandler.getPlayerByName(otherPlayer);
    }
     
    public static String playerCoords() {
        return getPlayer().getX() +", "+ getPlayer().getY() +", "+ getPlayer().heightLevel;
    }
 
    public static void teleportToPlayer(Client c) {
        try {
            if (otherPlayer.equalsIgnoreCase(c.playerName)) {
                c.sendMessage("<col=16777215><shad=14024897>You can't teleport to yourself!");
                return;
            }
            if (otherPlayer != null && !otherPlayer.equalsIgnoreCase("")) {
                c.getPA().movePlayer(getPlayer().getX(), getPlayer().getY(), getPlayer().heightLevel);
                c.sendMessage("<col=16777215><shad=62207>You telelported to "+ otherPlayer +".");
                otherPlayer = "";
            } else {
                c.sendMessage("<col=16777215><shad=14024897>There is no player to currently teleport to!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}