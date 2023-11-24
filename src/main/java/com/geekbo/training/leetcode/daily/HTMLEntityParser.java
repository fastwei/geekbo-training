package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

class HTMLEntityParser {
    /**
     * 算法的时间复杂度是O(n)，其中n是输入字符串的长度。由于Java中字符串是不可变类型，字符串的连接和替换操作都需要创建新的字符串对象，因此时间复杂度是线性的。
     */
    public String entityParser(String text) {
        Map<String, String> entities = new HashMap<>();
        entities.put("&quot;", "\"");
        entities.put("&apos;", "'");
        entities.put("&amp;", "&");
        entities.put("&gt;", ">");
        entities.put("&lt;", "<");
        entities.put("&frasl;", "/");

        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) == '&') {
                boolean found = false;
                for (String entity : entities.keySet()) {
                    if (text.startsWith(entity, i)) {
                        result.append(entities.get(entity));
                        i += entity.length();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.append(text.charAt(i));
                    i++;
                }
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }

        return result.toString();
    }
}