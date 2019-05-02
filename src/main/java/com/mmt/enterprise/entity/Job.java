package com.mmt.enterprise.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="job")
public class Job {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	private Long typeId;//job type
	private Long comId;
	private Long number; 
	private String startSalary;
	private String endSalary;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;
	private String edu;
	private String exp;
	private String tag;
	private Date publishTime;
	@Column(columnDefinition="TEXT")
	private String description;
	private String contact;
	private String contactPhone;
	private String email;
	private Long status;
	private Long topFlag;//是否置顶
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
    @JoinTable(name = "post_user_job",
    	joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> postUsers; //投递用户
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
    @JoinTable(name = "collect_user_job",
    	joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> collectUsers; //收藏用户
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the comId
	 */
	public Long getComId() {
		return comId;
	}
	/**
	 * @param comId the comId to set
	 */
	public void setComId(Long comId) {
		this.comId = comId;
	}
	/**
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Long number) {
		this.number = number;
	}
	/**
	 * @return the startSalary
	 */
	public String getStartSalary() {
		return startSalary;
	}
	/**
	 * @param startSalary the startSalary to set
	 */
	public void setStartSalary(String startSalary) {
		this.startSalary = startSalary;
	}

	/**
	 * @return the endSalary
	 */
	public String getEndSalary() {
		return endSalary;
	}
	/**
	 * @param endSalary the endSalary to set
	 */
	public void setEndSalary(String endSalary) {
		this.endSalary = endSalary;
	}
	/**
	 * @return the city
	 */
	public City getCity() {return city;}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	/**
	 * @return the edu
	 */
	public String getEdu() {
		return edu;
	}
	/**
	 * @param edu the edu to set
	 */
	public void setEdu(String edu) {
		this.edu = edu;
	}
	/**
	 * @return the exp
	 */
	public String getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(String exp) {
		this.exp = exp;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}
	/**
	 * @return the topFlag
	 */
	public Long getTopFlag() {
		return topFlag;
	}
	/**
	 * @param topFlag the topFlag to set
	 */
	public void setTopFlag(Long topFlag) {
		this.topFlag = topFlag;
	}
	/**
	 * @return the postUsers
	 */
	public List<User> getPostUsers() {
		return postUsers;
	}
	/**
	 * @param postUsers the postUsers to set
	 */
	public void setPostUsers(List<User> postUsers) {
		this.postUsers = postUsers;
	}
	/**
	 * @return the collectUsers
	 */
	public List<User> getCollectUsers() {
		return collectUsers;
	}
	/**
	 * @param collectUsers the collectUsers to set
	 */
	public void setCollectUsers(List<User> collectUsers) {
		this.collectUsers = collectUsers;
	}
}
