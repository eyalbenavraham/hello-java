package bom;

import java.util.*;

public class RomanNumbersToDecimal {
    private int stat = 0;
    private int statLevel = -1;
    private int n = 0;
    private int pos = 0;
    private String romanStr = "";

    private final String [] level0 = new String[]{"i","v","x"};
    private final String [] level1 = new String[]{"x","l","c"};
    private final String [] level2 = new String[]{"c","d","m"};
    private final String [] level3 = new String[]{"m","w","t"};

    private List<String []> levels = Arrays.asList(level0, level1, level2, level3);
    public RomanNumbersToDecimal(String romanStr) {
        this.romanStr = romanStr;
    }

    public int tonum() throws Exception {
        n = 0;

        int nCurrentLevel = levels.size() -1;
        int factor = (int) Math.pow(10, nCurrentLevel);
        String []digits0_9 = buildDigitsArrayForLevel(levels.get(nCurrentLevel));
        while (nCurrentLevel >= 0) {
            String decElement = getNextDecElement(digits0_9);
            if (null != decElement) {
                pos += decElement.length();
                setN(getN() + (decElementToInt(decElement, digits0_9) * factor));
            }
            if (--nCurrentLevel >= 0) {

                factor = (int) Math.pow(10, nCurrentLevel);
                digits0_9 = buildDigitsArrayForLevel(levels.get(nCurrentLevel));
            }
        }
        isDone();
        return getN();
    }

    private void isDone() throws Exception {
        if (pos != romanStr.length()) {
            throw new Exception("parsing fail for " + romanStr + ", position " + pos);
        }
    }

    private int decElementToInt(String decElement, String []digits0_9) {
        Map<String, Integer> m = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            m.put(digits0_9[i], i);
        }
        Integer r = m.get(decElement);
        return (r != null ? r.intValue() : 0);

    }

    private String getNextDecElement(String []digits0_9) {
        int remainLength = getRomanStr().length() - getPos();
        switch (remainLength) {
            case 0:
                return null;
            case 1:
                String tmp1 = getRomanStr().substring(pos, pos + 1);
                return getDecElement(tmp1, digits0_9);
            case 2:
                String tmp2 = getRomanStr().substring(pos, pos + 2);
                return getDecElement(tmp2, digits0_9);
            case 3:
                String tmp3 = getRomanStr().substring(pos, pos + 3);
                return getDecElement(tmp3, digits0_9);
            default:
                String tmp4 = getRomanStr().substring(pos, pos + 4);
                return getDecElement(tmp4, digits0_9);
        }
    }

    private String[] buildDigitsArrayForLevel(String []levelDigits) {
        String [] ret = new String[10];
        String i = levelDigits[0];
        String v = levelDigits[1];
        String x = levelDigits[2];
        int j = 0;
        ret[j++] = "";
        ret[j++] = i;
        ret[j++] = i + i;
        ret[j++] = i + i + i;
        ret[j++] = i + v;
        ret[j++] = v;
        ret[j++] = v + i;
        ret[j++] = v + i + i;
        ret[j++] = v + i + i + i;
        ret[j++] = i + x;

        return ret;
    }

    private String getDecElement(String istr, String []digits0_9) {
        if (istr.equalsIgnoreCase(digits0_9[8])) {
            return istr;
        }
        String s = istr.toLowerCase(Locale.ROOT);
        if (s.length() >= 3) {
            s = s.substring(0,3);
            List<String> offLen3 = Arrays.asList(digits0_9[3], digits0_9[7]);
            if (offLen3.contains(s)) {
                return s;
            }
        }
        if (s.length() >= 2) {
            s = s.substring(0, 2);
            List<String> offLen2 = Arrays.asList(digits0_9[2], digits0_9[4],digits0_9[6], digits0_9[7]);
            if (offLen2.contains(s)) {
                return s;
            }
        }
        if (s.length() >= 1) {
            s = s.substring(0, 1);
            List<String> offLen2 = Arrays.asList(digits0_9[1], digits0_9[5]);
            if (offLen2.contains(s)) {
                return s;
            }
        }
        return null;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getRomanStr() {
        return romanStr;
    }

    public void setRomanStr(String romanStr) {
        this.romanStr = romanStr;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public static void main(String [] argv) {
        String [] tests = new String[]{"i","x","xiiii", "xkiv", "miv", "mmlii"};
        for (String test : tests) {
            try {
                RomanNumbersToDecimal me = new RomanNumbersToDecimal(test);
                System.out.println(test + " ::: " + me.tonum());
            } catch (Exception e) {

               // e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}

