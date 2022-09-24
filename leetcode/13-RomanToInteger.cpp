#include <typeinfo>
#include <string.h>
// #include <cstdio>
// #include <cstring>
#include <iostream>
#include <string>
#include <stack>
#include <map>
#include <algorithm>

using namespace std;

/*
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
关键：
1. 通常为从大到小排列，即不可能出现"IVXLCDM"；
2. 只会出现诸如：IV, IX; XL, XC; CD, CM; 这样的前数小于后数，且只会出现一位；

    
*/


string s1 = "III";
string s2 = "LVIII";
string s3 = "MCMXCIV";

int romanToInteger(string s) {
	int res = 0;
	map<char, int> mp {{'I', 1},{'V', 5},{'X', 10},{'L', 50},{'C', 100},{'D', 500},{'M', 1000}};
	for(int i = 0; i < s.size()-1; i++) {
    if(mp[s[i]] < mp[s[i+1]]) {	// 前数小与后数
            res -= mp[s[i]];
    }
    else {
            res += mp[s[i]];
        }
	}
    // for last element in string s
    res += mp[s[s.size()-1]];
	printf("%d\n", res);
    return res;
}

/*
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.
*/

int i1 = 3, i2 = 58, i3 = 1994, i4 = 3999, i5 = 80;

string integerToRoman(int num) {
	string s = "";
	int res = -1;
	while (num > 0) {
		res = num / 1000;
		while(res-- > 0) {
			s.append("M");
		};
		num %= 1000;
		//
		res = num / 900;
		while(res-- > 0) {
			s.append("CM");
		};
		num %= 900;
		res = num / 500;
		while(res-- > 0) {
			s.append("D");
		};
		num %= 500;
		res = num / 400;
		while(res-- > 0) {
			s.append("CD");
		};
		num %= 400;
		res = num / 100;
		while(res-- > 0) {
			s.append("C");
		};
		num %= 100;
		res = num / 90;
		while(res-- > 0) {
			s.append("XC");
		};
		num %= 90;
		res = num / 50;
		while(res-- > 0) {
			s.append("L");
		};
		num %= 50;
		res = num / 40;
		while(res-- > 0) {
			s.append("XL");
		};
		num %= 40;
		res = num / 10;
		while(res-- > 0) {
			s.append("X");
		};
		num %= 10;
		res = num / 9;
		while(res-- > 0) {
			s.append("IX");
		};
		num %= 9;
		res = num / 5;
		while(res-- > 0) {
			s.append("V");
		};
		num %= 5;
		res = num / 4;
		while(res-- > 0) {
			s.append("IV");
		};
		num %= 4;
		//
		res = num / 1;
		while(res-- > 0) {
			s.append("I");
		};
		num %= 1;
	}
	cout << s << endl;
	return s;
}


int main() {

	// romanToInteger(s1);
	// romanToInteger(s2);
	// romanToInteger(s3);

	// integerToRoman(i1);
	// integerToRoman(i2);
	// integerToRoman(i3);
	integerToRoman(i4);
	integerToRoman(i5);
}



