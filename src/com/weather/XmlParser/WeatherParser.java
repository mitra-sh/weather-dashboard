package com.weather.XmlParser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.weather.XmlElements.Author;
import com.weather.XmlElements.Catagory;
import com.weather.XmlElements.Entry;
import com.weather.XmlElements.Feed;
import com.weather.XmlElements.Link;
import com.weather.XmlElements.LinkWithHreflangAndRelAttributes;
import com.weather.XmlElements.LinkWithRelAttribute;
import com.weather.XmlElements.Summary;

public class WeatherParser extends DefaultHandler {
	private String tempValue;
	private Feed tempFeed;
	private Entry entry;
	private Author author;
	private LinkWithRelAttribute linkWithRel;
	private LinkWithHreflangAndRelAttributes linkWithHreflangAndRel;
	private Link link;
	private Catagory catagory;
	private Summary summary;
	boolean inside_entry = false;

	@Override
	public void startElement(String s, String s1, String elementName, Attributes attributes) {

		if (elementName.equalsIgnoreCase("feed")) {
			tempFeed = new Feed();
			inside_entry = false;
			tempFeed.setXmlns(attributes.getValue("xmlns"));
			tempFeed.setLang(attributes.getValue("xml:lang"));
		}

		if (elementName.equalsIgnoreCase("link")) {
			if (inside_entry) {
				link = new Link();
				link.setHref(attributes.getValue("href"));
				link.setType(attributes.getValue("type"));
				entry.setLink(link);
			} else {
				if (attributes.getValue("hreflang") == null) {
					linkWithRel = new LinkWithRelAttribute();
					linkWithRel.setRel(attributes.getValue("rel"));
					linkWithRel.setHref(attributes.getValue("href"));
					linkWithRel.setType(attributes.getValue("type"));
					tempFeed.getLinksWithRel().add(linkWithRel);
				} else {
					linkWithHreflangAndRel = new LinkWithHreflangAndRelAttributes();
					linkWithHreflangAndRel.setRel(attributes.getValue("rel"));
					linkWithHreflangAndRel.setHref(attributes.getValue("href"));
					linkWithHreflangAndRel.setHreflang(attributes.getValue("type"));
					linkWithHreflangAndRel.setHreflang(attributes.getValue("hreflang"));
					tempFeed.setLinkWithHreflangAndRel(linkWithHreflangAndRel);
				}

			}
		}

		if (elementName.equalsIgnoreCase("author")) {
			author = new Author();
		}

		if (elementName.equalsIgnoreCase("entry")) {
			entry = new Entry();
			inside_entry = true;
		}
		if (elementName.equalsIgnoreCase("category")) {
			catagory = new Catagory();
			catagory.setTerm(attributes.getValue("term"));
			entry.setCatagory(catagory);

		}
		if (elementName.equalsIgnoreCase("summary")) {
			summary = new Summary();
			summary.setType(attributes.getValue("type"));

		}
	}

	@Override
	public void endElement(String s, String s1, String element) {

		if (element.equalsIgnoreCase("title")) {
			if (inside_entry) {
				entry.setTitle(tempValue);
			} else {
				tempFeed.setTitle(tempValue);
			}
		}

		if (element.equalsIgnoreCase("name")) {
			author.setName(tempValue);
		}

		if (element.equalsIgnoreCase("uri")) {
			author.setUri(tempValue);
		}

		if (element.equalsIgnoreCase("author")) {
			tempFeed.setAuthor(author);
		}

		if (element.equalsIgnoreCase("id")) {
			if (inside_entry) {
				entry.setId(tempValue);
			} else {
				tempFeed.setId(tempValue);
			}
		}
		if (element.equalsIgnoreCase("logo")) {
			tempFeed.setLogo(tempValue);
		}
		if (element.equalsIgnoreCase("icon")) {
			tempFeed.setIcon(tempValue);
		}
		if (element.equalsIgnoreCase("rights")) {
			tempFeed.setRights(tempValue);
		}

		if (element.equalsIgnoreCase("updated")) {
			if (inside_entry) {
				entry.setUpdated(tempValue);
			} else {
				tempFeed.setUpdated(tempValue);
			}
		}

		if (element.equalsIgnoreCase("entry")) {
			tempFeed.getEntries().add(entry);
			inside_entry = false;
		}
		if (element.equalsIgnoreCase("published")) {
			entry.setPublished(tempValue);
		}

		if (element.equalsIgnoreCase("summary")) {
			summary.setContent(tempValue);
			entry.setSummary(summary);
		}
	}

	@Override
	public void characters(char[] ac, int start, int length) {
		tempValue = new String(ac, start, length);
	}

	public String getTempValue() {
		return tempValue;
	}

	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}

	public Feed getTempFeed() {
		return tempFeed;
	}

	public void setTempFeed(Feed tempFeed) {
		this.tempFeed = tempFeed;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public LinkWithRelAttribute getLinkWithRel() {
		return linkWithRel;
	}

	public void setLinkWithRel(LinkWithRelAttribute linkWithRel) {
		this.linkWithRel = linkWithRel;
	}

	public LinkWithHreflangAndRelAttributes getLinkWithHreflangAndRel() {
		return linkWithHreflangAndRel;
	}

	public void setLinkWithHreflangAndRel(LinkWithHreflangAndRelAttributes linkWithHreflangAndRel) {
		this.linkWithHreflangAndRel = linkWithHreflangAndRel;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public Catagory getCatagory() {
		return catagory;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

}
