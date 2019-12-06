package LooCode;

public class test4 {

    //回文是一个正读和反读都一样的字符串

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        System.out.println(longestPalindrome("abcaabbbbbbbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaabbbaaaaaaaaaaaaaaaaabbbbdfasdfasdnfjsadfnasdjnfbjadsfj" +
                "dfgssssssssssssssgadfsgasdfsdfasdfasfdsaffdsgfdgdgdsfgfsdgdfsgdfsgdfsglasdfjkglajsdflgjsladfjkljsdoiferaonrflkasdfasudflnasdfnlskdaflfdl" +
                "sdfadshflkjadshfkljasdhfuiuoasdfowernfasodhjfiasdnkfnasdlkfhsdlkjfyyhsdklfhasdoif" +
                "sdfhasduifhasdkljfnjsdkhfasdkldddddddddddddddddddddddddddddddddddffffffffffffffffdssssssssssssssaaaaaaaaaaaaaasdffffffffffffffdsssssssssssss" +
                "sdkfjhasdiofueirniskdfjhsdklfnsdkfjasidhfsadjfnsdaf;sdlflksdfffffffffffffffffdaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "dfnas;dfadsfmsdfmdslkfji4-09ri09rj234i4u32804o34-1238491024jvi n3u4jjdklfjasd98ijfklasdfna[09euf098adsijfdnfiads jnf0sd9fu8 rfdnfdnsf" +
                "adfjnasdfuhaiajr9fiodufemniasniuelifjenuernksoelij" +
                "dfaskdfjoiaeu908234ijdifsdkfjdkalosdifqliuinf8eaef veraerfaefaaereqawfefiej9023j41091i2-30238194urjeifjaes v dhfav9eurv 9e0ura0e5hrvi " +
                "dfauis nvneiury89ewbasd6fdgsfdg"));
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }


}
