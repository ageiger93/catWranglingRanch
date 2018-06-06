 package server.model.players;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.mina.common.IoSession;
import server.Config;
import server.Server;
import java.net.URL;
import server.model.npcs.*;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.model.npcs.NPC;

import server.model.players.skills.Summoning;
import server.util.Stream;
import server.util.MadTurnipConnection;
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event; 
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.event.EventContainer;
import server.model.minigames.WarriorsGuild;
import server.model.minigames.Gambling;

public class Client extends Player {
public void resetRanks() {
 for (int i = 0; i < 10; i++) {
 ranks[i] = 0;
 rankPpl[i] = "";
 }
 }
 public void highscores() {
 getPA().sendFrame126(""+ Config.SERVER_NAME +" Top 10 Players", 6399);
 for(int i = 0; i < 10; i++) {
 if(ranks[i] > 0) {
 getPA().sendFrame126("Rank "+(i+1)+": "+rankPpl[i]+ "- Total Level: " +ranks[i], 6402+i);
 }
 }
 getPA().showInterface(6308);
 flushOutStream();
 resetRanks();
 }
 public int playerRank = 0;
 public static int[] ranks = new int[11];
 public static String[] rankPpl = new String[11]; 
	public byte buffer[] = null;
 public int cannonTimer = 0; 
 	public int s;
	public Stream inStream = null, outStream = null;
	private IoSession session;
	public static PlayerSave save;
	public static Client cliento2;
		public int totalstored;
	public int currentDamage = 0;
	public int followPlayer;
	public int ZombiesToKill;
	public int ZombiesKilled;
	public int RoundId;
	public int npcslot;
	public int summoningnpcid;
		public int timer;
	private TradeLog tradeLog = new TradeLog(this);
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Potions potion = new Potions(this);
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	private WarriorsGuild warriorsGuild = new WarriorsGuild();
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	private Gambling gamble = new Gambling(this);
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Runecrafting runecrafting = new Runecrafting(this);
	private Woodcutting woodcutting = new Woodcutting(this);
	private Mining mine = new Mining(this);
	public Agility ag = new Agility(this);
	private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Curse curse = new Curse(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	private Firemaking firemaking = new Firemaking(this);
	private Herblore herblore = new Herblore(this);
	public Summoning Summoning = new Summoning(this);
	private int somejunk;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
		public int dungRest = 0;
	public int returnCode = 2; 
	public int clawDamage;
	public int clawIndex;
	public int clawType = 0;
	private Future<?> currentTask;
	public boolean officialClient = true;
	public boolean isSmelting = false;
	public boolean slayerHelmEffect;
	public boolean basket = false;
	public long lastRefresh = 0;
	public boolean slayerHelmetEffect;
	public String lastKilled = "";

	private final String[] randomMessages = {
		"pie", "kebab", "chocolate", "bagel", "triangle",
		"square", "bread"
	};
	
	private final int[][] responseButtons = {
		{ 63013, 0 }, { 63014, 1 }, { 63015, 2 }, { 63009, 3 },
		{ 63010, 4 }, { 63011, 5 }, { 63012, 6 }
	};
	
	public void executeRandom() {
		getPA().sendFrame126("", 16131);
		getPA().showInterface(16135);
		foodType = Misc.random(6);
		getPA().sendFrame126("Please select the " + randomMessages[foodType] + " for a cash reward!", 16145);
	}
	
	public void checkResponse(int button) {
		for (int i = 0; i < responseButtons.length; i ++) {
			if (responseButtons[i][0] == button) {
				if (responseButtons[i][1] == foodType) {
					sendMessage("Congratulations! You've completed the random event.");
					getPA().closeAllWindows();
					getItems().addItem(995, Misc.random(2000));
					return;
				}
				
				/**
				 * You can add other things here, such as a teleport or something.
				 * This is where it fails and they click the wrong food type.
				 */
				sendMessage("Please select the " + randomMessages[foodType] + " to continue");
			}
		}
	}
	
	public int foodType = -1;
	public long lastRandom;
	public void walk(int EndX, int EndY, int Emote) {
     walkToEmote(Emote);
    getPA().walkTo2(EndX, EndY);
}
        public void walkToEmote(int id) {
                isRunning2 = false;
        playerWalkIndex = id;
                getPA().requestUpdates(); //this was needed to make the agility work
    }

    public void stopEmote() {
        playerWalkIndex = 0x333;
                agilityEmote = false;
                getPA().requestUpdates(); //this was needed to make the agility work
    }
        public void obsticle(int Emote, int Req, int newX, int newY, final int agilityTimer, int amtEXP, String message) {
                if (playerLevel[16] >= Req) {
                        agilityEmote = true;
                        walk(newX, newY, Emote);
                        sendMessage(message);
                        getPA().addSkillXP(amtEXP, playerAgility);
                        EventManager.getSingleton().addEvent(new Event() {
                                public void execute(EventContainer c) {
                                        stopEmote();
                                        c.stop();
                                }
                        }, agilityTimer);
                } else {
                sendMessage("You Need " + Req + " Agility To Do This Obsticle");
                }       
        }       

        public void agilityDelay(int Emote, final int X, final int Y, final int H, int Req, int amtEXP, String message) {
                if (playerLevel[16] >= Req) {
                        sendMessage(message);
                        startAnimation(Emote);
                        agilityEmote = true;
                        getPA().addSkillXP(amtEXP, playerAgility);
                        EventManager.getSingleton().addEvent(new Event() {
                                public void execute(EventContainer c) {
                                        getPA().movePlayer(X, Y, H);
                                        agilityEmote = false;
                                        c.stop();
                                }
                        }, 1000);
                } else {
                        sendMessage("You Need " + Req + " Agility To Do This Obsticle");
                }
        }
			
		public void applyFollowing()
	{
		if (follow2 > 0)
		{
			//Client p = Server.playerHandler.client[followId];
			Client p = (Client) Server.playerHandler.players[follow2];     
			if (p != null)
			{
				if (isDead)
				{
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(p.absX, p.absY, absX, absY, 25))
				{
					follow(0, 3, 1);
					return;
				}
			}
			else if (p == null)
			{
				follow(0, 3, 1);
			}
		}
		else if (follow2 > 0)
		{
			//Server.npcHandler.npcs.NPC npc = Server.npcHandler.npcs[followId2];
			if (Server.npcHandler.npcs[followId2] != null)
			{
				if (Server.npcHandler.npcs[followId2].isDead)
				{
					follow(0, 3, 1);
					return;
				}
				if (!goodDistance(Server.npcHandler.npcs[followId2].absX, Server.npcHandler.npcs[followId2].absY, absX, absY, 25))
				{
					follow(0, 3, 1);
					return;
				}
			}
			else if (Server.npcHandler.npcs[followId2] == null)
			{
				follow(0, 3, 1);
			}
		}
	}

	public int followDistance = 0;

		public void follow(int slot, int type, int distance)
	{
		if (slot > 0 && slot == follow2 && type == 1 && follow2 > 0 && followDistance != distance && (/*usingOtherRangeWeapons || */usingBow || usingMagic))
                    return;
		else if (slot > 0 && slot == followId2 && type == 0 && followId2 > 0 && followDistance >= distance && distance != 1)
                    return;
		//else if (type == 3 && followId2 == 0 && follow2 == 0)
                    //return;
		outStream.createFrame(174);
		if (freezeTimer > 0) {
			outStream.writeWord(0);
		} else {
			outStream.writeWord(slot);
			if (type == 0) {
				follow2 = 0;
				followId2 = slot;
				faceUpdate(followId2);
			} else if (type == 1) {
				followId2 = 0;
				follow2 = slot;
				faceUpdate(32768 + follow2);
			} else if (type == 3) {
				followId2 = 0;
				follow2 = 0;
				followDistance = 0;
				faceUpdate(65535);
			}
			followDistance = distance;
		}
		outStream.writeByte(type);
		outStream.writeWord(distance);
	}
	public Client(IoSession s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized(this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;
		}
		inStream = new Stream(new byte[Config.BUFFER_SIZE]);
		inStream.currentOffset = 0;
		buffer = new byte[Config.BUFFER_SIZE];
	}
	public void frame1() // cancels all player and npc emotes within area!
    {
        for (Player p : PlayerHandler.players) {
            if (p != null) {
                Client c = (Client) p;
                c.outStream.createFrame(1);
            }
        }
        updateRequired = true;
        appearanceUpdateRequired = true;
    }
		public Client getClient(String name) {
		name = name.toLowerCase();
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(validClient(i)) {
				Client client = getClient(i);
				if(client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}
	public Client getClient(int id) {
		return (Client) Server.playerHandler.players[id];
	}
	public boolean validClient(int id) {
		if (id < 0 || id > Config.MAX_PLAYERS) {
			return false;
		}
		return validClient(getClient(id));
	}
	public boolean validClient(String name) {
		return validClient(getClient(name));
	}
	public boolean validClient(Client client) {
		return (client != null && !client.disconnected);
	}
	public boolean validNpc(int index) {
		if (index < 0 || index >= Config.MAX_NPCS) {
			return false;
		}
		NPC n = getNpc(index);
		if (n != null) {
			return true;
		}
		return false;
	}
	public NPC getNpc(int index) {
		return ((NPC) Server.npcHandler.npcs[index]);
	}
	public void yell(String s) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (validClient(i)) {
				getClient(i).sendMessage(s);
			}
		}
	}
	
	public void degradeVls() {
if(playerEquipment[playerWeapon] == 13901 && vlsLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Vesta longsword crumbles to dust!");
vlsLeft = 1000;
}
}
public void degradeVSpear() {
if(playerEquipment[playerWeapon] == 13907 && vSpearLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Vesta spear crumbles to dust!");
vSpearLeft = 1000;
}
}
public void degradeStat() {
if(playerEquipment[playerWeapon] == 13904 && statLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Statius warhammer crumbles to dust!");
statLeft = 1000;
}
}
public void degradeVTop() {//vesta top
if(playerEquipment[playerChest] == 13889 && vTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Vesta chainbody crumbles to dust!");
vTopLeft = 1000;
}
}
public void degradeVLegs() {//vesta legs
if(playerEquipment[playerLegs] == 13895 && vLegsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Vesta plateskirt crumbles to dust!");
vLegsLeft = 1000;
}
}
public void degradeSTop() {//statius top
if(playerEquipment[playerChest] == 13886 && sTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Statius platebody crumbles to dust!");
sTopLeft = 1000;
}
}
public void degradeSLegs() {//statius legs
if(playerEquipment[playerLegs] == 13892 && sLegsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Statius platelegs crumbles to dust!");
sLegsLeft = 1000;
}
}
public void degradeSHelm() {//statius helm
if(playerEquipment[playerHat] == 13898 && sHelmLeft < 1){
playerEquipment[playerHat] = -1;
playerEquipmentN[playerHat] = 0;
getItems().wearItem(-1, 1, playerHat);
sendMessage("Your Statius full helm crumbles to dust!");
sHelmLeft = 1000;
}
}
public void degradeZHood() {//zuriel hood
if(playerEquipment[playerHat] == 13866 && zHoodLeft < 1){
playerEquipment[playerHat] = -1;
playerEquipmentN[playerHat] = 0;
getItems().wearItem(-1, 1, playerHat);
sendMessage("Your Zuriel hood crumbles to dust!");
zHoodLeft = 1000;
}
}
public void degradeZTop() {//zuriel top
if(playerEquipment[playerChest] == 13860 && zTopLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Zuriel robe top crumbles to dust!");
zTopLeft = 1000;
}
}
public void degradeZBottom() {//zuriel hood
if(playerEquipment[playerLegs] == 13863 && zBottomLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Zuriel robe bottom crumbles to dust!");
zBottomLeft = 1000;
}
}
public void degradeZStaff() {//zuriel staff
if(playerEquipment[playerWeapon] == 13870 && zStaffLeft < 1){
playerEquipment[playerWeapon] = -1;
playerEquipmentN[playerWeapon] = 0;
getItems().wearItem(-1, 1, 3);
sendMessage("Your Zuriel staff crumbles to dust!");
zStaffLeft = 1000;
}
}
public void degradeMBody() {//morrigans body
if(playerEquipment[playerChest] == 13872 && mBodyLeft < 1){
playerEquipment[playerChest] = -1;
playerEquipmentN[playerChest] = 0;
getItems().wearItem(-1, 1, playerChest);
sendMessage("Your Morrigans leather body crumbles to dust!");
mBodyLeft = 1000;
}
}
public void degradeMChaps() {//morrigans chaps
if(playerEquipment[playerLegs] == 13875 && mChapsLeft < 1){
playerEquipment[playerLegs] = -1;
playerEquipmentN[playerLegs] = 0;
getItems().wearItem(-1, 1, playerLegs);
sendMessage("Your Morrigans chaps crumbles to dust!");
mChapsLeft = 1000;
}
}
	
	
	public int maxstore = 0;

	public void storesummon(int npcType) {
		switch (npcType) {

		case 6807:

			if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()
									&& Server.npcHandler.npcs[i].npcId == npcslot) {
								sendMessage("You are now storing items inside your npc");
								Summoning().store();
							}
						}
					}
				}

			}
			break;

		}
	}

	public void firstslot() {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {

		}
	}

	public int summoningslot = 0;

	public int storeditems[] = new int[29];

	public boolean picking = false;

	public int amount[] = new int[29];
	public boolean occupied[] = new boolean[29];

	public boolean storing = false;

	
	public int attackingplayer;
	public int lastsummon;
	public boolean summon;
	
	
		public void jadSpawn() {
			//getPA().movePlayer(absX, absY, playerId * 4);
			getDH().sendDialogues(41, 2618);
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
	}
		/*	public void handCannonDestory() {
		cannonTimer = 0;
		int chance = playerLevel[playerFiremaking] * 5 + 25;
		if(specGfx)
			chance/=2;
		if(Misc.random(chance) == 1)
			EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
			if(cannonTimer <= 0) {
				gfx0(2140);
    			playerEquipment[playerWeapon] = -1;
    			sendMessage("Your hand cannon explodes!");
    			int damage = Misc.random(15) + 1;
				setHitDiff(damage);
				setHitUpdateRequired(true);
    			dealDamage(Misc.random(15) + 1);
    			updateRequired = true;
				getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
    			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
    			getItems().resetBonus();
				getItems().getBonus();
				getItems().writeBonus();
				getPA().requestUpdates();getOutStream().createFrame(34);
				getOutStream().writeWord(6);
				getOutStream().writeWord(1688);
				getOutStream().writeByte(playerWeapon);
				getOutStream().writeWord(0);
				getOutStream().writeByte(0);
				updateRequired = true; 
				setAppearanceUpdateRequired(true);
				c.stop();
				} else {
					cannonTimer--;
				}
			}
		}, 500);
	}*/

	public boolean specGfx = false;
	public void handCannonSpec() {
		cannonTimer = 0;
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				cannonTimer--;
				if(cannonTimer == 0) {
					gfx0(2141);
					specGfx = true;
				}
				if(cannonTimer == 1) {
					if (playerIndex > 0)
						getCombat().fireProjectilePlayer();
					else if (npcIndex > 0)
						getCombat().fireProjectileNpc();	
					c.stop();
				}
			}
		}, 25);
	}
	
		public void clearQInterface() {
		for(int iD = 29172; iD <= 29264;iD++){
			getPA().sendFrame126("", iD);
		}
		getPA().sendFrame126("@whi@"+ Config.SERVER_NAME +": "+PlayerHandler.getPlayerCount()+" @whi@Online", 29155); //Tab Title
		//getPA().sendFrame126("Time Played:", 663);
		
		getPA().sendFrame126("@whi@Owners @gre@Co-Owner", 29161); //1st section title
			getPA().sendFrame126("@whi@"+ Config.OWNER +" @gre@"+ Config.CO_OWNER +"", 29162); //1rd section content
		
		getPA().sendFrame126("@whi@"+ Config.SERVER_NAME +" Website:", 29163); //2nd section title
		
	}

 public int specRestore = 0;
 public int antiqueSelect = 0;
