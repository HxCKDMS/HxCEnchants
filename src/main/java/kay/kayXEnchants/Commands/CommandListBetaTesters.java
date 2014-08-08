package kay.kayXEnchants.Commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandListBetaTesters implements ICommand
{
  private List aliases;
  public CommandListBetaTesters()
  {
    this.aliases = new ArrayList();
    this.aliases.add("ListBetaTesters");
    this.aliases.add("ZedsXEnchTesters");
  }

  @Override
  public String getCommandName()
  {
    return "ListBetaTesters";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "ListBetaTesters";
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
		  //player.addChatMessage(COLOR + "eAlb" + COLOR + "6, " + COLOR + "4Yandeere" + COLOR + "6, " + COLOR + "2Sh3mm" + COLOR + "6, " + COLOR + "1rocklea1" + COLOR + "6, " + COLOR + "8DrPepsi");

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