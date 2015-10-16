package XMLtoJava;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

/**
 *
 * @author asus
 */
@XmlRootElement(name = "Envelope")
@XmlAccessorType(XmlAccessType.NONE)
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
public class Envelope 
{
   
   private Body body;
   
  
   public Body getBody() {
      return body;
   }
   
   @XmlElement(name = "Body")
   public void setBody(Body body) {
      this.body = body;
   } 
}


class Body
{
   
   private SendPayment sendPayment;
      
   public SendPayment getSendPayment() {
      return sendPayment;
   }
   
   @XmlElement(name = "sendPayment", namespace = "wsapi:Payment")
   public void setSendPayment(SendPayment sendPayment) {
      this.sendPayment = sendPayment;
   }
}

class SendPayment
{
   
   private String token;
   private String cardNumber;
   private String requestId;
   private double amount;
   private String currency;
   private ArrayList<Account> account;
   private int page;
   private ArrayList<Field> field;
   
   
   public String getToken() {
      return token;
   }
   
   public String getCardNumber() {
      return cardNumber;
   }
   
   public String getRequestId() {
      return requestId;
   }

   public double getAmount() {
      return amount;
   }    

   public String getCurrency() {
      return currency;
   }
   
   public ArrayList<Account> getAccount() {
      return account;
   }
   
   public int getPage() {
      return page;
   }
   
   public ArrayList<Field> getField() {
      return field;
   }
   
   @XmlElement
   public void setToken(String token) {
      this.token = token;
   }
   
   @XmlElement
   public void setCardNumber(String cardNumber) {
      this.cardNumber = cardNumber;
   }
   
   @XmlElement
   public void setRequestId(String requestId) {
      this.requestId = requestId;
   }

   @XmlElement
   public void setAmount(double amount) {
      this.amount = amount;
   }

   @XmlElement
   public void setCurrency(String currency) {
      this.currency = currency;
   }
   
   @XmlElement(name = "account", namespace = "wsapi:Utils")
   public void setAccount(ArrayList<Account> account) {
      this.account = account;
   }

   @XmlElement
   public void setPage(int page) {
      this.page = page;
   }
   @XmlElement
   public void setField(ArrayList<Field> field) {
      this.field = field;
   }

}

class Account
{
   private String type;
   private String account;
   
   
   public String getType() {
      return type;
   }
   
   
   public String getValue() {
      return account;
   }
   @XmlAttribute
   public void setType(String type) {
      this.type = type;
   }
   
   @XmlValue
   public void setValue(String account) {
      this.account = account;
   }   
}

class Field
{
   private String id;  
   private String value;

   public String getId() {
      return id;
   }

   public String getValue() {
      return value;
   }
   
   @XmlAttribute
   public void setId(String id) {
      this.id = id;
   }
   
   @XmlAttribute
   public void setValue(String value) {
      this.value = value;
   }
}