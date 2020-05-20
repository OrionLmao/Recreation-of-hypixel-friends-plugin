/*    */ package me.orion.listeners;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ 
/*    */ public class QuitE
/*    */   implements Listener {
/*    */   public QuitE(HypixelFriends plugin) {
/* 13 */     this.plugin = plugin;
/*    */   }
/*    */   private HypixelFriends plugin;
/*    */   @EventHandler
/*    */   public void onQuitE(PlayerQuitEvent e) {
/* 18 */     Player user = e.getPlayer();
/*    */     
/* 20 */     this.plugin.getHfUserManager().uncacheUser(user);
/*    */     
/* 22 */     if (!this.plugin.getServer().getOnlinePlayers().isEmpty())
/* 23 */       for (Player onlineUser : this.plugin.getServer().getOnlinePlayers()) {
/* 24 */         if (this.plugin.getHfUserManager().getUser(onlineUser).getFriendList().contains(user))
/* 25 */           this.plugin.getMessageUtil().message((CommandSender)onlineUser, this.plugin.getConfig().getString("messages.quit").replace("%0%", user.getName())); 
/*    */       }  
/*    */   }
/*    */ }\