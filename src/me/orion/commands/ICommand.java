package me.orion.commands;

import org.bukkit.command.CommandSender;

interface ICommand {
  void registerSubCommands();
  
  void execute(CommandSender paramCommandSender, String... paramVarArgs);
}