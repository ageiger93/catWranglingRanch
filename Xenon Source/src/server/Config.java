package server;
import server.model.npcs.*;


public class Config {

	public static final boolean SERVER_DEBUG = false;//needs to be false for Real world to work
	
	public static String SERVER_NAME = "Xenon";///Replaceing this will Change name of the server
	public static final String DONATION_AMOUNT = "$0.99";///Add the vote.php here only
	public static final String WELCOME_MESSAGE = "Welcome to Xenon";///replace this log in message
	public static final String WEBCLIENT = "http://mrzeerak8520.wix.com/xenon";///replace this log in message
	public static final String FORUMS = "http://mrzeerak8520.wix.com/xenon#!forums/c1c19";///Replace this link to replace all the forums
	public static final String BUY_SPINS = "";///This is the link to buy spins
	public static final String PAYPAL_EMAIL = "mrzeerak8520@gmail.com";///Enter the name of the site when you spam players
	public static final String VOTE4CASH = "http://mrzeerak8520.wix.com/xenon";///Add the vote.php here only
	public static final String SITE_SPAM = "www.YouJizz.com";///Enter the name of the site when you spam players " + Config.SITE_SPAM + "
	public static final String SITE_SPAM2 = "www.youporn.com";///Enter the name of the site when you spam players
	public static final int CLIENT_VERSION = 1;
	
	public static final String OWNER = "X";///
	public static final String CO_OWNER = "";///
	public static final String HEAD_ADMIN = "";///
	public static final String ADMIN = "Non";///
	public static final String HEAD_MOD = "";///
	public static final String MOD1 = "Non";///
	public static final String MOD2 = "Non";///
	public static final String MOD3 = "Non";///
	public static final String MOD4 = "Non";///
	public static final String GRAPHICS_TEAM = "X";///
	public static final String STAFF_OPENINGS = "";///
	
	public static final String TRADE_DISABLED_ONE = "";
	public static final String TRADE_DISABLED_TWO = "";
	public static final String TRADE_DISABLED_THREE = "";
	public static final String TRADE_DISABLED_FOUR = "";
	public static final String TRADE_DISABLED_FIVE = "";
	public static final String TRADE_DISABLED_SIX = "";
	
	public static final String DROP_DISABLED_ONE = "";
	public static final String DROP_DISABLED_TWO = "";
	public static final String DROP_DISABLED_THREE = "";
	public static final String DROP_DISABLED_FOUR = "";
	public static final String DROP_DISABLED_FIVE  = "";
	public static final String DROP_DISABLED_SIX = "";
	
	public static final String DUELING_DISABLED_ONE = "";
	public static final String DUELING_DISABLED_TWO = "";
	public static final String DUELING_DISABLED_THREE = "";
	public static final String DUELING_DISABLED_FOUR = "";
	public static final String DUELING_DISABLED_FIVE  = "";
	public static final String DUELING_DISABLED_SIX = "";
	
	public static final String NEWS_ONE = "<col=16777215><shad=14024897>["+ Config.SERVER_NAME +" News]:</col></shad><col=16777215><shad=62207> Donate for Donator and Spins!";
	public static final String NEWS_TWO = "<col=16777215><shad=14024897>["+ Config.SERVER_NAME +" News]:</col></shad><col=16777215><shad=62207> X is your owner. Careful";
	public static final String NEWS_THREE = "<col=16777215><shad=14024897>["+ Config.SERVER_NAME +" News]:</col></shad><col=16777215><shad=62207>  Join the fun on the forums!";
	public static final String NEWS_FOUR = "<col=16777215><shad=14024897>["+ Config.SERVER_NAME +" News]:</col></shad><col=16777215><shad=62207> Staff Spots are currently "+ Config.STAFF_OPENINGS +".";
	
	public static int MESSAGE_DELAY = 6000;
	public static final int ITEM_LIMIT = 20500; // item id limit, different clients have more items like silab which goes past 15000
	public static final int MAXITEM_AMOUNT = Integer.MAX_VALUE;
	public static final int BANK_SIZE = 700;
	public static final int MAX_PLAYERS = 1024;

	public static final int CONNECTION_DELAY = 100; // how long one ip can keep connecting
	public static final int IPS_ALLOWED = 100; // how many ips are allowed
		
	public static final boolean WORLD_LIST_FIX = false; // change to true if you want to stop that world--8 thing, but it can cause the screen to freeze on silabsoft client
	
