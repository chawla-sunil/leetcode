package org.example.companies;

public class GreyLabs {
    // 15 July 2026
    // Rate limit
    // Token bucket

    public static class TokenBucket {
        int capacity;
        int rate;

        public int availableTokens;
        public long lastFilledTime;

        public TokenBucket(int capacity, int rate, int availableTokens, long lastFilledTime) {
            this.capacity = capacity;
            this.rate = rate;
            this.availableTokens = availableTokens;
            this.lastFilledTime = lastFilledTime;
        }

        public boolean check() {
            refillTokens();

            if (availableTokens > 0) {
                availableTokens--;
                System.out.println("Request " + availableTokens + " is allowed");
                return true;
            }
            System.out.println("Request " + " is denied");
            return false;
        }

        public synchronized void refillTokens() {
            long now = System.currentTimeMillis();
            long passedTime = now - lastFilledTime;

            int newToken = rate *  (int) (passedTime/1000);
            availableTokens = Math.min(capacity, availableTokens + newToken);
            lastFilledTime = now;

        }

        public static void main(String[] args) {
            TokenBucket tokenBucket = new TokenBucket(3, 1, 3, System.currentTimeMillis());
            for (int i = 0; i < 20; i++) {
                if (tokenBucket.check()) {
                    System.out.println("Request " + i + " is allowed");
                } else {
                    System.out.println("Request " + i + " is denied");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Testing with multiple threads");
            Thread thread = new Thread(() -> {
                System.out.println("211");
                tokenBucket.check();
                tokenBucket.check();
                tokenBucket.check();
                tokenBucket.check();
                tokenBucket.check();
                tokenBucket.check();
            });
            thread.start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tokenBucket.check();
        }
    }
}
