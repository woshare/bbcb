package com.example.bbcb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**数据校验工具
 * @author woshare
 *
 */
public class DataCheckUtil {
    //数据校验正则表达式
    //手机号码
    public static final String mobilePhoneNumberRegex= "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    //固定电话，(包括验证国内区号,国际区号,分机号)
    public static final String telePhoneNumberRegex="^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

    //年月日
    public static final String yearMonthDayRegex="\\d{4}[-.]\\d{2}[-.]\\d{2}";
    //年月日时分秒
    public static final String yearMonthDayHourMinuteSecondRegex="\\d{4}[-.]\\d{2}[-.]\\d{2}(\\s\\d{2}:\\d{2}(:\\d{2})?)?";

    //数字
    public static final String isNumberRegex="^([+-]?)\\d*\\.?\\d+$";
    //整数
    public static final String integerRegex = "[0-9]*";
    //正整数
    public static final String positiveIntegerRegex = "^\\+?[1-9][0-9]*$";//"^[1-9]\\d*$"
    //负整数
    public static final String negativeIntegereRegex="^-[1-9]\\d*$";
    //正数（正整数 + 0）
    public static final String positiveAndZeroIntegerRegex = "^[1-9]\\d*|0$";
    //负数（负整数 + 0）
    public static final String negativeAndZeroIntegereRegex="^-[1-9]\\d*|0$";

    //浮点数
    public static final String decimalRegex ="^(-?\\d+)(\\.\\d+)?$";//"^([+-]?)\\d*\\.\\d+$"
    //正浮点数
    public static final String positiveDecimalRegex = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";
    //负浮点数
    public static final String negetiveDecimalRegex ="^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
    //非负浮点数（正浮点数 + 0）
    public static final String positiveAndZeroDecimalRegex="^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";
    //非正浮点数（负浮点数 + 0）
    public static final String negetiveAndZeroDecimalRegex="^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

    //词汇
    public static final String vocabularyRegex="^[A-Za-z]\\(\\)+$";//这个规则肯定是不够的
    //字母
    public static final String letterRegex="^[A-Za-z]+$";
    //大写字母
    public static final String upLetterRegex="^[A-Z]+$";
    //小写字母
    public static final String littleLetterRegex="^[a-z]+$";



    // idCardRegex1||idCardRegex2 两个正则或条件判断
    public static final String idCardRegex1="^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String idCardRegex2="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$";

    //中文
    public static final String isChineseRegex="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
    //中文名字
    public static final String isChinesePersonName="[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";
    //qq号
    public static final String qqRegex="^[1-9]*[1-9][0-9]*$";
    //压缩文件
    public static final String rarRegex="(.*)\\.(rar|zip|7zip|tgz)$";
    //图片
    public static final String pixRegex="(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";
    //IP
    public static final String ipRegex="^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";
    //邮编
    public static final String zipCodeRegex="^\\d{6}$";
    //ASCII码字符
    public static final String asciiRegex="^[\\x00-\\xFF]+$";

    //url
    public static final String urlRegex="^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";

    //邮件
    public static final String isEmailRegex="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    //颜色
    public static final String colorRegex="^[a-fA-F0-9]{6}$";

    /**
     * 这是一个正则匹配的基础方法
     * @param regex 正则表达式字符串
     * @param origin 需要匹配的数据
     * @return
     */
    public static boolean isMatch(String regex,String origin){
        if(origin==null||origin.isEmpty()||regex==null||regex.isEmpty()){
            return false;
        }
        Pattern pattern=Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher match=pattern.matcher(origin);
        return match.matches();
    }

    /**
     * 校验移动手机号码
     * @param origin
     * @return
     */
    public static boolean isMobilePhoneNumber(String origin){
        return isMatch(mobilePhoneNumberRegex,origin);
    }

    /**
     * 校验固定座机号码
     * @param origin
     * @return
     */
    public static boolean isTelePhoneNumber(String origin){
        return isMatch(telePhoneNumberRegex,origin);
    }
    /**
     *校验时间格式：yyyy-MM-dd HH:mm:ss
     * @param origin
     * @return
     */
    public static  boolean isYMDHMSDateTime(String origin){
        return isMatch(yearMonthDayHourMinuteSecondRegex,origin);
    }

    /**
     *校验时间格式：yyyy-MM-dd
     * @param origin
     * @return
     */
    public static  boolean isYearMonthDay(String origin){
        return isMatch(yearMonthDayRegex,origin);
    }

    /**
     * 是否浮点数
     * @param origin
     * @return
     */
    public static boolean IsDecimal(String origin) {
        return isMatch(decimalRegex,origin);
    }

    /**
     * 是否正浮点数
     * @param origin
     * @return
     */
    public static boolean IsPositiveDecimal(String origin) {
        return isMatch(positiveDecimalRegex,origin);
    }

    /**
     * 校验数字
     * @param origin
     * @return
     */
    public static  boolean isNumber(String origin){
       // return isMatch(numberRegex,origin);
        return false;
    }
    /**
     * 校验数字,不含0
     * @param origin
     * @return
     */
    public static  boolean isNumberNoZero(String origin){
        //return isMatch(numberNoZeroRegex,origin);
        return false;
    }

    /**
     * 校验单词
     * @param origin
     * @return
     */
    public static  boolean isVocabulary(String origin){
        return isMatch(vocabularyRegex,origin);
    }

    /**
     * 校验身份证号码
     * @param origin
     * @return
     */
    public static  boolean isIdCard(String origin){
        return isMatch(idCardRegex1,origin)||isMatch(idCardRegex2,origin);
    }
}
