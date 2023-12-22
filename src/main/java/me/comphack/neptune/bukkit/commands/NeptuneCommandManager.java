/*
 * Copyright (c) 2023 Vedant Mulay & Neptune Development
 *
 * Licensed under the MIT License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.comphack.neptune.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class NeptuneCommandManager implements CommandExecutor {

    private final Map<String, NeptuneCommand> commands;

    public NeptuneCommandManager() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(String commandName, NeptuneCommand neptuneCommand) {
        commands.put(commandName.toLowerCase(), neptuneCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            // Handle commands without sub-commands
            sender.sendMessage("Usage: /" + label + " <command>");
            return true;
        }

        String subCommand = args[0].toLowerCase();
        if (commands.containsKey(subCommand)) {
            NeptuneCommand neptuneCommand = commands.get(subCommand);

            // Check permissions
            if (neptuneCommand.getPermission() != null && !sender.hasPermission(neptuneCommand.getPermission())) {
                sender.sendMessage("You do not have permission to use this command.");
                return true;
            }

            // Check if the command is player-only
            if (neptuneCommand.isPlayerOnly() && !(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by players.");
                return true;
            }

            // Execute the command
            neptuneCommand.execute();
            return true;
        }

        // Unknown command or sub-command
        sender.sendMessage("Unknown command or sub-command.");
        return false;
    }
}
