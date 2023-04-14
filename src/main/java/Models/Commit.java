package Models;

import java.util.Date;
import java.util.List;

public class Commit {
    private String id;
    private String message;
    private Date authorDate;
    private Date committerDate;
    private List<Commit> children;
    private List<Change> changes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getAuthorDate() {
        return authorDate;
    }

    public void setAuthorDate(Date authorDate) {
        this.authorDate = authorDate;
    }

    public Date getCommitterDate() {
        return committerDate;
    }

    public void setCommitterDate(Date committerDate) {
        this.committerDate = committerDate;
    }

    public List<Commit> getChildren() {
        return children;
    }

    public void setChildren(List<Commit> children) {
        this.children = children;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public void setChanges(List<Change> changes) {
        this.changes = changes;
    }
}