public int lampused = 0;
public int lampxp = 0; 
 
public int totalLevel() {
    int total = 0;
    for(int i = 0; i <= 20; i++) {
        total += getPA().getLevelForXP(playerXP[i]);
    }
    return total;
}
public int getCombatLevel() {
        int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[4])) * 0.4875)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[6])) * 0.4875)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325) 
						+ ((getLevelForXP(playerXP[2])) * 0.325)
						+ ((getLevelForXP(playerXP[22])) * 0.121212));
			}
		return combatLevel;
	}
	
public void HighAndLow(){
	if (combatLevel < 15){
			int Low = 3;
			int High = combatLevel + 12;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
	if (combatLevel > 15 && combatLevel < 114){
			int Low = combatLevel - 12;
			int High = combatLevel + 12;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
	if (combatLevel > 114){
			int Low = combatLevel - 12;
			int High = 138;
				getPA().sendFrame126("@gre@"+Low+"@yel@ - @red@"+High+"", 199);

						}
						}



	public void flushOutStream() {	
		if(disconnected || outStream.currentOffset == 0) return;
		synchronized(this) {	
			StaticPacketBuilder out = new StaticPacketBuilder().setBare(true);
			byte[] temp = new byte[outStream.currentOffset]; 
			System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
			out.addBytes(temp);
			session.write(out.toPacket());
			outStream.currentOffset = 0;
		}
       }
	
	public void sendClan(String name, String message, String clan, int rights) {
		outStream.createFrameVarSizeWord(217);
		outStream.writeString(name);
		outStream.writeString(message);
		outStream.writeString(clan);
		outStream.writeWord(rights);
		outStream.endFrameVarSize();
	}
	
	public static final int PACKET_SIZES[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};

	public void destruct() {
		synchronized (this) {
               PlayerSave.saveGame(this);
                if(disconnected == true) { 
                   saveCharacter = true;
                }
		if(disconnected == true){
	     getTradeAndDuel().declineTrade();
 }
		if(session == null) 
			return;
		Server.panel.removeEntity(playerName);
		PlayerSave.saveGame(this);
		if (clanId >= 0)
			Server.clanChat.leaveClan(playerId, clanId);
		getPA().removeFromCW();
		if (inPits) {
		Server.fightPits.removePlayerFromPits(playerId);
		}
		Misc.println("[DEREGISTERED]: "+playerName+"  from IP: " +connectedFrom+ "");
		PlayerSave.saveGame(this);
                saveCharacter = true;
		HostList.getHostList().remove(session);
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}
}
	
		public void mymessage() {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				int r3 = 0;
				r3 = Misc.random(6);
				if (r3 == 0) {
					sendMessage(""+ Config.NEWS_ONE +"");
				} else if (r3 == 3) {
					sendMessage(""+ Config.NEWS_TWO +"");
				} else if (r3 == 4) {
					sendMessage(""+ Config.NEWS_THREE +"");
				} else if (r3 == 5) {
					sendMessage(""+ Config.NEWS_FOUR +"");
				}
			}
		}, 160000); //milisecondsservermessage
	};
	
	/**public void AutoSaveGame() {
		EventManager.getSingleton().addEvent(new Event() {
			public void execute(EventContainer c) {
				int r3 = 0;
				r3 = Misc.random(6);
				if (r3 == 0) {
					SaveGame();
				} else if (r3 == 3) {
					SaveGame();
				} else if (r3 == 4) {
					SaveGame();
				} else if (r3 == 5) {
					SaveGame();
				}
			}
		}, 9000); ///Server Auto Save
	};*/
	
	public void sendMessage(String s) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}
	
	public void sendDelayedMessage(String s,int secsUntilDisplay) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrameVarSize(253);
				outStream.writeString(s);
				outStream.endFrameVarSize();
			}
		}
	}
	public String[] qCS = { "Attack", "Strength", "Defence", "Ranged", "Prayer", "Magic", "Runecrafting"
						 , "Hitpoint", "Agility", "Herblore", "Thieving", "Crafting", "Fletching", "Slayer"
						 , "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting", "Farming" 
					};
	
	public int[][] qCAB = { {33206, 0}, 
							{33209, 2}, 
							{33212, 1}, 
							{33215, 4}, 
							{33218, 5}, 
							{33221, 6}, 
							{33224, 20}, 
							{33207, 3}, 
							{33210, 16}, 
							{33213, 15}, 
							{33216, 17}, 
							{33219, 12}, 
							{33222, 9}, 
							{47130, 18}, 
							{33208, 14}, 
							{33211, 13}, 
							{33214, 10}, 
							{33217, 7}, 
							{33220, 11}, 
							{33223, 8}, 
							{54104, 19}
				 };
