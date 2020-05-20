/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class HelpCmd extends SubCommand {
/*    */   public HelpCmd(HypixelFriends plugin, Permission permission) {
/*  9 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 14 */     if (!validatePermissions(user))
/*    */       return; 
/* 16 */     getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.help"));
/*    */   }
/*    */ }