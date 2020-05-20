/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orion.friend.Friend;
/*    */ import me.orion.friend.FriendRequest;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class AcceptCmd extends SubCommand {
/*    */   public AcceptCmd(HypixelFriends plugin, Permission permission) {
/* 14 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 19 */     if (!validatePermissions(user))
/*    */       return; 
/* 21 */     if (args.length == 1) {
/* 22 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-arguments").replace("%0%", "friend accept <name>"));
/*    */       
/*    */       return;
/*    */     } 
/* 26 */     if (args[1].length() > 16 || args[1].replaceAll("[a-zA-Z0-9_]", "").length() > 0) {
/* 27 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-input").replace("%0%", args[1]));
/*    */       
/*    */       return;
/*    */     } 
/*    */     OfflinePlayer potentialFriend;
/* 32 */     if ((potentialFriend = getPlugin().getServer().getOfflinePlayer(args[1])) == null || !potentialFriend.hasPlayedBefore()) {
/* 33 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.player-not-found").replace("%0%", args[1]));
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     for (Friend friend : getPlugin().getHfUserManager().getUser((Player)user).getFriendList().getAllFriends()) {
/* 38 */       if (friend.getUniqueId().equals(potentialFriend.getUniqueId())) {
/* 39 */         getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.already-friends"));
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 44 */     if (getPlugin().getFriendRequestManager().getFriendRequests(((Player)user).getUniqueId()).isEmpty()) {
/* 45 */       for (String line : getPlugin().getConfig().getStringList("messages.no-request")) {
/* 46 */         getPlugin().getMessageUtil().message(user, line.replace("%0%", Bukkit.getOfflinePlayer(args[1]).isOnline() ? Bukkit.getPlayer(args[1]).getDisplayName() : Bukkit.getOfflinePlayer(args[1]).getName()));
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 51 */     FriendRequest request = null;
/* 52 */     for (FriendRequest request1 : getPlugin().getFriendRequestManager().getFriendRequests(((Player)user).getUniqueId())) {
/* 53 */       if (request1.getSender().equals(potentialFriend))
/* 54 */         request = request1; 
/*    */     } 
/* 56 */     if (request == null) {
/* 57 */       for (String line : getPlugin().getConfig().getStringList("messages.no-request")) {
/* 58 */         getPlugin().getMessageUtil().message(user, line.replace("%0%", potentialFriend.isOnline() ? Bukkit.getPlayer(args[1]).getDisplayName() : potentialFriend.getName()));
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 63 */     request.accept(getPlugin());
/*    */     
/* 65 */     for (String line : getPlugin().getConfig().getStringList("messages.request-accepted"))
/* 66 */       getPlugin().getMessageUtil().message(user, line.replace("%0%", potentialFriend.isOnline() ? Bukkit.getPlayer(args[1]).getDisplayName() : potentialFriend.getName())); 
/*    */   }
/*    */ }