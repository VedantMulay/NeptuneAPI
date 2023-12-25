/*
 * MIT License
 *
 * Copyright (c) 2023 Vedant Mulay & Neptune Development
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.vedantmulay.neptuneapi.bukkit.commands;


import io.github.vedantmulay.neptuneapi.bukkit.menu.NeptuneCommand;
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
