package ooad.timewise.notes;

class Note {
    private String title;
    private String content;
    Note(String title){
        this.title = title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
