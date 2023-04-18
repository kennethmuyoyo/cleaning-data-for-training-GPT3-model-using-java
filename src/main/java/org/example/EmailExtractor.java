package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailExtractor {
        public static void main(String[] args) {
            String emails = "# **Email #1:**\n" +
                    "\n" +
                    "\n" +
                    "**Subject Line: take 2 drops of this every AM to lose 30 lbs?**\n" +
                    "\n" +
                    "\n" +
                    "If you no longer wish to receive helpful email tips on the best foods, exercises and nutrients from Natural Health Sherpa, click here to be removed from future emails.\n" +
                    "\n" +
                    "\n" +
                    "Hey, it's the Sherpa,\n" +
                    "\n" +
                    "\n" +
                    "According to Stanford research, your thyroid is NOT the most important factor for a fast, fat-burning metabolism. It's actually a \"fat-storing\" hormone made by your adrenal glands. And if you have too much of this hormone, your metabolism grinds to a halt... no matter how healthy your thyroid is...\n" +
                    "\n" +
                    "\n" +
                    "Luckily, there's a simple way to switch-off this hormone and speed up your metabolism... so you can start burning the stubborn fat on your tummy, thighs, and upper arms in a matter of days. Here it is:\n" +
                    "\n" +
                    "\n" +
                    "==> 2 drops of THIS, first thing in the morning switches off fat-storing hormone\n" +
                    "\n" +
                    "\n" +
                    "Naturally yours,\n" +
                    "\n" +
                    "\n" +
                    "# **Email #2:**\n" +
                    "\n" +
                    "\n" +
                    "**Subject Line: what pineapple juice does to your lungs**\n" +
                    "\n" +
                    "\n" +
                    "Do you wake up in the morning with stiff joints or pain in your hips, back, knees or elbows? Then chances are you're feeling the effects of chronic inflammation taking its toll on your body.\n" +
                    "\n" +
                    "\n" +
                    "The good news is that it is NEVER too late to help get this under control. And the best part is there are certain foods that help you do this naturally, without the need for prescriptions medications.\n" +
                    "\n" +
                    "\n" +
                    "3-Sec Quiz: What is the #1 Anti-inflammatory Food?\n" +
                    "\n" +
                    "\n" +
                    "A: Turmeric\n" +
                    "\n" +
                    "\n" +
                    "B: Onion\n" +
                    "\n" +
                    "\n" +
                    "C: Pineapple\n" +
                    "\n" +
                    "\n" +
                    "D: Black Pepper\n" +
                    "\n" +
                    "\n" +
                    "E: Other\n" +
                    "\n" +
                    "\n" +
                    "Make your selection above or click here to skip straight to the answer.\n" +
                    "\n" +
                    "\n" +
                    "Remember, it's NEVER too late to get chronic inflammation under control. You just need to know how to do it.\n" +
                    "\n" +
                    "\n" +
                    "==> Click here to discover the #1 anti-inflammatory food\n" +
                    "\n" +
                    "\n" +
                    "Naturally yours,\n" +
                    "\n" +
                    "\n" +
                    "# **Email #3:**\n" +
                    "\n" +
                    "\n" +
                    "**Subject Line: 5 second thyroid test reveals why**\n" +
                    "\n" +
                    "\n" +
                    "A sputtering, sluggish thyroid can BLOCK almost any attempt to burn fat off your tummy and thighs... no matter how hard you workout, or how little you eat. This is because a sluggish thyroid grinds your metabolism to a halt... and your body CAN'T burn enough calories to lose the stubborn fat. Frustrating!\n" +
                    "\n" +
                    "\n" +
                    "Luckily, researchers now know the #1 thyroid KILLER... And a fast, natural way to put it into high gear — so your thyroid can command your metabolism to burn the stubborn fat ASAP. Discover it here:\n" +
                    "\n" +
                    "\n" +
                    "==> THIS burns off stubborn fat ASAP by shifting your thyroid into high gear..\n" +
                    "\n" +
                    "\n" +
                    "Naturally yours,\n" +
                    "\n";
            List<EmailInfo> extractedEmails = extractEmails(emails);

            System.out.printf("%-10s %-60s %s%n", "Email", "Subject", "Message");
            System.out.println("---------------------------------------------------------------------------------------------");
            for (EmailInfo email : extractedEmails) {
                System.out.printf("Email #%d  %-60s %.60s...%n", email.getNumber(), email.getSubject(), email.getMessage());
            }
        }

        public static List<EmailInfo> extractEmails(String emails) {
            List<EmailInfo> extractedEmails = new ArrayList<>();
            Pattern emailPattern = Pattern.compile("# \\*\\*Email #(\\d+):\\*\\*\\n\\n\\*\\*Subject Line: (.+?)\\*\\*\\n\\n(.+?)(?=\\n\\n&|$)", Pattern.DOTALL);
            Matcher emailMatcher = emailPattern.matcher(emails);

            while (emailMatcher.find()) {
                int number = Integer.parseInt(emailMatcher.group(1));
                String subject = emailMatcher.group(2).trim();
                String message = emailMatcher.group(3).trim();
                extractedEmails.add(new EmailInfo(number, subject, message));
            }

            return extractedEmails;
        }
    }



class EmailInfo {
    private int number;
    private String subject;
    private String message;

    public EmailInfo(int number, String subject, String message) {
        this.number = number;
        this.subject = subject;
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
