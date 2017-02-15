package ie.nuigalway.sd3.entities;

import java.util.Date;

public class Thread {

    private Long id;
    private String title;
    private Date dt_created;
    private Date dt_updated;


    //constructors
    public Thread() {

    }

    public Thread(Long id, String title, Date dt_created, Date dt_updated) {
        this.id = id;
        this.title = title;
        this.dt_created = dt_created;
        this.dt_updated = dt_updated;
    }
    //constructors


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDt_created() {
        return dt_created;
    }

    public void setDt_created(Date dt_created) {
        this.dt_created = dt_created;
    }

    public Date getDt_updated() {
        return dt_updated;
    }

    public void setDt_updated(Date dt_updated) {
        this.dt_updated = dt_updated;
    }
    //getters and setters


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thread)) return false;

        Thread thread = (Thread) o;

        return getId().equals(thread.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
