package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        if(balance < 5000){
            throw new RuntimeException("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        int flag = 0;
        for(int i = 0 ; i < tradeLicenseId.length()-1 ; i++)
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i + 1))
            {
                flag = 1;
                break;
            }



        if(flag == 1) {
            String s = tradeLicenseId;
            int[] freq = new int[26];

            for (int i = 0 ; i < s.length() ; i++)
            {
                char ch = s.charAt(i);
                freq[ch - 'A']++;
            }

            int maxFreq = Integer.MIN_VALUE;
            int letter = 0;

            for (int i = 0 ; i < 26 ; i++)
            {
                int currFreq = freq[i];

                if (currFreq > maxFreq)
                {
                    maxFreq = currFreq;
                    letter = i;
                }
            }

            if (maxFreq > (s.length() + 1) / 2)
                throw new RuntimeException("Valid License can not be generated");


            int idx = 0;
            char[] ans = new char[s.length()];
            while (maxFreq > 0) {
                ans[idx] = (char) ('A' + letter);
                maxFreq--;
                freq[letter]--;
                idx += 2;
            }
            for (int j = 0; j < freq.length; j++) {
                while (freq[j] > 0) {
                    if (idx >= s.length())
                        idx = 1;
                    ans[idx] = (char) ('A' + j);
                    freq[j]--;
                    idx += 2;
                }
            }
            tradeLicenseId = String.valueOf(ans);
        }
    }
}