	public static final int[] ITEM_SELLABLE 		=	{6722,18509,15332,15333,15334,15335,15308,15312,15316,15320,15324,3842,12513,12515,12517,12519,12521,12523,12476,12470,12472,12474,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,7453,8839,8840,8842,11663,11664,11665,10499,
														9748,9948,9949,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
														9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
														9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765,995}; // what items can't be sold in any store
	public static final int[] ITEM_TRADEABLE 		= 	{773,1038,1039,1040,1041,1038,1039,1053,1054,1055,1056,1057,1058,1042,1043,1044,1045,1046,1047,1048,1049,12743,773,12742,6722,18509,15808,15914,15925,15936,16013,16035,16127,16262,19893,19892,15786,15797,15837,15892,16185,16153,16002,16046,16057,16068,16105,15332,15333,15334,15335,15308,15312,15316,15320,15324,19785,19786,19787,19788,19789,19790,13351,12513,12515,12517,12519,12521,12523,12476,12470,12472,12474,8850,9948,9949,10551,8839,8840,8842,11663,11664,11665,3842,3844,3840,8844,8845,8846,8847,8848,8849,8850,10551,6570,7462,7461,7460,7459,7458,7457,7456,7455,7454,7453,8839,8840,8842,11663,11664,11665,10499,
														9748,9754,9751,9769,9757,9760,9763,9802,9808,9784,9799,9805,9781,9796,9793,9775,9772,9778,9787,9811,9766,
														9749,9755,9752,9770,9758,9761,9764,9803,9809,9785,9800,9806,9782,9797,9794,9776,9773,9779,9788,9812,9767,
													9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765,12163,12170,12169,12158,12159,12160,12742,15098}; // what items can't be traded or staked
	public static final int[] UNDROPPABLE_ITEMS 	= 	{773,1038,1039,1040,1041,1038,1039,1053,1054,1055,1056,1057,1058,1042,1043,1044,1045,1046,1047,1048,1049,12743,773,12742,6722,18509,15808,15914,15925,15936,16013,16035,16127,16262,19893,19892,15786,15797,15837,15892,16185,16153,16002,16046,16057,16068,16105,15308,18328,18312,18296,18280,15312,15316,15320,15324,12470,12472,15098,12474}; // what items can't be dropped
	
	public static final int[] FUN_WEAPONS	=	{2460,2461,2462,2463,2464,2465,2466,2467,2468,2469,2470,2471,2471,2473,2474,2475,2476,2477}; // fun weapons for dueling
        public static final int[] CAT_ITEMS	=	{1555,6909,1556,12007,1557,1558,1559,1560,1561,1562,1563,1564,1565,7585,7584,12470,12472,12474,12513,12515,12517,12519,12521,12523,12476}; // fun weapons for dueling
	
	public static boolean ADMIN_CAN_TRADE = true; //can admins trade?
	public static boolean ADMIN_CAN_SELL_ITEMS = true; // can admins sell items?
	public static boolean ADMIN_DROP_ITEMS = true; // can admin drop items?
	public static boolean ADMIN_DUEL_ITEMS = true; // can admin duel?
	

	
	public static final int START_LOCATION_X = 3087; // start here
	public static final int START_LOCATION_Y = 3490;
	public static final int RESPAWN_X = 3087; // when dead respawn here
	public static final int RESPAWN_Y = 3493;
	public static final int DUELING_RESPAWN_X = 2834; // when dead in duel area spawn here
	public static final int DUELING_RESPAWN_Y = 3335;
	public static final int RANDOM_DUELING_RESPAWN = 5; // random coords
	
	public static final int NO_TELEPORT_WILD_LEVEL = 20; // level you can't tele on and above
	public static final int SKULL_TIMER = 5000; // how long does the skull last? seconds x 2
	public static final int TELEBLOCK_DELAY = 200000; // how long does teleblock last for.
	public static final boolean SINGLE_AND_MULTI_ZONES = true; // multi and single zones?
	public static final boolean COMBAT_LEVEL_DIFFERENCE = true; // wildy levels and combat level differences matters
	
	public static final boolean itemRequirements = true; // attack, def, str, range or magic levels required to wield weapons or wear items?
		
	public static final int MELEE_EXP_RATE = 4200; // damage * exp rate
	public static final int RANGE_EXP_RATE = 4200;
	public static final int MAGIC_EXP_RATE = 4200;
	public static final int EASY_MODE_EXP_RATE = 9999999;
	public static final int MEDIUM_MODE_EXP_RATE = 1;
	public static final int HARD_MODE_EXP_RATE = 1;
	public static final int EXTREME_MODE_EXP_RATE = 1;
	public static final double SERVER_EXP_BONUS = 1;
	
	public static boolean LOCK_EXPERIENCE = false;
	public static boolean MINI_GAMES = true;
	public static String LOGOUT_MESSAGE = "Click here to logout!";
	public static String DEATH_MESSAGE = "Oh dear you are dead!";
	public static boolean DOUBLE_EXP = true;
	public static int MAX_NPCS = Server.npcHandler.maxNPCs;
	
	public static final int INCREASE_SPECIAL_AMOUNT = 17500; // how fast your special bar refills
	public static final int INCREASE_SPECIAL_AMOUNT_WITH_RING = 10000; // how fast your special bar refills with ring of vigour
	public static final boolean PRAYER_POINTS_REQUIRED = true; // you need prayer points to use prayer
	public static final boolean PRAYER_LEVEL_REQUIRED = true; // need prayer level to use different prayers
	public static final boolean MAGIC_LEVEL_REQUIRED = true; // need magic level to cast spell
	public static final int GOD_SPELL_CHARGE = 300000; // how long does god spell charge last?
	public static final boolean RUNES_REQUIRED = true; // magic rune required?
	public static final boolean CORRECT_ARROWS = true; // correct arrows for bows?
	public static final boolean CRYSTAL_BOW_DEGRADES = false; // magic rune required?
	
