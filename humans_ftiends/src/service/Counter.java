package service;

public class Counter implements AutoCloseable {
    private int count = 0;
    private boolean closed = false;
    private boolean usedOutsideTry = false;

    public void add() {
        if (closed) {
            throw new IllegalStateException("Ресурс уже закрыт!");
        }
        count++;
    }

    public int getCount() {
        if (!closed) {
            usedOutsideTry = true;
        }
        return count;
    }

    @Override
    public void close() {
        closed = true;
        System.out.println("Ресурс Counter закрыт.");
        if (usedOutsideTry) {
            throw new IllegalStateException("Счётчик использован вне try-with-resources!");
        }
    }
}
