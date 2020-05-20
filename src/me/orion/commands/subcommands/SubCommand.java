/*    */ package me.orion.commands.subcommands;
/*    */ 
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public abstract class SubCommand implements ISubCommand {
/*    */   private HypixelFriends plugin;
/*    */   private Permission permission;
/*    */   
/*    */   public SubCommand(HypixelFriends plugin, Permission permission) {
/* 12 */     this.permission = permission;
/* 13 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   boolean validatePermissions(CommandSender user) {
/* 17 */     if (!user.hasPermission(getPermission())) {
/* 18 */       this.plugin.getMessageUtil().message(user, this.plugin.getConfig().getString("messages.no-permission"));
/* 19 */       return false;
/*    */     } 
/*    */     
/* 22 */     return true;
/*    */   }
/*    */   
/*    */   private Permission getPermission() {
/* 26 */     return this.permission;
/*    */   }
/*    */   
/*    */   HypixelFriends getPlugin() {
/* 30 */     return this.plugin;
/*    */   }
/*    */ }