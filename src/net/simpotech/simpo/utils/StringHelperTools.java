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
 * ���ܡ���;���ִ�BUG: ����ʵ��һЩͨ�õ��ַ�������
 *
 * @author
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringHelperTools {

    /**
     * ��ȡ��ǰUUID���������ݹ��� ������ṹ��ʹ�ô�ID�滻seq
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = (uuid.toString()).replaceAll("-", "");
        return uuidStr;
    }

    /**
     * nullֵת��
     *
     * @param args
     * @return ����ת��������
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
     * nullֵת��
     *
     * @param args
     * @return ����ת��������
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
     * NULL�ַ���ת������������Ϊnullʱ�����ؿ��ַ���
     *
     * @param o ת��ԭ����
     * @return �ַ���
     */
    public static String nvl(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    /**
     * NULL�ַ���ת������������Ϊnullʱ�����ؿ�ֵΪ0��BigDecimal����
     *
     * @param o ת��ԭ����
     * @return �ַ���
     */
    public static BigDecimal nvlToBigDecimal(Object o) {
        if (o == null) {
            return new BigDecimal(0);
        } else {
            return (BigDecimal) o;
        }
    }

    /**
     * NULL�ַ���ת������������Ϊnullʱ�����ؿ��ַ��� ��" " �滻Ϊ "&nbsp;" ����ҳ����ʾ����ո�
     *
     * @param o ת��ԭ����
     * @return �ַ���
     */
    public static String nvlShow(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim().replaceAll(" ", "&nbsp;");
    }

    /**
     * NULL�ַ���ת������������Ϊnullʱ������Ĭ��ֵ
     *
     * @param o   ת��ԭ����
     * @param res Ĭ��ֵ
     * @return �ַ���
     */
    public static String nvl(Object o, String res) {
        if (o == null) {
            return res;
        }
        return o.toString().trim();
    }

    /**
     * NULL����ַ���ת������������Ϊnull���ʱ������Ĭ��ֵ
     *
     * @param o   ת��ԭ����
     * @param res Ĭ��ֵ
     * @return �ַ���
     */
    public static String nvlHtml(Object o, String res) {
        if (o == null || o.toString().trim().equals("")) {
            return res;
        }
        return o.toString().trim();
    }

    /**
     * �ַ����滻���������Ҫ��������ʽ���ô˷����� ������String.replaceAll(String regex, String replacement)
     *
     * @param text ��Ҫ��������ַ���
     * @param from ��Ҫ���滻�����ַ���
     * @param to   ���滻�ɵ��ַ���
     * @return ���滻����ַ���
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
     * �滻�س�Ϊbr
     *
     * @param text ԭ�ı�
     * @return �滻���ı�
     */
    public static String replaceLineFeedCode(String text) {
        return replace(text, "\n", "<br>\n");
    }

    /**
     * �滻\tΪ4��html�ո�
     *
     * @param text ԭ�ı�
     * @return �滻���ı�
     */
    public static String replaceTab24Space(String text) {
        return replace(text, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
    }

    /**
     * ��html��ǩ�������ַ�������
     *
     * @param html html����
     * @return String �滻�����
     */
    public static String replaceQuotes(String html) {

        html = replace(html, "\"", "&quot;");

        return html;
    }

    /**
     * ��html��ǩ�������ַ�������
     *
     * @param html html����
     * @return String �滻�����
     */
    public static String replaceAllQuotes(String html) {

        html = replace(html, "\"", "&quot;");
        html = replace(html, "\'", "&apos;");

        return html;
    }

    /**
     * ��html��js�����õ��ַ�������alert�У�ת�� ������
     *
     * @param html html����
     * @return String �滻�����
     */
    public static String replaceJsApos(String html) {

        html = replace(html, "'", "\\'");

        return html;
    }

    /**
     * ��html��js�����õ��ַ�������alert�У�ת�� ������
     *
     * @param html html����
     * @return String �滻�����
     */
    public static String replaceJsQuote(String html) {

        html = replace(html, "\"", "\\\"");

        return html;
    }

    /**
     * ����Ƿ�Ϊ���֡����԰���С���㣬����С����������ܶ���һ���� ���԰������ţ����ǲ���ֻ�и��Ŷ�û���������֣� �������������
     *
     * @param s �������ַ���
     * @return true ��ʾ������, false ��ʾ��������
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
     * ����Ƿ�Ϊ����������Ϊ�����������ǲ���ֻ�и��Ŷ�û����������
     *
     * @param s �������ַ���
     * @return true ��ʾ������, false ��ʾ��������
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
     * �ж��ַ����Ƿ�Ϊnull����trim�󳤶�С��1
     *
     * @param arg Ҫ���жϵ��ַ���
     * @return true Ϊnull����trim�󳤶�С��1
     */
    public static boolean isEmpty(String arg) {
        if (arg == null || arg.trim().length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �����ַ���
     *
     * @param strSource ԭʼ�ַ���
     * @param strFrom   ��ʼ�ַ���
     * @param strTo     �����ַ���
     * @return �ַ���
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
     * ��ʽ������ַ���
     *
     * @param strPrice ԭʼ����ַ���
     * @return ����ַ�������ʱ����html�ո��������ء����
     */
    public static String addMoney(String strPrice) {
        strPrice = nvl(strPrice).trim();
        if (strPrice.equals("")) {
            return "&nbsp;";
        } else {
            return "��" + strPrice;
        }
    }

    /**
     * ��html��ǩ�������ַ�������
     *
     * @param html html����
     * @return String �滻�����
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
     * ���ַ���ת��ΪNULL
     *
     * @param str �ַ���
     * @return �ַ���
     */
    public static final String str2Null(String str) {
        if (str == null || str != null && "".equals(str.trim()))
            return null;
        else
            return str.trim();
    }

    /**
     * �ַ������
     *
     * @param sInputStr  �ַ���
     * @param cDelimiter ����ַ�
     * @return �ַ�������
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
     * ��������
     *
     * @param strDate ���ڴ�"yyyy-MM-dd"
     * @param add     ����
     * @return ���ڴ�
     */
    public static String getAddDate(String strDate, int add) {
        // ��������
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
     * �ж������Ƿ��ڽ���֮ǰ
     *
     * @param strDate ���ڴ�"yyyy-MM-dd"
     * @return �ڽ���֮ǰ������true�����򷵻�false
     */
    public static boolean judgeBefore(String strDate) {
        // ����ֵ

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
     * ��ֵת��
     *
     * @param o ���ֶ���
     * @return NULL��ո񣬷��ء�0��
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
     * ���ַ�����MD5���ܴ���
     *
     * @param inStr ��Ҫ��������ַ���
     * @return ���������ַ�������ת��Ϊ16���Ʊ�ʾ���ַ���
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
     * �ж��ļ��Ƿ����
     *
     * @param fileSrc �ļ�·��
     * @return �жϽ��
     */
    public static boolean fileExist(String fileSrc) {
        File file = new File(fileSrc);
        return (file.exists());
    }

    

    /**
     * ���ַ�������base64���룬��Ҫ������ҳ����ƴurl
     *
     * @param o ���������
     * @return �����ַ���
     */
    public static String encodeBase64(Object o) {
        return encodeBase64((String) o);
    }

    /**
     * ���ַ�������base64����
     *
     * @param o ���������
     * @return �����ַ���
     */
    public static String decodeBase64(Object o) {
        return decodeBase64((String) o);
    }

    /**
     * ��GBK�ַ�������ת���UTF-8
     *
     * @param str �������ַ���
     * @return �ַ���
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
     * ���ַ�������ת���UTF-8
     *
     * @param str �������ַ���
     * @return �ַ���
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
     * ��ʽ��int����ΪString����
     *
     * @param iFormat ��ֵ
     * @return String �ַ�����һλ��int�ڸ�λǰ��0;���򷵻�int��Stringֵ
     */
    public static String intFormatTOStr(int iFormat) {
        String strFormat = String.valueOf(iFormat);
        if (strFormat.length() < 2) {
            strFormat = "0" + strFormat;
        }
        return strFormat;
    }

    /**
     * ������ļ۸������ҷ��ţ�������ǧ��Ʋ��,��
     *
     * @param strPrice �۸��ַ���
     * @return ������ַ���
     */
    public static String addRMBQFP(String strPrice) {
        strPrice = nvl(strPrice).trim();
        // ������־
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
                    strPrice = "��-" + tempPrice + strPriceX;
                } else {
                    strPrice = "��" + tempPrice + strPriceX;
                }
            } else {
                if (!"".equals(strPrice) && !".".equals(strPrice)) {
                    if ("yes".equals(flag)) {
                        strPrice = "��-" + strPrice;
                    } else {
                        strPrice = "��" + strPrice;
                    }
                }
            }
        }
        return strPrice;
    }

    /**
     * ���ַ������ճ��Ȼ���
     *
     * @param s   ��Ҫ���е��ַ���
     * @param len �೤ʱ����Ҫ����
     * @return HTML�ַ���
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
     * ���ַ������ճ��Ȼ���(����������CSS��ʽʱ������)
     *
     * @param s   ��Ҫ���е��ַ���
     * @param len �೤ʱ����Ҫ����
     * @return HTML�ַ���
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
     * ��ȡ�ַ����з�GB2312�ַ�
     *
     * @param str ��Ҫ������ַ���
     * @return �ַ���
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
     * ȥ�����һ���ַ���
     *
     * @param str
     * @return
     */
    public static String subStringEnd(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * ƴ�ӳ�('1','2')�ȵ�
     *
     * @param objs
     * @return
     */
    public static String pjId(Object[] objs) {
        // ���id
        String ids = "(";
        for (Object object : objs) {
            ids += "'" + object + "',";
        }
        // ƴ�ӳ�('1','2','3')
        return subStringEnd(ids) + ")";
    }

    /**
     * ��doc��ʽ���ַ���ת��Ϊhtml��ʽ,���Ƕ�table����ԭ��
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
     * ��ȡ�ַ����������ַ������ȴ���Ҫ��ȡ�ĳ��ȣ�����ʾ������
     *
     * @param input
     * @param lettersNum Ӣ�ĸ��� ��һ������ռ����Ӣ��
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
            temp += "��";
        }

        return temp;

    }

    /**
     * ���ֽڽ�ȡ�ַ���
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
     * �ж��ַ����Ƿ�������
     *
     * @param str
     * @return boolean
     */
    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * �ж�map�Ƿ�Ϊ��
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
     * ��BigDecimal ��ʽ��"#0.00"
     *
     * @param dec
     * @return
     */
    public static String converFormatStr(BigDecimal dec) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(dec);
    }

    /**
     * ��BigDecimal ��ʽ�� "#0"
     *
     * @param dec
     * @return
     */
    public static String converFormatStrPrice(BigDecimal dec) {
        DecimalFormat formatter = new DecimalFormat("#0");
        return formatter.format(dec);
    }

    /**
     * unicode ת���� ����
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
     * �����������
     *
     * @param pwd_len ���ɵ�������ܳ���
     * @return ������ַ���
     */
    public static String genRandomNum(int pwd_len) {
        //35����Ϊ�����Ǵ�0��ʼ�ģ�26����ĸ+10������
        final int maxNum = 36;
        int i;  //���ɵ������
        int count = 0; //���ɵ�����ĳ���
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            //�����������ȡ����ֵ����ֹ���ɸ�����

            i = Math.abs(r.nextInt(maxNum));  //���ɵ������Ϊ36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /**
     * �ж�ĳ���ַ����Ƿ������������
     * @param stringArray
     * @param source
     * @return
     */
    public static boolean contains(String[] stringArray, String source) {
        // ת��Ϊlist
        List<String> tempList = Arrays.asList(stringArray);
        // ����list�İ�������,�����ж�
        if (tempList.contains(source)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �ж�ĳ���ַ����Ƿ������������
     *
     * @param stringArray ԭ����
     * @param source      ���ҵ��ַ���
     * @return �Ƿ��ҵ�
     */
    public static boolean contains(List<String> stringArray, String source) {
        // ����list�İ�������,�����ж�
        if (stringArray.contains(source)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �����ֱ�����λС��
     */
    public static BigDecimal getBigDecimal(BigDecimal number,int index) {
        if(!StringHelperTools.nvl(number).equals("")){
            return number.setScale(index,BigDecimal.ROUND_HALF_UP);
        }
        return number;
    }
    /**
     * �ж��ַ����Ƿ���������ַ�
     */
    public static boolean specialCcharacter(String str) {
        if(str.contains(" ")){
            return true;
        }
        String regEx = "[`@$%^&+=|{}':;',<>/?��@��%����&|{}������������������������]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * ��sqlͨ�������ת��
     */
    public static String wildcard(String str){
        if(!StringHelperTools.isEmpty(str)){
            return  str.replace("\\","\\\\").replace("%","\\%").replace("_","\\_");
        }
        return  str;
    }


    /**
     * ��html��ǩ�������ַ�������
     *
     * @param html html����
     * @return String �滻�����
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
     * ʹ��java������ʽȥ�������.��0
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//ȥ�������0
            s = s.replaceAll("[.]$", "");//�����һλ��.��ȥ��
        }
        return s;
    }
}


