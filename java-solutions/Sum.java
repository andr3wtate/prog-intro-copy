public class Sum {
    public static void main(String[] args) {
        if (args.length == 0) {
	    System.out.println(0);
	    return;
        }
        int ans = 0;
        for (int i = 0; i < args.length; i++) {
	    String curnumber = "";
            for (int j = 0; j < args[i].length(); j++) {
		if (!Character.isWhitespace(args[i].charAt(j))) {
		    curnumber = curnumber + args[i].charAt(j); 
		}
		if (Character.isWhitespace(args[i].charAt(j)) || j == args[i].length() - 1) {
		    if (curnumber.length() != 0) {
		        ans += Integer.parseInt(curnumber);
		    }
		    curnumber = "";
		}
            }
        }
	System.out.println(ans);
    }
}
