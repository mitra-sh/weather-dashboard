package com.weather.XmlElements;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	private String xmlns;
	private String lang;
	private String title;
	private List<LinkWithRelAttribute> linksWithRel;
	private LinkWithHreflangAndRelAttributes LinkWithHreflangAndRel;
	private Author author;
	private String updated;
	private String id;
	private String logo;
	private String icon;
	private String rights;
	private List<Entry> entries;

	public Feed() {
		entries = new ArrayList<Entry>();
		linksWithRel = new ArrayList<LinkWithRelAttribute>();
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<LinkWithRelAttribute> getLinksWithRel() {
		return linksWithRel;
	}

	public void setLinksWithRel(List<LinkWithRelAttribute> linksWithRel) {
		this.linksWithRel = linksWithRel;
	}

	public LinkWithHreflangAndRelAttributes getLinkWithHreflangAndRel() {
		return LinkWithHreflangAndRel;
	}

	public void setLinkWithHreflangAndRel(LinkWithHreflangAndRelAttributes linkWithHreflangAndRel) {
		LinkWithHreflangAndRel = linkWithHreflangAndRel;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
