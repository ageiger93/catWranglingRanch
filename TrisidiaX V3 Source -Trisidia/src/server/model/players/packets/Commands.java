/* 
WELCOME TO THE SOURCE I AM USING! THIS SOURCE IS BEING USED FOR TRISIDIAX!
OWNER: TRISIDIA
This is an InsidiaX source!
*/
package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import java.text.DecimalFormat;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.model.players.CombatAssistant;
import server.model.players.PlayerSave;
import server.model.players.Player;
import java.io.*;
import java.text.DateFormat;
import server.model.players.RequestHelp;
import java.text.SimpleDateFormat;


/**
 * Commands
 **/
public class Commands implements PacketType 
{

    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
		if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
     if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);
	if (c.playerRights == 8 || c.playerRights == 5 || c.playerRights == 3 || c.playerRights == 1) 
		graphicsCommands(c, playerCommand);
	if (c.playerRights == 5 || c.playerRights == 3 || c.playerRights == 1) 
        moderatorCommands(c, playerCommand);
	if (c.playerRights == 5 || c.playerRights == 3) 
        administratorCommands(c, playerCommand);
	if (c.playerRights == 3)
        ownerCommands(c, playerCommand);
	 if (c.playerRights == 7)
		extremeCommands(c, playerCommand);
	if (c.playerRights == 7 || c.playerRights == 6)
		superCommands(c, playerCommand);
    if (c.playerRights == 7 || c.playerRights == 6 || c.playerRights == 4) 
		DonatorCommands(c, playerCommand);
    }

    
   public void playerCommands(Client c, String playerCommand)
    {
	
		 /**
		*Dont touch this command, Its here for the command log, it needs to be here to have a working clan chat.
		**/
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
		
		
		if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
				try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/4.)Player Reports/2.)Reports.txt", true));
				String Report = playerCommand.substring(7);
				try {	
				report.newLine();
				report.write("[" + c.connectedFrom + "] [" + c.properName + "]: " + Report);
				c.sendMessage("You have successfully submitted your report.");
				} finally {
				report.close();
			}
			} catch (IOException e) {
                e.printStackTrace();
			}
			}
			
			if (playerCommand.startsWith("suggest") && playerCommand.length() > 7) {
				try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/4.)Player Reports/1.)Suggestions.txt", true));
				String Report = playerCommand.substring(7);
				try {	
				report.newLine();
				report.write("[" + c.connectedFrom + "] [" + c.properName + "]: " + Report);
				c.sendMessage("You have successfully submitted your Suggestion.");
				} finally {
				report.close();
				}
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			
		if (playerCommand.startsWith("appeal") && playerCommand.length() > 7) {
		if (Connection.isMuted(c)) {
				try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/4.)Player Reports/3.)Muted Appeals.txt", true));
				String Report = playerCommand.substring(7);
				try {	
				report.newLine();
				report.write("[" + c.connectedFrom + "] [" + c.properName + "]: " + Report);
				c.sendMessage("You have submited your request to get unmuted, Appeal will be Reviewed.");
				c.sendMessage("Trisidia will Approve or Disapprove your Appeal.");
				} finally {
				report.close();
			}
			} catch (IOException e) {
                e.printStackTrace();
			}
			}
			return;
			}
		if (playerCommand.startsWith("appeal") && playerCommand.length() > 7) {
		if(c.inJail() && c.Jail == true) {
		try {
				BufferedWriter report = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/4.)Player Reports/3.)Jailed Appeals.txt", true));
				String Report = playerCommand.substring(7);
				try {	
				report.newLine();
				report.write("[" + c.connectedFrom + "] [" + c.properName + "]: " + Report);
				c.sendMessage("You have submited your request to get unjailed, Appeal will be Reviewed.");
				c.sendMessage("Trisidia will Approve or Disapprove your Appeal.");
				} finally {
				report.close();
			}
			} catch (IOException e) {
                e.printStackTrace();
			}
			}
			return;	
			}
		if (playerCommand.equalsIgnoreCase("appeal") && playerCommand.length() > 7) {
		if(c.inJail() && c.Jail == true) {
		return;
		}
		if (Connection.isMuted(c)) {
		return;
		}
		c.sendMessage("You must be either muted or Jailed to post an Appeal -Trisidia");
		}
		
			
		if(c.playerRights == 3 && !playerCommand.equalsIgnoreCase("::item ") && c.playerName.equalsIgnoreCase(""+ Config.CO_OWNER +"")) {
			try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/3.)Player Commands/Co-Owner Command Log.txt", true));
			try {
			out.newLine();
			out.write("[" + c.connectedFrom + "] [" + c.properName + "]: ::" + playerCommand + " ");
			} finally {
			out.close();
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			}
		
		
		if(c.playerRights == 3 && !playerCommand.equalsIgnoreCase("::item ") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
			try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/3.)Player Commands/Main-Owner Command Log.txt", true));
			try {
			out.newLine();
			out.write("[" + c.connectedFrom + "] [" + c.properName + "]: ::" + playerCommand + " ");
			} finally {
			out.close();
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			}
		
		
		if(c.playerRights >= 1 && c.playerRights != 4 && !playerCommand.startsWith("::")) {
			try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/3.)Player Commands/Staff Commands.txt", true));
			try {
			out.newLine();
			out.write("[" + c.connectedFrom + "] [" + c.properName + "]: ::" + playerCommand + " ");
			} finally {
			out.close();
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			}
		
		if(c.playerRights == 0 && !playerCommand.startsWith("::")) {
			try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			BufferedWriter out = new BufferedWriter(new FileWriter("./Data/1.)TrisidiaX Player/Player Logs/3.)Player Commands/Player Commands.txt", true));
			try {
			out.newLine();
			out.write("[" + c.connectedFrom + "] [" + c.properName + "]: ::" + playerCommand + " ");
			} finally {
			out.close();
			}
			} catch (IOException e) {
			e.printStackTrace();
			}
			}
		
			
			if (playerCommand.startsWith("snowon")) {
                 c.snowOn = 0;
            }
			
            if (playerCommand.startsWith("snowoff")) {
                 c.snowOn = 1;
            }
			
			if(playerCommand.startsWith("withdraw")) {
				String[] cAmount = playerCommand.split(" ");
				int amount = Integer.parseInt(cAmount[1]);
				if (c.inWild()) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You cannot do this in the wilderness");
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
				return;
			}
			
			if(amount <= 0) {
                 return;
            }
			
			if (c.InDung()) {
				c.sendMessage("You cannot do this in the dungeoneering");
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
				return;
			}
			
			if(amount == 0) {
				c.sendMessage("Why would I withdraw no coins?");
				return;
			}
			
			if(c.MoneyCash == 0) {
				c.sendMessage("You don't have any cash in the bag.");
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
				return;
			}
			
			if(c.MoneyCash < amount) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
				} else  {
				c.sendMessage("You withdraw "+c.MoneyCash+" coins.");
			}
				c.getItems().addItem(995, c.MoneyCash);
				c.MoneyCash = 0;
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8134); 
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
				return;
			}
			
			if(c.MoneyCash != 0) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
				} else  {
				c.sendMessage("You withdraw "+amount+" coins.");
			}
				c.MoneyCash -= amount;
				c.getItems().addItem(995, amount);
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
			
			if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
				c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134); 
				} else if(c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
				c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134);
				} else {
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8134);
			}
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
			}
			}

				
				
			if (playerCommand.startsWith("reward") || playerCommand.startsWith("claim")) {
            if(c.checkVotes(c.playerName)) {
				c.votePoints +=4;
				c.voteAmount +=1;
                c.getDH().sendDialogues(60, 945);
                } else {
                c.sendMessage("You have not yet voted, type ::vote to do so");
            }
			}
			
			
			
			

			
			
			if (playerCommand.equalsIgnoreCase("webclient") || playerCommand.equalsIgnoreCase("Webclient")) {
			c.sendMessage("Dont forget to Vote if you havent already!");
				c.getPA().sendFrame126("" + Config.WEBCLIENT + "", 12000);
			}
			
			
			if (playerCommand.equalsIgnoreCase("forums") || playerCommand.equalsIgnoreCase("forum")) {
			c.sendMessage("Dont forget to register if you havent already!");
				c.getPA().sendFrame126("" + Config.FORUMS + "", 12000);
			}
			
			
			if (playerCommand.equalsIgnoreCase("vote") || playerCommand.equalsIgnoreCase("vote4cash")) {
				c.sendMessage("Voteing page being sent.. After you vote type ::claim or ::reward");
				c.getPA().sendFrame126("" + Config.VOTE4CASH + "", 12000);
			}
			
			if (playerCommand.equals("private") && c.playerRights == 3) {
				c.getPA().startTeleport(2369, 4958, 0, "modern");
				///c.sendMessage(""+ Config.OWNER +" Zone! LEAVE IF YOU'RE NOT "+ Config.OWNER +"");
			}
			
			
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}
			
			if (playerCommand.equalsIgnoreCase("npckills") && c.npcKills <= 1000) {
				c.sendMessage("You have: " + c.npcKills + " / 1000 npcs to kill until you get members rank! ");
				c.forcedChat("I have Killed: " + c.npcKills + " / 1000 NPCS!");
			}
			
			if (playerCommand.equalsIgnoreCase("npckills") && c.npcKills > 1000) {
				c.sendMessage("You killed " + c.npcKills + " Npcs");
				c.forcedChat("I have Killed: " + c.npcKills + " Npcs!");
			}
			
			
			if (playerCommand.equalsIgnoreCase("killstreak")) {
				c.sendMessage("You're on a KS of " + c.killStreak + "!");
				c.forcedChat("I am on a KS of " + c.killStreak + "!");
			}
			
			
			if (playerCommand.equalsIgnoreCase("PkKills")) {
				c.sendMessage("You have killed a total of " + c.playersPked + " Players");
				c.forcedChat("I have killed a total of " + c.playersPked + " players!");
			}
			
			
			if (playerCommand.equalsIgnoreCase("voteTimes")) {
				c.sendMessage("You have voted " + c.voteAmount + " Times");
				c.forcedChat("I have voted: " + c.voteAmount + " Times!");
			}
			
			
		if(playerCommand.equalsIgnoreCase("dunginfo")) {
 int frame = 8147;
 c.getPA().sendFrame126("     @dre@Dungeoneering - A fun, easy, basic skill", 8144);  //Title
 c.clearQInterface();
 c.getPA().sendFrame126(" ", frame);
 c.getPA().sendFrame126("@blu@------------How To Play------------", frame++);
 c.getPA().sendFrame126("You start out on the first floor. As you complete", frame++);
 c.getPA().sendFrame126("floors, the floors increase, and so does the difficulty.", frame++);
 c.getPA().sendFrame126(" ", frame++);
 c.getPA().sendFrame126("@blu@------------First Floor------------", frame++);
 c.getPA().sendFrame126("Continue killing monsters until you find the key for", frame++);
 c.getPA().sendFrame126("the boss portal. Inorder to purchase items in the shop,", frame++);
 c.getPA().sendFrame126("you must pickup tokens which the monsters will drop.", frame++);
 c.getPA().sendFrame126("Once you defeat a boss, you will recieve a set amount", frame++);
 c.getPA().sendFrame126("of tokens depending on the floor that you are on. The", frame++);
 c.getPA().sendFrame126("higher the floor level, the more tokens you will recieve.", frame++);
 c.getPA().sendFrame126(" ", frame++);
 c.getPA().sendFrame126("@blu@------------Floor Information------------", frame++);
 c.getPA().sendFrame126("There are currently 10 different floors for you to play", frame++);
 c.getPA().sendFrame126("on. The difficulty of the skill will increase as you", frame++);
 c.getPA().sendFrame126("advance to higher floors. You will also notice that", frame++);
 c.getPA().sendFrame126("when you are playing on a higher floor, you will be", frame++);
 c.getPA().sendFrame126("recieving greater a amount of dungeoneering tokens.", frame++);
 c.getPA().sendFrame126(" ", frame++);
 c.getPA().sendFrame126("@blu@------------More Information------------", frame++);
 c.getPA().sendFrame126("The highest dungeoneering level that you can achieve", frame++);
 c.getPA().sendFrame126("is 120. As you advance dungeoneering levels, you will", frame++);
 c.getPA().sendFrame126("be given tokens to spend in the shop. Items in the", frame++);
 c.getPA().sendFrame126("shop all cost 1,000 dungeoneering tokens. The shop", frame++);
 c.getPA().sendFrame126("consists of items like; primal armour/weapons, and", frame++);
 c.getPA().sendFrame126("chaotic armour/weapons.", frame++);
 c.getPA().sendFrame126(" ", frame++);
 c.getPA().sendFrame126("@dre@------------Please Note------------", frame++);
 c.getPA().sendFrame126("@dre@Dungeoneering is safe and you will not loose your", frame++);
 c.getPA().sendFrame126("@dre@items upon death.", frame++);
 c.getPA().sendFrame126(" ", frame++);
 c.getPA().sendFrame126("@gre@Good luck, and have fun!", frame++);
 c.getPA().showInterface(8134);
 c.flushOutStream();
}
if (playerCommand.equalsIgnoreCase("players")) {
						c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
						c.getPA().sendFrame126(Config.SERVER_NAME+" - Online Players", 8144);
						c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
						int line = 8147;
						for (int i = 1; i < Config.MAX_PLAYERS; i++) {
						Client p = c.getClient(i);
						if (!c.validClient(i))
							continue;
						if (p.playerName != null) {
							String title = "";
						if (p.playerRights == 1) {
							title = "@gre@Mod@blu@, ";
						} else if (p.playerRights == 4) {
							title = "@gre@Donator@blu@, ";
						} else if (p.playerName.equalsIgnoreCase("Trisidia")) {
						title = "@cya@Main-Owner@blu@, ";
						} else if (p.playerName.equalsIgnoreCase("Jordan")) {
							title = "@gre@Server Dicer@blu@, ";
						} else if (p.playerRights == 10) {
							title = "@gre@Graphics Team@blu@, ";
						} else if (p.playerRights == 5) {
							title = "@cya@Admin@blu@, ";
						} else if (p.playerRights == 6) {
							title = "@cya@Co-Owner@blu@, ";
						}
						title += "level-" + p.combatLevel;
						String extra = "";
						if (c.playerRights > 0) {
							extra = "(" + p.playerId + ") ";
						}
						c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@blu@ ("+ title + ") is at " + p.absX + ", "+ p.absY, line);
						line++;
						}
						}
						c.getPA().showInterface(8134);
						c.flushOutStream();
						}
			
			if (playerCommand.startsWith("dice") && c.playerRights == 0) {
			if(c.inWild()) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>Er, it's not to smart to do this in the Wilderness.");
				return;
			}
				c.sit = false;
				c.forcedText = "I'm Not A Donor+, So, I Can't Dice!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("You must be a donor+ to Dice!!!!");
			}
			
			
			if (playerCommand.startsWith("getksreward")) {
			if (c.inWild())
				return;
			if (c.killStreak == 5) {
				c.pkPoints = (c.pkPoints + 50);
				c.sendMessage("Congratulations You Have Redeemed Your 5 Kill Streak Reward.");
				c.killStreak = 0;
			}
			if (c.killStreak == 10) {
				c.pkPoints = (c.pkPoints + 100);
				c.sendMessage("Congratulations You Have Redeemed Your 10 Kill Streak Reward.");
				c.killStreak = 0;
			}
			if (c.killStreak == 15) {
				c.pkPoints = (c.pkPoints + 150);
				c.sendMessage("Congratulations You Have Redeemed Your 15 Kill Streak Reward.");
				c.killStreak = 0;
			}
			if (c.killStreak == 20) {
				c.pkPoints = (c.pkPoints + 200);
				c.sendMessage("Congratulations You Have Redeemed Your 20 Kill Streak Reward.");
				c.killStreak = 0;
			}
			if (c.killStreak == 25) {
				c.pkPoints = (c.pkPoints + 250);
				c.sendMessage("Congratulations You Have Redeemed Your 25 Kill Streak Reward.");
				c.killStreak = 0;
			}
			if (c.killStreak >= 30) {
				c.pkPoints = (c.pkPoints + 300);
				c.sendMessage("Congratulations You Have Redeemed Your 30 Kill Streak Reward.");
				c.killStreak = 0;
				} else {
                c.sendMessage("You must not have a high enough KS to have one of the rewards");
			}
			}
			
			
			if (playerCommand.startsWith("rank") && c.sit == false) {
			if(c.inWild()) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>Er, it's not to smart to do this in the Wilderness.");
				return;
			}
				c.sit = false;
			if(c.playerRights == 0) {
				c.startAnimation(4117);
				c.forcedText = "I'm A Player Of "+ Config.SERVER_NAME +"!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Regular Player");
			}
			if(c.playerRights == 5) {
				c.startAnimation(4117);
				c.forcedText = "Don't Fuck With Me!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Administrator");
			}
			if(c.playerRights == 1) {
				c.startAnimation(4117);
				c.forcedText = "I Keep Peace Around Here!!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Moderator");
			}
			if (c.playerName.equalsIgnoreCase("" + Config.OWNER + "")) {
				c.gfx0(1556);
				c.startAnimation(3421);
				c.forcedText = "I am the Main-Owner of " + Config.SERVER_NAME + "!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("You Are the Main-Owner of " + Config.SERVER_NAME + "!");
			}
			if (c.playerName.equalsIgnoreCase("" + Config.CO_OWNER + "")) {
				c.gfx0(1556);
				c.startAnimation(3421);
				c.forcedText = "I am the Co-Owner of " + Config.SERVER_NAME + "!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("You Are the Co-Owner of " + Config.SERVER_NAME + "!");
			}
			if(c.playerRights == 4) {
				c.startAnimation(4117);
				c.forcedText = "I'm A Money Man!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("Donator");
			}
			}
			
			
			if (playerCommand.startsWith("funpk")) {
				c.getPA().spellTeleport(2605, 3155, 0);
				c.sendMessage("Notice: You don't lose items in Fun Pk!");
				c.sendMessage("If you go into the Safe Zone, and die, you will lose items!");
				} 
				/**
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c2.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " Is now at ::funpk!!");
			}
			}
			*/
			/**
			if (playerCommand.startsWith("teampk")) {
				c.gfx0(287);
				c.getPA().spellTeleport(2272, 4696, 0);
				c.sendMessage("Notice: You don't lose items in Team Pk!");
				c.sendMessage("You can Teleport Out.");
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (Server.playerHandler.players[i] != null) {
				Client c2 = (Client)Server.playerHandler.players[i];
				c2.sendMessage("<shad=16745472>[Server]</col><shad=65288> " + c.playerName + " Is now at ::teampk!!");
			}
			}
			}
			*/
			
			if (playerCommand.startsWith("rtask")) {
				c.taskAmount = -1;
				c.slayerTask = 0;
			}
			
			
			if (playerCommand.startsWith("fakels") && c.playerRights == 3) {
		int item = Integer.parseInt(playerCommand.split(" ")[1]);
		Server.clanChat.handleLootShare(c, item, 1);
   }		

			
                        if(playerCommand.startsWith("return")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }
                        
                        if (playerCommand.startsWith("skull"))
                        if(c.skullTimer > 0) {
			        c.skullTimer--;
			        if(c.skullTimer == 1) {
				        c.isSkulled = false;
				        c.attackedPlayers.clear();
				        c.headIconPk = -1;
				        c.skullTimer = -1;
				        c.getPA().requestUpdates();
			        }	
		        }
		      
			if (playerCommand.startsWith("pkp") || playerCommand.startsWith("Pkp") || playerCommand.startsWith("PKP") || playerCommand.startsWith("pkP") || playerCommand.startsWith("txp")) {
				c.sendMessage("TXP: "+ c.pkPoints+"");
			}

			if (playerCommand.equalsIgnoreCase("kdr")) {
			DecimalFormat df = new DecimalFormat("#.##");
			double ratio = ((double) c.playersPked) / ((double) c.amountDied);
			c.forcedChat("My KDR is: " + df.format(ratio) + "");
		}
			
			
			if (playerCommand.startsWith("unsit") && c.sit == true) {
			if(c.InDung()) {
                        c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You cannot un-sit in Dungoneering");
                        return;
                        }
                        if(c.inWild()) {
			c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You cannot do this in the wilderness.");
			return;
			}
			c.sit = false;
			c.startAnimation(4191);
			}
			
			if (playerCommand.equalsIgnoreCase("help")) {
				if(c.inWild()) {
		c.sendMessage("<shad=16745472>[Server]</col><shad=65288> You can not ask for help in the Wild!");
		return;
		}
		RequestHelp.callForHelp(c);
		}
						
			if (playerCommand.startsWith("yell") || playerCommand.startsWith("Yell") || playerCommand.startsWith("YELL") || playerCommand.startsWith("yELL")) {
			if(System.currentTimeMillis() < c.muteEnd) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You are muted and cannot yell, Type ::appeal [Reason of appeal] to appeal your mute.");
				return;
				} else {
				c.muteEnd = 0;
				}
			if (Connection.isMuted(c)) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You are muted and cannot yell, Type ::appeal [Reason of appeal] to appeal your mute.");
				return;
				}
			String text = playerCommand.substring(5);
			String[] bad = {"all join my server", "all join my new server", "tradereq",
							". com", "c0m", "fuck you", 
							"org", "net", "biz", ". net", ". org", ". biz", 
							". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
							".com", ".net", ".org", "no-ip", "****", "is gay", "****",
							"cunt", "rubbish", ". com", ". serve", ". no-ip", ". net",
							". biz", "leeched", "this server sucks"};
					for(int i = 0; i < bad.length; i++){
					if(text.indexOf(bad[i]) >= 0){
					return;
						}
					}
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (Server.playerHandler.players[j] != null) {
				Client c2 = (Client)Server.playerHandler.players[j];
						
						
							
							if (c.playerName.equalsIgnoreCase("Yorben")) {
								c2.sendMessage("<col=16777215><shad=16515072><img=5> [Head Admin] <img=5></col></shad> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
		
							}else if (c.playerName.equalsIgnoreCase(""+ Config.CO_OWNER +"")) {
								c2.sendMessage("<col=16777215><shad=14024897><img=3> [Co Owner]</col></shad> <img=3> "
								+ Misc.optimizeText(c.playerName) +"</col></shad>:<col=16777215><shad=14024897> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerName.equalsIgnoreCase(""+ Config.HEAD_MOD +"")) {
								c2.sendMessage("<col=16777215><shad=65459><img=5> [Head Mod]</col></shad> <img=5>"
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
							
							}else if (c.playerRights == 3){
							c2.sendMessage("<col=16777215><shad=14024897><img=3> [Main Owner]</col></shad> <img=3><col=16710868><shad=11820804> "
								+ Misc.optimizeText(c.playerName) +"</col></shad>:<col=63487><shad=4980991> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerRights == 1){
								c2.sendMessage("<col=16777215><shad=58879><img=1> [Mod]</col></shad> <img=1> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerRights == 5){
								c2.sendMessage("<col=16777215><shad=16711705><img=5> [Admin]</col></shad> <img=5> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 6){
								c2.sendMessage("<col=16515072><shad=262025><img=6> [Super Donator]</col></shad> <img=6> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
							
							}else if (c.playerRights == 7){
								c2.sendMessage("<col=16515072><shad=65297><img=7> [Extreme Donator]</col></shad> <img=7> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerRights == 4){
								c2.sendMessage("<col=16515072><shad=5766915><img=4> [Donator]</col></shad> <img=4> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerRights == 8){
								c2.sendMessage("<col=16515072><shad=16712669><img=8> [Graphics Team]</col></shad> <img=8> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
						    }else if (c.playerRights == 0 && (c.npcKills >= 1000)) {
								c2.sendMessage("<col=16515072><shad=16712669>[Member]</col></shad> "
								+ Misc.optimizeText(c.playerName) +":<col=255> "
								+ Misc.optimizeText(playerCommand.substring(5)) +"");
								
							}else if (c.playerRights == 0 && (c.npcKills < 1000)) {
								c.sendMessage("You must become a member," + c.npcKills + "/1000 Npc Kills."); 
							/**}else if (c.playerRights == 0 && (c.npcKills < 1000)) {
							c2.sendMessage("[Guest]"
							+ Misc.optimizeText(c.playerName) +": "
							+ Misc.optimizeText(playerCommand.substring(5)) +"");	*/
									
							}
						}
					}
				}
        }
	
	
     public void graphicsCommands(Client c, String playerCommand)
    {	
	
	if (playerCommand.startsWith("dice") && c.sit == false) {
	if(c.inWild()) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>Er, it's not to smart to do this in the Wilderness.");
				return;
			}
				c.forcedText = "["+ Misc.optimizeText(c.playerName) +"] Just Rolled "+ Misc.random(100) +" On The Dice!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("you roll the dice...");
			
			}
			
		if (playerCommand.startsWith("timedmute") && c.playerRights >= 1 && c.playerRights <= 3) {
						try {  
						String[] args = playerCommand.split("-");
												if(args.length < 2) {
													c.sendMessage("Currect usage: ::timedmute-playername-time");
													return;
												}
												String playerToMute = args[1];
												int muteTimer = Integer.parseInt(args[2])*1000;
				 
						for(int i = 0; i < Config.MAX_PLAYERS; i++) {
							if(Server.playerHandler.players[i] != null) {
								if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMute)) {
									Client c2 = (Client) Server.playerHandler.players[i];
									c2.sendMessage("You have been muted by: " + c.playerName+" for "+muteTimer/1000+" seconds");
																		c2.muteEnd = System.currentTimeMillis()+ muteTimer;
									break;
								}
							}
						}
												 
																									 
					} catch(Exception e) {
						c.sendMessage("Player Must Be Offline.");
					}          
				}
					
	if (playerCommand.startsWith("mute") && playerCommand.charAt(3) == ' ') {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (Server.playerHandler.players[i] != null) {
			 Client c2 = (Client)Server.playerHandler.players[i];
			c2.sendMessage(" " + c.playerName + " JUST MUTED "+c2.playerName+"!! </col>");

			}
			}
			} 
			
	if (playerCommand.startsWith("mute")) {
			try {	
			String playerToBan = playerCommand.substring(5);
			Connection.addNameToMuteList(playerToBan);
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(Server.playerHandler.players[i] != null) {
			if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
			Client c2 = (Client)Server.playerHandler.players[i];
			c2.sendMessage("You have been muted by: " + c2.playerName);
			break;
			} 
			}
			}
			} catch(Exception e) {
			c.sendMessage("Player Must Be Offline.");
			}			
			}		
			
			
	if (playerCommand.startsWith("timedban") && c.playerRights >= 1 && c.playerRights <= 3) { // use as ::ban name
 
					try {  
                                String[] args = playerCommand.split("-");
                                if(args.length < 2) {
                                    
                                    c.sendMessage("Correct usage: ::ban-playername-time");
                                    return;
                                     
                                }
                                 
                                String playerToBan = args[1];
                                int secondsToBan = Integer.parseInt(args[2])*1000;
                
        for(int i = 0; i < Config.MAX_PLAYERS; i++) {
            if(Server.playerHandler.players[i] != null) {
                if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                                                            Player o = Server.playerHandler.players[i];
                                                            o.banStart = System.currentTimeMillis();
                                                            o.banEnd = System.currentTimeMillis()+ secondsToBan;
                                                            o.disconnected = true;
									Connection.addNameToBanList(playerToBan);
									Connection.addNameToFile(playerToBan);
																				break;
							}
						}
					}
         
					c.sendMessage("You banned the player: "+playerToBan+" for "+secondsToBan/1000+" seconds");     
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
	if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			}	
			
	public void moderatorCommands(Client c, String playerCommand) 
	{			
					if (playerCommand.startsWith("checkbank")) {
							if(c.InDung()) {
									c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
							return;
							}    
												String[] args = playerCommand.split(" ", 2);
								for(int i = 0; i < Config.MAX_PLAYERS; i++)
								{
									Client o = (Client) Server.playerHandler.players[i];
									if(Server.playerHandler.players[i] != null)
									{
										if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
										{
														c.getPA().otherBank(c, o);
										break;
										}
									}
								}
							}
							
						
					if (playerCommand.startsWith("checkinv")) {
								try {
									String[] args = playerCommand.split(" ", 2);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										Client o = (Client) Server.playerHandler.players[i];
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
														c.getPA().otherInv(c, o);
															break;
											}
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline."); 
									}
							}
							if (playerCommand.startsWith("unban")) {
								try {	
									String playerToBan = playerCommand.substring(6);
									Connection.removeNameFromBanList(playerToBan);
									c.sendMessage(playerToBan + " has been unbanned.");
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
								}
							}
					if (playerCommand.startsWith("unmute")) {
								try {	
									String playerToBan = playerCommand.substring(7);
									Connection.unMuteUser(playerToBan);
										c.sendMessage("You have Unmuted "+c.playerName+".");
									
									
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");

								}			
							}
					if (playerCommand.startsWith("ipmute")) {
								try {	
									String playerToBan = playerCommand.substring(7);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
												Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
												c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
												Client c2 = (Client)Server.playerHandler.players[i];
												c2.sendMessage("You have been muted by: " + c.playerName);
												break;
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
								}	
							}	
							
							
					if (playerCommand.startsWith("unipmute")) {
								try {	
									String playerToBan = playerCommand.substring(9);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
												Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
												c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
												break;
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
										}			
									}
					
									
					if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("<shad=16745472>[Server]</col><shad=65288> " +c2.playerName+ " Got banned by. " + c.playerName+ ".");

						}
					}
				}  
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
			try {	
			String playerToBan = playerCommand.substring(4);
			Connection.addNameToBanList(playerToBan);
			Connection.addNameToFile(playerToBan);
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(Server.playerHandler.players[i] != null) {
			if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
			Server.playerHandler.players[i].disconnected = true;
			Client c2 = (Client)Server.playerHandler.players[i];
			c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
			} 
			}
			}
			} catch(Exception e) {
			c.sendMessage("Player Must Be Offline.");
			}
			}
			
							if (playerCommand.equalsIgnoreCase("teletohelp")) {
						if(c.inWild()) {
								c.sendMessage("<shad=16745472>[Server]</col><shad=65288>You can not tele to someone in the wild!");
								return;
								}
						RequestHelp.teleportToPlayer(c);
						}
							
							
							if(playerCommand.startsWith("jail")) {
							if(c.inWild()) {
							c.sendMessage("<shad=16745472>[Server]</col><shad=65288> get out of the wild to jail-unjail!");
							return;
							}
							if(c.InDung()) {
							c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
							return;
							}          
							try {
							String playerToBan = playerCommand.substring(5);
							for(int i = 0; i < Config.MAX_PLAYERS; i++) {
							if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client)Server.playerHandler.players[i];
							if(c2.InDung()) {
							c.sendMessage("You cannot Jail/Unjail somone in Dung.");
							}
							int randomjail = Misc.random(3);
							if (randomjail == 1) {
							c2.getPA().startTeleport(2773, 2794, 0, "modern");
							}
							if (randomjail == 2) {
							c2.getPA().startTeleport(2775, 2796, 0, "modern");
							}
							if (randomjail == 3) {
							c2.getPA().startTeleport(2775, 2798, 0, "modern");
							}
							if (randomjail == 0) {
							c2.getPA().startTeleport(2775, 2800, 0, "modern");
							}
							c2.Jail = true;
							c2.sendMessage("You have been jailed by "+c.playerName+"");
							c.sendMessage("You have Jailed "+c2.playerName+".");
							} 
							}
							}
							} catch(Exception e) {
							c.sendMessage("Player Must Be Offline.");
							}
							}
							
							if(playerCommand.startsWith("unjail")) {
							if(c.inWild()) {
							c.sendMessage("<shad=16745472>[Server]</col><shad=65288> get out of the wild to jail-unjail!");
							return;
							}
										if(c.InDung()) {
							c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
							return;
							}    
											   try {
									String playerToBan = playerCommand.substring(7);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
									if(Server.playerHandler.players[i] != null) {
									if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
									Client c2 = (Client)Server.playerHandler.players[i];
									if(c2.InDung()) {
										c.sendMessage("You cannot Jail/Unjail somone in Dung.");
									}
									c2.getPA().startTeleport(3086, 3493, 0, "modern");
									c2.monkeyk0ed = 0;
									if(c2.InDung()) {
														c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
														return;
														}
														c2.Jail = false;
									c2.sendMessage("You have been unjailed by "+c.playerName+".");
									c.sendMessage("Successfully unjailed "+c2.playerName+".");
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
								}
							}
						
							}

    public void administratorCommands(Client c, String playerCommand)
    {
							if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			if (playerCommand.startsWith("empty") && c.playerName.equalsIgnoreCase(""+ Config.ADMIN +"")) {
												c.getItems().removeAllItems();
												c.sendMessage("You empty your inventory");
										}
					

							
							if (playerCommand.startsWith("pnpc"))
								{
								try {
									int newNPC = Integer.parseInt(playerCommand.substring(5));
									if (newNPC <= 500000 && newNPC >= 0) {
										c.npcId2 = newNPC;
										c.isNpc = true;
										c.updateRequired = true;
										c.setAppearanceUpdateRequired(true);
									} 
									else {
										c.sendMessage("No such P-NPC.");
									}
								} catch(Exception e) {
									c.sendMessage("Wrong Syntax! Use as ::pnpc #");
								}
							}
							
							if(playerCommand.startsWith("npc")) {
								try {
									int newNPC = Integer.parseInt(playerCommand.substring(4));
									if(newNPC > 0) {
										Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
										c.sendMessage("You spawn a Npc.");
									} else {
										c.sendMessage("No such NPC.");
									}
								} catch(Exception e) {
									
								}			
							}
							if (playerCommand.startsWith("ipmute") && playerCommand.charAt(3) == ' ') {
								for (int i = 0; i < Config.MAX_PLAYERS; i++) {
									if (Server.playerHandler.players[i] != null) {
										 Client c2 = (Client)Server.playerHandler.players[i];
										c2.sendMessage(" " + c.playerName + " JUST IPMUTED "+c2.playerName+"!! </col>");

										}
									}
							} 
							if (playerCommand.startsWith("object")) {
								String[] args = playerCommand.split(" ");				
								c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
							}
							
							if (playerCommand.startsWith("mark")) {
								try {	
									String playerToBan = playerCommand.substring(5);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
												Client c2 = (Client)Server.playerHandler.players[i];
												c2.BlackMarks++;
												c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
												c.sendMessage("You have given " + c2.playerName + " a blackmark.");
												if(c2.BlackMarks >= 5) {
												Connection.addNameToBanList(playerToBan);
												Connection.addNameToFile(playerToBan);
												Server.playerHandler.players[i].disconnected = true;
												}
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Online.");
								}
							}
							
							if (playerCommand.startsWith("shop") && c.playerRights == 3) {
							try {
								c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
							} catch(Exception e) {
								c.sendMessage("Invalid input data! try typing ::shop 1");
							}
						}
						
							
							if (playerCommand.startsWith("gfx") && c.playerRights == 3) {
								String[] args = playerCommand.split(" ");
								c.gfx0(Integer.parseInt(args[1]));
							}

							if (playerCommand.startsWith("xteleto")) {
								if(c.InDung()) {
							c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not xteleto when inside Dungeoneering");
							return;
							}          
								String name = playerCommand.substring(8);
								for (int i = 0; i < Config.MAX_PLAYERS; i++) {
									if (Server.playerHandler.players[i] != null) {
										if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
											c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
										}
									}
								}			
							}
							
					
	if (playerCommand.startsWith("xteletome") || playerCommand.startsWith("Xteletome") || playerCommand.startsWith("XTELETOME") || playerCommand.startsWith("xTeletome")) {
								try {	
									String playerToTele = playerCommand.substring(10);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
												Client c2 = (Client)Server.playerHandler.players[i];
												c2.sendMessage("You have been teleported to " + c.playerName);
												c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
												break;
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
								}			
							}
							
							
								
    }
	/**
	*Owner Commands Start Here
	**/
    public void ownerCommands(Client c, String playerCommand)
    { 
	if (playerCommand.startsWith("reloadspawns") && c.playerRights >= 3) {
						Server.npcHandler = null;
						Server.npcHandler = new server.model.npcs.NPCHandler();
						for (int j = 0; j < Server.playerHandler.players.length; j++) {
							if (Server.playerHandler.players[j] != null) {
								Client c2 = (Client)Server.playerHandler.players[j];
								 c2.sendMessage("<shad=15695415>[TX News]:" + c.playerName + " " + " Has reloaded all Npc Spawns.</col> " + Misc.optimizeText(playerCommand.substring(3)));
							}
						}
					 
					}
		if (playerCommand.startsWith("reloadshops") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
								Server.shopHandler = new server.world.ShopHandler();
								for (int j = 0; j < Server.playerHandler.players.length; j++) {
									if (Server.playerHandler.players[j] != null) {
										Client c2 = (Client)Server.playerHandler.players[j];
												  c2.sendMessage("<shad=15695415>[TX News]:" + c.playerName + " " + " Has refilled the shops.</col> " + Misc.optimizeText(playerCommand.substring(3)));
									}
								}
							}
							
							if (playerCommand.startsWith("ipban") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
								try {
									String playerToBan = playerCommand.substring(6);
									for(int i = 0; i < Config.MAX_PLAYERS; i++) {
										if(Server.playerHandler.players[i] != null) {
											if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
												Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
												Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
												c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
										Client c2 = (Client)Server.playerHandler.players[i];
												Server.playerHandler.players[i].disconnected = true;
												c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
											} 
										}
									}
								} catch(Exception e) {
									c.sendMessage("Player Must Be Offline.");
								}
							}
							
							
							if (playerCommand.startsWith("getip")) { 
							try {
					String iptoget = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {

							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(iptoget)) {
								c.sendMessage("Ip:"+Server.playerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("empty")) {
        		c.getItems().removeAllItems();
        		c.sendMessage("You empty your inventory");
			}
			if (playerCommand.equalsIgnoreCase("bank")) {
				if(c.InDung()) {
	                c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
			return;
			}    
				c.getPA().openUpBank();
			}
			
			if (playerCommand.startsWith("heal") && c.playerRights == 3) {
			if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
				String name = playerCommand.substring(5);
				if (c.validClient(name)) {
					Client p = c.getClient(name);
					for (int i = 0; i < 20; i++) {
						p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
						p.getPA().refreshSkill(i);
					}
					p.sendMessage("You have been healed by " + c.playerName + ".");
				} else {
					c.sendMessage("Player must be offline.");
				}
			} else {
				for (int i = 0; i < 20; i++) {
					c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
					c.getPA().refreshSkill(i);
				}
				c.freezeTimer = -1;
				c.frozenBy = -1;
				c.sendMessage("You have been healed.");
			}
		}

               if (playerCommand.startsWith("item")) {
			   if(c.inWild()) {
			c.sendMessage("<shad=16745472>[Server]</col><shad=65288> You can not spawn item in wildy!");
			return;
			}
				try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				for(int i : c.cantSpawn) {
				if(i == newItemID) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288> "+ Config.OWNER +" Has disabled this item from being spawned.");
				return;
				}
				}
				if ((newItemID <= 30000) && (newItemID >= 0)) {
				c.getItems().addItem(newItemID, newItemAmount);
				} else {
				c.sendMessage("That item ID does not exist.");
				}
				} else {
				c.sendMessage("Wrong usage: (Ex::item_ID_Amount)(::item 995 1))");
				}
				} catch(Exception e) {
				} 
				} 

				if (playerCommand.startsWith("copy")) {
	 int[]  arm = new int[14];
	 String name = playerCommand.substring(9);
	 for (int j = 0; j < Server.playerHandler.players.length; j++) {
	 	 if (Server.playerHandler.players[j] != null) {
	 	 	 Client c2 = (Client)Server.playerHandler.players[j];
	 	 	 if(c2.playerName.equalsIgnoreCase(playerCommand.substring(5))) {
	 	 	 	 for(int q = 0; q < c2.playerEquipment.length; q++) {
	 	 	 	 	 arm[q] = c2.playerEquipment[q];
	 	 	 	 	 c.playerEquipment[q] = c2.playerEquipment[q];
	 	 	 	 }
	 	 	 	 for(int q = 0; q < arm.length; q++) {
	 	 	 	 	 c.getItems().setEquipment(arm[q],1,q);
								 }
							 }	
						 }
					 }
				}
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
	       
                
                        if(playerCommand.startsWith("unpc")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }
          
						if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}
 