public String qC = "[Quick Chat] ";
	public void setSidebarInterface(int menuId, int form) {
		synchronized (this) {
			if(getOutStream() != null) {
				outStream.createFrame(71);
				outStream.writeWord(form);
				outStream.writeByteA(menuId);
			}
		}
	}	
public void CatchimpNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - foodDelay >= 1500) { //anti spamm
		if (playerLevel[21] >= Req) { //first we check if he's high enough to catch
			if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) { //player got net?
				if (playerLevel[21] + Misc.random(10) >= Misc.random(20) + Req) { //catch chance
				if (Misc.random(1000) == 1) {
				sendMessage("You caught a GIGANTIC Impling and gained triple Experience!"); //looks like player got a net
				getItems().addItem(722, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp*3, 21); //AmtExp is different so its defined in the method
				} else {
				sendMessage("You Catched an Impling!"); //looks like player got a net
				getItems().addItem(itemId, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp, 21); //AmtExp is different so its defined in the method
				}
				} else {
				sendMessage("You Failed To Catch The Impling");
				startAnimation(6999);
				}
			} else { //player got net?
			sendMessage("You need to wear a butterfly net!"); //looks like he doesn't
			return;
			}	
		} else {
		sendMessage("You need atleast "+ Req +" Hunter To catch that Impling!");
		return;
		}
		foodDelay = System.currentTimeMillis();// we use food timer but it really doesn't mather, this is just used for anti-spamm :)
	}
}			


