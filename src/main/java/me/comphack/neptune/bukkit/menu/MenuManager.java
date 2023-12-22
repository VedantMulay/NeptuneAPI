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

public class MenuManager {

    private final String title;
    private final List<Inventory> pages;
    private int currentPageIndex;

    public MenuManager(String title, List<Inventory> pages) {
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

    // Method to add a simple item to the current page
    public void addItem(MenuItem menuItem) {
        Inventory currentPage = getCurrentPage();
        currentPage.addItem(menuItem.getDisplayItem());
    }

    // Example method to add a page with simple items
    public void addPageWithItems(String pageTitle, List<MenuItem> menuItems) {
        Inventory page = Bukkit.createInventory(null, 9, pageTitle);

        for (MenuItem menuItem : menuItems) {
            page.addItem(menuItem.getDisplayItem());
        }

        pages.add(page);
    }

    // Example method to create a simple menu with items
    //public static MenuManager createSimpleMenu() {
    //    List<MenuItem> menuItems = new ArrayList<>();
    //    menuItems.add(new SimpleMenuItem(Material.APPLE, "Eat Apple", () -> System.out.println("Eating apple")));
    //    menuItems.add(new SimpleMenuItem(Material.DIAMOND_SWORD, "Equip Sword", () -> System.out.println("Equipping sword")));

    //    MenuManager paginatedMenu = new MenuManager("Simple Menu", new ArrayList<>());
    //    paginatedMenu.addPageWithItems("Page 1", menuItems);

    //    return paginatedMenu;
    //}
}
