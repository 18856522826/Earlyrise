package com.example.demo.org.interceptor;

/**
 * @Author : JCccc
 * @CreateTime : 2019/7/30
 * @Description :
 **/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


//屏蔽敏感词初始化


@SuppressWarnings({ "rawtypes", "unchecked" })
public class SensitiveWordInit {
    // 字符编码
    private String ENCODING = "UTF-8";
    // 初始化敏感字库
    public Map initKeyWord() {
// 读取敏感词库 ,存入Set中
        Set<String> wordSet = readSensitiveWordFile();
// 将敏感词库加入到HashMap中//确定有穷自动机DFA
        return addSensitiveWordToHashMap(wordSet);
    }
    // 读取敏感词库 ,存入HashMap中
    private Set<String> readSensitiveWordFile() {


        Set<String> wordSet = null;

//敏感词库
        //File file = new File("/home/jar/earlyrise/ma.txt");//线上
        File file = new File("d:/ma.txt");
        try {
// 读取文件输入流
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), ENCODING);


// 文件是否是文件 和 是否存在
            if (file.isFile() && file.exists()) {


                wordSet = new HashSet<String>();
// StringBuffer sb = new StringBuffer();
// BufferedReader是包装类，先把字符读到缓存里，到缓存满了，再读入内存，提高了读的效率。
                BufferedReader br = new BufferedReader(read);
                String txt = null;


// 读取文件，将文件内容放入到set中
                while ((txt = br.readLine()) != null) {
                    wordSet.add(txt);
                }
                br.close();
                /*
                 * String str = sb.toString(); String[] ss = str.split("，"); for
                 * (String s : ss) { wordSet.add(s); }
                 */
            }
// 关闭文件流
            read.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordSet;
    }
    // 将HashSet中的敏感词,存入HashMap中
    private Map addSensitiveWordToHashMap(Set<String> wordSet) {


// 初始化敏感词容器，减少扩容操作
        Map wordMap = new HashMap(wordSet.size());


        for (String word : wordSet) {
            Map nowMap = wordMap;
            for (int i = 0; i < word.length(); i++) {
// 转换成char型
                char keyChar = word.charAt(i);
// 获取
                Object tempMap = nowMap.get(keyChar);
// 如果存在该key，直接赋值

                if (tempMap != null) {
                    nowMap = (Map) tempMap;
                }
// 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                else {
// 设置标志位
                    Map<String, String> newMap = new HashMap<String, String>();
                    newMap.put("isEnd", "0");
// 添加到集合
                    nowMap.put(keyChar, newMap);
                    nowMap = newMap;
                }
// 最后一个
                if (i == word.length() - 1) {
                    nowMap.put("isEnd", "1");
                }
            }
        }
        return wordMap;
    }
}