public void CatchHunterNpc(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - foodDelay >= 1500) { //anti spamm
		if (playerLevel[21] >= Req) { //first we check if he's high enough to catch
			if (playerEquipment[playerWeapon] == 10010 || playerEquipment[playerWeapon] == 11259) { //player got net?
				if (playerLevel[21] + Misc.random(10) >= Misc.random(20) + Req) { //catch chance
				if (Misc.random(1000) == 1) {
				sendMessage("You caught a GIGANTIC butterfly and gained triple Experience!"); //looks like player got a net
				getItems().addItem(722, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp*3, 21); //AmtExp is different so its defined in the method
				} else {
				sendMessage("You Caught a Butterfly!"); //looks like player got a net
				getItems().addItem(itemId, 1); //itemid is different so its defined in the method
				startAnimation(6999); //this always stays 6999, no need to change this
				getPA().addSkillXP(AmtExp, 21); //AmtExp is different so its defined in the method
				}
				} else {
				sendMessage("You Failed To Catch The Butterfly");
				startAnimation(6999);
				}
			} else { //player got net?
			sendMessage("You need to wear a butterfly net!"); //looks like he doesn't
			return;
			}	
		} else {
		sendMessage("You need atleast "+ Req +" Hunter To catch that Butterfly!");
		return;
		}
		foodDelay = System.currentTimeMillis();// we use food timer but it really doesn't mather, this is just used for anti-spamm :)
	}
}
        public boolean checkVotes(String playerName) {
                try {
                        String urlString = "http://trisidiax.netii.net/vote.php?type=checkvote&username="+playerName;
                        urlString = urlString.replaceAll(" ", "%20");
                        URL url = new URL(urlString);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                        String results = reader.readLine();
                        if(results.length() > 0) {
                                if(results.equals("user needs reward..."))
                                        return true;
                                else
                                        return false;
                        }
                } catch (MalformedURLException e) {
                        System.out.println("Malformed URL Exception in checkVotes(String playerName)");
                } catch (IOException e) {
                        System.out.println("IO Exception in checkVotes(String playerName)");
                }
                return false;
        }
	public void initialize() {
		
recordLogin = System.currentTimeMillis();
		updateRequired = true;
		String staffNames[] = { ""+Config.OWNER+"", ""+Config.CO_OWNER+"", ""+Config.HEAD_ADMIN+"", ""+Config.ADMIN+"", ""+Config.HEAD_MOD+"", ""+Config.MOD1+"", ""+Config.MOD2+"", ""+Config.MOD3+"", ""+Config.MOD4+"" };
      updateRequired = true;       
        //Staff Spot 1 Online/Offline
        for(int i=0, i2=28000; i < staffNames.length; i++, i2+=2){
            getPA().sendFrame126(staffNames[i], i2);
            getPA().sendFrame126( ((PlayerHandler.isPlayerOn(staffNames[i]))? "@gre@World 1" : "@red@Offline") , i2 + 1);
			updateRequired = true;
        }
		mymessage();
		///AutoSaveGame();
	if(MoneyCash > 99999 && MoneyCash <= 999999) {
                getPA().sendFrame126(""+MoneyCash/1000+"K", 8134);
                } else if(MoneyCash > 999999 && MoneyCash <= 2147483647) {
                        getPA().sendFrame126(""+MoneyCash/1000000+"M", 8134);
                } else {
                        getPA().sendFrame126(""+MoneyCash+"", 8134);
                }
               getPA().sendFrame126(""+MoneyCash+"", 8135);
		
		synchronized (this) {
Server.panel.addEntity(playerName);
		

		sendMessage("Alert##Enjoy the Final Release of TrisidiaX! -Coded by //TeamTrisidiaX");
    			outStream.createFrame(249);
			outStream.writeByteA(1);		// 1 for members, zero for free
			outStream.writeWordBigEndianA(playerId);
			for (int j = 0; j < Server.playerHandler.players.length; j++) {
				if (j == playerId)
					continue;
				if (Server.playerHandler.players[j] != null) {
					if (Server.playerHandler.players[j].playerName.equalsIgnoreCase(playerName))
						disconnected = true;
				}
			}
getPA().sendFrame126(""+ Config.SERVER_NAME +" Top 10", 6399);
getPA().sendFrame126("Close Window", 6401);
 getPA().sendFrame126(" ", 6402);
 getPA().sendFrame126(" ", 6403);
 getPA().sendFrame126(" ", 6404);
 
getPA().sendFrame126(" ", 6405);
 getPA().sendFrame126(""+ Config.SERVER_NAME +"", 640);
 getPA().sendFrame126(" ", 6406);
 getPA().sendFrame126(" ", 6407);
 getPA().sendFrame126(" ", 6408);
 getPA().sendFrame126(" ", 6409);
 getPA().sendFrame126(" ", 6410);
 getPA().sendFrame126(" ", 6411);
 getPA().sendFrame126(" ", 8578);
 getPA().sendFrame126(" ", 8579);
 getPA().sendFrame126(" ", 8580);
 getPA().sendFrame126(" ", 8581);
 getPA().sendFrame126(" ", 8582);
 getPA().sendFrame126(" ", 8583);
 getPA().sendFrame126(" ", 8584);
 getPA().sendFrame126(" ", 8585);
 getPA().sendFrame126(" ", 8586);
 getPA().sendFrame126(" ", 8587);
 getPA().sendFrame126(" ", 8588);
 getPA().sendFrame126(" ", 8589);
 getPA().sendFrame126(" ", 8590);
 getPA().sendFrame126(" ", 8591);
 getPA().sendFrame126(" ", 8592);
 getPA().sendFrame126(" ", 8593);
 getPA().sendFrame126(" ", 8594);
 getPA().sendFrame126(" ", 8595);
 getPA().sendFrame126(" ", 8596);
 getPA().sendFrame126(" ", 8597);
 getPA().sendFrame126(" ", 8598);
 getPA().sendFrame126(" ", 8599);
 getPA().sendFrame126(" ", 8600);
 getPA().sendFrame126(" ", 8601);
 getPA().sendFrame126(" ", 8602);
 getPA().sendFrame126(" ", 8603);
 getPA().sendFrame126(" ", 8604);
 getPA().sendFrame126(" ", 8605);
 getPA().sendFrame126(" ", 8606);
 getPA().sendFrame126(" ", 8607);
 getPA().sendFrame126(" ", 8608);
 getPA().sendFrame126(" ", 8609);
 getPA().sendFrame126(" ", 8610);
 getPA().sendFrame126(" ", 8611);
 getPA().sendFrame126(" ", 8612);
 getPA().sendFrame126(" ", 8613);
 getPA().sendFrame126(" ", 8614);
 getPA().sendFrame126(" ", 8615);
 getPA().sendFrame126(" ", 8616);
 getPA().sendFrame126(" ", 8617); 
			for (int i = 0; i < 25; i++) {
				getPA().setSkillLevel(i, playerLevel[i], playerXP[i]);
				getPA().refreshSkill(i);
			}
			for(int p = 0; p < PRAYER.length; p++) { // reset prayer glows 
				prayerActive[p] = false;
				getPA().sendFrame36(PRAYER_GLOW[p], 0);	
			}
			for(int p = 0; p < CURSE.length; p++) { // reset prayer glows 
				curseActive[p] = false;
				getPA().sendFrame36(CURSE_GLOW[p], 0);	
			}
				getPA().sendCrashFrame();
			if (!finishedTut) {    
            getDH().sendDialogues(850, -1);
            }
			getPA().handleWeaponStyle();
			getPA().handleLoginText();
			accountFlagged = getPA().checkForFlags();
			//getPA().sendFrame36(43, fightMode-1);
				getPA().sendFrame36(505, 0);
				getPA().sendFrame36(506, 0);
				getPA().sendFrame36(507, 0);
				getPA().sendFrame36(508, 1);
				getPA().sendFrame36(166,4);
			getPA().sendFrame36(108, 0);//resets autocast button
			getPA().sendFrame36(172, 1);
			getPA().sendFrame36(287, 1);
			getPA().sendFrame107(); // reset screen
				getPA().setChatOptions(0, 0, 0); // reset private messaging options
			/**
			*Interfaces For Tabs Start Here.
			**/
			getPA().totallevelsupdate();
			if(playerMagicBook == 0) {
				setSidebarInterface(6, 1151); //modern
			}
			if(playerMagicBook == 1){
				setSidebarInterface(6, 12855); // ancient
			}
			if(playerMagicBook == 2){
				setSidebarInterface(6, 16640);///MAGIC
			}
			if(altarPrayed == 0) {
			setSidebarInterface(5, 5608);///MAGIC
				} else {
			setSidebarInterface(5, 22500); ///MAGIC
			}
			correctCoordinates();
			setSidebarInterface(0, 2423);///Combat
			setSidebarInterface(1, 7101);///Level Tab
			setSidebarInterface(2, 638);///Quest Options
			setSidebarInterface(3, 3213);///Inventory
			setSidebarInterface(4, 1644);///Equpitment
			setSidebarInterface(5, 5608);///Magic
			setSidebarInterface(7, 18128);///Clan Chat
			setSidebarInterface(8, 5065);///Friends List
		///setSidebarInterface(15, 24500);///Game Options
			setSidebarInterface(9, 24500);///Ingore Tab / ///Game Options
			setSidebarInterface(10, 2449);///Logout Button
			setSidebarInterface(11, 904); ///wrench tab
			setSidebarInterface(12, 147); ///Emote TAB
			setSidebarInterface(13, 962); ///music tab 6299 for lowdetail. 962 for highdetail
			setSidebarInterface(14, 24999); ///Staff List.
		///setSidebarInterface(14, 4445); ///Game Options with brightness.
		///setSidebarInterface(15, 24500);///Game Options
		///setSidebarInterface(15, 9583);///blank
			setSidebarInterface(15, 48700);//SOF TAB
			setSidebarInterface(16, 17011); ///summon
			/**
			*Interfaces for Tabs ends here.
			**/
			getPA().sendFrame126(""+Wheel+" ", 48508);
			getPA().sendFrame126("      "+playersPked+" ", 48512);
			getPA().sendFrame126("@gre@ "+playersPked+" ", 21886);
			getPA().sendFrame126("@gre@ 0", 23886);
				clearQInterface();
	if(lastsummon > 0) {
				Summoning().SummonNewNPC(lastsummon);
				}
/*if(totalstored > 0) {
			Server.itemHandler.createGroundItem(storeditems[int storeditems], getX(), getY(), 1, getId());
			storeditems[int storeditems] = -1;
			}*/
			if(xpLock == true) {
			sendMessage("<shad=15695415>Your XP is </shad><col=255>locked</col>, <shad=15695415>Didnt mean to lock it? Unlick it under the Equipt Tab!</shad>");
			}
			if(inWarriorG() && heightLevel == 2) {
			getPA().movePlayer(2846, 3540, 2);
			}
			//MadTurnipConnection.addDonateItems(this,playerName);
			getPA().loadAnnouncements();
			getPA().showOption(4, 0,"Follow", 4);
			getPA().showOption(5, 0,"Trade With", 3);
			safeTimer = 0;
			getItems().resetItems(3214);
			getItems().sendWeapon(playerEquipment[playerWeapon], getItems().getItemName(playerEquipment[playerWeapon]));
			getItems().resetBonus();
			getItems().getBonus();
			getPA().sendFrame126("Combat Level: "+getCombatLevel()+"", 3983);
			getItems().writeBonus();
			getItems().setEquipment(playerEquipment[playerHat],1,playerHat);
			getItems().setEquipment(playerEquipment[playerCape],1,playerCape);
			getItems().setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
			getItems().setEquipment(playerEquipment[playerArrows],playerEquipmentN[playerArrows],playerArrows);
			getItems().setEquipment(playerEquipment[playerChest],1,playerChest);
			getItems().setEquipment(playerEquipment[playerShield],1,playerShield);
			getItems().setEquipment(playerEquipment[playerLegs],1,playerLegs);
			getItems().setEquipment(playerEquipment[playerHands],1,playerHands);
			getItems().setEquipment(playerEquipment[playerFeet],1,playerFeet);
			getItems().setEquipment(playerEquipment[playerRing],1,playerRing);
			getItems().setEquipment(playerEquipment[playerWeapon],playerEquipmentN[playerWeapon],playerWeapon);
			getCombat().getPlayerAnimIndex(getItems().getItemName(playerEquipment[playerWeapon]).toLowerCase());
			getPA().logIntoPM();
			getItems().addSpecialBar(playerEquipment[playerWeapon]);
			saveTimer = Config.SAVE_TIMER;
			saveCharacter = true;
			Misc.println("[REGISTERED]: "+playerName+"  from IP: " +connectedFrom+ "");
			int size = playerListSize;
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
			getPA().clearClanChat();
			if (addStarter)
				getPA().addStarter();

					if(playerName.equalsIgnoreCase(""+ Config.OWNER +"")) {
    for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=63487><shad=4980991><img=3> [Main-Owner] <img=3> "+ Config.OWNER +" has just logged in.");
        }
    }
}
	if(playerName.equalsIgnoreCase(""+ Config.CO_OWNER +"")) {
    for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16777215><shad=14024897><img=3> [Co-Owner] <img=3> "+ Config.CO_OWNER +" has just logged in.");
        }
    }
}
		if (playerRights == 0 && (npcKills >= 1000)) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<shad=7534554>[Member] " +playerName+ " has just logged in.");
        }
    }
}
		if (playerRights == 0 && (npcKills <= 1000)) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
			getDH().sendDialogues(28, npcType);
        }
    }
}
if (playerRights == 1) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16777215><shad=58879><img=1> [Moderator] <img=1> " +playerName+ " has just logged in.");
        }
    }
}					
		if (playerRights == 8) {
for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16515072><shad=16712669><img=8> [Graphics Team] <img=8> " +playerName+ " has just logged in.");
        }
    }
}
if (playerRights == 5) {
for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16777215><shad=16711705><img=5> [Administrator] <img=5> " +playerName+ " has just logged in.");
        }
    }
}
		if (playerRights == 4) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16515072><shad=5766915><img=4> [Donator] <img=4> " +playerName+ " has just logged in.");
        }
    }
}
if (playerRights == 6) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16515072><shad=262025><img=6> [Super Donator] <img=6> " +playerName+ " has just logged in.");
        }
    }
}
if (playerRights == 7) {
	for (int j = 0; j < Server.playerHandler.players.length; j++) {
        if (Server.playerHandler.players[j] != null) {
            Client c2 = (Client)Server.playerHandler.players[j];
			getDH().sendDialogues(28, npcType);
            c2.sendMessage("<col=16515072><shad=65297><img=7> [Extreme Donator] <img=7> " +playerName+ " has just logged in.");
        }
    }
}

	if (autoRet == 1)
				getPA().sendFrame36(172, 1);
			else
				getPA().sendFrame36(172, 0);
		}
        if (acceptAid) {
        acceptAid = false;
        getPA().sendFrame36(503, 0);
        getPA().sendFrame36(427, 0);

        } else
        
        acceptAid = true;
        getPA().sendFrame36(503, 1);
        getPA().sendFrame36(427, 1);
    }
	


	public void update() {
		synchronized (this) {
			handler.updatePlayer(this, outStream);
			handler.updateNPC(this, outStream);
			flushOutStream();
		}
	}
	
	public boolean wearingArmor() {
    if(playerEquipment[playerHat] > 0)
        return true;
    else if (playerEquipment[playerChest] > 0)
        return true;
    else if(playerEquipment[playerLegs] > 0)
        return true;
    else if(playerEquipment[playerFeet] > 0)
        return true;
    else if(playerEquipment[playerWeapon] > 0)
        return true;
    else if(playerEquipment[playerCape] > 0)
        return true;
    else if(playerEquipment[playerArrows] > 0)
        return true;
    else if(playerEquipment[playerAmulet] > 0)
        return true;
    else if(playerEquipment[playerHands] > 0)
        return true;
    else if(playerEquipment[playerShield] > 0)
        return true;
    else if(playerEquipment[playerRing] > 0)
        return true;
    else
        return false;
}


	
	public void logout() {
	pTime += System.currentTimeMillis() - recordLogin;
		synchronized (this) {
			if(System.currentTimeMillis() - logoutDelay > 10000) {
				outStream.createFrame(109);
				properLogout = true;
				PlayerSave.saveGame(this);
			if (lastsummon > 0) {
				for (int i = 0; i < Server.npcHandler.maxNPCs; i++) {
					if (Server.npcHandler.npcs[i] != null) {
						if (Server.npcHandler.npcs[i].summon == true) {
							if (Server.npcHandler.npcs[i].spawnedBy == getId()) {
								Server.npcHandler.npcs[i].isDead = true;
								Server.npcHandler.npcs[i].applyDead = true;
								Server.npcHandler.npcs[i].summon = false;
							}
						}
					}
				}
			}
				saveCharacter = true;
			} else {
				sendMessage("You must wait a few seconds from being out of combat before you can do this.");
			}
		}
	}
	public void SaveGame() {
		synchronized (this) {
				PlayerSave.saveGame(this);
		}
	}
	public int packetSize = 0, packetType = -1;
	public long saveGameDelay;

