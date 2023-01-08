package gr.hua.dit.springbootdemo.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private int id;

    @Column(name="paymentAmount")
    private float paymentAmount;

    @Column(name="paymentType")
    private String paymentType;

    @Column(name = "paymentConfirmed", nullable = false)// 1 for confirmed, 0 for not
    private boolean paymentConfirmed;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "secretary_id")
    @JsonBackReference
    private Secretary secretary;

    public Payment(){

    }

    public Payment(float paymentAmount, String paymentType, boolean paymentConfirmed){
       this.paymentAmount = paymentAmount;
       this.paymentConfirmed = paymentConfirmed;
       this.paymentType = paymentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public boolean isPaymentConfirmed() {
        return paymentConfirmed;
    }

    public void setPaymentConfirmed(boolean paymentConfirmed) {
        this.paymentConfirmed = paymentConfirmed;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", amount=" + paymentAmount
                + ", type=" + paymentType + ", confirmed="
                + paymentConfirmed + "]";
    }

}
