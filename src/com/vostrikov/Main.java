package com.vostrikov;

public class Main {

    public static void main(String[] args) throws Exception {

        Account account = new Account(300000);

        System.out.println("------------------------Игрушечный банк------------------------");
        System.out.println("Первоначальный баланс " + account.getBalance());

        Thread client1WithdrawThread    = new WithdrawThread(account, 1000, 1);
        Thread client2DepositThread     = new DepositThread(account, 5000, 2);
        Thread client3DepositThread     = new DepositThread(account, 7005, 3);
        Thread client4WithdrawThread    = new WithdrawThread(account, 20000, 4);

        client1WithdrawThread.start();
        client2DepositThread.start();
        client3DepositThread.start();
        client4WithdrawThread.start();

        client1WithdrawThread.join();
        client2DepositThread.join();
        client3DepositThread.join();
        client4WithdrawThread.join();

        System.out.println(account.getBalance());

    }

    private static class WithdrawThread extends Thread {

        private final Account account;
        private final long amount;
        private final int clientNumber;

        private WithdrawThread(Account account, long amount, int clientNumber) {
            this.account        = account;
            this.amount         = amount;
            this.clientNumber   = clientNumber;
        }

        @Override
        public void run() {
            System.out.println("Клиент №" + clientNumber + ": Заявка(cl:entThreadName='Клиент №" + clientNumber
                    + ", amount=" + amount + ", requestType=CREDIT");
            account.withdraw(amount);
        }
    }

    private static class DepositThread extends Thread {

        private final Account account;
        private final long amount;
        private final int clientNumber;

        private DepositThread(Account account, long amount, int clientNumber) {
            this.account        = account;
            this.amount         = amount;
            this.clientNumber   = clientNumber;
        }

        @Override
        public void run() {
            System.out.println("Клиент №" + clientNumber + ": Заявка(cl:entThreadName='Клиент №" + clientNumber
                    + ", amount=" + amount + ", requestType=REPAYMENT");
            account.deposit(amount);
        }
    }

//        Account account = new Account(100000);
//        System.out.println("Begin balance = " + account.getBalance());
//
//        Thread withdrawThread = new WithdrawThread(account);
//        Thread withdrawThread1 = new WithdrawThread(account);
//        Thread depositThread = new DepositThread(account);
//        withdrawThread.start();
//        depositThread.start();
//        withdrawThread1.start();
//
//        withdrawThread.join();
//
//        depositThread.join();
//        withdrawThread1.join();
//
//        System.out.println("End balance = " + account.getBalance());
//    }
//
//
//    private static class WithdrawThread extends Thread {
//
//        private final Account account;
//
//        private WithdrawThread(Account account) {
//            this.account = account;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 20000; ++i) {
//                account.withdraw(1);
//            }
//        }
//    }
//
//
//    private static class DepositThread extends Thread {
//
//        private final Account account;
//
//        private DepositThread(Account account) {
//            this.account = account;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 20000; ++i) {
//                account.deposit(1);
//            }
//        }
//    }



















//        Account account = new Account(0);
//
//        new DepositThread(account).start();
//
//        account.waitAndWithdraw(50000000);
//
//        System.out.println("waitAndWithdraw finished, end balance = " + account.getBalance());
//    }
//
//
//    private static class DepositThread extends Thread {
//
//        private final Account account;
//
//        private DepositThread(Account account) {
//            this.account = account;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 60000000; ++i) {
//                account.deposit(1);
//            }
//        }
//    }
}
