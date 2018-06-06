package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Trading
 */
public class Trade implements PacketType {
	public boolean inTrade;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		
		if (c.inTrade) {
		c.sendMessage("You cannot walk while in a trade.");
		return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
                if (c.InDung()) {
                         c.sendMessage("You cannot trade inside Dungoneering!");
                         return;
		}
		if (c.inWild()) {
                         c.sendMessage("You can't trade in the wilderness!");
                         return;
		}
		if(c.playerRights == 5 && !Config.ADMIN_CAN_TRADE) {
			c.sendMessage("<shad=16745472>[Server]</col><shad=65288> "+ Config.OWNER +" has disaled Trading for Admins.");
			return;
		}
		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_ONE +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}

		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_TWO +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}

		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_THREE +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_FOUR +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_FIVE +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		if (c.playerName.equalsIgnoreCase(""+ Config.TRADE_DISABLED_SIX +"")){
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}
		
}
