package com.mcp.semi.common.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
  
  // 파일명 반환
  public String getFileSystemName(String originalFilename) {
    String extName = null;
    if (originalFilename.endsWith(".tar.gz")) extName = ".tar.gz";
    else extName = originalFilename.substring(originalFilename.lastIndexOf("."));
    return UUID.randomUUID().toString().replace("-", "") + extName;
  }
  
}