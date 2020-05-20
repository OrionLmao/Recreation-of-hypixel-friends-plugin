/*    */ package me.orion.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class MessageUtil {
/*    */   public void message(CommandSender user, String message) {
/* 12 */     user.sendMessage(color(message));
/*    */   }
/*    */   
/*    */   public void message(Player user, TextComponent message) {
/* 16 */     user.spigot().sendMessage((BaseComponent)message);
/*    */   }
/*    */   
/*    */   public void message(CommandSender user, List<String> message) {
/* 20 */     for (String line : message)
/* 21 */       message(user, line); 
/*    */   }
/*    */   
/*    */   private String color(String message) {
/* 25 */     return ChatColor.translateAlternateColorCodes('&', message);
/*    */   }
/*    */ }