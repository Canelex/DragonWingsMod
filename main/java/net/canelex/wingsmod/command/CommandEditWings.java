package net.canelex.wingsmod.command;

import net.canelex.wingsmod.WingsMod;
import net.canelex.wingsmod.gui.GuiEditWings;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommandEditWings extends CommandBase
{
	private WingsMod mod;

	public CommandEditWings(WingsMod mod)
	{
		this.mod = mod;
	}

	@Override
	public String getCommandName()
	{
		return "wingsmod";
	}

	@Override public String getCommandUsage(ICommandSender sender)
	{
		return "/wingsmod";
	}

	@Override public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override public void processCommand(ICommandSender sender, String[] args) throws CommandException
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		MinecraftForge.EVENT_BUS.unregister(this);
		Minecraft.getMinecraft().displayGuiScreen(new GuiEditWings(mod));
	}
}
