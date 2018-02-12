package org.grakovne.mds.publisher.configuraton;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Configuration {

    private Configurations configs;
    private PropertiesConfiguration properties;

    private String remixId;
    private String wallPostHash;
    private String publicId;
    private String uploadUrl;

    public Configuration() {

        configs = new Configurations();

        try {
            properties = configs.properties(new File("config.properties"));
        } catch (ConfigurationException cex) {
            throw new RuntimeException("Can't parse configuration file");
        }

        initConfig();
    }

    private void initConfig() {
        remixId = properties.getString("remix_id");
        wallPostHash = properties.getString("wall_hash");
        publicId = properties.getString("public_id");
        uploadUrl = properties.getString("upload_url");
    }

    public String getRemixId() {
        return remixId;
    }

    public String getWallPostHash() {
        return wallPostHash;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }
}
