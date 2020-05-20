/*    */ package me.orion.listeners;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ public class JoinE
/*    */   implements Listener {
/*    */   public JoinE(HypixelFriends plugin) {
/* 13 */     this.plugin = plugin;
/*    */   }
/*    */   private HypixelFriends plugin;
/*    */   @EventHandler
/*    */   public void onJoinE(PlayerJoinEvent e) {
/* 18 */     Player user = e.getPlayer();
/*    */     
/* 20 */     this.plugin.getHfUserManager().cacheUser(user);
/*    */     
/* 22 */     if (!this.plugin.getFriendRequestManager().getFriendRequests(user.getUniqueId()).isEmpty())
/* 23 */       for (String line : this.plugin.getConfig().getStringList("messages.pending-requests")) {
/* 24 */         this.plugin.getMessageUtil().message((CommandSender)user, line.replace("%0%", String.valueOf(this.plugin.getFriendRequestManager().getFriendRequests(user.getUniqueId()).size())));
/*    */       } 
/* 26 */     if (!this.plugin.getServer().getOnlinePlayers().isEmpty())
/* 27 */       for (Player onlineUser : this.plugin.getServer().getOnlinePlayers()) {
/* 28 */         if (this.plugin.getHfUserManager().getUser(onlineUser).getFriendList().contains(user))
/* 29 */           this.plugin.getMessageUtil().message((CommandSender)onlineUser, this.plugin.getConfig().getString("messages.join").replace("%0%", user.getName())); 
/*    */       }  
/*    */   }
/*    */ }