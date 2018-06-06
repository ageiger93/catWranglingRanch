package server.model.players.skills;

import java.util.HashMap;
import server.Config;
import server.model.players.Client;
import server.util.Misc;

/*
 * Handles Prayer Class
 */

public class Prayer {

	Client c;	
		public Prayer(Client c) {
			this.c = c;
		}
	/*
	 * Enum for Bones Data
	 */
	enum BonesData { 
		/*Starter Bones*/
		REGULAR(526, 5, "Bones"),
		NORMAL2(2530, 5, "Bones"), 
		Burnt(529, 5, "Burnt Bones"),
		BATB(530, 5, "Bat Bones"),
                WOLFB(2859, 5, "Wolf Bones"),
                MONKEYB0(3179, 5, "Monkey Bones"),
		MONKEYB1(3180, 5, "Monkey Bones"),
		MONKEYB2(3181, 5, "Monkey Bones"),
		MONKEYB3(3182, 5, "Monkey Bones"),
		MONKEYB4(3183, 5, "Monkey Bones"),
		MONKEYB5(3185, 5, "Monkey Bones"),
		MONKEYB6(3186, 5, "Monkey Bones"),
		MONKEYB7(3187, 5, "Monkey Bones"),
		
		/*Decent Bones*/
		BBONE(532, 15, "Big Bones"),
		Jogre(3125, 20, "Jogre Bones"),
		BJogre(3127, 21, "Burnt Jogre Bones"),
		Zogre(4812, 21, "Zogre Bones"),
		Shaik(3123, 22, "Shaikahan Bones"),
		Baby(534, 30, "Baby Dragon Bones"),
		Wyvern(6812, 40, "Wyvern Bones"),
		Dragon(536, 72, "Dragon Bones"),
		Fayrg(4830, 74, "Fayrg Bones"),
		
		/*Good Bones*/
		OurgB(14793, 75, "Ourg Bones"),
		RaurgBone(4832, 75, "Raurg Bones"),
		OurgBone(4834, 85, "Ourg Bones"),
		DagBone(6729, 125, "Dagannoth Bones"),	
		Frost(18830, 195, "Frost Bones"),
                ANCIENT(15410, 200, "Ancient Bones");
		
		
		static HashMap<Integer, BonesData> BoneInfo = new HashMap<Integer, BonesData>();
		
		int boneID, boneXP;
                String boneName;
        
        static {
            for (final BonesData bones : BoneInfo.values())
					BonesData.BoneInfo.put(bones.boneXP, bones);
        }
    
        /*
         * @param boneID
         * @param boneXP
         * @param boneName
         */
        BonesData(final int boneID, final int boneXP, final String boneName) {
        	this.boneID = boneID;
        	this.boneXP = boneXP;
        	this.boneName = boneName;
        }
        
        int getboneID() {
        	return boneID;
        }

        int getboneXP() {
               return boneXP;
        }
        
        private String getBoneName() {
		return toString().toLowerCase();
	}
}
	
	/*
    * @param ID The bone Id
    * @return
    */
	public boolean readBone(int boneID) {
       for (final BonesData bones : BonesData.values()) {
        	if(c.getItems().playerHasItem(bones.getboneID(), 1));
        		if (boneID == bones.getboneID()) {
        			return true;
        		}
        }
        return false;
	}
	
	/*
	 * @param boneID
	 * Handles burying bone
	 */
	public void boneOnGround(int boneID) {
		if (System.currentTimeMillis() - c.buryDelay > 1500) {
			for (final BonesData bones : BonesData.values()) {
				if (boneID == bones.getboneID()) {
					c.getItems().deleteItem(boneID, 1);
					c.sendMessage("The gods are pleased with your offering.");
					c.getPA().addSkillXP(bones.getboneXP() * Config.PRAYER_EXPERIENCE, 5);
					c.buryDelay = System.currentTimeMillis();
					c.startAnimation(827);
				}
			}
		}
	}
	
	/*
	 * @param boneID
	 * Handles bones on alter
	 */
	
	public void boneOnAlter(int boneID) {
		for (final BonesData bones : BonesData.values()) {
			if(boneID == bones.getboneID()) {
			c.startAnimation(896);
				c.gfx0(624);
					c.getItems().deleteItem(boneID, 1);
					c.sendMessage("The gods are pleased with your offering.");
					c.getPA().addSkillXP(bones.getboneXP() * 2 * Config.PRAYER_EXPERIENCE, 5);
			}
		}
	}
}