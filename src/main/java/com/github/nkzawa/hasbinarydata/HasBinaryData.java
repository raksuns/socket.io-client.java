package com.github.nkzawa.hasbinarydata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class HasBinaryData {

    private HasBinaryData() {}

    public static boolean hasBinary(Object data) {
        return recursiveCheckForBinary(data);
    }

    private static boolean recursiveCheckForBinary(Object obj) {
        if (obj == null) return false;

        if (obj instanceof byte[]) {
            return true;
        }

        if (obj instanceof JSONArray) {
            JSONArray _obj = (JSONArray)obj;
            int length = _obj.length();
            for (int i = 0; i < length; i++) {
                if (recursiveCheckForBinary(_obj.get(i))) {
                    return true;
                }
            }
        } else if (obj instanceof JSONObject) {
            JSONObject _obj = (JSONObject)obj;
            Iterator keys = _obj.keys();
            while (keys.hasNext()) {
                String key = (String)keys.next();
                if (recursiveCheckForBinary(_obj.get(key))) {
                    return true;
                }
            }
        }

        return false;
    }
}