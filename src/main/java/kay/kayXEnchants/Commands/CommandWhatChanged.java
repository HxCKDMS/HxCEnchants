package kay.kayXEnchants.Commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandWhatChanged implements ICommand
{
  private List aliases;
  public CommandWhatChanged()
  {
    this.aliases = new ArrayList();
    this.aliases.add("WhatChanged");
    this.aliases.add("ZedsXEnchChangelog");
  }

  @Override
  public String getCommandName()
  {
    return "WhatChanged";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "WhatChanged";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] astring)
  {
	  if(icommandsender instanceof EntityPlayer)
	  {
		  EntityPlayer player = (EntityPlayer) icommandsender;
		  //player.addChatMessage(COLOR + "3---DrZed's Extra Enchants v a1.0.5---");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "3 Added Command WhatChanged");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + DAGGER + COLOR + "3 Added Better Morph support and added Morph API");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + DAGGER + COLOR + "3 Tweaked Flight Mechanics more for Better IC2 Support");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + DAGGER + COLOR + "3 Added support for Essentials /fly");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "3 Added Commands to List Beta Testers and Such");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "3 Added Command to Reset the player abilities.");
	  }

  }

  public static final String DAGGER = String.copyValueOf(Character.toChars(0x86));
  public static final String COLOR = String.copyValueOf(Character.toChars(0xA7));

  @Override
  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions(ICommandSender icommandsender,
      String[] astring)
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex(String[] astring, int i)
  {
    return false;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}