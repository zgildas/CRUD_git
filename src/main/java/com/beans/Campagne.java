package com.beans;

public class Campagne {
	private String id;
	private String campaign_title;
	private String type;
	private Long emails_sent;
	private Long abuse_reports;
	private Long unsubscribed;
	private String send_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCampaign_title() {
		return campaign_title;
	}
	public void setCampaign_title(String campaign_title) {
		this.campaign_title = campaign_title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getEmails_sent() {
		return emails_sent;
	}
	public void setEmails_sent(Long c_emails_sent) {
		this.emails_sent = c_emails_sent;
	}
	public Long getAbuse_reports() {
		return abuse_reports;
	}
	public void setAbuse_reports(Long abuse_reports) {
		this.abuse_reports = abuse_reports;
	}
	public Long getUnsubscribed() {
		return unsubscribed;
	}
	public void setUnsubscribed(Long c_unsubscribed) {
		this.unsubscribed = c_unsubscribed;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}


}