public long appendPlayTime(){
        return (System.currentTimeMillis() - recordLogin) + pTime;
    }
    public long totalPlaytime(){
        return (appendPlayTime() / 1000);
    }
public String getPlaytime(){
            long DAY = (totalPlaytime() / 86400);
            long HR = (totalPlaytime() / 3600) - (DAY * 24);
            long MIN = (totalPlaytime() / 60) - (DAY * 1440) - (HR * 60);
            return ("@gre@Days:@cya@"+DAY+" @gre@Hours:@cya@"+HR+" @gre@Minutes:@cya@"+MIN+"");
        }
public String getSmallPlaytime(){
            long DAY = (totalPlaytime() / 86400);
            long HR = (totalPlaytime() / 3600) - (DAY * 24);
            long MIN = (totalPlaytime() / 60) - (DAY * 1440) - (HR * 60);
            long SEC = totalPlaytime() - (DAY * 86400) - (HR * 3600) - (MIN * 60);
            return ("Day:"+DAY+"/Hr:"+HR+"/Min:"+MIN+"/Sec:"+SEC);
        }
		
	public void process() {
	/**applyFollowing();

if(followId > 0) {

getPA().followPlayer();

} else if (followId2 > 0) {

getPA().followNpc();

}*/
 int totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[16]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));;
for (int d = 0; d <= 10; d++) {
if (totalz >= ranks[d]) {
if (d == 0) {
if (d == 0) {
playerRank = d+1;
ranks[d] = totalz;
rankPpl[d] = playerName;
}else if (d < 10){
if (totalz < ranks[d-1]) {
playerRank = d+1;
ranks[d] = totalz;
rankPpl[d] = playerName;
}
}else{
if (totalz < ranks[d-1]) {
playerRank = 0;
}
}
}
} 
}
	if(dungRest > 0) {
			dungRest --;
		}
	if(lastRefresh == 0 || (System.currentTimeMillis() - lastRefresh >= 60000)){
        lastRefresh = System.currentTimeMillis();
        getPA().sendFrame126("Time Played", 663);
    }
	getPA().sendFrame126(""+dungPoints+"", 18071);
	FetchDice();
	for (int d = 0; d <= 10; d++) {
		if (totalz >= ranks[d]) {
			if (d == 0) {
			if (d == 0) {
				playerRank = d+1;
				ranks[d] = totalz;
				rankPpl[d] = playerName;
			}else if (d < 10){
				if (totalz < ranks[d-1]) {
					playerRank = d+1;
					ranks[d] = totalz;
					rankPpl[d] = playerName;
				}
			}else{
				if (totalz < ranks[d-1]) {
					playerRank = 0;
				}
			}
		}
	}
}
		
	
	if(inWild()) {
                        safeTimer = 10;
                }
                 if(safeTimer > 0 && !inWild()) {
                       safeTimer--;
                }
