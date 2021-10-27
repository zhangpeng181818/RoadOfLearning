package com.nuc.zp.search;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinyinUtil {
    //
    private final static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    //输入拼音进行判断其中文
    public static String str2Pinyin(String str, String fill) {

        try {
            StringBuffer sb = new StringBuffer();
            //判断是中文
            boolean isCn = true;
            if (fill == null) {
                fill = " ";
            }

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == ' ') {
                    sb.append(",");
                }

                //1.判断是不是中文
                if (i > 0 && isCn) {
                    sb.append(fill);

                }
                if (c >= '\u4e00' && i <= '\u9fa5') {

                    isCn = true;
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]);

                } else {
                    //不是中文
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                        sb.append(c);
                    isCn = false;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String strFirst2Pinyin(String str) {

        try {
            StringBuffer sb = new StringBuffer();
            //判断是中文


            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                //1.判断是不是中文
                if (c >= '\u4e00' && i <= '\u9fa5') {


                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, format)[0]).charAt(0);

                } else {

                }

            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(str2Pinyin("张鹏",""));
    }
}

