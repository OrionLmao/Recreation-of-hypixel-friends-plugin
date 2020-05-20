/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orion.friend.Friend;
/*    */ import me.orion.utils.HFUser;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class RemoveCmd
/*    */   extends SubCommand {
/*    */   public RemoveCmd(HypixelFriends plugin, Permission permission) {
/* 17 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 22 */     if (!validatePermissions(user))
/* 23 */       return;  HFUser hfUser = getPlugin().getHfUserManager().getUser((Player)user);
/*    */     
/* 25 */     if (args.length == 1) {
/* 26 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-arguments").replace("%0%", "friend remove <name>"));
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     if (args[1].length() > 16 || args[1].replaceAll("[a-zA-Z0-9_]", "").length() > 0) {
/* 31 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-input").replace("%0%", args[1]));
/*    */       
/*    */       return;
/*    */     } 
/*    */     OfflinePlayer oldFriend;
/* 36 */     if ((oldFriend = Bukkit.getOfflinePlayer(args[1])) == null || !oldFriend.hasPlayedBefore()) {
/* 37 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.player-not-found").replace("%0%", args[1]));
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     for (Friend friend : hfUser.getFriendList().getAllFriends()) {
/* 42 */       if (!friend.getUniqueId().equals(oldFriend.getUniqueId())) {
/*    */         continue;
/*    */       }
/* 45 */       hfUser.getFriendList().removeFriend(oldFriend);
/* 46 */       getPlugin().getHfUserManager().saveUser((OfflinePlayer)user);
/*    */       
/* 48 */       if (oldFriend.isOnline()) {
/* 49 */         getPlugin().getHfUserManager().getUser((Player)oldFriend).getFriendList().removeFriend((OfflinePlayer)user);
/* 50 */         getPlugin().getHfUserManager().saveUser(oldFriend);
/*    */         
/* 52 */         for (String line : getPlugin().getConfig().getStringList("messages.unfriend-receiver")) {
/* 53 */           getPlugin().getMessageUtil().message((CommandSender)oldFriend, line.replace("%0%", ((Player)user).getDisplayName()));
/*    */         }
/*    */       } 
/* 56 */       FileConfiguration storage = getPlugin().getStorageUtil().getFile(oldFriend.getUniqueId().toString());
/* 57 */       List<String> friends = storage.getStringList("friends");
/* 58 */       friends.remove(((Player)user).getUniqueId().toString());
/*    */       
/* 60 */       storage.set("friends", friends);
/* 61 */       getPlugin().getStorageUtil().saveFile(storage, oldFriend.getUniqueId().toString());
/*    */       
/* 63 */       for (String line : getPlugin().getConfig().getStringList("messages.unfriend-sender")) {
/* 64 */         getPlugin().getMessageUtil().message(user, line.replace("%0%", oldFriend.isOnline() ? Bukkit.getPlayer(oldFriend.getUniqueId()).getDisplayName() : oldFriend.getName()));
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 69 */     for (String line : getPlugin().getConfig().getStringList("messages.not-friends"))
/* 70 */       getPlugin().getMessageUtil().message(user, line.replace("%0%", oldFriend.isOnline() ? Bukkit.getPlayer(oldFriend.getUniqueId()).getDisplayName() : oldFriend.getName())); 
/*    */   }
/*    */ }