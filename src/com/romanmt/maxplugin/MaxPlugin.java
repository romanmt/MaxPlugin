package com.romanmt.maxplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MaxPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("BUDDER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	private void generateCube(Location loc, int length, Material material){
		// Set one corner of the cube to the given location.
		// Uses getBlockN() instead of getN() to avoid casting to an int later.
		int x1 = loc.getBlockX();
		int y1 = loc.getBlockY();
		int z1 = loc.getBlockZ();

		// Figure out the opposite corner of the cube by taking the corner and adding length to all coordinates.
		int x2 = x1 + length;
		int y2 = y1 + length;
		int z2 = z1 + length;

		World world = loc.getWorld();
		// Loop over the cube in the x dimension.
		for (int xPoint = x1; xPoint <= x2; xPoint++) {
			// Loop over the cube in the y dimension.
			for (int yPoint = y1; yPoint <= y2; yPoint++) {
				// Loop over the cube in the z dimension.
				for (int zPoint = z1; zPoint <= z2; zPoint++) {
					// Get the block that we are currently looping over.
					Block currentBlock = world.getBlockAt(xPoint, yPoint, zPoint);
					// Set the block to the specified material
					currentBlock.setType(material);
				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if(command.getName().equalsIgnoreCase("build")){
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player player = (Player) sender;
				Location loc = player.getLocation();
				loc.setY(loc.getY() + 15);
				int size = 10;
				Material material = null;
				if(args.length > 0)
				{
					String materialName = args[1].toUpperCase();
					try {
						size = Integer.parseInt(args[0]);
					}
					catch(Exception e) {
						sender.sendMessage("The seconds parameter must be a number.");
					}
					material = Material.getMaterial(materialName);
					material = (material == null) ? Material.DIAMOND : material;
				}
				getLogger().info("cube size: " + size);
				generateCube(loc, size, material);
			}
			return true;
		}
		return super.onCommand(sender, command, label, args);
	}
}
