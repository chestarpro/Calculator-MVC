package com.company;

public class MyStack<T> {
    private int stackSize;
    private T[] stackArray;
    private int top;

    public MyStack(int size) {
        stackSize = size;
        stackArray = (T[]) new Object[stackSize];
        top = -1;
    }

    public void push(T element) {
        stackArray[++top] = element;
    }

    public T pop() {
        return stackArray[top--];
    }

    public T peek() {
        return stackArray[top];
    }

    public boolean empty() {
        return (top != -1);
    }

    public boolean full() {
        return (top == stackSize - 1);
    }
}