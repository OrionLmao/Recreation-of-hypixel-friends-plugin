package me.orion.commands.subcommands;

import org.bukkit.command.CommandSender;

interface ISubCommand {
  void execute(CommandSender paramCommandSender, String... paramVarArgs);
}
