import java.util.Scanner;

/*public class PythagoreanNumerology {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your full birth name: ");
        String name = sc.nextLine();
        System.out.print("Enter your birthdate in the format DD/MM/YYYY: ");
        String dob = sc.nextLine();

        // Calculate Life Path Number
        int lifePathNum = calculateLifePathNum(dob);
        System.out.println("Your Life Path Number is: " + lifePathNum);

        // Calculate Expression Number
        int exprNum = calculateExpressionNum(name);
        System.out.println("Your Expression Number is: " + exprNum);

        // Calculate Soul Urge Number
        int soulUrgeNum = calculateSoulUrgeNum(name);
        System.out.println("Your Soul Urge Number is: " + soulUrgeNum);

        // Calculate Pythagorean Number
        int pythagoreanNum = calculatePythagoreanNum(name);
        System.out.println("Your Pythagorean Number is: " + pythagoreanNum);
    }

    // Calculate Life Path Number
    public static int calculateLifePathNum(String dob) {
        String[] parts = dob.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        int sum = day + month + year;
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

    // Calculate Expression Number
    public static int calculateExpressionNum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = Character.toUpperCase(name.charAt(i));
            if (Character.isLetter(c)) {
                sum += getPythagoreanNum(c);
            }
        }
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

    // Calculate Soul Urge Number
    public static int calculateSoulUrgeNum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = Character.toUpperCase(name.charAt(i));
            if (isVowel(c)) {
                sum += getVowelNum(c);
            }
        }
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

//    // Calculate Pythagorean Number
//    public static int calculatePythagoreanNum(String name) {
//        int num = 0;
//        for (char c : name.toUpperCase().toCharArray()) {
//            num += getPythagoreanNum(c);
//        }
//        while (num > 9) {
//            num = getPythagoreanNum(Integer.toString(num).charAt(0)) + getPythagoreanNum(Integer.toString(num).charAt(1));
//        }
//        return num;
//    }

    // Get Pythagorean Numerology value for a letter
    public static int getPythagoreanNum(char c) {
        switch (c) {
            case 'A': return 1;
            case 'B': return 2;
            case 'C': return 3;
            case 'D': return 4;
            case 'E': return 5;
            case 'F': return 6;
            case 'G': return 7;
            case 'H': return 8;
            case 'I': return 9;
            case 'J': return 1;
            case 'K': return 2;
            case 'L': return 3;
            case 'M': return 4;
            case 'N': return 5;
            case 'O': return 6;
            case 'P': return 7;
            case 'Q': return 8;
            case 'R': return 9;
            case 'S': return 1;
            case 'T': return 2;
            case 'U': return 3;
            case 'V': return 4;
            case 'W': return 5;
            case 'X': return 6;
            case 'Y': return 7;
            case 'Z': return 8;
            default: return 0;
        }
    }

    // Calculate Pythagorean Numerology number for a name
    public static int calculatePythagoreanNum(String name) {
        int num = 0;
        for (char c : name.toUpperCase().toCharArray()) {
            num += getPythagoreanNum(c);
        }
        while (num > 9) {
            num = getPythagoreanNum(Integer.toString(num).charAt(0)) + getPythagoreanNum(Integer.toString(num).charAt(1));
        }
        return num;
    }

// public static void main(String[] args) {
//     String name = "JOHN DOE";
//     int pythagoreanNum = calculatePythagoreanNum(name);
//     System.out.println("The Pythagorean number for the name " + name + " is " + pythagoreanNum);
// }

    // Returns true if the given character is a vowel
    public static boolean isVowel(char c) {
        char vowel = Character.toUpperCase(c);
        return vowel == 'A' || vowel == 'E' || vowel == 'I' || vowel == 'O' || vowel == 'U';
    }

    // Returns the vowel number for a given vowel character
// Assumes the given character is a vowel
    public static int getVowelNum(char c) {
        char vowel = Character.toUpperCase(c);
        switch (vowel) {
            case 'A':
            case 'J':
            case 'S':
                return 1;
            case 'E':
            case 'N':
            case 'T':
                return 5;
            case 'I':
            case 'O':
            case 'Z':
                return 9;
            case 'U':
                return 3;
            default:
                return 0;
        }
    }


}
*/
public class PythagoreanNumerology {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your full birth name: ");
        String name = sc.nextLine();
        System.out.print("Enter your birthdate in the format DD/MM/YYYY: ");
        String dob = sc.nextLine();

        // Calculate Life Path Number
        int lifePathNum = calculateLifePathNum(dob);
        System.out.println("Your Life Path Number is: " + lifePathNum);

        // Calculate Expression Number
        int exprNum = calculateExpressionNum(name);
        System.out.println("Your Expression Number is: " + exprNum);

        // Calculate Soul Urge Number
        int soulUrgeNum = calculateSoulUrgeNum(name);
        System.out.println("Your Soul Urge Number is: " + soulUrgeNum);

