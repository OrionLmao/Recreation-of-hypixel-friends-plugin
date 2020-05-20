/*    */ package me.orion.commands;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import me.orion.hf.HypixelFriends;
/*    */ import me.orion.hf.commands.subcommands.SubCommand;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ abstract class Command
/*    */   implements CommandExecutor, ICommand {
/*    */   private Map<String, SubCommand> subCommands;
/*    */   private HypixelFriends plugin;
/*    */   private CommandSender user;
/*    */   private String[] args;
/*    */   
/*    */   Command(HypixelFriends plugin) {
/* 18 */     this.subCommands = new HashMap<>();
/* 19 */     this.plugin = plugin;
/*    */     
/* 21 */     registerSubCommands();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
/* 26 */     this.user = commandSender;
/* 27 */     this.args = strings;
/*    */     
/* 29 */     execute(this.user, this.args);
/* 30 */     return true;
/*    */   }
/*    */   
/*    */   void addSubCommand(String name, SubCommand subCommand) {
/* 34 */     this.subCommands.put(name, subCommand);
/*    */   }
/*    */   
/*    */   void executeSubCommand(String name) {
/* 38 */     if (!getSubCommands().containsKey(name)) {
/* 39 */       ((SubCommand)getSubCommands().get("add")).execute(this.user, this.args);
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     ((SubCommand)getSubCommands().get(name)).execute(this.user, this.args);
/*    */   }
/*    */   
/*    */   private Map<String, SubCommand> getSubCommands() {
/* 47 */     return this.subCommands;
/*    */   }
/*    */   
/*    */   HypixelFriends getPlugin() {
/* 51 */     return this.plugin;
/*    */   }
/*    */ }