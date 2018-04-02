package br.com.pv;

import java.io.Serializable;
import java.util.List;

public class GitHub implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<GitHubRepository> repositories;
	private List<GitHubCloneUrl> cloneUrls;
	private String urlAvatared;
	private String urlTabRepositories;

	// Getters e Setters
	public List<GitHubRepository> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<GitHubRepository> repositories) {
		this.repositories = repositories;
	}

	public List<GitHubCloneUrl> getCloneUrls() {
		return cloneUrls;
	}

	public void setCloneUrls(List<GitHubCloneUrl> cloneUrls) {
		this.cloneUrls = cloneUrls;
	}

	public String getUrlAvatared() {
		return urlAvatared;
	}

	public void setUrlAvatared(String urlAvatared) {
		this.urlAvatared = urlAvatared;
	}

	public String getUrlTabRepositories() {
		return urlTabRepositories;
	}

	public void setUrlTabRepositories(String urlTabRepositories) {
		this.urlTabRepositories = urlTabRepositories;
	}

}