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

package me.comphack.neptune.bungee.config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.*;

import java.util.HashMap;
import java.util.Map;

public class NeptuneBungeeConfig {

    private final File configFile;
    private Map<String, Object> configData;

    public NeptuneBungeeConfig(String configFileName) {
        this.configFile = new File("plugins/NeptuneBungee/" + configFileName);
        this.configData = new HashMap<>();
        loadConfig();
    }

    public void loadConfig() {
        try {
            if (!configFile.exists()) {
                copyDefaultConfig();
            }

            try (FileReader reader = new FileReader(configFile)) {
                Yaml yaml = new Yaml();
                configData = yaml.load(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(configFile)) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            Yaml yaml = new Yaml(options);
            yaml.dump(configData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyDefaultConfig() {
        try {
            Path pluginDirectory = Paths.get(configFile.getParent());
            if (!Files.exists(pluginDirectory)) {
                Files.createDirectories(pluginDirectory);
            }

            InputStream defaultConfigStream = getClass().getResourceAsStream("/" + configFile.getName());

            if (defaultConfigStream != null) {
                Files.copy(defaultConfigStream, Paths.get(configFile.toURI()));
            } else {
                throw new FileNotFoundException("Default " + configFile.getName() + " not found in resources.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getConfigValue(String key) {
        return configData.get(key);
    }

    public void setConfigValue(String key, Object value) {
        configData.put(key, value);
        saveConfig();
    }
}
