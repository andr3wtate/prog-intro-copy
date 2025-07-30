import java.math.BigInteger;

public class SumBigIntegerPunct {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(0);
            return;
        }
        BigInteger ans = new BigInteger("0");
        for (int i = 0; i < args.length; i++) {
            StringBuilder curnumber = new StringBuilder("");
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.getType(args[i].charAt(j)) != Character.START_PUNCTUATION
		   && Character.getType(args[i].charAt(j)) != Character.END_PUNCTUATION
		   && !Character.isWhitespace(args[i].charAt(j))) {
                    curnumber.append(args[i].charAt(j));
                }
                if (Character.getType(args[i].charAt(j)) == Character.START_PUNCTUATION
		   || Character.getType(args[i].charAt(j)) == Character.END_PUNCTUATION
		   || Character.isWhitespace(args[i].charAt(j)) || j == args[i].length() - 1) {
                    if (curnumber.length() != 0) {
                        BigInteger cur = new BigInteger(curnumber.toString());
                        ans = ans.add(cur);
                    }
                    curnumber.delete(0, curnumber.length());
                }
            }
        }
        System.out.println(ans);
    }
}
