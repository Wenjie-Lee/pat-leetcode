/*
* Convert a non-negative integer num to its English words representation.
Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Constraints: 0 <= num <= 2^31 - 1
* */

/*
* 英语低于thousand有 hundred，ten and one，高于thousand每3位确定一个单位
* */


public class Main {
    static String blank = "+";
    static String[] digits = {"Thousand", "Million", "Billion"};
    static String[] tens = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten"};
    static String[] aDozen = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    static String[] dozens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};

    // process thousand-level res
    public static StringBuilder thousandLevel(int num) {
        StringBuilder sb = new StringBuilder();
        int hundred = num / 100;
        int dozen = num % 100 / 10;
        int ones = num - 100*hundred - 10*dozen;
        if(hundred > 0 ) {
            sb.append(tens[hundred]).append(blank).append("Hundred");
            if(dozen > 1) {                                                         // 12X
                sb.append(blank).append(dozens[dozen]);
                if(ones > 0) { sb.append(blank).append(tens[ones]); }
            }
            else if(dozen == 0) {
                if(ones > 0) { sb.append(blank).append(tens[ones]); }               // 10X
            }
            else { sb.append(blank).append(aDozen[ones]); }                         // 11X
        } else {                                                                    // 00X
            if(dozen > 1) {                                                         // 02X
                sb.append(dozens[dozen]);
                if(ones > 0) { sb.append(blank).append(tens[ones]); }
            }
            else if(dozen == 0) {
                if(ones > 0) { sb.append(tens[ones]); }                             // 00X
            }
            else if(dozen == 1) { sb.append(aDozen[ones]); }                        // 01X
            else                { sb.append(tens[ones]); }                          // 00X
        }

        return sb;
    }

    public static String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        if(num == 0) {
            sb.append("Zero");
        }
        else {
            int res = -1;
            while (num > 0) {
                res = num / 1000000000; // Billion-level
                if (res > 0) {
                    sb.append(thousandLevel(res)).append(blank).append(digits[2]);
                }
                num = num - res * 1000000000;
                if (num > 0 && res > 0) {
                    sb.append(blank);
                }

                res = num / 1000000; // Million-level
                if (res > 0) {
                    sb.append(thousandLevel(res)).append(blank).append(digits[1]);
                }
                num = num - res * 1000000;
                if (num > 0 && res > 0) {
                    sb.append(blank);
                }

                res = num / 1000; // Thousand-level
                if (res > 0) {
                    sb.append(thousandLevel(res)).append(blank).append(digits[0]);
                }
                num = num - res * 1000;

                if (num > 0 && res > 0) {
                    sb.append(blank);
                }
                res = num; // Ones-level
                sb.append(thousandLevel(res));
                num = 0;
            }
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println("Hello world!");
        String a = numberToWords(0);
        System.out.printf("%d: %s\n", 0, a);
        a = numberToWords(1);
        System.out.printf("%d: %s\n", 1, a);
        a = numberToWords(10);
        System.out.printf("%d: %s\n", 10, a);
        a = numberToWords(11);
        System.out.printf("%d: %s\n", 11, a);
        a = numberToWords(20);
        System.out.printf("%d: %s\n", 20, a);
        a = numberToWords(21);
        System.out.printf("%d: %s\n", 21, a);
        a = numberToWords(101);
        System.out.printf("%d: %s\n", 101, a);
        a = numberToWords(1000);
        System.out.printf("%d: %s\n", 1000, a);
        a = numberToWords(1234);
        System.out.printf("%d: %s\n", 1234, a);
        a = numberToWords(1200);
        System.out.printf("%d: %s\n", 1200, a);
        a = numberToWords(1201);
        System.out.printf("%d: %s\n", 1201, a);
        a = numberToWords(1234567);
        System.out.printf("%d: %s\n", 1234567, a);
        a = numberToWords(Integer.MAX_VALUE);
        System.out.printf("%d: %s\n", Integer.MAX_VALUE, a);
    }
}