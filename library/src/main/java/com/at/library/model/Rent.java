package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = -4158742374158942716L;
	
	@EmbeddedId
	private RentPK pk;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Temporal(TemporalType.DATE)
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
	
	@Transient
	public Book getBook(){
		return pk.getBook();
	}
	
	@Transient
	public void setBook(Book book){
		pk.setBook(book);
	}
	
	@Transient
	public Date getInitDate(){
		return pk.getInitDate();
	}
	
	@Transient
	public void setInitDate(Date initDate){
		pk.setInitDate(initDate);
	}

}
