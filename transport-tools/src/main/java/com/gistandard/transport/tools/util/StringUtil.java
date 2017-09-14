package com.gistandard.transport.tools.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yujie
 * @ClassName StringUtil
 * @Description
 * @Version 1.0
 * @Date 2015-06-11
 */
public class StringUtil {

    /**
     * 对象转字符串，如果对象为null，则返回null
     * @param o
     * @return
     */
    public static String getObjStr(Object o){
        return o == null ? null : o.toString();
    }

    /**
     * 对象转字符串，如果对象为空，则返回默认值
     * @param o
     * @param defaultStr 默认值
     * @return
     */
    public static String getNotNullStr(Object o, String defaultStr){
        if(o == null || "".equals(o.toString())){
            return defaultStr;
        }
        return o.toString();
    }

    public static String  getLocalServerIp(){
        String serverIp= "";
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            for (int i = 0; i < ipAddr.length; i++) {
                if (i > 0) {
                    serverIp += ".";
                }
                serverIp += ipAddr[i] & 0xFF;
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            return serverIp;
        }
    }

    /**
     * 格式化数字，如果带小数，小数部分尾数是0，就不显示
     * @param number
     * @return
     */
    public static String formatNumber(Double number){
        if(number == null){
            return "";
        }
        DecimalFormat df1 = new DecimalFormat("#,###,###,###.####");
        return df1.format(number);
    }

    public static String formatNumber(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return "";
        }
        return formatNumber(bigDecimal.doubleValue());
    }

    public static String formatNumber(String numberStr){
        if(numberStr == null || "".equals(numberStr)){
            return "";
        }
        try {
            double number = new Double(numberStr);
            return formatNumber(number);
        }
        catch (Exception e){
            return "";
        }
    }

    /**
     * 格式化数字百分比形式，小数部分最多两位
     * @param number
     * @return
     */
    public static String formatNumberPercent(Double number){
        if(number == null){
            return "";
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(number);
    }

    public static String formatNumberPercent(BigDecimal bigDecimal){
        if(bigDecimal == null) {
            return "";
        }
        return formatNumberPercent(bigDecimal.doubleValue());
    }

    public static String formatNumberPercent(String numberStr){
        if(numberStr == null || "".equals(numberStr)){
            return "";
        }
        try {
            double number = new Double(numberStr);
            return formatNumberPercent(number);
        }
        catch (Exception e){
            return "";
        }
    }

    public static String getStackMsg(Throwable e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * 计算百分比
     *
     * @param divisor
     * @param dividend
     * @return String
     */
    public static String getPercent(Object divisor, Object dividend){
        if(divisor == null || dividend == null){
            return "";
        }
        NumberFormat percent = NumberFormat.getPercentInstance();
        //建立百分比格式化引用
        percent.setMaximumFractionDigits(2);
        BigDecimal a = toBig(divisor);
        BigDecimal b = toBig(dividend);
        if(a.equals(toBig(0)) || b.equals(toBig(0)) || a.equals(toBig(0.0)) || b.equals(toBig(0.0))){
            return "0.00%";
        }
        BigDecimal c = a.divide(b, 4, BigDecimal.ROUND_DOWN);
        return percent.format(c);
    }


    /**
     * 计算比例
     *
     * @param divisor
     * @param dividend
     * @return String
     */
    public static String divideNumber(Object divisor, Object dividend){
        if(divisor == null || dividend == null){
            return "";
        }
        BigDecimal a = toBig(divisor);
        BigDecimal b = toBig(dividend);
        if(a.equals(toBig(0)) || b.equals(toBig(0))){
            return "0";
        }
        BigDecimal c = a.divide(b, 2, BigDecimal.ROUND_DOWN);
        return c.toString();
    }

    /**
     * 转换为BigDecimal
     *
     * @param o
     * @return BigDecimal
     */
    public static BigDecimal toBig(Object o) {
        if (o == null || o.toString().equals("") || o.toString().equals("NaN")) {
            return new BigDecimal(0);
        }
        return new BigDecimal(o.toString());
    }

    /**
     * unicode格式字符串，替换中文
     * @param unicode
     * @return
     */
    public static String unicodeToStr(String unicode){
        if(unicode == null || "".equals(unicode))
            return "";
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(unicode.substring(pos));
        return sb.toString();
    }

    /**
     * 判断对象值是否为空：
     * 若对象为字符串，判断对象值是否为null或空格;
     * 若对象为数组，判断对象值是否为null，或数组个数是否为0;
     * 若对象为List。判断对象值是否为null，或List元素是否个数为0;
     * 其他类型对象，只判断值是否为null.
     * @param value
     * @return true-是
     */
    public static boolean isEmpty(Object value)
    {
        if (value == null) {
            return true;
        } else if ((value instanceof String) && (((String) value).trim().length() < 1)) {
            return true;
        } else if (value.getClass().isArray()) {
            if (0 == java.lang.reflect.Array.getLength(value)) {
                return true;
            }
        } else if (value instanceof List) {
            if (((List) value).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static String IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        // =====================(end)=====================
        return "";
    }

    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }


    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getIpAddressFromReq(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String toUpperCaseFirst(String str){
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String toLowerCaseFirst(String str){
        char[] cs=str.toCharArray();
        cs[0]+=32;
        return String.valueOf(cs);
    }

    /**
     * List转换成String,分割
     * @param numbers
     * @return
     */
    public static String listConvertToString(List<Integer> numbers) {
        StringBuilder builder = new StringBuilder();
         for (int number : numbers) {
            builder.append(number);
            builder.append(",");
        }
         builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 字符串转Integer
     * @param str
     * @return
     */
    public static Integer createInteger(final String str) {
        if (str == null || StringUtils.isEmpty(str)) {
            return null;
        }
        // decode() handles 0xAABD and 0777 (hex and octal) as well.
        try {
            return Integer.decode(str);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取6位随机数字，作为验证码
     *
     * @return
     */
    public static int getRandomNum() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }


    /**
     * ASCII!?33(Decimal)
     */
    static final char DBC_CHAR_START = 33; // !

    /**
     * ASCII~?126(Decimal)
     */
    static final char DBC_CHAR_END = 126; // ~

    /**
     * ASCII??65281
     */
    static final char SBC_CHAR_START = 65281; // ?

    /**
     * ASCII??65374
     */
    static final char SBC_CHAR_END = 65374; // ?

    /**
     * ASCII
     */
    static final int CONVERT_STEP = 65248; //

    /**
     * ?ASCII?
     */
    static final char SBC_SPACE = 12288; //  12288
    /**
     * ?ASCII32(Decimal)
     */
    static final char DBC_SPACE = ' '; //
    /**
     * <PRE>
     * ->
     * 半角字符 转全角字符
     * </PRE>
     */
    private static String bj2qj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == DBC_SPACE) { // ?
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) { // !~
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else { // ascii
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <PRE>
     * 全角字符转半角字符
     *
     * </PRE>
     */
    public static String qj2bj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // ??
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { //
                buf.append(DBC_SPACE);
            } else { // ???
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }
    
}
