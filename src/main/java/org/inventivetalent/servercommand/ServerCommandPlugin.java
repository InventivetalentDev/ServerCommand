package org.inventivetalent.servercommand;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerCommandPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ("server".equals(command.getName())) {
			if (args.length == 0) {
				return false;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage("Command only available to players");
				return false;
			}
			String server = args[0];
			connect((Player) sender, server);
			return true;
		}


		return super.onCommand(sender, command, label, args);
	}

	void connect(Player who, String target) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(target);

		who.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
}
