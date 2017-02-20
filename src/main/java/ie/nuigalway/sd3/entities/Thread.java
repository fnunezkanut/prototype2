package ie.nuigalway.sd3.entities;

import java.util.Date;

public class Thread {

    private Long id;
    private String title;
    private Date dt_created;
    private Date dt_updated;
    private Long customer_user_id;
    private Long support_user_id;


    //constructors
    public Thread() {

    }

    public Thread(Long id, String title) {
        this.id = id;
        this.title = title;
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

    public Long getCustomer_user_id() {
        return customer_user_id;
    }

    public void setCustomer_user_id( Long customer_user_id ) {
        this.customer_user_id = customer_user_id;
    }

    public Long getSupport_user_id() {
        return support_user_id;
    }

    public void setSupport_user_id( Long support_user_id ) {
        this.support_user_id = support_user_id;
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
