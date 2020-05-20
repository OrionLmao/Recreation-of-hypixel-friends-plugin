/*    */ package me.orion.friend;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ public class Friend
/*    */ {
/*    */   private UUID uuid;
/*    */   
/*    */   public Friend(UUID uuid) {
/* 11 */     this.uuid = uuid;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 15 */     return isOnline() ? Bukkit.getPlayer(this.uuid).getDisplayName() : Bukkit.getOfflinePlayer(this.uuid).getName();
/*    */   }
/*    */   
/*    */   public UUID getUniqueId() {
/* 19 */     return this.uuid;
/*    */   }
/*    */   
/*    */   public boolean isOnline() {
/* 23 */     return Bukkit.getOfflinePlayer(this.uuid).isOnline();
/*    */   }
/*    */ }