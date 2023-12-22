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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class NeptuneMenu {

    private final String title;
    private final List<Inventory> pages;
    private int currentPageIndex;

    public NeptuneMenu(String title, List<Inventory> pages) {
        this.title = title;
        this.pages = pages;
        this.currentPageIndex = 0;
    }

    public void nextPage() {
        if (currentPageIndex < pages.size() - 1) {
            currentPageIndex++;
        }
    }

    public void previousPage() {
        if (currentPageIndex > 0) {
            currentPageIndex--;
        }
    }

    public Inventory getCurrentPage() {
        return pages.get(currentPageIndex);
    }

    public String getTitle() {
        return title;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public int getTotalPages() {
        return pages.size();
    }

    public void open(Player player) {
        player.openInventory(getCurrentPage());
    }
}
