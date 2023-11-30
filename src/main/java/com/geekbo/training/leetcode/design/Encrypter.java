package com.geekbo.training.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 2227. Encrypt and Decrypt Strings
 Hard
 You are given a character array keys containing unique characters and a string array values containing strings of length 2. You are also given another string array dictionary that contains all permitted original strings after decryption. You should implement a data structure that can encrypt or decrypt a 0-indexed string.

 A string is encrypted with the following process:

 For each character c in the string, we find the index i satisfying keys[i] == c in keys.
 Replace c with values[i] in the string.
 Note that in case a character of the string is not present in keys, the encryption process cannot be carried out, and an empty string "" is returned.

 A string is decrypted with the following process:

 For each substring s of length 2 occurring at an even index in the string, we find an i such that values[i] == s. If there are multiple valid i, we choose any one of them. This means a string could have multiple possible strings it can decrypt to.
 Replace s with keys[i] in the string.
 Implement the Encrypter class:

 Encrypter(char[] keys, String[] values, String[] dictionary) Initializes the Encrypter class with keys, values, and dictionary.
 String encrypt(String word1) Encrypts word1 with the encryption process described above and returns the encrypted string.
 int decrypt(String word2) Returns the number of possible strings word2 could decrypt to that also appear in dictionary.


 Example 1:

 Input
 ["Encrypter", "encrypt", "decrypt"]
 [[['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]], ["abcd"], ["eizfeiam"]]
 Output
 [null, "eizfeiam", 2]

 Explanation
 Encrypter encrypter = new Encrypter([['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]);
 encrypter.encrypt("abcd"); // return "eizfeiam".
 // 'a' maps to "ei", 'b' maps to "zf", 'c' maps to "ei", and 'd' maps to "am".
 encrypter.decrypt("eizfeiam"); // return 2.
 // "ei" can map to 'a' or 'c', "zf" maps to 'b', and "am" maps to 'd'.
 // Thus, the possible strings after decryption are "abad", "cbad", "abcd", and "cbcd".
 // 2 of those strings, "abad" and "abcd", appear in dictionary, so the answer is 2.
 *
 */
class Encrypter {
    class TrieNode{
        int count;
        TrieNode[] letter;
        public TrieNode(){
            letter=new TrieNode[26];
            count=0;
        }

        public void add(String s){
            TrieNode p=this;
            for(int i=0;i<s.length();i++){
                char ch=s.charAt(i);
                if(p.letter[ch-97]==null){
                    p.letter[ch-97]=new TrieNode();
                }
                p=p.letter[ch-97];
            }
            p.count++;
        }

    }
    Map<Character,String> charMap;
    Map<String, Set<Character>> valueMap;
    TrieNode root;
    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        charMap=new HashMap<>();
        valueMap=new HashMap<>();
        root=new TrieNode();
        for(int i=0;i<keys.length;i++){
            charMap.put(keys[i],values[i]);
            valueMap.computeIfAbsent(values[i],key->new HashSet<>()).add(keys[i]);
        }
        for(String word:dictionary){
            String encrypted=encrypt(word);
            if(null!=encrypted)
                root.add(encrypted);
        }
    }
    public String encrypt(String word1) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<word1.length();i++){
            if(charMap.get(word1.charAt(i))==null)
                return null;
            sb.append(charMap.get(word1.charAt(i)));
        }
        return sb.toString();
    }
    public int decrypt(String word2) {
        TrieNode p=root;
        for(int i=0;i<word2.length();i++){
            p=p.letter[word2.charAt(i)-97];
            if(p==null)
                return 0;
        }
        return p.count;
    }

    public static void main(String[] args) {
        // Test case
        char[] keys = {'a', 'b', 'c', 'd'};
        String[] values = {"ei", "zf", "ei", "am"};
        String[] dictionary = {"abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"};

        Encrypter encrypter = new Encrypter(keys, values, dictionary);

        String encryptedWord = encrypter.encrypt("abcd");
        System.out.println("Encrypted word: " + encryptedWord); // Expected output: "eizfeiam"

        int possibleDecryptions = encrypter.decrypt("eizfeiam");
        System.out.println("Possible decryptions: " + possibleDecryptions); // Expected output: 2
    }
}