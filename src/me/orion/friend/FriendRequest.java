/*    */ package me.orion.friend;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class FriendRequest {
/*    */   private int expireTime;
/*    */   private UUID requested;
/*    */   private Long sent;
/*    */   private UUID uuid;
/*    */   
/*    */   public FriendRequest(UUID uuid, UUID requested, int expireTime) {
/* 19 */     this.sent = Long.valueOf(System.currentTimeMillis());
/* 20 */     this.expireTime = expireTime;
/* 21 */     this.requested = requested;
/* 22 */     this.uuid = uuid;
/*    */   }
/*    */   
/*    */   public void accept(HypixelFriends plugin) {
/* 26 */     plugin.getFriendRequestManager().removeRequest(getRequested().getUniqueId(), this);
/*    */     
/* 28 */     if (plugin.getStorageUtil().getFile(getSender().getUniqueId().toString()).getBoolean("friends-list-full")) {
/* 29 */       plugin.getMessageUtil().message((CommandSender)getRequested(), plugin.getConfig().getStringList("messages.request-not-allowed"));
/*    */       
/* 31 */       if (getSender().isOnline()) {
/* 32 */         for (String line : plugin.getConfig().getStringList("messages.max-friends")) {
/* 33 */           plugin.getMessageUtil().message((CommandSender)getSender(), line.replace("%0%", String.valueOf(plugin.getHfUserManager().getUser((Player)getSender()).getFriendLimit())));
/*    */         }
/*    */       }
/*    */       return;
/*    */     } 
/* 38 */     plugin.getHfUserManager().getUser((Player)getRequested()).getFriendList().addFriend(new Friend(this.uuid));
/* 39 */     plugin.getHfUserManager().saveUser(getRequested());
/*    */     
/* 41 */     if (getSender().isOnline()) {
/* 42 */       Player user = (Player)getSender();
/* 43 */       for (String line : plugin.getConfig().getStringList("messages.request-accepted")) {
/* 44 */         plugin.getMessageUtil().message((CommandSender)user, line.replace("%0%", ((Player)getRequested()).getDisplayName()));
/*    */       }
/* 46 */       plugin.getHfUserManager().getUser(user).getFriendList().addFriend(new Friend(this.requested));
/* 47 */       plugin.getHfUserManager().saveUser((OfflinePlayer)user);
/*    */       
/*    */       return;
/*    */     } 
/* 51 */     FileConfiguration storage = plugin.getStorageUtil().getFile(this.uuid.toString());
/* 52 */     List<String> friends = storage.getStringList("friends");
/* 53 */     friends.add(this.requested.toString());
/*    */     
/* 55 */     storage.set("friends", friends);
/* 56 */     plugin.getStorageUtil().saveFile(storage, this.uuid.toString());
/*    */   }
/*    */   
/*    */   public void deny(HypixelFriends plugin) {
/* 60 */     plugin.getFriendRequestManager().removeRequest(getRequested().getUniqueId(), this);
/*    */   }
/*    */   
/*    */   public OfflinePlayer getSender() {
/* 64 */     return Bukkit.getOfflinePlayer(this.uuid);
/*    */   }
/*    */   
/*    */   OfflinePlayer getRequested() {
/* 68 */     return Bukkit.getOfflinePlayer(this.requested);
/*    */   }
/*    */   
/*    */   public int getRemainingTime() {
/* 72 */     return (int)((this.expireTime * 60) - System.currentTimeMillis() / 1000L - this.sent.longValue() / 1000L) / 60;
/*    */   }
/*    */   
/*    */   boolean isExpired() {
/* 76 */     return (System.currentTimeMillis() / 1000L - this.sent.longValue() / 1000L >= (this.expireTime * 60));
/*    */   }
/*    */ }