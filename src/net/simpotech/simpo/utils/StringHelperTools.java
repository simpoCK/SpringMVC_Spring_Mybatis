package net.simpotech.simpo.utils;

import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 功能、用途、现存BUG: 帮助实现一些通用的字符串处理
 *
 * @author
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringHelperTools {

    /**
     * 获取当前UUID，用做数据关联 新增表结构请使用此ID替换seq
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = (uuid.toString()).replaceAll("-", "");
        return uuidStr;
    }

    /**
     * null值转换
     *
     * @param args
     * @return 返回转换后数组
     */
    public static Object[] nullToString(Object args[]) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null || "null".equals(args[i])) {
                args[i] = "";
            }
        }
        return args;
    }

    /**
     * null值转换
     *
     * @param args
     * @return 返回转换后数组
     */
    public static Object[] STringnullToString(Object args[]) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                args[i] = "";
            }
        }
        return args;
    }

    /**
     * NULL字符串转换，参数对象为null时，返回空字符串
     *
     * @param o 转换原对象
     * @return 字符串
     */
    public static String nvl(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    /**
     * NULL字符串转换，参数对象为null时，返回空值为0的BigDecimal对象
     *
     * @param o 转换原对象
     * @return 字符串
     */
    public static BigDecimal nvlToBigDecimal(Object o) {
        if (o == null) {
            return new BigDecimal(0);
        } else {
            return (BigDecimal) o;
        }
    }

    /**
     * NULL字符串转换，参数对象为null时，返回空字符串 将" " 替换为 "&nbsp;" 用于页面显示多个空格
     *
     * @param o 转换原对象
     * @return 字符串
     */
    public static String nvlShow(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim().replaceAll(" ", "&nbsp;");
    }

    /**
     * NULL字符串转换，参数对象为null时，返回默认值
     *
     * @param o   转换原对象
     * @param res 默认值
     * @return 字符串
     */
    public static String nvl(Object o, String res) {
        if (o == null) {
            return res;
        }
        return o.toString().trim();
    }

    /**
     * NULL或空字符串转换，参数对象为null或空时，返回默认值
     *
     * @param o   转换原对象
     * @param res 默认值
     * @return 字符串
     */
    public static String nvlHtml(Object o, String res) {
        if (o == null || o.toString().trim().equals("")) {
            return res;
        }
        return o.toString().trim();
    }

    /**
     * 字符串替换。如果不需要用正则表达式请用此方法； 否则用String.replaceAll(String regex, String replacement)
     *
     * @param text 需要被处理的字符串
     * @param from 需要被替换掉的字符串
     * @param to   被替换成的字符串
     * @return 被替换后的字符串
     * @see String#replaceAll(String, String)
     */
    public static String replace(String text, String from, String to) {
        if (text == null || from == null || to == null) {
            return null;
        }
        StringBuffer newText = new StringBuffer(text.length() * 2);
        int pos1 = 0;
        int pos2 = text.indexOf(from);
        while (pos2 >= 0) {
            newText.append(text.substring(pos1, pos2) + to);
            pos1 = pos2 + from.length();
            pos2 = text.indexOf(from, pos1);
        }
        newText.append(text.substring(pos1, text.length()));
        return newText.toString();
    }

    /**
     * 替换回车为br
     *
     * @param text 原文本
     * @return 替换后文本
     */
    public static String replaceLineFeedCode(String text) {
        return replace(text, "\n", "<br>\n");
    }

    /**
     * 替换\t为4个html空格
     *
     * @param text 原文本
     * @return 替换后文本
     */
    public static String replaceTab24Space(String text) {
        return replace(text, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
    }

    /**
     * 对html标签或特殊字符串编码
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String replaceQuotes(String html) {

        html = replace(html, "\"", "&quot;");

        return html;
    }

    /**
     * 对html标签或特殊字符串编码
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String replaceAllQuotes(String html) {

        html = replace(html, "\"", "&quot;");
        html = replace(html, "\'", "&apos;");

        return html;
    }

    /**
     * 对html中js中引用的字符（比如alert中）转换 单引号
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String replaceJsApos(String html) {

        html = replace(html, "'", "\\'");

        return html;
    }

    /**
     * 对html中js中引用的字符（比如alert中）转换 单引号
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String replaceJsQuote(String html) {

        html = replace(html, "\"", "\\\"");

        return html;
    }

    /**
     * 检查是否为数字。可以包含小数点，但是小数点个数不能多于一个； 可以包含负号，但是不能只有负号而没有其他数字； 不允许包含逗号
     *
     * @param s 被检查的字符串
     * @return true 表示是数字, false 表示不是数字
     */
    public static boolean isNumber(String s) {
        boolean pointfirsttime = true;

        int i = 0;
        if (s == null || s.length() < 1) {
            return false;
        }

        boolean negative = false;

        if (s.charAt(0) == '-') {
            i++;
            negative = true;
        }

        while (i < s.length()) {
            if (!Character.isDigit(s.charAt(i))) {
                if ('.' == s.charAt(i) && pointfirsttime) {
                    pointfirsttime = false;
                } else {
                    return false;
                }
            }
            i++;
        }

        if (negative && (i == 1)) {
            return false;
        }

        return true;
    }

    /**
     * 检查是否为整数。可以为负整数，但是不能只有负号而没有其他数字
     *
     * @param s 被检查的字符串
     * @return true 表示是整数, false 表示不是整数
     */
    public static boolean isInteger(String s) {
        int i = 0;
        if (s == null || s.trim().length() < 1) {
            return false;
        }

        boolean negative = false;

        if (s.charAt(0) == '-') {
            i++;
            negative = true;
        }

        while (i < s.length()) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
            i++;
        }
        if (negative && (i == 1)) {
            return false;
        }

        return true;
    }


    /**
     * 判断字符串是否为null或者trim后长度小于1
     *
     * @param arg 要被判断的字符串
     * @return true 为null或者trim后长度小于1
     */
    public static boolean isEmpty(String arg) {
        if (arg == null || arg.trim().length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查找字符串
     *
     * @param strSource 原始字符串
     * @param strFrom   开始字符串
     * @param strTo     结束字符串
     * @return 字符串
     */
    public static String find(String strSource, String strFrom, String strTo) {
        String strDest = "";
        int intFromIndex = strSource.indexOf(strFrom) + strFrom.length();
        int intToIndex = strSource.indexOf(strTo);
        if (intFromIndex < intToIndex) {
            strDest = strSource.substring(intFromIndex, intToIndex);
        }
        return strDest;
    }

    /**
     * 格式化金额字符串
     *
     * @param strPrice 原始金额字符串
     * @return 金额字符串。空时返回html空格，其他返回“＄金额”
     */
    public static String addMoney(String strPrice) {
        strPrice = nvl(strPrice).trim();
        if (strPrice.equals("")) {
            return "&nbsp;";
        } else {
            return "＄" + strPrice;
        }
    }

    /**
     * 对html标签或特殊字符串编码
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String HtmlEncode(String html) {

        if (isEmpty(html)) {
            return html;
        }

        html = replace(html, "&", "&amp;");
        html = replace(html, "<", "&lt;");
        html = replace(html, ">", "&gt;");
        html = replace(html, "\n", "<br>");
        html = replace(html, "\"", "&quot;");

        return html;
    }

    /**
     * 空字符串转化为NULL
     *
     * @param str 字符串
     * @return 字符串
     */
    public static final String str2Null(String str) {
        if (str == null || str != null && "".equals(str.trim()))
            return null;
        else
            return str.trim();
    }

    /**
     * 字符串拆分
     *
     * @param sInputStr  字符串
     * @param cDelimiter 拆分字符
     * @return 字符串数组
     */
    public static String[] split(String sInputStr, char cDelimiter) {
        int iStrLen = sInputStr.length();
        int iTokCount = 0;

        if (0 == iStrLen) return null;

        for (int p = 0; p < iStrLen; p++)
            if (sInputStr.charAt(p) == cDelimiter) iTokCount++;

        String Tokens[] = new String[iTokCount + 1];

        int iToken = 0;
        int iLast = 0;
        for (int iNext = 0; iNext < iStrLen; iNext++) {
            if (sInputStr.charAt(iNext) == cDelimiter) {
                if (iLast == iNext)
                    Tokens[iToken] = "";
                else
                    Tokens[iToken] = sInputStr.substring(iLast, iNext);
                iLast = iNext + 1;
                iToken++;
            } // fi (sInputStr[iNext]==cDelimiter)
        } // next

        if (iLast >= iStrLen)
            Tokens[iToken] = "";
        else
            Tokens[iToken] = sInputStr.substring(iLast, iStrLen);

        return Tokens;
    } // split

    /**
     * 增加日期
     *
     * @param strDate 日期串"yyyy-MM-dd"
     * @param add     天数
     * @return 日期串
     */
    public static String getAddDate(String strDate, int add) {
        // 返回日期
        String strReturn = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(strDate);

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, add);

            strReturn = sdf.format(c.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strReturn;
    }

    /**
     * 判断日期是否在今天之前
     *
     * @param strDate 日期串"yyyy-MM-dd"
     * @return 在今天之前，返回true，否则返回false
     */
    public static boolean judgeBefore(String strDate) {
        // 返回值

        boolean blnReturn = true;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(strDate);

            Calendar c = Calendar.getInstance();
            c.setTime(date);

            Calendar c2 = Calendar.getInstance();
            c2.setTime(new Date());

            blnReturn = c.before(c2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return blnReturn;
    }

    /**
     * 数值转换
     *
     * @param o 数字对象
     * @return NULL或空格，返回“0”
     */
    public static String nvlnum(Object o) {
        if (o == null) {
            return "0";
        }

        if ("".equals(o.toString().trim())) {
            return "0";
        }
        return o.toString();
    }

    /**
     * 对字符串作MD5加密处理
     *
     * @param inStr 需要被处理的字符串
     * @return 被处理后的字符串，被转换为16进制表示的字符串
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String inStr) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inStr.getBytes());
            byte[] r = md.digest();

            for (int i = 0; i < r.length; i++) {
                byte b = r[i];
                sb.append(Character.forDigit((b >> 4 & 0x0F), 16));
                sb.append(Character.forDigit((b & 0x0F), 16));
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }

    /**
     * 判断文件是否存在
     *
     * @param fileSrc 文件路径
     * @return 判断结果
     */
    public static boolean fileExist(String fileSrc) {
        File file = new File(fileSrc);
        return (file.exists());
    }

    

    /**
     * 对字符串进行base64编码，主要用于网页汉字拼url
     *
     * @param o 待编码对象
     * @return 编码字符串
     */
    public static String encodeBase64(Object o) {
        return encodeBase64((String) o);
    }

    /**
     * 对字符串进行base64解码
     *
     * @param o 待解码对象
     * @return 解码字符串
     */
    public static String decodeBase64(Object o) {
        return decodeBase64((String) o);
    }

    /**
     * 对GBK字符串进行转码成UTF-8
     *
     * @param str 待解码字符串
     * @return 字符串
     * @throws Exception
     */
    public static String strGBKtoUtf8(String str) throws Exception {

        String toStr = null;

        if (str != null) {
            try {
                toStr = new String(str.getBytes("gbk"), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        return toStr;
    }

    /**
     * 对字符串进行转码成UTF-8
     *
     * @param str 待解码字符串
     * @return 字符串
     * @throws Exception
     */
    public static String strtoUtf8(String str) throws Exception {

        String toStr = null;

        if (str != null) {
            try {
                toStr = new String(str.getBytes(), "utf8");
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        return toStr;
    }

    /**
     * 格式化int类型为String类型
     *
     * @param iFormat 数值
     * @return String 字符串。一位的int在个位前加0;否则返回int的String值
     */
    public static String intFormatTOStr(int iFormat) {
        String strFormat = String.valueOf(iFormat);
        if (strFormat.length() < 2) {
            strFormat = "0" + strFormat;
        }
        return strFormat;
    }

    /**
     * 将传入的价格加人民币符号（￥）和千分撇（,）
     *
     * @param strPrice 价格字符串
     * @return 处理后字符串
     */
    public static String addRMBQFP(String strPrice) {
        strPrice = nvl(strPrice).trim();
        // 负数标志
        String flag = "no";
        if (!"".equals(strPrice)) {
            if ("-".equals(strPrice.substring(0, 1))) {
                strPrice = strPrice.substring(1);
                flag = "yes";
            }
            int pointIndex = strPrice.indexOf(".");
            String strPriceZ = "";
            String strPriceX = "";
            if (pointIndex > 0) {
                strPriceZ = strPrice.substring(0, pointIndex);
                strPriceX = strPrice.substring(pointIndex);
            } else {
                strPriceZ = strPrice;
                strPriceX = "";
            }

            if (strPriceZ.length() >= 4) {
                int qfpNum = (strPriceZ.length() - (strPriceZ.length() % 3)) / 3;
                String tempPrice = "";
                for (int i = 0; i < qfpNum; i++) {
                    tempPrice = ","
                            + strPriceZ.substring(strPriceZ.length() - (i + 1) * 3,
                            strPriceZ.length() - i * 3) + tempPrice;
                }
                if (strPriceZ.length() % 3 == 0) {
                    tempPrice = tempPrice.trim().substring(1);
                } else {
                    tempPrice = strPriceZ.substring(0, strPriceZ.length() % 3) + tempPrice;
                }
                if ("yes".equals(flag)) {
                    strPrice = "￥-" + tempPrice + strPriceX;
                } else {
                    strPrice = "￥" + tempPrice + strPriceX;
                }
            } else {
                if (!"".equals(strPrice) && !".".equals(strPrice)) {
                    if ("yes".equals(flag)) {
                        strPrice = "￥-" + strPrice;
                    } else {
                        strPrice = "￥" + strPrice;
                    }
                }
            }
        }
        return strPrice;
    }

    /**
     * 对字符串按照长度换行
     *
     * @param s   需要换行的字符传
     * @param len 多长时，需要换行
     * @return HTML字符串
     */
    public static String autoChangeRow(String s, int len) {
        String sReurlt = "";
        for (int i = 0; i < s.length(); i = i + len) {
            if (i == 0) {
                sReurlt = s.substring(0, s.length() > len ? len : s.length());
            } else {
                sReurlt = sReurlt + "</br>";
                sReurlt = sReurlt + s.substring(i, s.length() > i + len ? i + len : s.length());
            }
        }
        return sReurlt;
    }

    /**
     * 对字符串按照长度换行(当文字中有CSS样式时，适用)
     *
     * @param s   需要换行的字符传
     * @param len 多长时，需要换行
     * @return HTML字符串
     */
    public static String autoChangeRowWithCSS(String s, int len) {
        String[] oldS = s.split(",");
        String[] newS = s.split(",");
        String strNewS = "";
        String strOldS = "";
        for (int i = 0; i < newS.length; i++) {
            if (newS[i].charAt(0) == '<') {
                newS[i] = newS[i].substring(newS[i].indexOf(">") + 1, newS[i].indexOf("<", 2));
            }
            strNewS += "," + newS[i];
        }
        strNewS = strNewS.substring(1);
        strNewS = autoChangeRow(strNewS, len);
        newS = strNewS.split(",");
        for (int i = 0; i < oldS.length; i++) {
            if (oldS[i].charAt(0) == '<') {
                oldS[i] = oldS[i].substring(0, oldS[i].indexOf(">") + 1) + newS[i]
                        + oldS[i].substring(oldS[i].indexOf("<", 2), oldS[i].length());
            } else {
                oldS[i] = newS[i];
            }
            strOldS += "," + oldS[i];
        }
        strOldS = strOldS.substring(1);
        return strOldS;
    }

    /**
     * 获取字符串中非GB2312字符
     *
     * @param str 需要处理的字符串
     * @return 字符串
     */
    public static String getNotGB2312(String str) {
        str = nvl(str);
        char[] chars = str.toCharArray();
        String GB2312 = "";
        for (int i = 0; i < chars.length; i++) {
            try {
                byte[] bytes = ("" + chars[i]).getBytes("GB2312");
                if (bytes.length == 2) {
                    int[] ints = new int[2];
                    ints[0] = bytes[0] & 0xff;
                    ints[1] = bytes[1] & 0xff;
                    if (!(ints[0] >= 0xb0 && ints[0] <= 0xf7 && ints[1] >= 0xa1 && ints[1] <= 0xfe)) {
                        GB2312 += chars[i];
                    }
                } else {
                    GB2312 += chars[i];
                }
            } catch (Exception e) {
                GB2312 += chars[i];
                System.out.println("ERR=====" + str);
                e.printStackTrace();
            }
        }
        return GB2312;
    }

    /**
     * 去掉最后一个字符串
     *
     * @param str
     * @return
     */
    public static String subStringEnd(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * 拼接成('1','2')等等
     *
     * @param objs
     * @return
     */
    public static String pjId(Object[] objs) {
        // 拆分id
        String ids = "(";
        for (Object object : objs) {
            ids += "'" + object + "',";
        }
        // 拼接成('1','2','3')
        return subStringEnd(ids) + ")";
    }

    /**
     * 把doc格式的字符串转换为html格式,但是对table保持原样
     *
     * @param sourceStr
     * @return targetStr
     */
    public static String getTargetStr(String sourceStr) {
        StringBuffer buf = new StringBuffer();
        int tabSIndex = sourceStr.indexOf("<table>");
        int tabEIndex = sourceStr.indexOf("</table>");
        if (tabSIndex == -1 || tabEIndex == -1) {
            return sourceStr;
        }
        String headStr = sourceStr.substring(0, tabSIndex);
        headStr = headStr.replaceAll("\r\n", "<br>");
        String middleStr = sourceStr.substring(tabSIndex + 1, tabEIndex);
        String endStr = sourceStr.substring(tabEIndex + 1, sourceStr.length() - 1);
        buf.append(headStr);
        buf.append(middleStr);
        if (endStr.indexOf("<table>") != -1) {
            buf.append(getTargetStr(endStr));
        }
        return buf.toString();
    }

    /**
     * 截取字符串，输入字符串长度大于要截取的长度，则显示“…”
     *
     * @param input
     * @param lettersNum 英文个数 ，一个中文占两个英文
     * @return
     */
    public static String subString2(String input, int lettersNum) {

        if (input == null || input.trim() == "") {
            return "";
        }
        String tmpStr = input.trim();

        if (tmpStr.length() * 2 <= lettersNum) {
            return tmpStr;
        }

        int num = 0;
        String temp = "";
        for (int i = 0; i < tmpStr.length() && num < lettersNum; i++) {
            if (tmpStr.substring(i, i + 1).getBytes().length > 1) {
                num += 2;
                temp = tmpStr.substring(0, i + 1);
            } else {
                num += 1;
                temp = tmpStr.substring(0, i + 1);
            }

        }

        if (temp.length() == tmpStr.length()) {
            return temp;
        } else {
            while (num > lettersNum - 2) {
                int i = temp.length();

                if (temp.substring(i - 1, i).getBytes().length > 1) {
                    num = num - 2;
                } else {
                    num = num - 1;
                }
                temp = temp.substring(0, i - 1);
            }
            temp += "…";
        }

        return temp;

    }

    /**
     * 按字节截取字符串
     *
     * @param sourceStr
     * @param byteLen
     * @return
     */
    public static String cutStringByByte(String sourceStr, int byteLen) {
        if (sourceStr == null) return "";
        String targetStr = sourceStr;
        byte[] sourceByte = sourceStr.getBytes();
        if (sourceByte.length > byteLen) {
            targetStr = new String(sourceByte, 0, byteLen);
        }
        return targetStr;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return boolean
     */
    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 判断map是否为空
     *
     * @param <T>
     * @param map
     * @return
     */
    public static <T> boolean mapNotValue(Map<Object, T> map) {
        if (map != null && map.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 将BigDecimal 格式化"#0.00"
     *
     * @param dec
     * @return
     */
    public static String converFormatStr(BigDecimal dec) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(dec);
    }

    /**
     * 讲BigDecimal 格式化 "#0"
     *
     * @param dec
     * @return
     */
    public static String converFormatStrPrice(BigDecimal dec) {
        DecimalFormat formatter = new DecimalFormat("#0");
        return formatter.format(dec);
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     */

    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }

        return outBuffer.toString();

    }


    /**
     * 生成随机密码
     *
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int pwd_len) {
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            //生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /**
     * 判断某个字符串是否存在于数组中
     * @param stringArray
     * @param source
     * @return
     */
    public static boolean contains(String[] stringArray, String source) {
        // 转换为list
        List<String> tempList = Arrays.asList(stringArray);
        // 利用list的包含方法,进行判断
        if (tempList.contains(source)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断某个字符串是否存在于数组中
     *
     * @param stringArray 原数组
     * @param source      查找的字符串
     * @return 是否找到
     */
    public static boolean contains(List<String> stringArray, String source) {
        // 利用list的包含方法,进行判断
        if (stringArray.contains(source)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将数字保留几位小数
     */
    public static BigDecimal getBigDecimal(BigDecimal number,int index) {
        if(!StringHelperTools.nvl(number).equals("")){
            return number.setScale(index,BigDecimal.ROUND_HALF_UP);
        }
        return number;
    }
    /**
     * 判断字符串是否包含特殊字符
     */
    public static boolean specialCcharacter(String str) {
        if(str.contains(" ")){
            return true;
        }
        String regEx = "[`@$%^&+=|{}':;',<>/?！@￥%……&|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 对sql通配符进行转义
     */
    public static String wildcard(String str){
        if(!StringHelperTools.isEmpty(str)){
            return  str.replace("\\","\\\\").replace("%","\\%").replace("_","\\_");
        }
        return  str;
    }


    /**
     * 对html标签或特殊字符串编码
     *
     * @param html html代码
     * @return String 替换后代码
     */
    public static String HtmlEncodeTS(String html) {

        if (isEmpty(html)) {
            return html;
        }
        html = replace(html, "<", "&lt;");
        html = replace(html, ">", "&gt;");
        return html;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}


