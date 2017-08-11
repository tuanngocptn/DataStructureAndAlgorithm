package util.collection;

import com.google.gson.Gson;

public class LinkedLstStack<E> {
    LinkedLst head = null;
    LinkedLst tail = null;
    LinkedLst current = null;

    class LinkedLst {
        E e;
        LinkedLst next;

        private LinkedLst() {
        }

        private LinkedLst(E e, LinkedLst next) {
            this.e = e;
            this.next = next;
        }
    }

    public LinkedLstStack() {
    }

    public void push(E value) {
        LinkedLst linkedLst = new LinkedLst(value,null);
        if (isEmpty()) {
            linkedLst.next = tail;
            current = tail = head = linkedLst;
        } else {
            linkedLst.next = head;
            head = linkedLst;
        }
    }

    public boolean hasNext() {
        if (isEmpty()) return false;
        else if (current.next != null) return true;
        return false;
    }

    public boolean isEmpty() {
        return head == null || tail == null || current == null;
    }

    public E pop() {
        if(!isEmpty()) {
            E value = head.e;
            head = head.next;
            return value;
        }
        return null;
    }

    public E peek() {
    	if(head != null){
	        current = head;
	        return current.e;
    	}
    	return null;
    }

    public E get(){
        E e = null;
        if(current != null){
            e = current.e;
            current = current.next;
        }
        return e;
    }

    public String display() {
        if (!isEmpty()) {
            peek();
            StringBuilder result = new StringBuilder();
            Gson gson = new Gson();
            result = result.append(gson.toJson(get()));
            while (!isEmpty()) {
                result.append(",");
                result = result.append(gson.toJson(get()));
            }
            return String.format("%s%s%s", "[", result.toString(), "]");
        }
        peek();
        return "[]";
    }
    
    public int length(){
    	int len = 0;
    	LinkedLst linkedLst = head;
    	while (linkedLst != null) {
			len++;
			linkedLst = linkedLst.next;
		}
    	return len;
    }
    
    public E removeLast() {
    	if(!isEmpty()) {
    		LinkedLst linkedLst = head;
	    	LinkedLst linkedLstRemove = linkedLst.next; 
	    	while (linkedLstRemove.next != null) {
				linkedLst = linkedLst.next;
				linkedLstRemove = linkedLstRemove.next;
			}
	    	linkedLst.next = null;
	    	tail = linkedLst;
	    	return linkedLstRemove.e;
        }
        return null;
	}
    
    public boolean delete(int serial){
    	if(serial < 1){
    	    if(pop() == null){
    	    	return false;
    	    }
    	   	return true;
    	}  	
    	if (serial >= length() - 1) {
    		if(removeLast() == null){
    			return false;
    		}
			return true;
		}
    	LinkedLst linkedLst = head;
    	LinkedLst linkedLstRemove = linkedLst.next;   
    	for(int i = 0 ; i < serial - 1; i++){
    		linkedLst = linkedLst.next;
			linkedLstRemove = linkedLstRemove.next;
    	}
    	linkedLst.next = linkedLstRemove.next;
    	return true;
    	
    }
}
