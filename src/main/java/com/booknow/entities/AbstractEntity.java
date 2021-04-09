package com.booknow.entities;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.catalina.users.AbstractUser;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.booknow.Views.DefaultView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


/**
 * Super class to all entity classes. Contains properties related to auditing of
 * data
 * 
 * @author 
 */
@MappedSuperclass
//@Audited
@EntityListeners( AuditingEntityListener.class)
public abstract class AbstractEntity extends IdentifiableEntity implements Serializable {

	private static final long serialVersionUID = -2700585394392789902L;

	@Column(name = "created_date")
	@CreatedDate
	//@Property(hidden = true)
	protected Date createdDate;

	@Column(name = "last_modified_date")
	@LastModifiedDate
	//@Property(hidden = true)
	protected Date lastModifiedDate;

	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id")
	//@Property(hidden = true)
	protected AbstractUser createdBy;

	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_modified_by_id")
	//@Property(hidden = true)
	protected AbstractUser lastModifiedBy;

	//@Property(hidden = true)
	@javax.persistence.Version
	@JsonView(DefaultView.class)
	@Column(name = "version")
	private long version;

	public AbstractEntity() {}
	
	public AbstractEntity(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public AbstractUser getLastModifiedBy() {
		return lastModifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public AbstractUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AbstractUser createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastModifiedBy(AbstractUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}