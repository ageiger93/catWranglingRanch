package server.model.items;

import server.model.players.Client;
import server.util.Misc;
import server.Config;

/**
 * 
 * @author Ryan / Lmctruck30
 *
 */

public class UseItem {

	public static void ItemonObject(Client c, int objectID, int objectX, int objectY, int itemId) {
		if (!c.getItems().playerHasItem(itemId, 1))
			return;
		switch(objectID) {
			case 2783:
				c.getSmithingInt().showSmithInterface(itemId);
			break;
			case 7965:
			case 8389:
				c.getFarming().checkItemOnObject(itemId);
			break;
			case 2728:
			case 12269:
				c.getCooking().itemOnObject(itemId);
			break;
			case 15621:
				if(c.absX == 2857 && c.absY == 3537 || c.absX == 2851 && c.absY == 3537) {
				c.getWarriorsGuild().handleArmor(c, itemId, objectX, objectY);
				}
			break;
			case 409:
				if (c.getPrayer().readBone(itemId))
					c.getPrayer().boneOnAlter(itemId);
			break;
		default:
			if(c.playerRights == 3)
				Misc.println("Player At Object id: "+objectID+" with Item id: "+itemId);
			break;
		}
		
	}

public static void ItemonItem(Client c, int itemUsed, int useWith) {
	    
		/**Fix for the mother fucking brew on brew to unnote the brew
		*Example: Unoted Sara Bew on a noted Brew unnotes the brew
		*Fix by Trisidia Owner of RSPS TrisidiaX
		*© 20012 by TrisidiaX 
		**/
                if (itemUsed == 6686 && useWith == 6685 || itemUsed == 6685
                                && useWith == 6686) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6689 || itemUsed == 6689
                                && useWith == 6686) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6687 || itemUsed == 6687
                                && useWith == 6686) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6691 || itemUsed == 6691
                                && useWith == 6686) {
                        return;
                }
                if (itemUsed == 6688 && useWith == 6686 || itemUsed == 6686
                                && useWith == 6688) {
                        return;
                }
                if (itemUsed == 6688 && useWith == 6687 || itemUsed == 6687
                                && useWith == 6688) {
                        return;
                }
                if (itemUsed == 6690 && useWith == 6688 || itemUsed == 6688
                                && useWith == 6690) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6688 || itemUsed == 6688
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6685 || itemUsed == 6685
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6690 || itemUsed == 6690
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6689 || itemUsed == 6689
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6691 || itemUsed == 6691
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6692 && useWith == 6687 || itemUsed == 6687
                                && useWith == 6692) {
                        return;
                }
                if (itemUsed == 6688 && useWith == 6689 || itemUsed == 6689
                                && useWith == 6688) {
                        return;
                }
                if (itemUsed == 6688 && useWith == 6685 || itemUsed == 6685
                                && useWith == 6688) {
                        return;
                }
                if (itemUsed == 6688 && useWith == 6691 || itemUsed == 6691
                                && useWith == 6688) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6692 || itemUsed == 6692
                                && useWith == 6686) {
                        return;
                }
                if (itemUsed == 6690 && useWith == 6685 || itemUsed == 6685
                                && useWith == 6690) {
                        return;
                }
                if (itemUsed == 6690 && useWith == 6689 || itemUsed == 6689
                                && useWith == 6690) {
                        return;
                }
                if (itemUsed == 6690 && useWith == 6691 || itemUsed == 6691
                                && useWith == 6690) {
                        return;
                }
                if (itemUsed == 6690 && useWith == 6687 || itemUsed == 6687
                                && useWith == 6690) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 2435 || itemUsed == 2435
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 2435 && useWith == 139 || itemUsed == 139
                                && useWith == 2435) {
                        return;
                }
                if (itemUsed == 2435 && useWith == 141 || itemUsed == 141
                                && useWith == 2435) {
                        return;
                }
                if (itemUsed == 2435 && useWith == 143 || itemUsed == 143
                                && useWith == 2435) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 139 || itemUsed == 139
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 141 || itemUsed == 141
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 140 || itemUsed == 140
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 142 && useWith == 140 || itemUsed == 140
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 141 && useWith == 140 || itemUsed == 140
                                && useWith == 141) {
                        return;
                }
                if (itemUsed == 143 && useWith == 140 || itemUsed == 140
                                && useWith == 143) {
                        return;
                }
                if (itemUsed == 142 && useWith == 2435 || itemUsed == 2435
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 144 && useWith == 2435 || itemUsed == 2435
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 144 && useWith == 140 || itemUsed == 140
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 143 && useWith == 140 || itemUsed == 140
                                && useWith == 143) {
                        return;
                }
                if (itemUsed == 139 && useWith == 140 || itemUsed == 140
                                && useWith == 139) {
                        return;
                }
                if (itemUsed == 140 && useWith == 2435 || itemUsed == 2435
                                && useWith == 140) {
                        return;
                }
                if (itemUsed == 144 && useWith == 143 || itemUsed == 143
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 143 || itemUsed == 143
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 142 || itemUsed == 142
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 144 || itemUsed == 144
                                && useWith == 2434) {
                        return;
                }
                if (itemUsed == 139 && useWith == 142 || itemUsed == 142
                                && useWith == 139) {
                        return;
                }
                if (itemUsed == 141 && useWith == 142 || itemUsed == 142
                                && useWith == 141) {
                        return;
                }
                if (itemUsed == 142 && useWith == 143 || itemUsed == 143
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 142 && useWith == 139 || itemUsed == 139
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 142 && useWith == 143 || itemUsed == 143
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 144 && useWith == 142 || itemUsed == 142
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 142 && useWith == 141 || itemUsed == 141
                                && useWith == 142) {
                        return;
                }
                if (itemUsed == 144 && useWith == 139 || itemUsed == 139
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 144 && useWith == 141 || itemUsed == 141
                                && useWith == 144) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 2440 || itemUsed == 2440
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 158 || itemUsed == 158
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 158 && useWith == 160 || itemUsed == 160
                                && useWith == 158) {
                        return;
                }
                if (itemUsed == 158 && useWith == 162 || itemUsed == 162
                                && useWith == 158) {
                        return;
                }
                if (itemUsed == 162 && useWith == 160 || itemUsed == 160
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 160 || itemUsed == 160
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 162 && useWith == 160 || itemUsed == 160
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 162 && useWith == 161 || itemUsed == 161
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 162 && useWith == 159 || itemUsed == 159
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 2440 && useWith == 158 || itemUsed == 158
                                && useWith == 2440) {
                        return;
                }
                if (itemUsed == 2440 && useWith == 160 || itemUsed == 160
                                && useWith == 2440) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 162 || itemUsed == 162
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 160 && useWith == 159 || itemUsed == 159
                                && useWith == 160) {
                        return;
                }
                if (itemUsed == 160 && useWith == 157 || itemUsed == 157
                                && useWith == 160) {
                        return;
                }
                if (itemUsed == 160 && useWith == 161 || itemUsed == 161
                                && useWith == 160) {
                        return;
                }
                if (itemUsed == 2440 && useWith == 157 || itemUsed == 157
                                && useWith == 2440) {
                        return;
                }
                if (itemUsed == 158 && useWith == 157 || itemUsed == 157
                                && useWith == 158) {
                        return;
                }
                if (itemUsed == 158 && useWith == 159 || itemUsed == 159
                                && useWith == 158) {
                        return;
                }
                if (itemUsed == 158 && useWith == 161 || itemUsed == 161
                                && useWith == 158) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 159 || itemUsed == 159
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 157 || itemUsed == 157
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 161 || itemUsed == 161
                                && useWith == 2441) {
                        return;
                }
                if (itemUsed == 162 && useWith == 2440 || itemUsed == 2440
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 162 && useWith == 157 || itemUsed == 157
                                && useWith == 162) {
                        return;
                }
                if (itemUsed == 2436 && useWith == 150 || itemUsed == 150
                                && useWith == 2436) {
                        return;
                }
                if (itemUsed == 2436 && useWith == 146 || itemUsed == 146
                                && useWith == 2436) {
                        return;
                }
                if (itemUsed == 2436 && useWith == 148 || itemUsed == 148
                                && useWith == 2436) {
                        return;
                }
                if (itemUsed == 150 && useWith == 145 || itemUsed == 145
                                && useWith == 150) {
                        return;
                }
                if (itemUsed == 150 && useWith == 147 || itemUsed == 147
                                && useWith == 150) {
                        return;
                }
                if (itemUsed == 150 && useWith == 149 || itemUsed == 149
                                && useWith == 150) {
                        return;
                }
                if (itemUsed == 145 && useWith == 146 || itemUsed == 146
                                && useWith == 145) {
                        return;
                }
                if (itemUsed == 145 && useWith == 148 || itemUsed == 148
                                && useWith == 145) {
                        return;
                }
                if (itemUsed == 147 && useWith == 148 || itemUsed == 148
                                && useWith == 147) {
                        return;
                }
                if (itemUsed == 149 && useWith == 148 || itemUsed == 148
                                && useWith == 149) {
                        return;
                }
                if (itemUsed == 149 && useWith == 146 || itemUsed == 146
                                && useWith == 149) {
                        return;
                }
                if (itemUsed == 147 && useWith == 146 || itemUsed == 146
                                && useWith == 147) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 146 || itemUsed == 146
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 148 || itemUsed == 148
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 148 && useWith == 146 || itemUsed == 146
                                && useWith == 148) {
                        return;
                }
                if (itemUsed == 146 && useWith == 150 || itemUsed == 150
                                && useWith == 146) {
                        return;
                }
                if (itemUsed == 148 && useWith == 150 || itemUsed == 150
                                && useWith == 148) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 150 || itemUsed == 150
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 2436 || itemUsed == 2436
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 145 || itemUsed == 145
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 147 || itemUsed == 147
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2437 && useWith == 149 || itemUsed == 149
                                && useWith == 2437) {
                        return;
                }
                if (itemUsed == 2442 && useWith == 164 || itemUsed == 164
                                && useWith == 2442) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 164 || itemUsed == 164
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 166 || itemUsed == 166
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 168 || itemUsed == 168
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 2442 || itemUsed == 2442
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 163 || itemUsed == 163
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 165 || itemUsed == 165
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 167 || itemUsed == 167
                                && useWith == 2443) {
                        return;
                }
                if (itemUsed == 164 && useWith == 163 || itemUsed == 163
                                && useWith == 164) {
                        return;
                }
                if (itemUsed == 164 && useWith == 165 || itemUsed == 165
                                && useWith == 164) {
                        return;
                }
                if (itemUsed == 164 && useWith == 167 || itemUsed == 167
                                && useWith == 164) {
                        return;
                }
                if (itemUsed == 2442 && useWith == 166 || itemUsed == 166
                                && useWith == 2442) {
                        return;
                }
                if (itemUsed == 2442 && useWith == 168 || itemUsed == 168
                                && useWith == 2442) {
                        return;
                }
                if (itemUsed == 165 && useWith == 166 || itemUsed == 166
                                && useWith == 165) {
                        return;
                }
                if (itemUsed == 168 && useWith == 163 || itemUsed == 163
                                && useWith == 168) {
                        return;
                }
                if (itemUsed == 168 && useWith == 165 || itemUsed == 165
                                && useWith == 168) {
                        return;
                }
                if (itemUsed == 168 && useWith == 166 || itemUsed == 166
                                && useWith == 168) {
                        return;
                }
                if (itemUsed == 164 && useWith == 166 || itemUsed == 166
                                && useWith == 164) {
                        return;
                }
                if (itemUsed == 164 && useWith == 168 || itemUsed == 168
                                && useWith == 164) {
                        return;
                }
                if (itemUsed == 166 && useWith == 168 || itemUsed == 168
                                && useWith == 166) {
                        return;
                }
                if (itemUsed == 168 && useWith == 167 || itemUsed == 167
                                && useWith == 168) {
                        return;
                }
                if (itemUsed == 163 && useWith == 166 || itemUsed == 166
                                && useWith == 163) {
                        return;
                }
                if (itemUsed == 167 && useWith == 166 || itemUsed == 166
                                && useWith == 167) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 2444 || itemUsed == 2444
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 172 && useWith == 2444 || itemUsed == 2444
                                && useWith == 172) {
                        return;
                }
                if (itemUsed == 174 && useWith == 2444 || itemUsed == 2444
                                && useWith == 174) {
                        return;
                }
                if (itemUsed == 172 && useWith == 169 || itemUsed == 169
                                && useWith == 172) {
                        return;
                }
                if (itemUsed == 174 && useWith == 169 || itemUsed == 169
                                && useWith == 174) {
                        return;
                }
                if (itemUsed == 172 && useWith == 171 || itemUsed == 171
                                && useWith == 172) {
                        return;
                }
                if (itemUsed == 174 && useWith == 171 || itemUsed == 171
                                && useWith == 174) {
                        return;
                }
                if (itemUsed == 172 && useWith == 173 || itemUsed == 173
                                && useWith == 172) {
                        return;
                }
                if (itemUsed == 174 && useWith == 173 || itemUsed == 173
                                && useWith == 174) {
                        return;
                }
                if (itemUsed == 170 && useWith == 2444 || itemUsed == 2444
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 170 || itemUsed == 170
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 172 || itemUsed == 172
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 170 && useWith == 172 || itemUsed == 172
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 170 && useWith == 169 || itemUsed == 169
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 170 && useWith == 171 || itemUsed == 171
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 170 && useWith == 173 || itemUsed == 173
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 174 && useWith == 172 || itemUsed == 172
                                && useWith == 174) {
                        return;
                }
                if (itemUsed == 170 && useWith == 174 || itemUsed == 174
                                && useWith == 170) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 174 || itemUsed == 174
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 169 || itemUsed == 169
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 171 || itemUsed == 171
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 173 || itemUsed == 173
                                && useWith == 2445) {
                        return;
                }
                if (itemUsed == 3025 && useWith == 3024 || itemUsed == 3024
                                && useWith == 3025) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3025 || itemUsed == 3025
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3025 || itemUsed == 3025
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3025 || itemUsed == 3025
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3027 || itemUsed == 3027
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3029 || itemUsed == 3029
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3029 || itemUsed == 3029
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3024 || itemUsed == 3024
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3026 || itemUsed == 3026
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3028 || itemUsed == 3028
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3027 && useWith == 3030 || itemUsed == 3030
                                && useWith == 3027) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3027 || itemUsed == 3027
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3025 && useWith == 3026 || itemUsed == 3026
                                && useWith == 3025) {
                        return;
                }
                if (itemUsed == 3025 && useWith == 3028 || itemUsed == 3028
                                && useWith == 3025) {
                        return;
                }
                if (itemUsed == 3025 && useWith == 3030 || itemUsed == 3030
                                && useWith == 3025) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3024 || itemUsed == 3024
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3026 || itemUsed == 3026
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3028 || itemUsed == 3028
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3029 && useWith == 3030 || itemUsed == 3030
                                && useWith == 3029) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3024 || itemUsed == 3024
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3026 || itemUsed == 3026
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3028 || itemUsed == 3028
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3031 && useWith == 3030 || itemUsed == 3030
                                && useWith == 3031) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3043 || itemUsed == 3043
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3045 || itemUsed == 3045
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3047 || itemUsed == 3047
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3040 || itemUsed == 3040
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3046 || itemUsed == 3046
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3044 || itemUsed == 3044
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3042 || itemUsed == 3042
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3045 || itemUsed == 3045
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3043 && useWith == 3047 || itemUsed == 3047
                                && useWith == 3043) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3045 || itemUsed == 3045
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3047 || itemUsed == 3047
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3040 || itemUsed == 3040
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3042 || itemUsed == 3042
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3044 || itemUsed == 3044
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3046 || itemUsed == 3046
                                && useWith == 3041) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3046 || itemUsed == 3046
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3044 || itemUsed == 3044
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3042 || itemUsed == 3042
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3045 && useWith == 3040 || itemUsed == 3040
                                && useWith == 3045) {
                        return;
                }
                if (itemUsed == 3047 && useWith == 3046 || itemUsed == 3046
                                && useWith == 3047) {
                        return;
                }
                if (itemUsed == 3047 && useWith == 3044 || itemUsed == 3044
                                && useWith == 3047) {
                        return;
                }
                if (itemUsed == 3047 && useWith == 3042 || itemUsed == 3042
                                && useWith == 3047) {
                        return;
                }
                if (itemUsed == 3047 && useWith == 3040 || itemUsed == 3040
                                && useWith == 3047) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6685) { 
                        return;
                }
                if (itemUsed == 6686 && useWith == 6689) { 
                        return;
                }
                if (itemUsed == 6686 && useWith == 6687) {
                        return;
                }
                if (itemUsed == 6686 && useWith == 6691) { 
                        return;
                }
                if (itemUsed == 2434 && useWith == 2435) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 139) {
                        return;
                }
                if (itemUsed == 2434 && useWith == 141) { 
                        return;
                }
                if (itemUsed == 2434 && useWith == 143) { 
                        return;
                }
                if (itemUsed == 2441 && useWith == 2440) {
                        return;
                }
                if (itemUsed == 2441 && useWith == 157) { 
                        return;
                }
                if (itemUsed == 2441 && useWith == 159) { 
                        return;
                }
                if (itemUsed == 2441 && useWith == 161) { 
                        return;
                }
                if (itemUsed == 2437 && useWith == 2436) { 
                        return;
                }
                if (itemUsed == 2437 && useWith == 145) { 
                        return;
                }
                if (itemUsed == 2437 && useWith == 147) { 
                        return;
                }
                if (itemUsed == 2437 && useWith == 149) { 
                        return;
                }
                if (itemUsed == 2443 && useWith == 2442) { 
                        return;
                }
                if (itemUsed == 2443 && useWith == 163) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 165) {
                        return;
                }
                if (itemUsed == 2443 && useWith == 167) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 2444) { 
                        return;
                }
                if (itemUsed == 2445 && useWith == 169) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 171) {
                        return;
                }
                if (itemUsed == 2445 && useWith == 173) { 
                        return;
                }
                if (itemUsed == 3025 && useWith == 3024) { 
                        return;
                }
                if (itemUsed == 3025 && useWith == 3026) { 
                        return;
                }
                if (itemUsed == 3025 && useWith == 3028) { 
                        return;
                }
                if (itemUsed == 3025 && useWith == 3030) {
                        return;
                }
                if (itemUsed == 3041 && useWith == 3040) { 
                        return;
                }
                if (itemUsed == 3041 && useWith == 3042) { 
                        return;
                }
                if (itemUsed == 3041 && useWith == 3044) { 
                        return;
                }
                if (itemUsed == 3041 && useWith == 3046) { 
                        return;
                }
		c.Summoning.ItemonItem(itemUsed, useWith);			
		if (itemUsed == 227 || useWith == 227)
			c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getHerblore().checkItem(useWith, 5) || c.getHerblore().checkItem(itemUsed, 1) || c.getHerblore().checkItem(useWith, 1) || c.getHerblore().checkItem(itemUsed, 5))
            c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getItems().getItemName(itemUsed).contains("(") && c.getItems().getItemName(useWith).contains("("))
			c.getPotMixing().mixPotion2(itemUsed, useWith);
		if (itemUsed == 1733 || useWith == 1733)
			c.getCrafting().handleLeather(itemUsed, useWith);
		if (itemUsed == 1755 || useWith == 1755)
			c.getCrafting().handleChisel(itemUsed,useWith);
		if (itemUsed == 946 || useWith == 946)
			c.getFletching().handleLog(itemUsed,useWith);
		if (itemUsed == 53 || useWith == 53 || itemUsed == 52 || useWith == 52)
			c.getFletching().makeArrows(itemUsed, useWith);
		if ((itemUsed == 1540 && useWith == 11286) || (itemUsed == 11286 && useWith == 1540)) {
			if (c.playerLevel[c.playerSmithing] >= 95) {
				c.getItems().deleteItem(1540, c.getItems().getItemSlot(1540), 1);
				c.getItems().deleteItem(11286, c.getItems().getItemSlot(11286), 1);
				c.getItems().addItem(11283,1);
				c.sendMessage("You combine the two materials to create a dragonfire shield.");
				c.getPA().addSkillXP(500 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
			} else {
				c.sendMessage("You need a smithing level of 95 to create a dragonfire shield.");
			}
		}
		if (itemUsed == 261 && useWith == 2436) {
			if (c.getItems().playerHasItem(2436, 1) && c.getItems().playerHasItem(261, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(261, c.getItems().getItemSlot(261),1);
					c.getItems().deleteItem(2436, c.getItems().getItemSlot(2436),1);
					c.getItems().addItem(15308,1);
					c.sendMessage("You make an Extreme Attack (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Attack (4) and Clean Avantoe for this potion.");
			}
		}
		if (itemUsed == 267 && useWith == 2440) {
			if (c.getItems().playerHasItem(2440, 1) && c.getItems().playerHasItem(267, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(267, c.getItems().getItemSlot(267),1);
					c.getItems().deleteItem(2440, c.getItems().getItemSlot(2440),1);
					c.getItems().addItem(15312,1);
					c.sendMessage("You make an Extreme Strength (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Strength (4) and Clean Dwarf Weed for this potion.");
			}
		}
		if (itemUsed == 2481 && useWith == 2442) {
			if (c.getItems().playerHasItem(2442, 1) && c.getItems().playerHasItem(2481, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(2481, c.getItems().getItemSlot(2481),1);
					c.getItems().deleteItem(2442, c.getItems().getItemSlot(2442),1);
					c.getItems().addItem(15316,1);
					c.sendMessage("You make an Extreme Defence (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Super Defence (4) and Clean Lantadyme for this potion.");
			}
		}
		if (itemUsed == 3000 && useWith == 3040) {
			if (c.getItems().playerHasItem(3040, 1) && c.getItems().playerHasItem(3000, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
					c.getItems().deleteItem(3040, c.getItems().getItemSlot(3040),1);
					c.getItems().addItem(15320,1);
					c.sendMessage("You make an Extreme Magic (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Magic Potion (4) and Clean Snapdragon for this potion.");
			}
		}
		if (itemUsed == 259 && useWith == 2444) {
			if (c.getItems().playerHasItem(2444, 1) && c.getItems().playerHasItem(259, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(259, c.getItems().getItemSlot(259),1);
					c.getItems().deleteItem(2444, c.getItems().getItemSlot(2444),1);
					c.getItems().addItem(15324,1);
					c.sendMessage("You make an Extreme Ranging (4).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme.");
				}
			} else {
				c.sendMessage("You need a Ranging Potion (4) and Clean Irit for this potion.");
			}
		}
		if (itemUsed == 269 && useWith == 15308 || itemUsed == 269 && useWith == 15312 || itemUsed == 269 && useWith == 15316 || itemUsed == 269 && useWith == 15320 || itemUsed == 269 && useWith == 15324) {
       		if (c.getItems().playerHasItem(15308, 1) && c.getItems().playerHasItem(15312, 1) && c.getItems().playerHasItem(15316, 1) && c.getItems().playerHasItem(15320, 1) && c.getItems().playerHasItem(15324, 1)){
            	if (c.playerLevel[c.playerHerblore] >= 96) {
            	c.getItems().deleteItem(269, c.getItems().getItemSlot(269),1);
            	c.getItems().deleteItem(15308, c.getItems().getItemSlot(15308),1);
            	c.getItems().deleteItem(15312, c.getItems().getItemSlot(15312),1);
            	c.getItems().deleteItem(15316, c.getItems().getItemSlot(15316),1);
            	c.getItems().deleteItem(15320, c.getItems().getItemSlot(15320),1);
            	c.getItems().deleteItem(15324, c.getItems().getItemSlot(15324),1);
            	c.getItems().addItem(15332,1);
                c.sendMessage("You make a Overload Potion (4).");
                c.getPA().addSkillXP(500 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
            	} else {
                c.sendMessage("You need a herblore level of 96 to make that potion.");
            	}
            	} else {
                	c.sendMessage("You need all extreme potions to make a Overload.");
            	}
        }
		if (itemUsed == 12435) { 
			if (c.lastsummon == 6874) {
			if(c.gwdelay > 1);
				c.getItems().bankItem(useWith, c.getItems().getItemSlot(useWith), 1);
				c.getItems().deleteItem(itemUsed, 1);
				c.sendMessage("Your Pack yak sends an item to your bank."); 
				c.gfx0(1316);
				c.startAnimation(7660);
				c.sendMessage("You can only do this every 5 Minutes!");
				c.gwdelay = 600;
			} else {
				c.sendMessage("You must wait another 5 Minutes to use this scroll.");
			}
		}
		if (itemUsed == 9142 && useWith == 9190 || itemUsed == 9190 && useWith == 9142) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9241, boltsMade);
				c.getPA().addSkillXP(boltsMade * 6 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}
		}
		if (itemUsed == 9143 && useWith == 9191 || itemUsed == 9191 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9242, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}		
		}
		if (itemUsed == 9143 && useWith == 9192 || itemUsed == 9192 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9243, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9193 || itemUsed == 9193 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9244, boltsMade);
				c.getPA().addSkillXP(boltsMade * 10 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9194 || itemUsed == 9194 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9245, boltsMade);
				c.getPA().addSkillXP(boltsMade * 13 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}		
		}
		if (itemUsed == 1601 && useWith == 1755 || itemUsed == 1755 && useWith == 1601) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				c.getItems().deleteItem(1601, c.getItems().getItemSlot(1601), 1);
				c.getItems().addItem(9192, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}
		}
		if (itemUsed == 1607 && useWith == 1755 || itemUsed == 1755 && useWith == 1607) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				c.getItems().deleteItem(1607, c.getItems().getItemSlot(1607), 1);
				c.getItems().addItem(9189, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}
		}
		if (itemUsed == 1605 && useWith == 1755 || itemUsed == 1755 && useWith == 1605) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				c.getItems().deleteItem(1605, c.getItems().getItemSlot(1605), 1);
				c.getItems().addItem(9190, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}
		}
		if (itemUsed == 1603 && useWith == 1755 || itemUsed == 1755 && useWith == 1603) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1603, c.getItems().getItemSlot(1603), 1);
				c.getItems().addItem(9191, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed == 1615 && useWith == 1755 || itemUsed == 1755 && useWith == 1615) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1615, c.getItems().getItemSlot(1615), 1);
				c.getItems().addItem(9193, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed >= 11710 && itemUsed <= 11714 && useWith >= 11710 && useWith <= 11714) {
			if (c.getItems().hasAllShards()) {
				c.getItems().makeBlade();
			}		
		}
		if (itemUsed == 985 && useWith == 987 || itemUsed == 987 && useWith == 985) {
c.getItems().deleteItem(985, c.getItems().getItemSlot(985),1);
c.getItems().deleteItem(987, c.getItems().getItemSlot(987),1);
c.getItems().addItem(989,1);
}
		if (itemUsed == 2368 && useWith == 2366 || itemUsed == 2366 && useWith == 2368) {
			c.getItems().deleteItem(2368, c.getItems().getItemSlot(2368),1);
			c.getItems().deleteItem(2366, c.getItems().getItemSlot(2366),1);
			c.getItems().addItem(1187,1);
		}
		if (c.getItems().isHilt(itemUsed) || c.getItems().isHilt(useWith)) {
			int hilt = c.getItems().isHilt(itemUsed) ? itemUsed : useWith;
			int blade = c.getItems().isHilt(itemUsed) ? useWith : itemUsed;
			if (blade == 11690) {
				c.getItems().makeGodsword(hilt);
			}
		}
		if (itemUsed == 4151 && useWith == 10531 || itemUsed == 10531 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15444,1);	
		}

		if (itemUsed == 4151 && useWith == 10537 || itemUsed == 10537 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15443,1);	
		}

		if (itemUsed == 4151 && useWith == 10533 || itemUsed == 10533 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15442,1);	
		}

		if (itemUsed == 4151 && useWith == 10534 || itemUsed == 10534 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15441,1);	
		}

		if (itemUsed == 11235 && useWith == 10531 || itemUsed == 10531 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15704,1);	
		}

		if (itemUsed == 11235 && useWith == 10537 || itemUsed == 10537 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15703,1);	
		}

		if (itemUsed == 3188 && useWith == 15441 || itemUsed == 15441 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15442 || itemUsed == 15442 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15443 || itemUsed == 15443 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15444 || itemUsed == 15444 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15701 || itemUsed == 15701 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15702 || itemUsed == 15702 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15703 || itemUsed == 15703 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15704 || itemUsed == 15704 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 11235 && useWith == 10533 || itemUsed == 10533 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15702,1);	
		}

		if (itemUsed == 11235 && useWith == 10534 || itemUsed == 10534 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15701,1);	
		}

		if (itemUsed == 13736 && useWith == 13746 || itemUsed == 13746 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13738,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13748 || itemUsed == 13748 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13740,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13750 || itemUsed == 13750 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13742,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13752 || itemUsed == 13752 && useWith == 13736) {
				if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13744,1);
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}				
		
		if (itemUsed == 13734 && useWith == 13754 || itemUsed == 13754 && useWith == 13734) {
		if (c.playerLevel[c.playerPrayer] >= 75) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13736,1);	
					} else {
				c.sendMessage("You need a Prayer level of 75 to Make a blessed spiritshield.");
			}
		}
		switch(itemUsed) {
			case 1511:
			case 1521:
			case 1519:
			case 1517:
			case 1515:
			case 1513:
			case 590:
				c.getFiremaking().checkLogType(itemUsed, useWith);
			break;
			
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemUsed+" with Item id: "+useWith);
			break;
		}	
	}
	public static void ItemonNpc(Client c, int itemId, int npcId, int slot) {
		switch(itemId) {
		
		default:
			if(c.playerRights == 3)
				Misc.println("Player used Item id: "+itemId+" with Npc id: "+npcId+" With Slot : "+slot);
			break;
		}
		
	}


}
