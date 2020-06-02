package com.cy.ruoyi.common.utils.util;

import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段校驗
 */
@Log4j2
public class RegexUtil {

   public final static boolean isNull(Object[] objs){
      if(objs==null||objs.length==0){
          return true;
      }
      return false;
   }

    public final static boolean isNull(Object obj){
       return obj == null;
    }

   public final static boolean isNull(Integer integer){
      if(integer==null||integer==0){
          return true;
      }
      return false;
   }
   public final static boolean isNull(Collection collection){
      if (collection==null||collection.size()==0){
          return true;
      }
      return false;
   }
   public final static boolean isNull(Map map){
      if (map==null||map.size()==0){
          return true;
      }
      return false;
   }
   public final static boolean isNull(String str){
      return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase());
   }
   public final static boolean isNull(Long longs){
      if (longs==null||longs==0){
          return true;
      }
      return false;
   }
   public final static boolean isNotNull(Long longs){
      return !isNull(longs);
   }
   public final static boolean isNotNull(String str){
      return !isNull(str);
   }
   public final static boolean isNotNull(Collection collection){
      return !isNull(collection);
   }
   public final static boolean isNotNull(Map map){
      return !isNull(map);
   }
   public final static boolean isNotNull(Integer integer){
      return !isNull(integer);
   }
   public final static boolean isNotNull(Object[] objs){
      return !isNull(objs);
   }
    public final static boolean isNotNull(Object obj){
        return !isNull(obj);
    }
   /**
    * 匹配URL地址
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isUrl(String str) {
      return match(str, "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
   }
   /**
    * 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isPwd(String str) {
      return match(str, "^[a-zA-Z]\\w{6,12}$");
   }
   /**
    * 验证字符，只能包含中文、英文、数字、下划线等字符。
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean stringCheck(String str) {
      return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$");
   }
   /**
    * 匹配Email地址
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isEmail(String str) {
      return match(str, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
   }
   /**
    * 匹配非负整数（正整数+0）
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isInteger(String str) {
      return match(str, "^[+]?\\d+$");
   }
   /**
    * 判断数值类型，包括整数和浮点数
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isNumeric(String str) {
      if(isFloat(str) || isInteger(str)){
          return true;
      }
      return false;
   }
   /**
    * 只能输入数字
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isDigits(String str) {
      return match(str, "^[0-9]*$");
   }
   /**
    * 匹配正浮点数
    * 
    * @param str
    * @return
    * @author jiqinlin
    */
   public final static boolean isFloat(String str) {
      return match(str, "^[-\\+]?\\d+(\\.\\d+)?$");
   }
   /**
    * 联系电话(手机/电话皆可)验证
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isTel(String text){
      if (isMobile(text)||isPhone(text)){
          return true;
      }
      return false;
   }
   /**
    * 电话号码验证  
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isPhone(String text){
      return match(text, "^(\\d{3,4}-?)?\\d{7,9}$");
   }
   /**
    * 手机号码验证
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isMobile(String text){
      if (text.length()!=11){
          return false;
      }
      return match(text, "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");
   }

   /**
    * 身份证号码验证 
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isIdCardNo(String text){
      return match(text, "^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");
   }
   /**
    * 邮政编码验证 
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isZipCode(String text){
      return match(text, "^[0-9]{6}$");
   }
   /**
    * 判断整数num是否等于0
    * 
    * @param num
    * @return
    * @author jiqinlin
    */
   public final static boolean isIntEqZero(int num){
      return num==0;
   }
   /**
    * 判断整数num是否大于0
    * 
    * @param num
    * @return
    * @author jiqinlin
    */
   public final static boolean isIntGtZero(int num){return num>0;
   }
   /**
    * 判断整数num是否大于或等于0
    * 
    * @param num
    * @return
    * @author jiqinlin
    */
   public final static boolean isIntGteZero(int num){return num>=0;
   }
   /**
    * 判断浮点数num是否等于0
    * 
    * @param num 浮点数
    * @return
    * @author jiqinlin
    */
   public final static boolean isFloatEqZero(float num){return num==0f;
   }
   /**
    * 判断浮点数num是否大于0
    * 
    * @param num 浮点数
    * @return
    * @author jiqinlin
    */
   public final static boolean isFloatGtZero(float num){
       return num>0f;
   }
   /**
    * 判断浮点数num是否大于或等于0
    * 
    * @param num 浮点数
    * @return
    * @author jiqinlin
    */
   public final static boolean isFloatGteZero(float num){
      return num>=0f;
   }
   /**
    * 判断是否为合法字符(a-zA-Z0-9-_)
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isRightfulString(String text){
      return match(text, "^[A-Za-z0-9_-]+$");
   }
   /**
    * 判断英文字符(a-zA-Z)
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isEnglish(String text){
      return match(text, "^[A-Za-z]+$");
   }
   /**
    * 判断中文字符(包括汉字和符号)
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isChineseChar(String text){
      return match(text, "^[\u0391-\uFFE5]+$");
   }
   /**
    * 匹配汉字
    * 
    * @param text
    * @return
    * @author jiqinlin
    */
   public final static boolean isChinese(String text){
      return match(text, "^[\u4e00-\u9fa5]+$");
   }
   /**
    * 是否包含中英文特殊字符，除英文"-_"字符外
    * 
    * @param str
    * @return
    */
   public static boolean isContainsSpecialChar(String text) {
      if (StringUtils.isBlank(text)){
          return false;
      }
      String[] chars={"[","`","~","!","@","#","$","%","^","&","*","(",")","+","=","|","{","}","'",
            ":",";","'",",","[","]",".","<",">","/","?","~","！","@","#","￥","%","…","&","*","（","）",
            "—","+","|","{","}","【","】","‘","；","：","”","“","’","。","，","、","？","]"};
      for(String ch : chars){
         if (text.contains(ch)){
             return true;
         }
      }
      return false;
   }
   /**
    * 过滤中英文特殊字符，除英文"-_"字符外
    * 
    * @param text
    * @return
    */
   public static String stringFilter(String text) {
      String regExpr="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
      Pattern p = Pattern.compile(regExpr);
      Matcher m = p.matcher(text);
      return m.replaceAll("").trim();
   }
   /**
    * 过滤html代码
    * 
    * @param inputString 含html标签的字符串
    * @return
    */
   public static String htmlFilter(String inputString) {
      // 含html标签的字符串
      String htmlStr = inputString;
      String textStr = "";
      Pattern pScript;
      Matcher mScript;
      Pattern pStyle;
      Matcher mStyle;
      Pattern pHtml;
      Matcher mHtml;
      Pattern pba;
      Matcher mba;

      try {
         // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
         String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

         // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
         String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

         // 定义HTML标签的正则表达式
         String regExHtml = "<[^>]+>";
         String patternStr = "\\s+";

         pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
         mScript = pScript.matcher(htmlStr);
         // 过滤script标签
         htmlStr = mScript.replaceAll("");

         pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
         mStyle = pStyle.matcher(htmlStr);
         // 过滤style标签
         htmlStr = mStyle.replaceAll("");

         pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
         mHtml = pHtml.matcher(htmlStr);
         // 过滤html标签
         htmlStr = mHtml.replaceAll("");

         pba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
         mba = pba.matcher(htmlStr);
         // 过滤空格
         htmlStr = mba.replaceAll("");

         textStr = htmlStr;

      } catch (Exception e) {
          e.printStackTrace();
          log.error("Html2Text: " + e.getMessage());
      }
      // 返回文本字符串
      return textStr;
   }
   /**
    * 正则表达式匹配
    * 
    * @param text 待匹配的文本
    * @param reg 正则表达式
    * @return
    * @author jiqinlin
    */
   private final static boolean match(String text, String reg) {
      if (StringUtils.isBlank(text) || StringUtils.isBlank(reg)){
          return false;
      }
      return Pattern.compile(reg).matcher(text).matches();
   }

   /**
    * 校验参数，非空判断
    * @param param
    * @return
    */
   public static boolean validateParams(Object...param){
      for(Object paramsObj :param){

         if(null == paramsObj){
            return false;
         }
         if(paramsObj instanceof String && StringUtils.isEmpty(String.valueOf(paramsObj))){
            return false;
         }
      }
      return true;
   }

    /**
     * 单个param值校验
     * @param param
     * @return
     */
    public static boolean validateParam(Object param){
        if(null == param){
            return false;
        }
        if(param instanceof String && StringUtils.isEmpty(String.valueOf(param))){
            return false;
        }
        return true;
    }
    /**
     * 将param 转为String字符串,以逗号分隔
     * @param param
     * @return
     */
    public static String paramsToString(String[] param) {
       if (param == null || param.length == 0){
           return "";
       }
       StringBuilder sb = new StringBuilder();
       for (String value: param) {
           sb.append(",");
           sb.append(value);
       }
       return sb.toString();
    }
    /**
     * 将param 转为String字符串,并以自定义符号拼接
     * @param param String数组
     * @param key 自定义字符
     * @return
     */
    public static String paramsToString(String key, String... param) {
        if (param == null || param.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String value: param) {
            sb.append(key);
            sb.append(value);
        }
        sb.append(key);
        return sb.toString();
    }
    /**
     * 将param String字符串以自定义符合包围
     * @param param 字符串
     * @param key 用于包含的Key
     * @return
     */
    public static String paramsSurroundString(String key, String param) {
        if (StringUtils.isBlank(param)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append(param);
        sb.append(key);
        return sb.toString();
    }


   /**
    *
    *  附 ： 常用的正则表达式：
    *    匹配特定数字：
    *    ^[1-9]d*$　 　 //匹配正整数
    *    ^-[1-9]d*$ 　 //匹配负整数
    *    ^-?[1-9]d*$　　 //匹配整数
    *    ^[1-9]d*|0$　 //匹配非负整数（正整数 + 0）
    *    ^-[1-9]d*|0$　　 //匹配非正整数（负整数 + 0）
    *    ^[1-9]d*.d*|0.d*[1-9]d*$　　 //匹配正浮点数
    *    ^-([1-9]d*.d*|0.d*[1-9]d*)$　 //匹配负浮点数
    *    ^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$　 //匹配浮点数
    *    ^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$　　 //匹配非负浮点数（正浮点数 + 0）
    *    ^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$　　//匹配非正浮点数（负浮点数 + 0）
    *    评注：处理大量数据时有用，具体应用时注意修正
    *
    *    匹配特定字符串：
    *    ^[A-Za-z]+$　　//匹配由26个英文字母组成的字符串
    *    ^[A-Z]+$　　//匹配由26个英文字母的大写组成的字符串
    *    ^[a-z]+$　　//匹配由26个英文字母的小写组成的字符串
    *    ^[A-Za-z0-9]+$　　//匹配由数字和26个英文字母组成的字符串
    *    ^w+$　　//匹配由数字、26个英文字母或者下划线组成的字符串
    *
    *    在使用RegularExpressionValidator验证控件时的验证功能及其验证表达式介绍如下:
    *
    *    只能输入数字：“^[0-9]*$”
    *    只能输入n位的数字：“^d{n}$”
    *    只能输入至少n位数字：“^d{n,}$”
    *    只能输入m-n位的数字：“^d{m,n}$”
    *    只能输入零和非零开头的数字：“^(0|[1-9][0-9]*)$”
    *    只能输入有两位小数的正实数：“^[0-9]+(.[0-9]{2})?$”
    *    只能输入有1-3位小数的正实数：“^[0-9]+(.[0-9]{1,3})?$”
    *    只能输入非零的正整数：“^+?[1-9][0-9]*$”
    *    只能输入非零的负整数：“^-[1-9][0-9]*$”
    *    只能输入长度为3的字符：“^.{3}$”
    *    只能输入由26个英文字母组成的字符串：“^[A-Za-z]+$”
    *    只能输入由26个大写英文字母组成的字符串：“^[A-Z]+$”
    *    只能输入由26个小写英文字母组成的字符串：“^[a-z]+$”
    *    只能输入由数字和26个英文字母组成的字符串：“^[A-Za-z0-9]+$”
    *    只能输入由数字、26个英文字母或者下划线组成的字符串：“^w+$”
    *    验证用户密码:“^[a-zA-Z]\\w{5,17}$”正确格式为：以字母开头，长度在6-18之间，
    *
    *    只能包含字符、数字和下划线。
    *    验证是否含有^%&’,;=?$”等字符：“[^%&’,;=?$x22]+”
    *    只能输入汉字：“^[u4e00-u9fa5],{0,}$”
    *    验证Email地址：“^w+[-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*$”
    *    验证InternetURL：“^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$”
    *    验证电话号码：“^((d{3,4})|d{3,4}-)?d{7,8}$”
    *
    *    正确格式为：“XXXX-XXXXXXX”，“XXXX-XXXXXXXX”，“XXX-XXXXXXX”，
    *
    *    “XXX-XXXXXXXX”，“XXXXXXX”，“XXXXXXXX”。
    *    验证身份证号（15位或18位数字）：“^d{15}|d{}18$”
    *    验证一年的12个月：“^(0?[1-9]|1[0-2])$”正确格式为：“01”-“09”和“1”“12”
    *    验证一个月的31天：“^((0?[1-9])|((1|2)[0-9])|30|31)$” 正确格式为：“01”“09”和“1”“31”。
    *
    *    匹配中文字符的正则表达式： [u4e00-u9fa5]
    *    匹配双字节字符(包括汉字在内)：[^x00-xff]
    *    匹配空行的正则表达式：n[s| ]*r
    *    匹配HTML标记的正则表达式：/< (.*)>.*|< (.*) />/
    *    匹配首尾空格的正则表达式：(^s*)|(s*$)
    *    匹配Email地址的正则表达式：w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*
    *    匹配网址URL的正则表达式：^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$
    *
    */

}