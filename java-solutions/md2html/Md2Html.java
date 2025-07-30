package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Md2Html {

    public static boolean isMarkup(char x) {
        return x == '*' || x == '_' || x == '-' || x == '`';
    }

    public static String symb(char x) {
        return switch (x) {
            case '<' -> "&lt;";
            case '>' -> "&gt;";
            case '&' -> "&amp;";
            default -> String.valueOf(x);
        };
    }

    public static boolean isSpace(String s, int ind) {
        return ind < 0 || ind >= s.length() || Character.isWhitespace(s.charAt(ind));
    }

    public static String getMd(String s, boolean fl) {
        StringBuilder ans = new StringBuilder("<");
        if (fl) {
            ans.append("/");
        }
        switch (s) {
            case "*", "_" -> ans.append("em");
            case "**", "__" -> ans.append("strong");
            case "--" -> ans.append("s");
            case "`" -> ans.append("code");
        }
        ans.append(">");
        return ans.toString();
    }

    public static void write(String s, int hrefType, StringBuilder hrefText, StringBuilder href, BufferedWriter out) throws IOException {
        switch (hrefType) {
            case 1 -> hrefText.append(s);
            case 2 -> href.append(s);
            default -> out.write(s);
        }
    }

    public static List<Integer> find(ArrayList <String> all, List <Integer> l, char x) {
        if (l.get(0) == Integer.MAX_VALUE) {
            return List.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        int ind1 = l.get(0), j = l.get(1);
        for (; ind1 < all.size(); ind1++) {
            boolean fl1 = false;
            for (; j < all.get(ind1).length(); j++) {
                if (all.get(ind1).charAt(j) == x && (j == 0 || all.get(ind1).charAt(j - 1) != '\\')) {
                    fl1 = true;
                    break;
                }
            }
            if (fl1) {
                if (x != '>') {
                    j++;
                    if (j == all.get(ind1).length()) {
                        ind1++;
                        j = 0;
                    }
                }
                return List.of(ind1, j);
            }
            j = 0;
        }
        return List.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        ArrayList<String> all = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    all.add(s);
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
            try {
                HashMap<String, Boolean> fl = new HashMap<>();
                String s;
                StringBuilder hrefText = new StringBuilder();
                StringBuilder href = new StringBuilder();
                boolean newParagraph = true, isFirst = true, isHref = false;
                int hrefType = 0;
                int h = -1;
                for (int ind = 0; ind < all.size(); ind++) {
                    s = all.get(ind);
                    if (s.isEmpty()) {
                        newParagraph = true;
                        if (h > 0) {
                            write("</h" + h + ">", hrefType, hrefText, href, out);
                        } else if (h == 0) {
                            write("</p>", hrefType, hrefText, href, out);
                        }
                        h = -1;
                        while (ind < all.size() && all.get(ind).isEmpty()) {
                            ind++;
                        }
                        if (ind >= all.size()) {
                            break;
                        }
                        s = all.get(ind);
                    }
                    if (!isFirst) {
                        write(System.lineSeparator(), hrefType, hrefText, href, out);
                    }
                    isFirst = false;
                    int i = 0;
                    if (newParagraph) {
                        while (i < s.length() && s.charAt(i) == '#') {
                            i++;
                        }
                        if (i != 0 && i < s.length() && Character.isWhitespace(s.charAt(i))) {
                            write("<h" + i + ">", hrefType, hrefText, href, out);
                            h = i;
                            i++;
                        } else {
                            write("<p>", hrefType, hrefText, href, out);
                            i = 0;
                            h = 0;
                        }
                    }
                    newParagraph = false;
                    for (; i < s.length(); i++) {
                        if (s.charAt(i) == '[' && (i == 0 || s.charAt(i - 1) != '\\') && hrefType == 0) {
                            isHref = true;
                            List<Integer> l = find(all, find(all, find(all, List.of(ind, i), ']'), '<'), '>');
                            if (!(l.getFirst() < all.size())) {
                                isHref = false;
                                out.write("[");
                                continue;
                            }
                            hrefType = 1;
                            hrefText.append('[');
                            continue;
                        }
                        if (isHref && s.charAt(i) == ']' && (i == 0 || s.charAt(i - 1) != '\\') && hrefType == 1) {
                            hrefText.append(']');
                            i++;
                            href.append('<');
                            hrefType = 2;
                            continue;
                        }
                        if (isHref && s.charAt(i) == '>' && (i == 0 || s.charAt(i - 1) != '\\') && hrefType == 2) {
                            out.write("<a href='" + href.substring(1, href.length()) + "'>" + hrefText.substring(1, hrefText.length() - 1) + "</a>");
                            hrefType = 0;
                            href = new StringBuilder();
                            hrefText = new StringBuilder();
                            isHref = false;
                            continue;
                        }
                        if (isHref && hrefType == 2) {
                            write(String.valueOf(s.charAt(i)), hrefType, hrefText, href, out);
                            continue;
                        }
                        if (s.charAt(i) == '\\' && i + 1 < s.length() && (isMarkup(s.charAt(i + 1)) || s.charAt(i + 1) == '[' || s.charAt(i + 1) == ']')) {
                            write(symb(s.charAt(i + 1)), hrefType, hrefText, href, out);
                            i++;
                            continue;
                        } else if (s.charAt(i) == '\\') {
                            write(symb(s.charAt(i + 1)), hrefType, hrefText, href, out);
                            i++;
                            continue;
                        }
                        if (isMarkup(s.charAt(i))) {
                            int iPrev = i;
                            String s1 = String.valueOf(s.charAt(i));
                            if (i + 1 < s.length() &&
                                    isMarkup(s.charAt(i + 1)) &&
                                    s.charAt(i) == s.charAt(i + 1) &&
                                    s.charAt(i) != '`') {
                                s1 += s.charAt(i + 1);
                                i++;
                            }
                            if (s1.length() == 1 && Character.getType(s1.charAt(0)) == Character.DASH_PUNCTUATION
                                    || isSpace(s, i + 1) && isSpace(s, iPrev - 1)) {
                                write(symb(s.charAt(i)), hrefType, hrefText, href, out);
                                continue;
                            }
                            if (!fl.containsKey(s1)) {
                                fl.put(s1, true);
                            }
                            fl.put(s1, !fl.get(s1));
                            write(getMd(s1, fl.get(s1)), hrefType, hrefText, href, out);
                            continue;
                        }
                        write(symb(s.charAt(i)), hrefType, hrefText, href, out);
                    }
                }
                if (h > 0) {
                    write("</h" + h + ">", hrefType, hrefText, href, out);
                } else if (h == 0) {
                    write("</p>", hrefType, hrefText, href, out);
                }
            }
            finally {
                out.close();
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

