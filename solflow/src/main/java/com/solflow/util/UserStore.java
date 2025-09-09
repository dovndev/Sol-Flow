package com.solflow.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple persistent user store using JSON + SHA-256 password hashing.
 * Not for production use (no salting, etc.).
 */
public class UserStore {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type MAP_TYPE = new TypeToken<Map<String, String>>() {}.getType();
    private final File storageFile;
    private Map<String, String> users; // username -> passwordHash

    public UserStore() {
        this(new File("users.json"));
    }

    public UserStore(File file) {
        this.storageFile = file;
        load();
    }

    public synchronized boolean createUser(String username, char[] password) {
        if (username == null || username.isBlank()) return false;
        if (users.containsKey(username)) return false;
        users.put(username, hash(password));
        save();
        return true;
    }

    public synchronized boolean validate(String username, char[] password) {
        String stored = users.get(username);
        if (stored == null) return false;
        return stored.equals(hash(password));
    }

    public synchronized boolean exists(String username) {
        return users.containsKey(username);
    }

    public synchronized Map<String, String> snapshot() {
        return Collections.unmodifiableMap(new HashMap<>(users));
    }

    private void load() {
        if (!storageFile.exists()) {
            users = new HashMap<>();
            return;
        }
        try (FileReader r = new FileReader(storageFile, StandardCharsets.UTF_8)) {
            users = GSON.fromJson(r, MAP_TYPE);
            if (users == null) users = new HashMap<>();
        } catch (IOException e) {
            users = new HashMap<>();
        }
    }

    private void save() {
        try (FileWriter w = new FileWriter(storageFile, StandardCharsets.UTF_8)) {
            GSON.toJson(users, w);
        } catch (IOException ignored) {
        }
    }

    private String hash(char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = new String(password).getBytes(StandardCharsets.UTF_8);
            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