	public static final int SAVE_TIMER = 2; // save every 1 minute
	public static final int NPC_RANDOM_WALK_DISTANCE = 1; // the square created , 3x3 so npc can't move out of that box when randomly walking
	public static final int NPC_FOLLOW_DISTANCE = 20; // how far can the npc follow you from it's spawn point, 													
	public static final int[] UNDEAD_NPCS = {90,91,92,93,94,103,104,73,74,75,76,77}; // undead npcs

	/**
	 * Barrows Reward
	 */
	
	
	/**
	 * Glory
	 */
	public static final int EDGEVILLE_X = 3087;
	public static final int EDGEVILLE_Y = 3492;
	public static final String EDGEVILLE = "";
	public static final int AL_KHARID_X = 3293;
	public static final int AL_KHARID_Y = 3174;
	public static final String AL_KHARID = "";
	public static final int KARAMJA_X = 3087;
	public static final int KARAMJA_Y = 3500;
	public static final String KARAMJA = "";
	public static final int MAGEBANK_X = 2538;
	public static final int MAGEBANK_Y = 4716;
	public static final String MAGEBANK = "";
	
	/**
	* Teleport Spells
	**/
	// modern
	public static final int VARROCK_X = 3210;
	public static final int VARROCK_Y = 3424;
	public static final String VARROCK = "";
	public static final int LUMBY_X = 3222;
	public static final int LUMBY_Y = 3218;
	public static final String LUMBY = "";
	public static final int FALADOR_X = 2964;
	public static final int FALADOR_Y = 3378;
	public static final String FALADOR = "";
	public static final int CAMELOT_X = 2757;
	public static final int CAMELOT_Y = 3477;
	public static final String CAMELOT = "";
	public static final int ARDOUGNE_X = 2662;
	public static final int ARDOUGNE_Y = 3305;
	public static final String ARDOUGNE = "";
	public static final int WATCHTOWER_X = 3087;
	public static final int WATCHTOWER_Y = 3500;
	public static final String WATCHTOWER = "";
	public static final int TROLLHEIM_X = 3243;
	public static final int TROLLHEIM_Y = 3513;
	public static final String TROLLHEIM = "";
	
	// ancient
	
	public static final int PADDEWWA_X = 3098;
	public static final int PADDEWWA_Y = 9884;
	
	public static final int SENNTISTEN_X = 3322;
	public static final int SENNTISTEN_Y = 3336;

    public static final int KHARYRLL_X = 3492;
	public static final int KHARYRLL_Y = 3471;

	public static final int LASSAR_X = 3006;
	public static final int LASSAR_Y = 3471;
	
	public static final int DAREEYAK_X = 3161;
	public static final int DAREEYAK_Y = 3671;
	
	public static final int CARRALLANGAR_X = 3156;
	public static final int CARRALLANGAR_Y = 3666;
	
	public static final int ANNAKARL_X = 3288;
	public static final int ANNAKARL_Y = 3886;
	
	public static final int GHORROCK_X = 2977;
	public static final int GHORROCK_Y = 3873;
 
	public static final int TIMEOUT = 20;
	public static final int CYCLE_TIME = 475;
	public static final int BUFFER_SIZE = 10000;
	public static final int MAX_PROCESS_PACKETS = 7;
	
	/**
	 * Slayer Variables
	 */
	public static final int[][] SLAYER_TASKS = {{1,87,90,4,5}, //low tasks
												{6,7,8,9,10}, //med tasks
																						{11,12,13,14,15}, //high tasks
																						{16,17,18,19,20},//elite tasks
																						{1,1,15,20,25}, //low reqs
																						{30,35,40,45,50}, //med reqs
																						{60,75,80,85,90}, //high reqs
																						{70,85,90,99}}; //elite reqs
	
	/**
	* Skill Experience Multipliers
	*/	
	public static final int CONSTRUCTION_EXPERIENCE = 9999999;
	public static final int WOODCUTTING_EXPERIENCE = 150;///Works
	public static final int MINING_EXPERIENCE = 300;///Works
	public static final int SMITHING_EXPERIENCE = 300;///Works
	public static final int FARMING_EXPERIENCE = 150;///Works
	public static final int FIREMAKING_EXPERIENCE = 200;///Works
	public static final int HERBLORE_EXPERIENCE = 300;///Works
	public static final int FISHING_EXPERIENCE = 1000;///Broke
	public static final int AGILITY_EXPERIENCE = 1000;///Broke
	public static final int PRAYER_EXPERIENCE = 300;///Works
	public static final int RUNECRAFTING_EXPERIENCE = 700;///Works
	public static final int CRAFTING_EXPERIENCE = 400;///Works
	public static final int THIEVING_EXPERIENCE = 400;///Works
	public static final int SLAYER_EXPERIENCE = 459;///Works
	public static final int COOKING_EXPERIENCE = 250;///Works
	public static final int FLETCHING_EXPERIENCE = 350;///Works
}
