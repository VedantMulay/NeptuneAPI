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

package me.comphack.neptune.bukkit.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NeptuneConfig {

    // Path to the config file
    private Path file;

    // Path to the data directory
    private Path dir;

    // The configuration for the config
    private YamlConfiguration yaml;

    public NeptuneConfig(JavaPlugin plugin, String name) {

        // Sets the directories location
        dir = plugin.getDataFolder().toPath();

        // If it doesn't exist, create it, and replace the value
        // with the newly created directory
        if (!Files.exists(dir)) {
            try {
                dir = Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Saves the file from the resources directory to the data directory
        plugin.saveResource(name, false);

        // Creates the file in the data directory
        file = dir.resolve(name);

        // New configuration
        yaml = new YamlConfiguration();

        // Loads the file data into the configuration
        try {
            yaml.load(file.toString());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    // Saves the configuration data
    public void save() {
        try {
            yaml.save(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simple Getter
    public YamlConfiguration getYaml() {
        return yaml;
    }

    public void reload() {
        try {
            yaml.load(file.toString());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    // Gets the path to the data directory
    public Path getDataPath() {
        return dir;
    }
}