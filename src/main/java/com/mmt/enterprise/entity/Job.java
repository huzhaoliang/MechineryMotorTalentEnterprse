package com.mmt.enterprise.entity;

import java.sql.Clob;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
	private JobType jobType;//job type
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_id")
	private EnterpriseUser company;
	private Long number; 
	private String salary;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
	private City city;
	private Long edu;
	private Long exp;
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
	 * @return the jobType
	 */
	public JobType getJobType() {
		return jobType;
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	/**
	 * @return the company
	 */
	public EnterpriseUser getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(EnterpriseUser company) {
		this.company = company;
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
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	/**
	 * @return the edu
	 */
	public Long getEdu() {
		return edu;
	}
	/**
	 * @param edu the edu to set
	 */
	public void setEdu(Long edu) {
		this.edu = edu;
	}
	/**
	 * @return the exp
	 */
	public Long getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(Long exp) {
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
