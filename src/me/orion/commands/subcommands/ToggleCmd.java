/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orion.utils.HFUser;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class ToggleCmd extends SubCommand {
/*    */   public ToggleCmd(HypixelFriends plugin, Permission permission) {
/* 12 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 17 */     if (!validatePermissions(user))
/* 18 */       return;  HFUser hfUser = getPlugin().getHfUserManager().getUser((Player)user);
/*    */     
/* 20 */     hfUser.toggleRequests();
/* 21 */     getPlugin().getHfUserManager().saveUser((OfflinePlayer)user);
/*    */     
/* 23 */     for (String line : getPlugin().getConfig().getStringList("messages.toggle"))
/* 24 */       getPlugin().getMessageUtil().message(user, line.replace("%0%", hfUser.isAllowingRequests() ? "enabled" : "disabled")); 
/*    */   }
/*    */ }