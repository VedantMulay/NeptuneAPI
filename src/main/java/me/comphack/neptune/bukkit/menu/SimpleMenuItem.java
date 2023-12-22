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

package me.comphack.neptune.bukkit.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SimpleMenuItem implements MenuItem {

    private final ItemStack displayItem;
    private final Runnable onClickAction;

    public SimpleMenuItem(Material material, String displayName, Runnable onClickAction) {
        this.displayItem = new ItemStack(material);
        this.displayItem.getItemMeta().setDisplayName(displayName);
        this.onClickAction = onClickAction;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }

    @Override
    public void onClick(Player player) {
        onClickAction.run();
    }
}
