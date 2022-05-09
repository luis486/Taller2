package entity;

public class WishList {

    private int id;
    private long creationDate;
    private int paid;
    private long payDate;
    private int userID;

    public WishList(int id, long creationDate, int paid, long payDate, int userID) {
        this.id = id;
        this.creationDate = creationDate;
        this.paid = paid;
        this.payDate = payDate;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid){ this.paid = paid;
    }

    public long getPayDate() {
        return payDate;
    }

    public void setPayDate(long payDate) {
        this.payDate = payDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}