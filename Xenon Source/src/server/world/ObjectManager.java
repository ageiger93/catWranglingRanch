package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
	c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(3192, 2818, 3335, 1, 10); //ingamehighscores
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(2213, 2837, 3442, 1, 10); //Bank Stall
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(4008, 2851, 2965, 1, 10); //spec alter
		c.getPA().checkObjectSpawn(11356, 2826, 3355, 0, 10); //frost dragon portals
		c.getPA().checkObjectSpawn(8972, 2474, 3440, 0, 10); //Strykeworms portal
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10); //Dungeoneering Rock
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); //Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(2014, 1921, 4640, 0, 10); //Zombie Minigame Chalice of Eternity
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); //Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); //Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); //dungie blocker
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); //dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); //dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); //dung floor 7 portalEND
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2422, 9429, 0, 10); //escape ladder
                c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
				 c.getPA().checkObjectSpawn(-1, 3090, 3503, 0, 10);
		c.getPA().checkObjectSpawn(16078, 1920, 4636, 0, 10);
		c.getPA().checkObjectSpawn(7315, 2871, 2952, 1, 10);
		c.getPA().checkObjectSpawn(2996, 3348, 3338, 2, 10);//al key chest
                c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
				
					///Home Objects!	
c.getPA().checkObjectSpawn(3192, 1, 1, 0, 10); //hs board
		c.getPA().checkObjectSpawn(411, 3090, 3498, 1, 10);   /// Choras Altar
		c.getPA().checkObjectSpawn(4008, 3096, 3500, 0, 10);  ///Spec Attack Altar
		c.getPA().checkObjectSpawn(6552, 3091, 3506, -2, 10);  ///Ancient Altar
		c.getPA().checkObjectSpawn(409, 3090, 3488, -1, 10);  /// Altar/
		c.getPA().checkObjectSpawn(410, 3098, 3502, 1, 10);   ///Guthix Altar
		c.getPA().checkObjectSpawn(12356, 3090, 3492, 0, 10); ///Portal at home/
		c.getPA().checkObjectSpawn(2996, 3091, 3488, 2, 10);  ///al key chest/
		c.getPA().checkObjectSpawn(103, 3094, 3488, 0, 10);  ///Squeal Of Fortune Chest/
		c.getPA().checkObjectSpawn(3192, 2403, 3492, 0, 10);  ///hs board
		c.getPA().checkObjectSpawn(3515, 3091, 3496, -1, 10);//Varrock west bank
		c.getPA().checkObjectSpawn(11356, 3094, 3487, 0, 10); ///frost dragon portals/
		///End of home objects

		c.getPA().checkObjectSpawn(4874, 2848, 3430, 1, 10);///Home Stalls lvl 1
		c.getPA().checkObjectSpawn(4875, 2847, 3430, 1, 10);///Home Stalls lvl 25
		c.getPA().checkObjectSpawn(4876, 2846, 3430, 0, 10);///Home Stalls lvl 50
		c.getPA().checkObjectSpawn(4877, 2845, 3430, 0, 10);///Home Stalls lvl 75
		c.getPA().checkObjectSpawn(4878, 2844, 3430, 0, 10);///Home Stalls lvl 90
		///
		c.getPA().checkObjectSpawn(4008, 2850, 3352, 0, 10);
		c.getPA().checkObjectSpawn(6552, 2850, 3345, 0, 10);
		c.getPA().checkObjectSpawn(2403, 2847, 3333, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2846, 3333, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
                c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
                c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
                c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(1277, 2048, 3244, 0, 10);
c.getPA().checkObjectSpawn(13405, 3352, 3348, 0, 10);//home portal
		c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3244, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2048, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2049, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3243, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2071, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3263, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2072, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3263, 0, 10);
		//end of trees
         
       
		//empty bulding spaces
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3247, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3248, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3249, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3250, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3251, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3252, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3253, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3254, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3255, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3256, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3257, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3258, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(2284, 2851, 3428, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2845, 2957, 1, 10);
				c.getPA().checkObjectSpawn(2403, 2844, 2957, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2854, 2962, 1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	    c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(411, 2850, 3349, 1, 10); // Curse Prayers

		
		c.getPA().checkObjectSpawn(13615, 2525, 4770, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2523, 4770, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2521, 4770, 2, 10); // tormented demons donor


		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10); 

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2842, 2954, 1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2852, 2950, 2, 10);
		c.getPA().checkObjectSpawn(409, 2853, 3348, 1, 10);
		c.getPA().checkObjectSpawn(410, 2854, 3348, 1, 10);
		c.getPA().checkObjectSpawn(409, 2530, 4779, 3, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
				c.getPA().checkObjectSpawn(104, 2522, 4780, -2, 10); //Donatorchest
				c.getPA().checkObjectSpawn(104, 2877, 10204, -2, 10); //Donatorchest
				c.getPA().checkObjectSpawn(104, 2882, 10204, -2, 10); //Donatorchest
				
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		
		c.getPA().checkObjectSpawn(-1, 3076, 3510, 1, 10);
c.getPA().checkObjectSpawn(-1, 3078, 3510, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3510, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3510, 1, 10);
c.getPA().checkObjectSpawn(-1, 3084, 3510, 1, 10);
c.getPA().checkObjectSpawn(-1, 3079, 3507, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3507, 1, 10);
c.getPA().checkObjectSpawn(-1, 3083, 3507, 1, 10);
c.getPA().checkObjectSpawn(-1, 3084, 3509, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3509, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3511, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3512, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3512, 1, 10);
c.getPA().checkObjectSpawn(-1, 3084, 3512, 1, 10);
c.getPA().checkObjectSpawn(-1, 3083, 3513, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3513, 1, 10);
c.getPA().checkObjectSpawn(-1, 3079, 3513, 1, 10);
c.getPA().checkObjectSpawn(-1, 3078, 3513, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3513, 1, 10);
c.getPA().checkObjectSpawn(-1, 3075, 3421, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3075, 3422, 1, 10);
c.getPA().checkObjectSpawn(-1, 3074, 3421, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3074, 3422, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3421, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3075, 3424, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3418, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3075, 3413, 1, 10);
c.getPA().checkObjectSpawn(-1, 3078, 3412, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3075, 3415, 1, 10);
c.getPA().checkObjectSpawn(-1, 3078, 3413, 1, 10);		
c.getPA().checkObjectSpawn(-1, 3084, 3409, 1, 10);
c.getPA().checkObjectSpawn(10436, 3425, 2915, 1, 10);

		c.getPA().checkObjectSpawn(-1, 3078, 3427, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3078, 3428, 1, 10);	
		c.getPA().checkObjectSpawn(-1, 3078, 3429, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3078, 3430, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3078, 3431, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3078, 3428, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3427, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3428, 1, 10);	
		c.getPA().checkObjectSpawn(-1, 3077, 3429, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3430, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3431, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3428, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3076, 3430, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3073, 3427, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3073, 3429, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3073, 3430, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3073, 3431, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3075, 3429, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3076, 3430, 1, 10);
	
		//Edgeville Pking Zone
		c.getPA().checkObjectSpawn(-1, 3095, 3498, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3096, 3498, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3095, 3499, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3090, 3494, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3091, 3495, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3092, 3496, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3090, 3496, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3093, 3488, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3092, 3488, 0, 10);
		
		c.getPA().checkObjectSpawn(-1, 2689, 3716, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2689, 3715, 1, 10);
		//newhomenullouts
		c.getPA().checkObjectSpawn(-1, 2840, 10196, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 10196, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2842, 10196, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2843, 10196, 1, 10);
		
		c.getPA().checkObjectSpawn(-1, 2840, 10198, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 10198, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2842, 10198, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2843, 10198, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2839, 10198, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2869, 10188, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2870, 10188, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2871, 10188, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2872, 10188, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2873, 10188, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2869, 10189, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2870, 10189, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2871, 10189, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2872, 10189, 1, 10);
		c.getPA().checkObjectSpawn(-1, 2873, 10189, 1, 10);
		// FUCKING GREEN TREES
		c.getPA().checkObjectSpawn(-1, 2726, 3482, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2726, 3483, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2725, 3482, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2725, 3483, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2722, 3481, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2722, 3482, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2723, 3482, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2723, 3481, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2723, 3477, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2723, 3476, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2722, 3477, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2737, 3487, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2737, 3488, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2738, 3488, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2738, 3487, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2739, 3486, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2740, 3486, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2740, 3485, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2739, 3485, 0, 10);
		
		c.getPA().checkObjectSpawn(-1, 2737, 3485, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2737, 3484, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2738, 3485, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2738, 3484, 0, 10);
		
		c.getPA().checkObjectSpawn(-1, 3085, 3407, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3084, 3407, 0, 10);
		c.getPA().checkObjectSpawn(10251, 3080, 3421, 0, 10);


		c.getPA().checkObjectSpawn(-1, 2735, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2735, 3479, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2734, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2734, 3479, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2733, 3479, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2733, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2732, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2732, 3479, 0, 10);
		
		c.getPA().checkObjectSpawn(-1, 2732, 3481, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2732, 3482, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2733, 3481, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2733, 3482, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2749, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2748, 3480, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2749, 3481, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2748, 3481, 0, 10);
		//FOUNTAINS
		c.getPA().checkObjectSpawn(-1, 2751, 3485, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2751, 3484, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2750, 3485, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2750, 3484, 0, 10);
		
		c.getPA().checkObjectSpawn(-1, 2750, 3487, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2750, 3488, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2751, 3487, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2751, 3488, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2761, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2762, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2760, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2763, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2764, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2765, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2757, 3503, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2758, 3503, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2761, 3511, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2765, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2759, 3513, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2757, 3513, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2755, 3511, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2755, 3509, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2750, 3504, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2766, 3504, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2762, 3499, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2753, 3499, 0, 10);
		//DeathAltar
c.getPA().checkObjectSpawn(-1, 2209, 4830, 1, 10);
c.getPA().checkObjectSpawn(-1, 2210, 4830, 1, 10);
c.getPA().checkObjectSpawn(-1, 2209, 4831, 1, 10);
c.getPA().checkObjectSpawn(-1, 2210, 4831, 1, 10);

c.getPA().checkObjectSpawn(-1, 2199, 4829, 1, 10);
c.getPA().checkObjectSpawn(-1, 2119, 4828, 1, 10);
c.getPA().checkObjectSpawn(-1, 2200, 4829, 1, 10);
c.getPA().checkObjectSpawn(-1, 2200, 4828, 1, 10);
c.getPA().checkObjectSpawn(-1, 2218, 4821, 1, 10);
c.getPA().checkObjectSpawn(-1, 2224, 4849, 1, 10);
c.getPA().checkObjectSpawn(-1, 2219, 4845, 1, 10);
c.getPA().checkObjectSpawn(-1, 2218, 4843, 1, 10);
c.getPA().checkObjectSpawn(-1, 2215, 4825, 1, 10);
c.getPA().checkObjectSpawn(-1, 2218, 4822, 1, 10);
c.getPA().checkObjectSpawn(-1, 2219, 4819, 1, 10);
c.getPA().checkObjectSpawn(-1, 2218, 4820, 1, 10);
c.getPA().checkObjectSpawn(-1, 2221, 4819, 1, 10);
c.getPA().checkObjectSpawn(-1, 2220, 4818, 1, 10);
c.getPA().checkObjectSpawn(-1, 2222, 4818, 1, 10);





c.getPA().checkObjectSpawn(-1, 2658, 10102, 1, 10);
//NewDungeoneering
c.getPA().checkObjectSpawn(-1, 3078, 3441, 1, 10);
c.getPA().checkObjectSpawn(-1, 2658, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2654, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2652, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2650, 10101, 1, 10);
c.getPA().checkObjectSpawn(-1, 2658, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2645, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2644, 10102, 1, 10);
c.getPA().checkObjectSpawn(-1, 2645, 10101, 1, 10);
c.getPA().checkObjectSpawn(-1, 2644, 10101, 1, 10);
c.getPA().checkObjectSpawn(-1, 2644, 10101, 1, 10);
c.getPA().checkObjectSpawn(-1, 2640, 10100, 1, 10);
c.getPA().checkObjectSpawn(-1, 2639, 10100, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10098, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10098, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10097, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10096, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10095, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10094, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10093, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10092, 1, 10);
c.getPA().checkObjectSpawn(-1, 2637, 10098, 1, 10);
c.getPA().checkObjectSpawn(-1, 2637, 10097, 1, 10);
c.getPA().checkObjectSpawn(-1, 2637, 10096, 1, 10);
c.getPA().checkObjectSpawn(-1, 2637, 10095, 1, 10);
c.getPA().checkObjectSpawn(-1, 2637, 10094, 1, 10);

c.getPA().checkObjectSpawn(-1, 2638, 10092, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10091, 1, 10);
c.getPA().checkObjectSpawn(-1, 2639, 10092, 1, 10);
c.getPA().checkObjectSpawn(-1, 2639, 10091, 1, 10);

c.getPA().checkObjectSpawn(-1, 2638, 10088, 1, 10);
c.getPA().checkObjectSpawn(-1, 2638, 10087, 1, 10);
c.getPA().checkObjectSpawn(-1, 2639, 10088, 1, 10);
c.getPA().checkObjectSpawn(-1, 2639, 10087, 1, 10);
c.getPA().checkObjectSpawn(-1, 2640, 10070, 1, 10);
c.getPA().checkObjectSpawn(-1, 2640, 10068, 1, 10);
c.getPA().checkObjectSpawn(-1, 2641, 10065, 1, 10);
c.getPA().checkObjectSpawn(-1, 2643, 10063, 1, 10);


c.getPA().checkObjectSpawn(-1, 2652, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2653, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2654, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2655, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2656, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2657, 10061, 1, 10);
c.getPA().checkObjectSpawn(-1, 2652, 10060, 1, 10);
c.getPA().checkObjectSpawn(-1, 2653, 10060, 1, 10);
c.getPA().checkObjectSpawn(-1, 2654, 10060, 1, 10);
c.getPA().checkObjectSpawn(-1, 2655, 10060, 1, 10);
c.getPA().checkObjectSpawn(-1, 2656, 10060, 1, 10);
c.getPA().checkObjectSpawn(-1, 2657, 10060, 1, 10);
c.getPA().checkObjectSpawn(8987, 2670, 10062, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10077, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10078, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10079, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10077, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10078, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10079, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10077, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10078, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10079, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2674, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10080, 1, 10);
c.getPA().checkObjectSpawn(-1, 2675, 10080, 1, 10);

c.getPA().checkObjectSpawn(-1, 2673, 10084, 1, 10);
c.getPA().checkObjectSpawn(-1, 2673, 10085, 1, 10);
c.getPA().checkObjectSpawn(-1, 2672, 10084, 1, 10);
c.getPA().checkObjectSpawn(-1, 2672, 10085, 1, 10);

c.getPA().checkObjectSpawn(4151, 2673, 10091, 1, 10);


//NexLawArea
c.getPA().checkObjectSpawn(-1, 2463, 4834, 1, 10);
c.getPA().checkObjectSpawn(-1, 2463, 4833, 1, 10);
c.getPA().checkObjectSpawn(-1, 2463, 4832, 1, 10);
c.getPA().checkObjectSpawn(-1, 2463, 4831, 1, 10);
c.getPA().checkObjectSpawn(-1, 2463, 4830, 1, 10);

c.getPA().checkObjectSpawn(-1, 2464, 4834, 1, 10);
c.getPA().checkObjectSpawn(-1, 2464, 4833, 1, 10);
c.getPA().checkObjectSpawn(-1, 2464, 4832, 1, 10);
c.getPA().checkObjectSpawn(-1, 2464, 4831, 1, 10);
c.getPA().checkObjectSpawn(-1, 2464, 4830, 1, 10);

c.getPA().checkObjectSpawn(-1, 2465, 4834, 1, 10);
c.getPA().checkObjectSpawn(-1, 2465, 4833, 1, 10);
c.getPA().checkObjectSpawn(-1, 2465, 4832, 1, 10);
c.getPA().checkObjectSpawn(-1, 2465, 4831, 1, 10);
c.getPA().checkObjectSpawn(-1, 2465, 4830, 1, 10);

c.getPA().checkObjectSpawn(-1, 2465, 4827, 1, 10);
c.getPA().checkObjectSpawn(-1, 2464, 4827, 1, 10);
c.getPA().checkObjectSpawn(-1, 2463, 4827, 1, 10);
//End
c.getPA().checkObjectSpawn(-1, 3077, 3438, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3439, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3440, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3441, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3442, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3443, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3436, 1, 10);
c.getPA().checkObjectSpawn(-1, 2507, 4724, 1, 10);
c.getPA().checkObjectSpawn(-1, 2500, 4721, 1, 10);
c.getPA().checkObjectSpawn(-1, 2516, 4721, 1, 10);
c.getPA().checkObjectSpawn(-1, 3082, 3420, 1, 10);
c.getPA().checkObjectSpawn(-1, 3083, 3420, 1, 10);
c.getPA().checkObjectSpawn(-1, 3082, 3421, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3420, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3419, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3420, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3419, 1, 10);

c.getPA().checkObjectSpawn(-1, 3079, 3421, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3422, 1, 10);
c.getPA().checkObjectSpawn(-1, 3083, 3421, 1, 10);



c.getPA().checkObjectSpawn(-1, 2690, 3726, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3725, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3724, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3723, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3722, 1, 10);


c.getPA().checkObjectSpawn(-1, 2689, 3721, 1, 10);
c.getPA().checkObjectSpawn(-1, 2689, 3720, 1, 10);
c.getPA().checkObjectSpawn(-1, 2689, 3719, 1, 10);
c.getPA().checkObjectSpawn(-1, 2689, 3718, 1, 10);
c.getPA().checkObjectSpawn(-1, 2689, 3717, 1, 10);



c.getPA().checkObjectSpawn(-1, 2689, 3716, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3715, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3714, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3713, 1, 10);
c.getPA().checkObjectSpawn(-1, 2690, 3712, 1, 10);


c.getPA().checkObjectSpawn(-1, 3078, 3443, 1, 10);
c.getPA().checkObjectSpawn(-1, 3079, 3439, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3439, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3442, 1, 10);
c.getPA().checkObjectSpawn(-1, 3076, 3445, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3445, 1, 10);
c.getPA().checkObjectSpawn(-1, 3078, 3445, 1, 10);
c.getPA().checkObjectSpawn(-1, 3079, 3445, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3445, 1, 10);
c.getPA().checkObjectSpawn(-1, 3082, 3445, 1, 10);

c.getPA().checkObjectSpawn(-1, 3082, 3444, 1, 10);
c.getPA().checkObjectSpawn(-1, 3081, 3442, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3445, 1, 10);


c.getPA().checkObjectSpawn(-1, 3080, 3438, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3439, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3440, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3441, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3442, 1, 10);
c.getPA().checkObjectSpawn(-1, 3080, 3443, 1, 10);


c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
c.getPA().checkObjectSpawn(-1, 2737, 3477, 1, 10);
c.getPA().checkObjectSpawn(-1, 2736, 3477, 1, 10);
c.getPA().checkObjectSpawn(-1, 3660, 3519, 1, 10);
//Purplestuff
c.getPA().checkObjectSpawn(-1, 2374, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4953, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4953, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4954, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4954, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4955, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4955, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4956, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4956, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4957, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4957, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4958, 1, 10);
c.getPA().checkObjectSpawn(-1, 2604, 9912, 0, 10); //Zombie Minigame Chalice of Eternity
c.getPA().checkObjectSpawn(-1, 2603, 9912, 0, 10); //Zombie Minigame Chalice of Eternity
c.getPA().checkObjectSpawn(-1, 2603, 9910, 0, 10); //Zombie Minigame Chalice of Eternity
c.getPA().checkObjectSpawn(-1, 2604, 9910, 0, 10); //Zombie Minigame Chalice of Eternity
c.getPA().checkObjectSpawn(-1, 2374, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4959, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4959, 1, 10);

c.getPA().checkObjectSpawn(-1, 2374, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4960, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4960, 1, 10);


c.getPA().checkObjectSpawn(-1, 2374, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4961, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4961, 1, 10);


c.getPA().checkObjectSpawn(-1, 2374, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4962, 1, 10);
c.getPA().checkObjectSpawn(-1, 2376, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2375, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2374, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4963, 1, 10);
c.getPA().checkObjectSpawn(-1, 2376, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2375, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2374, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4964, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4964, 1, 10);


c.getPA().checkObjectSpawn(-1, 2374, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4965, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4965, 1, 10);


c.getPA().checkObjectSpawn(-1, 2374, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2373, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2372, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2371, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2370, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2369, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2368, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2367, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2366, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2365, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2364, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2363, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2362, 4966, 1, 10);
c.getPA().checkObjectSpawn(-1, 2361, 4966, 1, 10);
/**c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(3192, 2818, 3335, 1, 10); //ingamehighscores
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(2213, 2837, 3442, 1, 10); //Bank Stall
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(4008, 2851, 2965, 1, 10); //spec alter
		c.getPA().checkObjectSpawn(11356, 2826, 3355, 0, 10); //frost dragon portals
		c.getPA().checkObjectSpawn(8972, 2474, 3440, 0, 10); //Strykeworms portal
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10); //Dungeoneering Rock
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); //Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(2014, 1921, 4640, 0, 10); //Zombie Minigame Chalice of Eternity
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); //Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); //Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); //dungie blocker
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); //dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); //dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); //dung floor 7 portalEND
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2422, 9429, 0, 10); //escape ladder
                c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
				 c.getPA().checkObjectSpawn(-1, 3090, 3503, 0, 10);
		c.getPA().checkObjectSpawn(16078, 1920, 4636, 0, 10);
		c.getPA().checkObjectSpawn(7315, 2871, 2952, 1, 10);
		c.getPA().checkObjectSpawn(2996, 3348, 3338, 2, 10);//al key chest
                c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
				
					///Home Objects!	
c.getPA().checkObjectSpawn(3192, 1, 1, 0, 10); //hs board
		c.getPA().checkObjectSpawn(411, 3090, 3498, 1, 10);   /// Choras Altar
		c.getPA().checkObjectSpawn(4008, 3096, 3500, 0, 10);  ///Spec Attack Altar
		c.getPA().checkObjectSpawn(6552, 3091, 3506, -2, 10);  ///Ancient Altar
		c.getPA().checkObjectSpawn(409, 3090, 3488, -1, 10);  /// Altar/
		c.getPA().checkObjectSpawn(410, 3098, 3502, 1, 10);   ///Guthix Altar
		c.getPA().checkObjectSpawn(12356, 3090, 3492, 0, 10); ///Portal at home/
		c.getPA().checkObjectSpawn(2996, 3091, 3488, 2, 10);  ///al key chest/
		c.getPA().checkObjectSpawn(103, 3094, 3488, 0, 10);  ///Squeal Of Fortune Chest/
		c.getPA().checkObjectSpawn(3192, 2403, 3492, 0, 10);  ///hs board
		c.getPA().checkObjectSpawn(11356, 3094, 3487, 0, 10); ///frost dragon portals/
		///End of home objects

		c.getPA().checkObjectSpawn(4874, 2848, 3430, 1, 10);///Home Stalls lvl 1
		c.getPA().checkObjectSpawn(4875, 2847, 3430, 1, 10);///Home Stalls lvl 25
		c.getPA().checkObjectSpawn(4876, 2846, 3430, 0, 10);///Home Stalls lvl 50
		c.getPA().checkObjectSpawn(4877, 2845, 3430, 0, 10);///Home Stalls lvl 75
		c.getPA().checkObjectSpawn(4878, 2844, 3430, 0, 10);///Home Stalls lvl 90
		///
		c.getPA().checkObjectSpawn(4008, 2850, 3352, 0, 10);
		c.getPA().checkObjectSpawn(6552, 2850, 3345, 0, 10);
		c.getPA().checkObjectSpawn(2403, 2847, 3333, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2846, 3333, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
                c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
                c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
                c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(1277, 2048, 3244, 0, 10);
c.getPA().checkObjectSpawn(13405, 3352, 3348, 0, 10);//home portal
		c.getPA().checkObjectSpawn(1277, 2049, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3244, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2048, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2049, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2050, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2051, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2052, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2053, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2054, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2055, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2056, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2057, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2058, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2059, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2060, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2061, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2062, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2063, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2064, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2065, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2066, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2067, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2068, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2069, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2070, 3243, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3243, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2071, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2071, 3263, 0, 10);

		c.getPA().checkObjectSpawn(1277, 2072, 3244, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3245, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3246, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3247, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3248, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3249, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3250, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3251, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3252, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3253, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3254, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3255, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3256, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3257, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3258, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3259, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3260, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3261, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3262, 0, 10);
		c.getPA().checkObjectSpawn(1277, 2072, 3263, 0, 10);
		//end of trees
         
       
		//empty bulding spaces
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3247, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3247, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3248, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3248, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3249, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3249, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3250, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3250, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3251, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3251, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3252, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3252, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3253, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3253, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3254, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3254, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3255, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3255, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3256, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3256, 0, 10);
		//1
		c.getPA().checkObjectSpawn(11214, 2069, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2065, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2061, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2057, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2053, 3257, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2049, 3257, 0, 10);
		//2
		c.getPA().checkObjectSpawn(11214, 2067, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2063, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2059, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2055, 3258, 0, 10);
		c.getPA().checkObjectSpawn(11214, 2051, 3258, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(2284, 2851, 3428, 0, 10);
		c.getPA().checkObjectSpawn(12356, 2845, 2957, 1, 10);
				c.getPA().checkObjectSpawn(2403, 2844, 2957, 2, 10);
		c.getPA().checkObjectSpawn(2996, 2854, 2962, 1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	    c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4774, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(411, 2850, 3349, 1, 10); // Curse Prayers

		
		c.getPA().checkObjectSpawn(13615, 2525, 4770, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2523, 4770, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2521, 4770, 2, 10); // tormented demons donor


		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10); 

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 2842, 2954, 1, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 2852, 2950, 2, 10);
		c.getPA().checkObjectSpawn(409, 2853, 3348, 1, 10);
		c.getPA().checkObjectSpawn(410, 2854, 3348, 1, 10);
		c.getPA().checkObjectSpawn(409, 2530, 4779, 3, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
				c.getPA().checkObjectSpawn(104, 2522, 4780, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);*/

	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}