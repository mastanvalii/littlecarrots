	package com.kafka.email.bean;

	import java.io.Serializable;

	public class CommonMail implements Serializable {
		private String tomail;
		private String message;
		private String subject;
		public CommonMail() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CommonMail(String tomail, String message, String subject) {
			super();
			this.tomail = tomail;
			this.message = message;
			this.subject = subject;
		}
		public String getTomail() {
			return tomail;
		}
		public void setTomail(String tomail) {
			this.tomail = tomail;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		@Override
		public String toString() {
			return "CommonMail [tomail=" + tomail + ", message=" + message + ", subject=" + subject + "]";
		}

		
		
	}

	

