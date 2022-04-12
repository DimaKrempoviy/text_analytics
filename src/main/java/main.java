import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class wordFreq {
    private static final Scanner in = new Scanner(System.in);
    private static String[] w = null;
    private static int[] r = null;
    public static void main(String[] args) {
        System.out.println("Absolute path to the file:  ");
        String fileName = in.next();
        System.out.println("Choose one of the following actions: ");
        System.out.println("1) top N most used words ");
        System.out.println("2) top N most used characters");
        System.out.println("3) analytics by words (how many which word occurs in percentage and their number)");
        System.out.println("4) character analytics (how many and how many characters occur in percentage)");
        System.out.println("5) save analytics");
        int number = in.nextInt();
        switch (number) {
            case 1:
            try {
                System.out.println("Enter 'N' value: ");
                int n = in.nextInt();
                w = new String[n];
                r = new int[n];
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String text = "";
                String sz = null;
                while ((sz = br.readLine()) != null) {
                    text = text.concat(sz);
                }
                String[] words = text.split(" ");
                String[] uniqueLabels;
                int count = 0;
                uniqueLabels = getUniqLabels(words);
                for (int j = 0; j < n; j++) {
                    r[j] = 0;
                }
                for (String l : uniqueLabels) {
                    if ("".equals(l) || null == l) {
                        break;
                    }
                    for (String s : words) {
                        if (l.equals(s)) {
                            count++;
                        }
                    }

                    for (int i = 0; i < n; i++) {
                        if (count > r[i]) {
                            r[i] = count;
                            w[i] = l;
                            break;
                        }
                    }
                    count = 0;
                }
                display(n);
            } catch (Exception e) {
                System.err.println("ERR " + e.getMessage());
            }
            break;

            case 2:
        }
    }

    public static void display(int n){
        for(int k=0; k<n; k++){
            System.out.println("Label :: "+w[k]+"\tCount :: "+r[k]);
        }
    }

    private static String[] getUniqLabels(String[] keys)
    {
        String[] uniqueKeys = new String[keys.length];

        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;

        for(int i=1; i<keys.length ; i++)
        {
            for(int j=0; j<=uniqueKeyIndex; j++)
            {
                if(keys[i].equals(uniqueKeys[j]))
                {
                    keyAlreadyExists = true;
                }
            }

            if(!keyAlreadyExists)
            {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }

}