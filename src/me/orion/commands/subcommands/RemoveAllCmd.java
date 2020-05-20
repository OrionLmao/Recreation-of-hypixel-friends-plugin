/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orion.utils.HFUser;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class RemoveAllCmd extends SubCommand {
/*    */   public RemoveAllCmd(HypixelFriends plugin, Permission permission) {
/* 12 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 17 */     if (!validatePermissions(user))
/* 18 */       return;  HFUser hfUser = getPlugin().getHfUserManager().getUser((Player)user);
/*    */     
/* 20 */     if (hfUser.getFriendList().getFriendsListSize() <= 0) {
/* 21 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.friends-list-already-empty"));
/*    */       
/*    */       return;
/*    */     } 
/* 25 */     hfUser.getFriendList().clear();
/* 26 */     getPlugin().getHfUserManager().saveUser((OfflinePlayer)user);
/* 27 */     getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.friends-list-cleared"));
/*    */   }
/*    */ }