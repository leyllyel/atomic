import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger beautifulWordsCount3 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsCount4 = new AtomicInteger(0);
    private static AtomicInteger beautifulWordsCount5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindromeThread = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text)) {
                    if (text.length() == 3) {
                        beautifulWordsCount3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsCount4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsCount5.incrementAndGet();
                    }
                }
            }
        });

        Thread sameLetterThread = new Thread(() -> {
            for (String text : texts) {
                if (isSameLetter(text)) {
                    if (text.length() == 3) {
                        beautifulWordsCount3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsCount4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsCount5.incrementAndGet();
                    }
                }
            }
        });

        Thread increasingLetterThread = new Thread(() -> {
            for (String text : texts) {
                if (isIncreasingLetters(text)) {
                    if (text.length() == 3) {
                        beautifulWordsCount3.incrementAndGet();
                    } else if (text.length() == 4) {
                        beautifulWordsCount4.incrementAndGet();
                    } else if (text.length() == 5) {
                        beautifulWordsCount5.incrementAndGet();
                    }
                }
            }
        });

        palindromeThread.start();
        sameLetterThread.start();
        increasingLetterThread.start();

        palindromeThread.join();
        sameLetterThread.join();
        increasingLetterThread.join();

        System.out.println("Красивых слов с длиной 3: " + beautifulWordsCount3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + beautifulWordsCount4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + beautifulWordsCount5.get() + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        int length = text.length();
        for (int i = 0; i < length / 2; i++) {
            if (text.charAt(i) != text.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSameLetter(String text) {
        char firstLetter = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstLetter) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIncreasingLetters(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
