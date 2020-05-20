/*    */ package me.orion.commands;
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orionf.commands.subcommands.SubCommand;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class FriendCmd extends Command {
/*    */   public FriendCmd(HypixelFriends plugin) {
/* 11 */     super(plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerSubCommands() {
/* 16 */     addSubCommand("accept", (SubCommand)new AcceptCmd(getPlugin(), new Permission("hypixelfriends.accept")));
/* 17 */     addSubCommand("add", (SubCommand)new AddCmd(getPlugin(), new Permission("hypixelfriends.add")));
/* 18 */     addSubCommand("deny", (SubCommand)new DenyCmd(getPlugin(), new Permission("hypixelfriends.deny")));
/* 19 */     addSubCommand("help", (SubCommand)new HelpCmd(getPlugin(), new Permission("hypixelfriends.help")));
/* 20 */     addSubCommand("list", (SubCommand)new ListCmd(getPlugin(), new Permission("hypixelfriends.list")));
/* 21 */     addSubCommand("removeall", (SubCommand)new RemoveAllCmd(getPlugin(), new Permission("hypixelfriends.removeall")));
/* 22 */     addSubCommand("remove", (SubCommand)new RemoveCmd(getPlugin(), new Permission("hypixelfriends.remove")));
/* 23 */     addSubCommand("requests", (SubCommand)new RequestsCmd(getPlugin(), new Permission("hypixelfriends.requests")));
/* 24 */     addSubCommand("toggle", (SubCommand)new ToggleCmd(getPlugin(), new Permission("hypixelfriends.toggle")));
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender sender, String... args) {
/* 29 */     if (!(sender instanceof Player))
/* 30 */       return;  Player user = (Player)sender;
/*    */     
/* 32 */     if (args.length == 0) {
/* 33 */       executeSubCommand("help");
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     executeSubCommand(args[0]);
/*    */   }
/*    */ }