package com.neo.fastdfs;

import org.csource.common.IniFileReader;
import org.csource.common.MyException;
import org.csource.fastdfs.TrackerGroup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @program: springbootleaning
 * @description: ClientGlobel
 * @author: YePengFei
 * @create: 2020-09-21 18:19
 **/
public class ClientGlobals {
    public static int g_connect_timeout = 5000;
    public static int g_network_timeout = 30000;
    public static String g_charset = "UTF-8";
    public static boolean g_anti_steal_token = false;
    public static String g_secret_key = "FastDFS1234567890";
    public static int g_tracker_http_port = 80;
    public static TrackerGroup g_tracker_group;

    public static int getG_connect_timeout() {
        return g_connect_timeout;
    }

    public static void setG_connect_timeout(int g_connect_timeout) {
        ClientGlobals.g_connect_timeout = g_connect_timeout;
    }

    public static int getG_network_timeout() {
        return g_network_timeout;
    }

    public static void setG_network_timeout(int g_network_timeout) {
        ClientGlobals.g_network_timeout = g_network_timeout;
    }

    public static String getG_charset() {
        return g_charset;
    }

    public static void setG_charset(String g_charset) {
        ClientGlobals.g_charset = g_charset;
    }

    public static boolean isG_anti_steal_token() {
        return g_anti_steal_token;
    }

    public static void setG_anti_steal_token(boolean g_anti_steal_token) {
        ClientGlobals.g_anti_steal_token = g_anti_steal_token;
    }

    public static String getG_secret_key() {
        return g_secret_key;
    }

    public static void setG_secret_key(String g_secret_key) {
        ClientGlobals.g_secret_key = g_secret_key;
    }

    public static int getG_tracker_http_port() {
        return g_tracker_http_port;
    }

    public static void setG_tracker_http_port(int g_tracker_http_port) {
        ClientGlobals.g_tracker_http_port = g_tracker_http_port;
    }

    public static TrackerGroup getG_tracker_group() {
        return g_tracker_group;
    }

    public static void setG_tracker_group(TrackerGroup g_tracker_group) {
        ClientGlobals.g_tracker_group = g_tracker_group;
    }

    public static void init(String conf_filename) throws IOException, MyException {
        IniFileReader iniReader = new IniFileReader(conf_filename);
        System.out.println("******************************************************");
        System.out.println(iniReader);
        System.out.println("******************************************************");
        g_connect_timeout = getIntValue("connect_timeout", 5);
        if (g_connect_timeout < 0) {
            g_connect_timeout = 5;
        }

        g_connect_timeout *= 1000;
        g_network_timeout = iniReader.getIntValue("network_timeout", 30);
        if (g_network_timeout < 0) {
            g_network_timeout = 30;
        }

        g_network_timeout *= 1000;
        g_charset = iniReader.getStrValue("charset");
        if (g_charset == null || g_charset.length() == 0) {
            g_charset = "ISO8859-1";
        }

        String[] szTrackerServers = iniReader.getValues("tracker_server");
        if (szTrackerServers == null) {
            throw new MyException("item \"tracker_server\" in " + conf_filename + " not found");
        } else {
            InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];

            for(int i = 0; i < szTrackerServers.length; ++i) {
                String[] parts = szTrackerServers[i].split("\\:", 2);
                if (parts.length != 2) {
                    throw new MyException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
                }

                tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            }

            g_tracker_group = new TrackerGroup(tracker_servers);
            g_tracker_http_port = iniReader.getIntValue("http.tracker_http_port", 80);
            g_anti_steal_token = iniReader.getBoolValue("http.anti_steal_token", false);
            if (g_anti_steal_token) {
                g_secret_key = iniReader.getStrValue("http.secret_key");
            }

        }
    }

    public static int getIntValue(String name, int default_value) {
        String szValue = getStrValue(name);
        return szValue == null ? default_value : Integer.parseInt(szValue);
    }


    public static String getStrValue(String name) {
        Hashtable paramTable = null;
        Object obj = paramTable.get(name);
        if (obj == null) {
            return null;
        } else {
            return obj instanceof String ? (String)obj : (String)((ArrayList)obj).get(0);
        }
    }
}
