package com.geekbo.training.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 535. Encode and Decode TinyURL
 * Medium
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL
 * such as https://leetcode.com/problems/design-tinyurl and it returns a short URL
 * such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 * <p>
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL
 * and the tiny URL can be decoded to the original URL.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 * <p>
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after decoding it.
 */
public class EncodeAndDecodeTinyURL {
    private Map<String, String> urlMap;
    private final String BASE_URL = "http://tinyurl.com/";
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int SHORT_URL_LENGTH = 6;

    public EncodeAndDecodeTinyURL() {
        urlMap = new HashMap<>();
    }

    // Encodes a URL to a shortened URL
    public String encode(String longUrl) {
        String shortUrl = generateShortUrl();
        urlMap.put(shortUrl, longUrl);
        return BASE_URL + shortUrl;
    }

    // Decodes a shortened URL to its original URL
    public String decode(String shortUrl) {
        String shortKey = shortUrl.substring(BASE_URL.length());
        return urlMap.get(shortKey);
    }

    // Generates a random short URL
    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EncodeAndDecodeTinyURL encodeAndDecodeTinyURL = new EncodeAndDecodeTinyURL();

        // Test case 1
        String longUrl1 = "https://leetcode.com/problems/design-tinyurl";
        String encodedUrl1 = encodeAndDecodeTinyURL.encode(longUrl1);
        String decodedUrl1 = encodeAndDecodeTinyURL.decode(encodedUrl1);

        System.out.println("Long URL: " + longUrl1);
        System.out.println("Encoded URL: " + encodedUrl1);
        System.out.println("Decoded URL: " + decodedUrl1);
    }
}