if (playerCommand.startsWith("npcid")) {
    try {
        String name = playerCommand.substring(6).replaceAll(" ", "_");
        int npcId = Server.npcHandler.getNpcId(name);
        c.sendMessage("Id: "+npcId+" Name: "+name.replaceAll("_", " "));
    } catch (Exception e) {
        c.sendMessage("To avoid exceptions, type the command correctly. '::npcname X'");
    }
}

if (playerCommand.equals("alltome") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
			c2.teleportToX = c.absX;
                        c2.teleportToY = c.absY;
                        c2.heightLevel = c.heightLevel;
				c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
			}



				if (playerCommand.startsWith("runes") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
					c.getItems().addItem(554, 241700000);
					c.getItems().addItem(555, 241700000);
					c.getItems().addItem(556, 241700000);
					c.getItems().addItem(557, 241700000);
					c.getItems().addItem(558, 241700000);
					c.getItems().addItem(559, 241700000);
					c.getItems().addItem(560, 241700000);
					c.getItems().addItem(562, 241700000);
					c.getItems().addItem(563, 241700000);
					c.getItems().addItem(561, 241700000);
					c.getItems().addItem(9075, 241700000);
					c.getItems().addItem(565, 241700000);
					c.getItems().addItem(566, 241700000);
					}
	
		if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"") && c.playerName.equalsIgnoreCase(""+ Config.CO_OWNER +"")) {
				try {	
					String playerToDemote = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been demoted by " + c.playerName);
								c2.playerRights = 0;
								c2.logout();
								c2.isDonator = 0;
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
if (playerCommand.startsWith("rape")) {
			try { 
				String playerToBan = playerCommand.substring(5);
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan))
				{
				Client c2 = (Client)Server.playerHandler.players[i];
				c.sendMessage("You have RAPED " + c2.playerName);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);													           
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				c2.getPA().sendFrame126("" + Config.SITE_SPAM + "", 12000);
				break;
					}
			}
		}
	} catch(Exception e) {
	c.sendMessage("Player Must Be Offline.");
	}
}

	if (playerCommand.startsWith("alert") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				String msg = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("Alert##" + Config.SERVER_NAME + " Announcement##" + msg + "##");

					}
				}
			}
			
			if (playerCommand.startsWith("interface")) {
								String[] args = playerCommand.split(" ");
								c.getPA().showInterface(Integer.parseInt(args[1]));
							}
							if (playerCommand.startsWith("sm") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
								for (int j = 0; j < Server.playerHandler.players.length; j++) {
									if (Server.playerHandler.players[j] != null) {
										Client c2 = (Client)Server.playerHandler.players[j];
										c2.sendMessage("<shad=15695415>[TX News]</col> " + Misc.optimizeText(playerCommand.substring(3)));
									}
								}
							}
			if (playerCommand.startsWith("setlevel") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
				try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client target = null;
				for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(Server.playerHandler.players[i] != null) {
				if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
				target = (Client)Server.playerHandler.players[i];
				break;
				}
				}
				}
				if (target == null) {
				c.sendMessage("Player doesn't exist.");
				return;
				}
				c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
				target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
				target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
				target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
				target.getPA().refreshSkill(skill);
				} catch(Exception e) {
				c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
				}            
				}       
            if (playerCommand.equalsIgnoreCase("levelids")) {
					c.sendMessage("Attack = 0,   Defence = 1,  Strength = 2,");
					c.sendMessage("Constitution = 3,   Ranged = 4,   Prayer = 5,");
					c.sendMessage("Magic = 6,   Cooking = 7,   Woodcutting = 8,");
					c.sendMessage("Fletching = 9,   Fishing = 10,   Firemaking = 11,");
					c.sendMessage("Crafting = 12,   Smithing = 13,   Mining = 14,");
					c.sendMessage("Herblore = 15,   Agility = 16,   Thieving = 17,");
					c.sendMessage("Slayer = 18,   Farming = 19,   Runecrafting = 20");
                    c.sendMessage("Hunter = 21,   summoning = 22,   pk = 23   Dungeoneering = 24");
                        }
				if (playerCommand.startsWith("pring") && c.playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
					c.getItems().addItem(773, 1);
					}
    }

      public void extremeCommands(Client c, String playerCommand)
    {
	}
	
	public void superCommands(Client c, String playerCommand)
    {
	}
	public void DonatorCommands(Client c, String playerCommand)
    {
    		if (playerCommand.startsWith("dice") && c.sit == false) {
	if(c.inWild()) {
				c.sendMessage("<shad=16745472>[Server]</col><shad=65288>Er, it's not to smart to do this in the Wilderness.");
				return;
			}
				c.forcedText = "["+ Misc.optimizeText(c.playerName) +"] Just Rolled "+ Misc.random(100) +" On The Dice!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.sendMessage("you roll the dice...");
			
			}
if (playerCommand.equalsIgnoreCase("bank")) {
				if(c.InDung()) {
	                c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
			return;
			}    
				c.getPA().openUpBank();
			}
}
}