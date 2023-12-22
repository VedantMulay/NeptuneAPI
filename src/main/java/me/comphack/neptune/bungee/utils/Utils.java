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

package me.comphack.neptune.bungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {

    public void globalAnnounce(String message) {
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        }
    }

}
