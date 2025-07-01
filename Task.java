public class Task {
    private String text;
    private boolean done;

    public Task(String text) {
        this.text = text;
        this.done = false;
    }

    public String getText() { 
        return text; 
    }
    public boolean isDone() { 
        return done; 
    }
    public void setDone(boolean done) { 
        this.done = done; 
    }
}
