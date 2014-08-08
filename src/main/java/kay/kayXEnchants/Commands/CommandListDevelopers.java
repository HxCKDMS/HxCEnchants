package kay.kayXEnchants.Commands;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class CommandListDevelopers implements ICommand
{
  private List aliases;
  public CommandListDevelopers()
  {
    this.aliases = new ArrayList();
    this.aliases.add("ListDevs");
    this.aliases.add("XEnchDevs");
  }

  @Override
  public String getCommandName()
  {
    return "ListDevelopers";
  }

  @Override
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "ListDevelopers";
  }

  @Override
  public List getCommandAliases()
  {
    return this.aliases;
  }

  @Override
  public void processCommand(ICommandSender icommandsender, String[] string)
  {
	  if(icommandsender instanceof EntityPlayer)
	  {
		  EntityPlayer player = (EntityPlayer) icommandsender;
          player.addChatComponentMessage(new ChatComponentText(COLOR + "1test"));

		  //player.addChatMessage(COLOR + "3---DrZed's Extra Enchants v a1.0.5---");
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "9Head Developer" + COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "3DrZed"+ COLOR + "6|" + COLOR + "4KeldonSlayer" + COLOR + "6" + COLOR + "l" + DAGGER);
		  //player.addChatMessage(COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "9Co-Dev/Debugger" + COLOR + "6" + COLOR + "l" + DAGGER + COLOR + "2TehPers" + COLOR + "6" + COLOR + "l" + DAGGER);
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