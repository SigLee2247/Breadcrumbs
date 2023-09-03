package com.onbording.noteapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Node {
    private int size;
    private String title;
    private Node next;
    private Node end;

    public Node(String title) {
        this.title = title;
    }

    public Node(int size, String title, Node next, Node end) {
        this.size = size;
        this.title = title;
        this.next = next;
        this.end = end;
    }

    public void push(String element){
        Node node = new Node(element);
        if (this.end == null) {
            this.next = node;
            this.end = node;
        } else {
            this.end.next = node;
            this.end = node;
        }
        size++;
    }

    public String poll(){
        size--;
        String result = this.title;
        if(this.next != null){
            this.title = this.next.getTitle();
            this.next = this.next.getNext();
        }
        return result;
    }

    public String peek(){
        if(this.end != null)
            return this.end.title;
        return this.title;
    }

    public String[] toArray(){
        Node node = new Node(this.size,this.title,this.next,this.end);
        String[] str = new String[size];
        int i = 0;
        while(node != null){
            str[i++] = node.getTitle();
            if(node.next !=null) {
                node = new Node(node.size, node.next.title, node.next.next, node.next.end);
            }else{
                node = null;
            }
        }
        return str;
    }
}