        // Calculate Pythagorean Number
        int pythagoreanNum = calculatePythagoreanNum(name);
        System.out.println("Your Pythagorean Number is: " + pythagoreanNum);
    }

    // Calculate Life Path Number
    public static int calculateLifePathNum(String dob) {
        String[] parts = dob.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        int sum = day + month + year;
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

    // Calculate Expression Number
    public static int calculateExpressionNum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = Character.toUpperCase(name.charAt(i));
            if (Character.isLetter(c)) {
                sum += getPythagoreanNum(c);
            }
        }
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

    // Calculate Soul Urge Number
    public static int calculateSoulUrgeNum(String name) {
        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = Character.toUpperCase(name.charAt(i));
            if (isVowel(c)) {
                sum += getVowelNum(c);
            }
        }
        while (sum > 9) {
            sum = sum / 10 + sum % 10;
        }
        return sum;
    }

//    // Calculate Pythagorean Number
//    public static int calculatePythagoreanNum(String name) {
//        int num = 0;
//        for (char c : name.toUpperCase().toCharArray()) {
//            num += getPythagoreanNum(c);
//        }
//        while (num > 9) {
//            num = getPythagoreanNum(Integer.toString(num).charAt(0)) + getPythagoreanNum(Integer.toString(num).charAt(1));
//        }
//        return num;
//    }

    // Get Pythagorean Numerology value for a letter
    public static int getPythagoreanNum(char c) {
        /*switch (c) {
            case 'A': return 1;
            case 'B': return 2;
            case 'C': return 3;
            case 'D': return 4;
            case 'E': return 5;
            case 'F': return 6;
            case 'G': return 7;
            case 'H': return 8;
            case 'I': return 9;
            case 'J': return 1;
            case 'K': return 2;
            case 'L': return 3;
            case 'M': return 4;
            case 'N': return 5;
            case 'O': return 6;
            case 'P': return 7;
            case 'Q': return 8;
            case 'R': return 9;
            case 'S': return 1;
            case 'T': return 2;
            case 'U': return 3;
            case 'V': return 4;
            case 'W': return 5;
            case 'X': return 6;
            case 'Y': return 7;
            case 'Z': return 8;
            default: return 0;
        }*/

        switch (c) {
            case 'A': return 1;
            case 'B': return 2;
            case 'C': return 3;
            case 'D': return 4;
            case 'E': return 5;
            case 'F': return 6;
            case 'G': return 7;
            case 'H': return 8;
            case 'I': return 9;
            case 'J': return 1;
            case 'K': return 2;
            case 'L': return 3;
            case 'M': return 4;
            case 'N': return 5;
            case 'O': return 6;
            case 'P': return 7;
            case 'Q': return 8;
            case 'R': return 9;
            case 'S': return 1;
            case 'T': return 2;
            case 'U': return 3;
            case 'V': return 4;
            case 'W': return 5;
            case 'X': return 6;
            case 'Y': return 7;
            case 'Z': return 8;
            default: return 0;
        }


    }

    // Calculate Pythagorean Numerology number for a name
//    public static int calculatePythagoreanNum(String name) {
//        int num = 0;
//        for (char c : name.toUpperCase().toCharArray()) {
//            num += getPythagoreanNum(c);
//        }
//        while (num > 9) {
//            num = getPythagoreanNum(Integer.toString(num).charAt(0)) + getPythagoreanNum(Integer.toString(num).charAt(1));
//        }
//        return num;
//    }

// public static void main(String[] args) {
//     String name = "JOHN DOE";
//     int pythagoreanNum = calculatePythagoreanNum(name);
//     System.out.println("The Pythagorean number for the name " + name + " is " + pythagoreanNum);
// }

    // Returns true if the given character is a vowel
    /*public static boolean isVowel(char c) {
        char vowel = Character.toUpperCase(c);
        return vowel == 'A' || vowel == 'E' || vowel == 'I' || vowel == 'O' || vowel == 'U';
    }

    // Returns the vowel number for a given vowel character
// Assumes the given character is a vowel
    public static int getVowelNum(char c) {
        char vowel = Character.toUpperCase(c);
        switch (vowel) {
            case 'A':
            case 'J':
            case 'S':
                return 1;
            case 'E':
            case 'N':
            case 'T':
                return 5;
            case 'I':
            case 'O':
            case 'Z':
                return 9;
            case 'U':
                return 3;
            default:
                return 0;
        }
    }*/



    // Check if a character is a vowel
    public static boolean isVowel(char c) {
        return "AEIOUY".indexOf(Character.toUpperCase(c)) != -1;
    }

    // Get Pythagorean Numerology value for a vowel
    public static int getVowelNum(char c) {
        int num = getPythagoreanNum(c);
        return isVowel(c) ? num : 0;
    }

    // Calculate Pythagorean Numerology for a name
    public static int calculatePythagoreanNum(String name) {
        int num = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isLetter(c)) {
                num += getPythagoreanNum(c);
            }
        }
        while (num > 9) {
            num = num / 10 + num % 10;
        }
        return num;
    }



}

