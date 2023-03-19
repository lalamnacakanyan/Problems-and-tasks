package codeSignal;


import java.util.Arrays;
import java.util.HashSet;

public class Tasks {
    //task 23
    //Last night you partied a little too hard. Now there's a black and white photo of you that's about to go viral! You can't let this ruin your reputation, so you want to apply the box blur algorithm to the photo to hide its content.
    //The pixels in the input image are represented as integers. The algorithm distorts the input image in the following way: Every pixel x in the output image has a value equal to the average value of the pixel values from the 3 × 3 square that has its center at x, including x itself. All the pixels on the border of x are then removed.
    //Return the blurred image as an integer, with the fractions rounded down.
    public static int[][] makeBoxBlur(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        int[][] outputArray = new int[rows - 2][cols - 2];

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int sum = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        sum += image[k][l];

                    }
                }
                outputArray[i - 1][j - 1] = sum / 9;
            }
        }
        return outputArray;
    }

    //task 22
    //You are given an array of integers representing coordinates of obstacles situated on a straight line.
    //Assume that you are jumping from the point with coordinate 0 to the right. You are allowed only to make jumps of the same length represented by some integer.
    // Find the minimal length of the jump enough to avoid all the obstacles.
    public static int minLength(int[] inputArray) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int max = inputArray[0];
        for (int i = 0; i < inputArray.length; i++) {
            hs.add(inputArray[i]);
            max = Math.max(max, inputArray[i]);
        }
        for (int i = 1; i <= max; i++) {
            int j;
            for (j = i; j <= max; j = j + i) {

                if (hs.contains(j))
                    break;
            }
            if (j > max)
                return i;
        }
        return max + 1;
    }


    //task 21
    //An IP address is a numerical label assigned to each device (e.g., computer, printer) participating in a computer network that uses the Internet Protocol for communication. There are two versions of the Internet protocol, and thus two versions of addresses. One of them is the IPv4 address.
    //Given a string, find out if it satisfies the IPv4 address naming rules.
    public static boolean findIPv4Address(String inputString) {
        String[] parts = inputString.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 255) {
                    return false;
                }
                if (part.length() > 1 && part.startsWith("0")) {
                    return false;
                }

            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    //task 20
    //Given an array of integers, find the maximal absolute difference between any two of its adjacent elements.
    public static int findArrayMaximalAdjacentDifference(int[] inputArray) {
        int diff[] = new int[2 * (inputArray.length - 1)];
        int j = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (i == 0 && i + 1 < inputArray.length) {
                diff[j] += inputArray[i] - inputArray[i + 1];
                j++;
            }
            if (i - 1 >= 0 && i + 1 < inputArray.length) {
                diff[j] += inputArray[i] - inputArray[i - 1];
                j++;
                diff[j] += inputArray[i] - inputArray[i + 1];
                j++;
            }
            if (i == inputArray.length - 1) {
                diff[j] += inputArray[i] - inputArray[i - 1];
                j++;
            }
        }
        Arrays.sort(diff);

        return diff[diff.length - 1];
    }


    //task 19
    //Call two arms equally strong if the heaviest weights they each are able to lift are equal.
    //Call two people equally strong if their strongest arms are equally strong (the strongest arm can be both the right and the left), and so are their weakest arms.
    //Given your and your friend's arms' lifting capabilities find out if you two are equally strong.

    public static boolean findTwoEquallyStrong(int yourLeft, int yourRight, int friendsLeft, int friendsRight) {
        if ((yourLeft == friendsLeft && yourRight == friendsRight) ||
                (yourLeft == friendsRight && yourRight == friendsLeft) &&
                        yourLeft + yourRight == friendsLeft + friendsRight) {
            return true;
        }
        return false;
    }

    //task 18
    // Given a string, find out if its characters can be rearranged to form a palindrome.
    public static boolean findPalindrome(String inputString) {
        if (inputString.length() == 1) {
            return true;
        }
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);

            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        if (set.size() <= 1) {
            return true;
        }
        return false;
    }

    //task 17
    //You are given an array of integers. On each move you are allowed to increase exactly one of its element by one. Find the minimal number of moves required to obtain a strictly increasing sequence from the input.

    public static int findMinimalNumber(int[] inputArray) {
        int count = 0;

        for (int i = 1; i < inputArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (inputArray[i] <= inputArray[j]) {
                    count += inputArray[j] - inputArray[i] + 1;
                    inputArray[i] = inputArray[j] + 1;

                }
            }
        }
        return count;
    }

    //task 16
    // Two arrays are called similar if one can be obtained from another by swapping at most one pair of elements in one of the arrays.
    // Given two arrays a and b, check whether they are similar.
    public static boolean checkSimilar(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }

        int count = 0;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                count++;
                if (index1 == -1) {
                    index1 = i;
                } else if (index2 == -1) {
                    index2 = i;
                } else {
                    return false;
                }
            }
        }
        return (count == 0 || (count == 2 && a[index1] == b[index2] && a[index2] == b[index1]));
    }

    //task 15
    //Given a rectangular matrix of characters, add a border of asterisks(*) to it.
    public static String[] addBorder(String[] picture) {
        int rows = picture.length;
        int cols = picture[0].length();

        String[] pictureBorder = new String[rows + 2];

        String border = "";
        for (int i = 0; i < cols + 2; i++) {
            border += "*";
        }
        pictureBorder[0] = border;
        pictureBorder[rows + 1] = border;

        for (int i = 0; i < rows; i++) {
            pictureBorder[i + 1] = "*" + picture[i] + "*";
        }
        return pictureBorder;
    }

    //task 14
    //Several people are standing in a row and need to be divided into two teams. The first person goes into team 1, the second goes into team 2, the third goes into team 1 again, the fourth into team 2, and so on.
    //You are given an array of positive integers - the weights of the people. Return an array of two integers, where the first element is the total weight of team 1, and the second element is the total weight of team 2 after the division is complete.
    public static int[] alternatingSums(int[] a) {
        int team1 = 0, team2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) {
                team1 += a[i];
            } else {
                team2 += a[i];
            }
        }
        int[] alternatingSum = {team1, team2};
        return alternatingSum;
    }

    //task 13
    //Write a function that reverses characters in (possibly nested) parentheses in the input string.
    //Input strings will always be well-formed with matching ()s.
    public static String reverseInParentheses(String inputString) {
        int firstInd = inputString.lastIndexOf("(");
        int lastInd = inputString.indexOf(")", firstInd);

        while (firstInd != -1) {
            String revStr = new StringBuilder(inputString.substring(firstInd + 1, lastInd)).reverse().toString();
            String first = inputString.substring(0, firstInd);
            String last = inputString.substring(lastInd + 1);

            inputString = first + revStr + last;
            firstInd = inputString.lastIndexOf("(");
            lastInd = inputString.indexOf(")", firstInd);
        }
        return inputString;
    }

    //task 12
    //Some people are standing in a row in a park. There are trees between them which cannot be moved. Your task is to rearrange the people by their heights in a non-descending order without moving the trees. People can be very tall!
    public static int[] sortingByHeight(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                count++;
            }
        }
        int[] positivDigits = new int[count];
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                positivDigits[k++] += a[i];
            }
        }
        Arrays.sort(positivDigits);
        int[] output = new int[a.length];
        int j = 0;
        int l = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                output[j] = a[i];
            } else {
                output[j] = positivDigits[l];
                l++;
            }
            j++;
        }
        return output;
    }

    //task 11
    //Ticket numbers usually consist of an even number of digits. A ticket number is considered lucky if the sum of the first half of the digits is equal to the sum of the second half.
    //Given a ticket number n, determine if it's lucky or not.
    public static boolean findLuckyTicket(int n) {
        String strn = Integer.toString(n);
        int[] nArray = new int[strn.length()];

        for (int i = 0; i < strn.length(); i++) {
            nArray[i] = Character.getNumericValue(strn.charAt(i));
        }
        int sum1 = 0, sumAll = 0;
        for (int i = 0; i < nArray.length; i++) {
            sumAll += nArray[i];
        }
        for (int i = 0; i < nArray.length / 2; i++) {
            sum1 += nArray[i];
        }
        if (sumAll - sum1 != sum1) {
            return false;
        }
        return true;
    }

    //task 10
    //Given two strings, find the number of common characters between them.
    public static int findNumber(String s1, String s2) {
        String min = null, max = null;

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.length() > s2.length()) {
                    min = s1;
                    max = s2;
                } else {
                    max = s1;
                    min = s2;
                }
            }
        }

        char[] charArraymin = min.toCharArray();
        char[] charArraymax = max.toCharArray();


        int count = 0;
        char change = ' ';

        for (int i = 0; i < charArraymin.length; i++) {
            for (int j = 0; j < charArraymax.length; j++) {
                if (charArraymin[i] == charArraymax[j]) {
                    count++;
                    charArraymin[i] = change;
                    charArraymax[j] = change;
                    break;
                }
            }
        }
        return count;
    }

    //task 9
    // Given an array of strings, return another array containing all of its longest strings.
    public static String[] returnAllLongestStrings(String[] inputArray) {
        if (inputArray.length == 1) {
            return inputArray;
        } else {
            int[] longItems = new int[inputArray.length];
            int k = 0;
            for (String str : inputArray) {
                int length = str.length();
                longItems[k++] = length;
            }
            Arrays.sort(longItems);
            int longItem = longItems[inputArray.length - 1];
            int count = 0;
            for (int item : longItems) {
                if (item == longItem) {
                    count++;
                }
            }
            String[] outputArray = new String[count];
            Arrays.fill(outputArray, "");
            int i = 0;
            for (String s : inputArray) {
                if (s.length() == longItem) {
                    outputArray[i++] += s;
                }
            }
            return outputArray;
        }
    }

    //task 8
    //After becoming famous, the CodeBots decided to move into a new building together. Each of the rooms has a different cost, and some of them are free, but there's a rumour that all the free rooms are haunted! Since the CodeBots are quite superstitious, they refuse to stay in any of the free rooms, or any of the rooms below any of the free rooms.
    //Given matrix, a rectangular matrix of integers, where each value represents the cost of the room, your task is to return the total sum of all rooms that are suitable for the CodeBots (ie: add up all the values that don't appear below a 0).
    public static int matrixElementsSum(int[][] matrix) {
        int sumAll = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                sumAll += matrix[i][j];
                if (matrix[i][j] == 0) {
                    break;
                }
            }
        }
        return sumAll;
    }

    //task 7
    //Given a sequence of integers as an array, determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.
    //Note: sequence a0, a1, ..., an is considered to be a strictly increasing if a0 < a1 < ... < an. Sequence containing only one element is also considered to be strictly increasing.
    public static boolean almostIncreasingSequence(int[] sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length - 1; i++) {
            if (i + 1 < sequence.length && sequence[i] >= sequence[i + 1]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
            if (i + 2 <= sequence.length - 1
                    && sequence[i] >= sequence[i + 2]
                    && i - 1 >= 0
                    && sequence[i - 1] >= sequence[i + 1]) {
                return false;
            }
        }
        return true;
    }


    //task 6
    //Ratiorg got statues of different sizes as a present from CodeMaster for his birthday, each statue having an non-negative integer size. Since he likes to make things perfect, he wants to arrange them from smallest to largest so that each statue will be bigger than the previous one exactly by 1. He may need some additional statues to be able to accomplish that. Help him figure out the minimum number of additional statues needed.
    public static int makeArrayConsecutive2(int[] statues) {
        Arrays.sort(statues);
        for (int i = 0; i < statues.length; i++) {
        }
        return (statues[statues.length - 1] - statues[0] + 1) - statues.length;
    }

    //task 5
    //Below we will define an n-interesting polygon. Your task is to find the area of a polygon for a given n.
    //A 1-interesting polygon is just a square with a side of length 1. An n-interesting polygon is obtained by taking the n - 1-interesting polygon and appending 1-interesting polygons to its rim, side by side. You can see the 1-, 2-, 3- and 4-interesting polygons in the picture below.
    public static int shapeArea(int n) {
        int bound = 0;
        for (int i = (2 * n - 1); i > 0; i = i - 2) {
            bound += i;
        }
        return 2 * (bound) - (2 * n - 1);
    }

    //task 4
    //Given an array of integers, find the pair of adjacent elements that has the largest product and return that product.
    public static int adjacentElementsProduct(int[] inputArray) {
        int[] multiplyArray = new int[inputArray.length - 1];
        int k = 0;

        for (int i = 0; i < inputArray.length - 1; i++) {
            multiplyArray[k++] = (inputArray[i] * inputArray[i + 1]);
        }
        int temp = 0;
        for (int i = 0; i < multiplyArray.length; i++) {
            for (int j = i + 1; j < multiplyArray.length; j++) {
                if (multiplyArray[i] < multiplyArray[j]) {
                    temp = multiplyArray[i];
                    multiplyArray[i] = multiplyArray[j];
                    multiplyArray[j] = temp;
                }

            }
        }

        return multiplyArray[0];
    }

}