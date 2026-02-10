package com.somya.bfhl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class BfhlService {

    @Value("${gemini.api.key}")
    private String apiKey;

    // Fibonacci
    public List<Integer> getFibonacci(int n) {
        List<Integer> fib = new ArrayList<>();
        if (n <= 0) return fib;

        fib.add(0);
        if (n == 1) return fib;

        fib.add(1);
        for (int i = 2; i < n; i++) {
            fib.add(fib.get(i - 1) + fib.get(i - 2));
        }
        return fib;
    }

    // Prime check
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public List<Integer> getPrimes(List<Integer> numbers) {
        List<Integer> primes = new ArrayList<>();
        for (int n : numbers) {
            if (isPrime(n)) primes.add(n);
        }
        return primes;
    }

    // HCF
    public int getHCF(List<Integer> numbers) {
        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = gcd(result, numbers.get(i));
        }
        return result;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // LCM
    public int getLCM(List<Integer> numbers) {
        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }
        return result;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    // GEMINI AI CALL
    public String askAI(String question) {
        try {
            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String prompt = "Answer in ONE WORD only: " + question;

            String body = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + prompt + "\" } ] } ] }";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
            }

            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (sc.hasNext()) response.append(sc.nextLine());
            sc.close();

            String text = response.toString();

            // crude extraction of answer word
            int idx = text.indexOf("text");
            if (idx != -1) {
                String sub = text.substring(idx + 7);
                int end = sub.indexOf("\"");
                return sub.substring(0, end).trim().split(" ")[0];
            }

            return "Unknown";

        } catch (Exception e) {
            return "Error";
        }
    }
}