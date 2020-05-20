/*    */ package me.orion.utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import me.orion.HypixelFriends;
/*    */ import me.orion.friend.Friend;
/*    */ import me.orion.friend.FriendList;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class HFUserManager {
/*    */   private Map<UUID, HFUser> users;
/*    */   
/*    */   public HFUserManager(HypixelFriends plugin) {
/* 17 */     this.plugin = plugin;
/* 18 */     init();
/*    */   }
/*    */   private HypixelFriends plugin;
/*    */   private void init() {
/* 22 */     this.users = new HashMap<>();
/*    */     
/* 24 */     if (!this.plugin.getServer().getOnlinePlayers().isEmpty())
/* 25 */       for (Player user : this.plugin.getServer().getOnlinePlayers())
/* 26 */         cacheUser(user);  
/*    */   }
/*    */   
/*    */   public void saveUser(OfflinePlayer user) {
/* 30 */     FileConfiguration storage = this.plugin.getStorageUtil().getFile(user.getUniqueId().toString());
/* 31 */     List<String> friends = new ArrayList<>();
/*    */     
/* 33 */     for (Friend friend : getUser((Player)user).getFriendList().getAllFriends()) {
/* 34 */       friends.add(friend.getUniqueId().toString());
/*    */     }
/* 36 */     storage.set("preferences.allow-friend-requests", Boolean.valueOf(getUser((Player)user).isAllowingRequests()));
/* 37 */     storage.set("friends", friends);
/*    */     
/* 39 */     this.plugin.getStorageUtil().saveFile(storage, user.getUniqueId().toString());
/*    */   }
/*    */   
/*    */   public void cacheUser(Player user) {
/* 43 */     if (!this.plugin.getStorageUtil().fileExists(user.getUniqueId().toString())) {
/* 44 */       this.plugin.getStorageUtil().createFile(user.getUniqueId().toString());
/*    */       
/* 46 */       FileConfiguration fileConfiguration = this.plugin.getStorageUtil().getFile(user.getUniqueId().toString());
/*    */       
/* 48 */       fileConfiguration.set("preferences.allow-friend-requests", Boolean.valueOf(true));
/* 49 */       fileConfiguration.set("friends-list-full", Boolean.valueOf(false));
/* 50 */       fileConfiguration.set("friends", new ArrayList());
/*    */       
/* 52 */       this.plugin.getStorageUtil().saveFile(fileConfiguration, user.getUniqueId().toString());
/*    */     } 
/*    */     
/* 55 */     FileConfiguration config = this.plugin.getStorageUtil().getFile(user.getUniqueId().toString());
/* 56 */     List<Friend> friendsList = new ArrayList<>();
/* 57 */     int friendLimit = this.plugin.getConfig().getInt("options.max-friends.default");
/*    */     
/* 59 */     if (friendsList.size() > friendLimit) {
/* 60 */       config.set("friends-list-full", Boolean.valueOf(true));
/*    */     }
/* 62 */     for (String uuid : config.getStringList("friends")) {
/* 63 */       friendsList.add(new Friend(UUID.fromString(uuid)));
/*    */     }
/* 65 */     FriendList friendList = new FriendList(friendsList);
/*    */     
/* 67 */     boolean allowingRequests = config.getBoolean("preferences.allow-friend-requests");
/*    */     
/* 69 */     for (String perm : this.plugin.getConfig().getConfigurationSection("options.max-friends").getKeys(false)) {
/* 70 */       if (user.hasPermission("hypixelfriends.limit." + perm))
/* 71 */         friendLimit = this.plugin.getConfig().getInt("options.max-friends." + perm); 
/*    */     } 
/* 73 */     this.users.put(user.getUniqueId(), new HFUser(friendList, allowingRequests, friendLimit));
/*    */   }
/*    */   
/*    */   public void uncacheUser(Player user) {
/* 77 */     saveUser((OfflinePlayer)user);
/*    */     
/* 79 */     this.users.remove(user.getUniqueId());
/*    */   }
/*    */   
/*    */   public HFUser getUser(Player user) {
/* 83 */     return this.users.get(user.getUniqueId());
/*    */   }
/*    */ }