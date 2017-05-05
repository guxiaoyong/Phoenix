package pers.guxiaoyong.print;

import java.math.BigInteger;

/**
 * Author:guxiaoyong.
 * Date:2017/4/20.
 * Time:下午8:02.
 * Description:
 */
public class Main {

    public static void main(String[] args) {
        try {

            String tableName = "A1";
            String SSNO = "YY00030002-20170423-0010";
            String time = "16:45:50";
            String peopleNum = "10";

            String itemsHexString ="";


            String itemName = "炖品老妈辣椒蹄花";
            String itemNum = "10";
            String itemCode = "690002707241";
            String itemHexString = "1B441A21001D2111"+bytesToHexString(itemName.getBytes("gb2312"))+"1D2100091D2111"+bytesToHexString(itemNum.getBytes("gb2312"))+"1D2100091D211120301D21000D0A1B440B11001D21111D210009D7F6B7A8A3BA091D21111D21000D0A1D77021D68401D48021D6B430C"+bytesToHexString(itemCode.getBytes());

            itemsHexString = itemsHexString + itemHexString;

            String hexString ="1B440C001D2111"+bytesToHexString(tableName.getBytes("gb2312"))+"1D2100091B47011D2133BCD3B5A51B47001D21000D0AD3AAD2B5C1F7CBAE3A20"+bytesToHexString(SSNO.getBytes("gb2312"))+"0D0A1D2111CAB1BCE43A20"+bytesToHexString(time.getBytes("gb2312"))+"1D21000D0A1B44160009C8CBCAFD3A20"+bytesToHexString(peopleNum.getBytes("gb2312"))+"0D0A1B441A2200C6B7CFEE09CAFDC1BF09D6BBCAFD0D0A2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D"+"0D0A"+itemsHexString+"2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A1D2111"+bytesToHexString(tableName.getBytes("gb2312"))+"1D21000D0A1B440A00D5FBB5A5B1B8D7A2A3BA091D21111D21000D0A1D56420A";

            byte[] bytes = hexStringToBytes(hexString);
            String realString = new String(bytes,"gb2312");
            System.out.println(realString);
            System.out.println(bytesToHexString(realString.getBytes("gb2312")));


            String a1 = "A1";

            System.out.println(bytes2hex01(a1.getBytes("gb2312")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getPrintJobHexStringFor(){

        return "";
    }

    /**
     * 方式一
     *
     * @param bytes
     * @return
     */
    public static String bytes2hex01(byte[] bytes)
    {
        /**
         * 第一个参数的解释，记得一定要设置为1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }

    /**
     * 16进制字符串转换为byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase().replace(" ", "");
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * byte[]转换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
