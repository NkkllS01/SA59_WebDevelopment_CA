package sg.edu.nus.ophone.model;

import jakarta.persistence.*;

//code by Team3.Chen Sirui
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    private int id;
    
    @Column(length = 50)
    private String status;
    public OrderStatus() {}
    public OrderStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
