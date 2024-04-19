package com.mcp.semi.common.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

  public static String getSha256(String original) {
    StringBuilder builder = new StringBuilder();
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(original.getBytes());
      byte[] bytes = digest.digest();
      for(int i = 0; i < bytes.length; i++) {
        builder.append(String.format("%02X", bytes[i]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return builder.toString();
  }
  
  
  public static String getPreventXss(String original) {
    return original.replace("<script>", "&lt;script&gt;").replace("</script>", "&lt;/script&gt;");
  }

  
  public static String getRandomString(int count, boolean letter, boolean number) {
    StringBuilder builder = new StringBuilder();
    List <String> list = new ArrayList<String>();
    if(letter) {
      for(char ch = 'A'; ch <= 'Z'; ch++) {
        list.add(ch + "");
      }
      for(char ch = 'a'; ch <= 'z'; ch++) {
        list.add(ch + "");
      }
    }
    if(number) {
      for(int n = 0; n <= 9; n++) {
        list.add(n + "");
      }
    }
    SecureRandom secureRandom = new SecureRandom();
    if(letter || number) {
      while(count > 0) {
        builder.append(list.get(secureRandom.nextInt(list.size())));
        count--;
      }
    }
    return builder.toString();
  }
  
	
}