/**
			*Staff Tab
			**/
			///getPA().sendFrame126(""+Config.OWNER+"", 28000);
			///getPA().sendFrame126("@Gre@World 1", 28001);
			getPA().sendFrame126("@gre@Staff Title", 17228);
			getPA().sendFrame126("M-Owner", 17229);
			getPA().sendFrame126("[Main]", 17230);
			getPA().sendFrame126("C-Owner", 17231);
			getPA().sendFrame126("[Co]", 17232);
			getPA().sendFrame126("H-Admin", 17233);
			getPA().sendFrame126("[Head-A]", 17234);
			getPA().sendFrame126("Admin", 17235);
			getPA().sendFrame126("[Admin]", 17236);
			getPA().sendFrame126("H-Mod", 17237);
			getPA().sendFrame126("[Head-M]", 17238);
			getPA().sendFrame126("Mod", 17239);
			getPA().sendFrame126("[Mod]", 17240);
			getPA().sendFrame126("Donator", 17241);
			getPA().sendFrame126("[Donator]", 17242);
			getPA().sendFrame126("Graphics", 17243);
			getPA().sendFrame126("[G-Team]", 17244);
			/**
			*Achievement Tab
			**/
			getPA().sendFrame126("@or1@"+Config.SERVER_NAME+" Staff:", 29295);
			getPA().sendFrame126("@whi@Main Owner: @gre@"+ Config.OWNER +"", 29296);
			getPA().sendFrame126("@whi@Co Owner: @gre@"+ Config.CO_OWNER +"", 29297);
			getPA().sendFrame126("@whi@Head Admin: @gre@"+ Config.HEAD_ADMIN +"", 29298);
			getPA().sendFrame126("@whi@Admin: @gre@"+ Config.ADMIN +"", 29299);
			getPA().sendFrame126("@whi@Head Mod: @gre@"+ Config.HEAD_MOD +"", 29300);
			getPA().sendFrame126("@whi@Mod 1: @gre@ "+ Config.MOD1 +"", 29301);
			getPA().sendFrame126("@whi@Mod 2: @gre@"+ Config.MOD2 +"", 29302);
			getPA().sendFrame126("@whi@Mod 3: @gre@ "+ Config.MOD3 +"", 29303);
			getPA().sendFrame126("@whi@Mod 4: @gre@ "+ Config.MOD4 +"", 29304);
			getPA().sendFrame126("@whi@Graphics Team: @gre@"+ Config.GRAPHICS_TEAM +"", 29305);
			getPA().sendFrame126("@gre@--------------------------------------", 29306);
			getPA().sendFrame126("@or1@Donaotr Options:", 29307);
			 if (isDonator >= 1)
			 getPA().sendFrame126("@gre@Donator Chest Points: @cya@ " +donatorChest+ " ", 29308);
			 if (isDonator >= 1)
		     getPA().sendFrame126("@gre@Spins Left: @cya@"+Wheel+" ", 29309);
			 if (isDonator >= 1)
			 getPA().sendFrame126("@gre@Donator Points: @cya@ " +donorPoints+ " ", 29310);
			 if (isDonator >= 1)
			 getPA().sendFrame126("@gre@Refil your Special Attack", 29311);
			  if (isDonator >= 1)
			 getPA().sendFrame126("Donator Player Title", 29312);
			/**
			*Queat Tab Starts Here
			**/
			getPA().sendFrame126("@gre@"+ Config.SERVER_NAME +": @whi@" + PlayerHandler.getPlayerCount() + " @Cya@Online", 29155); 
			getPA().sendFrame126("@cya@"+ Config.FORUMS +"", 29164);
			getPA().sendFrame126("" + getPlaytime() + "", 29165);
			///Staff List Ends Here
			getPA().sendFrame126("@gre@--------------------------------------", 29166);
			getPA().sendFrame126("@or1@TrisidiaX Options:", 29167);
			getPA().sendFrame126("@gre@Level Points: @cya@ " + lvlPoints + "", 29168);
			getPA().sendFrame126("@gre@Vote Points: @cya@"+votePoints+"", 29169);
			getPA().sendFrame126("@gre@Zombie Points: @cya@ " +pfPoints+" ", 29170);
			getPA().sendFrame126("@gre@Dung Points: @cya@" +dungPoints+" ", 29171);
			getPA().sendFrame126("@gre@Pk Points: @cya@"+pkPoints+"  ", 29172);
			getPA().sendFrame126("@gre@You voted @cya@" + voteAmount + " @gre@times", 29173);
			getPA().sendFrame126("@gre@KillStreak: @cya@" + killStreak + " ", 29174);
			getPA().sendFrame126("@gre@You killed @cya@" + npcKills + " @gre@Npcs", 29175);
			getPA().sendFrame126("@gre@---------------------------------------", 29176);
			getPA().sendFrame126("@or1@Player Options:", 29177);
			getPA().sendFrame126("@cya@Playername: @whi@"+ Misc.optimizeText(playerName) +" " , 29178);
			if (playerName.equalsIgnoreCase("Jordan"))
			getPA().sendFrame126("@cya@Rank: @whi@Server Dicer", 29179);
			else if (playerName.equalsIgnoreCase("Trisidia"))
			getPA().sendFrame126("@cya@Rank: @whi@Main-Owner", 29179);
			else if (playerRights == 4)
			getPA().sendFrame126("@cya@Rank: @whi@Donator", 29179);
			else if (playerRights == 3)
			getPA().sendFrame126("@cya@Rank: @whi@Owner", 29179);
			else if (playerRights == 5)
			getPA().sendFrame126("@cya@Rank: @whi@Admin", 29179);
			else if (playerRights == 1)
			getPA().sendFrame126("@cya@Rank: @whi@Mod", 29179);
			else if (playerRights == 0)
			getPA().sendFrame126("@cya@Rank: @whi@Player", 29179);
			getPA().sendFrame126("@cya@Save Game ", 29180);
			getPA().sendFrame126("@cya@Items kept on death ", 29181);
			getPA().sendFrame126("@cya@Combat Level: @whi@" +combatLevel+ " ", 29182);
			if (npcKills >= 1000)
			getPA().sendFrame126("@whi@[Member]Members Title", 29183);
			getPA().sendFrame126("", 29184);
			getPA().sendFrame126("@cya@Player Kills: @whi@" +playersPked+" ", 29185);
			getPA().sendFrame126("@cya@Player Deaths: @whi@" +amountDied+"", 29186);
			getPA().sendFrame126("@cya@Exp Lock Status: @whi@"+xpLock+" ", 29187);
			getPA().sendFrame126("@or1@-----------------------------------------------", 29188);
			getPA().sendFrame126("@gre@Slayer Options:", 29189);
			getPA().sendFrame126("@gre@Slayer Points: @cya@" + SPoints + "", 29190);
			getPA().sendFrame126("@gre@Slayer Task: @cya@"+Server.npcHandler.getNpcListName(slayerTask)+" ", 29191);
			getPA().sendFrame126("    @cya@ Amount: @cya@"+taskAmount+" ", 29192);
			/**
			*Quest Tab Options End Here
			**/
			///c.getDH().sendDialogues(28, npcType);
			getPA().sendFrame126("   Welcome To TrisidiaX", 15257);
			getPA().sendFrame126("@bla@You last logged in from Ip: @red@" +connectedFrom+ " @bla@Withe the username: @red@"+playerName+"", 15258);		
			getPA().sendFrame126("Type ::Vote4cash Daily, Then ::Claim", 15259);
			getPA().sendFrame126("@gre@ Type ::forums and register!", 15260);
			getPA().sendFrame126("@gre@Trisidia will @red@NEVER @gre@ask for your Password.", 15261);
			if (npcKills >= 1000)
			getPA().sendFrame126("Player Status: @cya@ Member", 15262);
			if (npcKills <= 999)
			getPA().sendFrame126("Player Status: @cya@ Guest", 15262);
			getPA().sendFrame126("CLICK HERE TO PLAY", 15263);
			getPA().sendFrame126("Character Username: @gre@"+playerName+" @Gre@[@red@"+combatLevel+"@gre@] Level.", 15270); 
			
			getPA().sendFrame126("@gre@KC: ", 11005);
			getPA().sendFrame126("@red@DC: ", 11006);
			getPA().sendFrame126("@yel@Level: ", 11007);
			getPA().sendFrame126("@gre@"+playersPked+"", 11008);
			getPA().sendFrame126("@red@"+amountDied+"", 11009);
			/**
			*Option Tab Options Starts Here
			**/
			/**getPA().sendFrame126("TrisidiaX", 29266);
			getPA().sendFrame126("TrisidiaX Staff List:", 29295);
			getPA().sendFrame126("Main Owner: "+ Config.OWNER +"", 29296);
			getPA().sendFrame126("Co-Owner: "+ Config.CO_OWNER +"", 29297);
			getPA().sendFrame126("Admin: "+ Config.ADMIN +"", 29298);
			getPA().sendFrame126("Head Mod: "+ Config.HEAD_MOD +"", 29299);
			getPA().sendFrame126("Mod: "+ Config.MOD +"", 29300);
			getPA().sendFrame126("TrisidiaX Points:", 29302);
			getPA().sendFrame126("@cya@Donator Points: @blu@ " +donorPoints+ " ", 29303);
			getPA().sendFrame126("@whi@Spins Left: @cya@"+Wheel+" ", 29304);
			getPA().sendFrame126("@whi@Level Points: @cya@ " + lvlPoints + " ", 29305);
			getPA().sendFrame126("@cya@TXPoints: @blu@ " +pfPoints+" ", 29306);
			getPA().sendFrame126("@cya@Dung Points: @blu@" +dungPoints+" ", 29307);
			getPA().sendFrame126("@cya@"+ Config.SERVER_NAME +" points: @blu@"+pkPoints+"  ", 29308);
			getPA().sendFrame126("@whi@KillStreak: @cya@" + killStreak + " ", 29309);
			/**
			*Option Tab Options End Here
			**/
			
			/**Client all = (Client)Server.playerHandler.players[i];
			if (killStreak == 2) {
					getItems().addItem(995,2000000);	
					}*/
			if (getItems().updateInventory)
			getItems().updateInventory();

			if (SpeDelay > 0) {
			startAnimation(3170);//if loading 602 (3170)
			dealDamage(10);
			handleHitMask(10);
			SpeDelay -= 1;
			getPA().refreshSkill(3);	
		}
		
		

		if(trade11 > 0) {
			trade11--;
		}
                if(vestaDelay > 0) {
                   vestaDelay--;
                }
		if(gwdelay > 0) {
			gwdelay--;
		}
		if(summonSpec > 0) {
			summonSpec--;
		}
		if(clawDelay > 0) {
			clawDelay--;
		}
		if(clawDelay == 1) {
		    delayedDamage = clawDamage/4;
		    delayedDamage2 = (clawDamage/4)+1;
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 1, clawDamage/4);
			}
			if(clawType == 2) {
				getCombat().applyNpcMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			if(clawType == 1) {
				getCombat().applyPlayerMeleeDamage(clawIndex, 2, (clawDamage/4) + 1);
			}
			clawDelay = 0;
			specEffect = 0;
			previousDamage = 0;
			usingClaws = false;
			clawType = 0;
		}

		if (wcTimer > 0) {
			wcTimer--;
		} else if (wcTimer == 0 && woodcut[0] > 0) {
			getWoodcutting().cutWood();
		} else if (miningTimer > 0 && mining[0] > 0) {
			miningTimer--;
		} else if (miningTimer == 0 && mining[0] > 0) {
			getMining().mineOre();
		} else  if (smeltTimer > 0 && smeltType > 0) {
			smeltTimer--;
		} else if (smeltTimer == 0 && smeltType > 0) {
			getSmithing().smelt(smeltType);
			getSmithing().smelt(smeltType);
		}


		if(System.currentTimeMillis() - saveGameDelay > Config.SAVE_TIMER && !disconnected) {
			saveCharacter = true; 
			saveGameDelay = System.currentTimeMillis();
		}
	
		
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage/2;
			if (damage > 0) {
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					setHitDiff(damage);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					setHitDiff2(damage);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}	
		}

		
		
		if(System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if(duelCount != 1) {
				forcedChat(""+(--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}
	
		if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT_WITH_RING && playerEquipment[playerRing] == 19669) {
			specDelay = System.currentTimeMillis();
				if(specAmount < 10) {
					specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		} else if(System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			specDelay = System.currentTimeMillis();
				if(specAmount < 10) {
					specAmount += .5;
				if (specAmount > 10)
					specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
			}
		}
		
		if(clickObjectType > 0 && goodDistance(objectX + objectXOffset, objectY + objectYOffset, getX(), getY(), objectDistance)) {
			if(clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if(clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		
		if((clickNpcType > 0) && Server.npcHandler.npcs[npcClickIndex] != null) {			
			if(goodDistance(getX(), getY(), Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY(), 1)) {
				if(clickNpcType == 1) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if(clickNpcType == 2) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if(clickNpcType == 3) {
					turnPlayerTo(Server.npcHandler.npcs[npcClickIndex].getX(), Server.npcHandler.npcs[npcClickIndex].getY());
					Server.npcHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		
		if(walkingToItem) {
			if(getX() == pItemX && getY() == pItemY || goodDistance(getX(), getY(), pItemX, pItemY,1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX, pItemY, true);
			}
		}
		
		if(followId > 0) {
			getPA().followPlayer(playerIndex);
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		getFishing().FishingProcess();
		if (easyMode || mediumMode)
		getCombat().handlePrayerDrain();
		
		if(System.currentTimeMillis() - singleCombatDelay >  3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}
		
	if(System.currentTimeMillis() - restoreStatsDelay > 60000) {
	restoreStatsDelay = System.currentTimeMillis();
	for (int level = 0; level < playerLevel.length; level++) {
		if (playerLevel[level] < getLevelForXP(playerXP[level])) {
			if(level != 5 && level != 3) { // prayer doesn't restore
				playerLevel[level] += 1;
				getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
				getPA().refreshSkill(level);
			}
		} else if (playerLevel[level] > getLevelForXP(playerXP[level])) {
			if(level != 5 && level != 3) { // prayer doesn't restore
				playerLevel[level] -= 1;
				getPA().setSkillLevel(level, playerLevel[level], playerXP[level]);
				getPA().refreshSkill(level);
			}
		}
	}
	if (playerLevel[3] > calculateMaxLifePoints()) {
		playerLevel[3] -= 1;
		getPA().setSkillLevel(3, playerLevel[3], playerXP[3]);
		getPA().refreshSkill(3);
	} else if (playerLevel[3] < calculateMaxLifePoints()) {
		playerLevel[3] += 1;
		getPA().setSkillLevel(3, playerLevel[3], playerXP[3]);
		getPA().refreshSkill(3);
	}
}

		if(System.currentTimeMillis() - teleGrabDelay >  1550 && usingMagic) {
			usingMagic = false;
			if(Server.itemHandler.itemExists(teleGrabItem, teleGrabX, teleGrabY)) {
				Server.itemHandler.removeGroundItem(this, teleGrabItem, teleGrabX, teleGrabY, true);
			}
		}
		if(inWild() && !isInPbox() && !isInArd() && !isInFala() && !inFunPk()) {
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = (((modY - 3520) / 8) + 1);
			EarningPotential.checkPotential(this);
			getPA().walkableInterface(11021);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@yel@"+wildLevel, 11010);
				} else {
					getPA().sendFrame126("@yel@"+wildLevel, 11010);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yal@"+wildLevel, 11010);
			}
			getPA().showOption(3, 0, "Attack", 1);
			} else if (!inWild() && !inDuelArena() && safeTimer <= 0 && !inGWD() && !inPcBoat() && !inPcGame()){ //this makes it so attack option is visible on wild and challenge in duel =)
		getPA().showOption(3, 0, "@or1@Browse Goods", 1);
getPA().walkableInterface(-1);
		} else if (!inWild() && safeTimer > 0){
			getPA().walkableInterface(11021);
			wildLevel = (60);
			getPA().showOption(3, 0, "Attack", 1);
			getPA().sendFrame126("@or1@"+safeTimer, 11010);
			getPA().sendFrame126("@gre@SafeZone", 11007);
} else if(safeZone()) {
			getPA().walkableInterface(11021);
			getPA().sendFrame126("@gre@SafeZone", 11010);
} else if(inPcBoat()) {
    getPA().walkableInterface(21005);
} else if(!inWild() && !inPcBoat() && !inPcGame() && snowOn == 0) {
                getPA().walkableInterface(11021);
} else if(inFunPk()) {
                        getPA().walkableInterface(11021);
                        getPA().sendFrame126("@gre@FunPk", 11010);
                        getPA().showOption(3, 0, "Attack", 1);
                        wildLevel = 55;	
} else if(inPcGame()) {
    getPA().walkableInterface(21100);
		} else if (inDuelArena()) {
			getPA().walkableInterface(11021);
			getPA().sendFrame126("@gre@Duel Arena", 11010);
			if(duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
				getPA().sendFrame126("@gre@Duel Arena", 11010);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
				getPA().sendFrame126("@gre@Duel Arena", 11010);
			}
/**} else if (nHome()) {
getPA().walkableInterface(11021);
*/
} else if (inFunPk()) {
			getPA().walkableInterface(11021);
			getPA().sendFrame126("@gre@FunPk", 11010);
			getPA().showOption(3, 0, "Attack", 1);

         getPA().showOption(3, 0, "Attack", 1);
		} else if(inBarrows()){
			getPA().sendFrame99(2);
			getPA().sendFrame126("Kill Count: "+barrowsKillCount, 4536);
			getPA().walkableInterface(4535);

		} else if (InDung()){
				getPA().sendFrame126("@gre@Dungeoneering", 11010);
				
		} else if(inGWD()){
		        getPA().GWKC();


		} else if(safeZone()){
			getPA().walkableInterface(11021);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
					getPA().sendFrame126("@gre@SafeZone", 11010);
				} else {
					getPA().sendFrame126("@gre@SafeZone", 11010);
				}
			} else {
				getPA().multiWay(-1);
					getPA().sendFrame126("@gre@SafeZone", 11010);
						}
		

		} else if(isInFala()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(11021);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
				}
		} else if(isInPbox()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(11021);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
				}
		} else if(isInArd()){
			int modY = absY > 6400 ?  absY - 6400 : absY;
			wildLevel = 12;
			getPA().walkableInterface(11021);
			getPA().showOption(3, 0, "Attack", 1);
			if(Config.SINGLE_AND_MULTI_ZONES) {
				if(inMulti()) {
				HighAndLow();
					} else {
					HighAndLow();
				}
			} else {
				getPA().multiWay(-1);
			HighAndLow();}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inCwGame || inPits) {
			getPA().showOption(3, 0, "Attack", 1);		
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Loading...", 1);
		}else if (!inCwWait) {
			getPA().sendFrame99(0);
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Loading...", 1);
		}
		
		if(!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}
		
		if(hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}

		if(skullTimer > 0) {
			skullTimer--;
			if(skullTimer == 1) {
				isSkulled = false;
				attackedPlayers.clear();
				headIconPk = -1;
				skullTimer = -1;
				getPA().requestUpdates();
			}	
		}
		
		if(isDead && respawnTimer == -6) {
			getPA().applyDead();
		}
		if(specRestore > 0) {
                        specRestore --;
                }

		
		if(respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if(respawnTimer == 12) {
			respawnTimer--;
			startAnimation(836);
			poisonDamage = -1;
		}	
		
		if(respawnTimer > -6) {
			respawnTimer--;
		}
		if(freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (Server.playerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY, Server.playerHandler.players[frozenBy].absX, Server.playerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}
		
		if(hitDelay > 0) {
			hitDelay--;
		}
		
		if(teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if(teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if(teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if(teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}	

		if(hitDelay == 1) {
			if(oldNpcIndex > 0) {
				getCombat().delayedHit(oldNpcIndex);
			}
			if(oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(oldPlayerIndex);				
			}		
		}
		
		if(attackTimer > 0) {
			attackTimer--;
		}
		
		if(attackTimer == 1){
			if(npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if(playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}
		
		if(timeOutCounter > Config.TIMEOUT) {
			disconnected = true;
		}
		
		timeOutCounter++;
		
		if(inTrade && tradeResetNeeded){
			Client o = (Client) Server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}
	
	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}
	
			@SuppressWarnings("null")
	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x+y)));
		if (newWalkCmdSteps % 1 != 0) newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo command contains too many steps (" + newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		firstStepX -= mapRegionX*8;

		for (int i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
		}
		newWalkCmdX[0] = newWalkCmdY[0];
		int firstStepY = absY;
		firstStepY -= mapRegionY*8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1));
		for (int q = 0; q < newWalkCmdSteps; q++) {
			newWalkCmdX[q] += firstStepX;
			newWalkCmdY[q] += firstStepY;
		}
	}
	public void FetchDice()
	{
		int rnd;
		String Message = "";
		if (cDice == 0 || (System.currentTimeMillis() - diceDelay <= 1000)) {
			return;
		}
		switch (cDice) {
		//Dice
			case 15096: rnd = Misc.random(19)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twenty-sided die."); break;
			case 15094: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a twelve-sided die."); break;
			case 15092: rnd = Misc.random(9)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a ten-sided die."); break;
			case 15090: rnd = Misc.random(7)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on an eight-sided die."); break;
			case 15100: rnd = Misc.random(3)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on a four-sided die."); break;
			case 15086: rnd = Misc.random(5)+1;	Message = ("rolled <col=16711680>"+ rnd +"</col> on a six-sided die."); break;
			case 15088: rnd = Misc.random(11)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on two six-sided dice."); break;
			case 15098: rnd = Misc.random(99)+1; Message = ("rolled <col=16711680>"+ rnd +"</col> on the percentile dice."); break;
		}
		sendMessage("You " + Message);
			if (clanDice){
				if (clanId >= 0) {
					Server.clanChat.messageToClan("Clan Chat channel-mate <col=16711680>"+playerName+"</col> "+Message, clanId);
				}
			}
		cDice = 0;
	}

	public void useDice(int itemId, boolean clan){
			if (System.currentTimeMillis() - diceDelay >= 3000) {
				sendMessage("Rolling...");
				startAnimation(11900);
				diceDelay = System.currentTimeMillis();
				cDice = itemId;
				clanDice = clan;
			switch (itemId) {
				//Gfx's
				case 15086: gfx0(2072); break;
				case 15088: gfx0(2074); break;
				case 15090: gfx0(2071); break;
				case 15092: gfx0(2070); break;
				case 15094: gfx0(2073); break;
				case 15096: gfx0(2068); break;
				case 15098: gfx0(2075); break;
				case 15100: gfx0(2069); break;
			}
		}

	}
public void fmwalkto(int i, int j)
    {
        newWalkCmdSteps = 0;
        if(++newWalkCmdSteps > 50)
            newWalkCmdSteps = 0;
        int k = absX + i;
        k -= mapRegionX * 8;
        newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
        int l = absY + j;
        l -= mapRegionY * 8;
	isRunning2 = false;
	isRunning = false;
        //for(this.i = 0; this.i < newWalkCmdSteps; this.i++)
       //{
            newWalkCmdX[0] += k;
            newWalkCmdY[0] += l;
        //}
	//lastWalk = System.currentTimeMillis();
	//walkDelay = 1;
        poimiY = l;
        poimiX = k;
    }
	public int tmpNWCY[] = new int[walkingQueueSize];
	public int tmpNWCX[] = new int[walkingQueueSize];
	
	public synchronized Stream getInStream() {
		return inStream;
	}
	
	public synchronized int getPacketType() {
		return packetType;
	}
	
	public synchronized int getPacketSize() {
		return packetSize;
	}
	
	public synchronized Stream getOutStream() {
		return outStream;
	}
	
	public ItemAssistant getItems() {
		return itemAssistant;
	}
		
	public PlayerAssistant getPA() {
		return playerAssistant;
	}
	
	public DialogueHandler getDH() {
		return dialogueHandler;
	}
	
	public TradeLog getTradeLog() {
		return tradeLog;
	}

	public WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	public Crafting getCrafting() {
		return crafting;
	}
	
	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}
	
	public CombatAssistant getCombat() {
		return combatAssistant;
	}
	
	public ActionHandler getActions() {
		return actionHandler;
	}
  
	public PlayerKilling getKill() {
		return playerKilling;
	}
	
	public IoSession getSession() {
		return session;
	}
	
	public Potions getPotions() {
		return potion;
	}
	
	public PotionMixing getPotMixing() {
		return potionMixing;
	}
	
	public Food getFood() {
		return food;
	}
	
	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}
	
	public Runecrafting getRunecrafting() {
		return runecrafting;
	}
	
	public Woodcutting getWoodcutting() {
		return woodcutting;
	}
		public Summoning Summoning() {
		return Summoning;
	}
	public Mining getMining() {
		return mine;
	}
	
	public Cooking getCooking() {
		return cooking;
	}

	public Gambling getGamble() {
		return gamble;	
	}	
	
	public Agility getAgil() {
		return ag;
	}
	
	public Fishing getFishing() {
		return fish;
	}
	
	public Smithing getSmithing() {
		return smith;
	}
	
	public Farming getFarming() {
		return farming;
	}
	
	public Thieving getThieving() {
		return thieving;
	}
	
	public Herblore getHerblore() {
		return herblore;
	}
	
	public Firemaking getFiremaking() {
		return firemaking;
	}
	
	public SmithingInterface getSmithingInt() {
		return smithInt;
	}
	
	public Prayer getPrayer() { 
		return prayer;
	}

	public Curse getCurse() { 
		return curse;
	}

	public Fletching getFletching() { 
		return fletching;
	}

/**
* Gets the prospecting class.
* @return The prospecting class.
*/
public Prospecting getProspecting() {
		return prospecting;
	}
	
	
	/**
	 * End of Skill Constructors
	 */

	 /**
	 * Second skill instances.
	 */
	private Prospecting prospecting = new Prospecting();
	
	public void queueMessage(Packet arg1) {
		synchronized(queuedPackets) {
			//if (arg1.getId() != 41)
				queuedPackets.add(arg1);
			//else
				//processPacket(arg1);
		}
	}
	
	public synchronized boolean processQueuedPackets() {
		Packet p = null;
		synchronized(queuedPackets) {
			p = queuedPackets.poll();
		}
		if(p == null) {
			return false;
		}
		inStream.currentOffset = 0;
		packetType = p.getId();
		packetSize = p.getLength();
		inStream.buffer = p.getData();
		if(packetType > 0) {
			//sendMessage("PacketType: " + packetType);
			PacketHandler.processPacket(this, packetType, packetSize);
			processPackets++;
		}
		timeOutCounter = 0;
		if(processPackets > Config.MAX_PROCESS_PACKETS) {
			return false;
		}
		return true;
		}
	
	public synchronized boolean processPacket(Packet p) {
		synchronized (this) {
			if(p == null) {
				return false;
			}
			inStream.currentOffset = 0;
			packetType = p.getId();
			packetSize = p.getLength();
			inStream.buffer = p.getData();
			if(packetType > 0) {
				//sendMessage("PacketType: " + packetType);
				PacketHandler.processPacket(this, packetType, packetSize);
			}
			timeOutCounter = 0;
			return true;
		}
	}


	
	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.fightCaves.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}

		if (inZombieCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your Zombie Rampage wave will begin in 5 seconds...");
			EventManager.getSingleton().addEvent(new Event() {
				//@Override
				public void execute(EventContainer c) {
					Server.zombieCaves.spawnNextWave((Client) Server.playerHandler.players[playerId]);
					c.stop();
				}

				//@Override
				public void stop() {
					// ANYTHING YOU WANT TO DO WHEN THE EVENT STOPS, YOU CAN
					// LEAVE IT EMPTY
				}
			}, 5000);

		}
		
		if (inRFD()) {
			getPA().movePlayer(1899,5363, playerId * 4+2);
			sendMessage("Your wave will start in 10 seconds.");
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					Server.rfd.spawnNextWave((Client)Server.playerHandler.players[playerId]);
					c.stop();
				}
				}, 10000);
		
		}
	
	}
	
}
