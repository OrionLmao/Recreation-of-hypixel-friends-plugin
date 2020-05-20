/*    */ package me.orion.friend;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class FriendRequestManager {
/*    */   private Map<UUID, FriendRequest> friendRequests;
/*    */   
/*    */   public FriendRequestManager(HypixelFriends plugin) {
/* 14 */     this.friendRequests = new HashMap<>();
/* 15 */     this.plugin = plugin;
/*    */     
/* 17 */     startExpiryCheckThread();
/*    */   }
/*    */   private HypixelFriends plugin;
/*    */   private void startExpiryCheckThread() {
/* 21 */     (new BukkitRunnable()
/*    */       {
/*    */         public void run() {
/* 24 */           for (UUID uuid : FriendRequestManager.this.friendRequests.keySet()) {
/* 25 */             if (((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).isExpired()) {
/* 26 */               if (((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getSender().isOnline()) {
/* 27 */                 Player user = (Player)((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getSender();
/* 28 */                 for (String line : FriendRequestManager.this.plugin.getConfig().getStringList("messages.request-expired-sender")) {
/* 29 */                   FriendRequestManager.this.plugin.getMessageUtil().message((CommandSender)user, line.replace("%0%", ((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getRequested().getName()));
/*    */                 }
/*    */               } 
/* 32 */               if (((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getRequested().isOnline()) {
/* 33 */                 Player user = (Player)((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getRequested();
/* 34 */                 for (String line : FriendRequestManager.this.plugin.getConfig().getStringList("messages.request-expired-receiver")) {
/* 35 */                   FriendRequestManager.this.plugin.getMessageUtil().message((CommandSender)user, line.replace("%0%", ((FriendRequest)FriendRequestManager.this.friendRequests.get(uuid)).getSender().getName()));
/*    */                 }
/*    */               } 
/* 38 */               FriendRequestManager.this.removeRequest(uuid, (FriendRequest)FriendRequestManager.this.friendRequests.get(uuid));
/*    */             } 
/*    */           } 
/*    */         }
/* 42 */       }).runTaskTimerAsynchronously((Plugin)this.plugin, 0L, 20L);
/*    */   }
/*    */   
/*    */   public void addRequest(FriendRequest request) {
/* 46 */     this.friendRequests.put(request.getRequested().getUniqueId(), request);
/*    */   }
/*    */   
/*    */   void removeRequest(UUID uuid, FriendRequest request) {
/* 50 */     this.friendRequests.remove(uuid, request);
/*    */   }
/*    */   
/*    */   public List<FriendRequest> getFriendRequests(UUID uuid) {
/* 54 */     List<FriendRequest> requests = new ArrayList<>();
/*    */     
/* 56 */     if (this.friendRequests.isEmpty()) {
/* 57 */       return requests;
/*    */     }
/* 59 */     for (UUID uuid1 : this.friendRequests.keySet()) {
/* 60 */       if (uuid1.equals(uuid))
/* 61 */         requests.add(this.friendRequests.get(uuid1)); 
/*    */     } 
/* 63 */     return requests;
/*    */   }
/*    */ }