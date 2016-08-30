package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 1538901993209704439L;

	@EmbeddedId
	private RentPK pk = new RentPK();

	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;

	public RentPK getPk() {
		return pk;
	}

	public void setPk(RentPK pk) {
		this.pk = pk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public Book getBook(){
		return pk.getBook();
	}
	
	public void setBook(Book book){
		pk.setBook(book);
	}
	
	public Date getInitDate(){
		return pk.getInitDate();
	}
	
	public void setInitDate(Date initDate){
		pk.setInitDate(initDate);
	}

}
