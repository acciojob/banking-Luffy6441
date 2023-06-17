package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId;

    public CurrentAccount() {
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000)
            throw new RuntimeException("Insufficient Balance");

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
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        char[] chars = tradeLicenseId.toCharArray();
        int n = chars.length;

        for (int i = 1; i < n; i += 2)
            if (chars[i] == chars[i - 1]) {
                for (int j = i + 1; j < n; j++) {
                    if (chars[j] != chars[i - 1]) {
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;
                        break;
                    }
                }

                if (chars[i] == chars[i - 1])
                    throw new RuntimeException("Valid License can not be generated");

            }


        this.tradeLicenseId = new String(chars);
    }

}