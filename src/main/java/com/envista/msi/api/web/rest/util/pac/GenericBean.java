package com.envista.msi.api.web.rest.util.pac;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sreedhar Mallepaddi
 * @Version : 1.0 Sreedhar Mallepaddi 25-apr-2003 Created
 * @Revised: added the four accessor methods
 *           </p>
 *           <p>
 *           This is a GenericBean which will be extended by all beans.
 *           </p>
 * 
 */

public class GenericBean implements IBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(GenericBean.class);
	private String createUser;
	private String lastUpdateUser;
	private Timestamp createDate;
	private Timestamp lastUpdateDate;

	public GenericBean() {
	}

	public String toString() {
		String value = "";
		try {
			PropertyDescriptor[] pd = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {
				if (pd[i].getName().equalsIgnoreCase("class"))
					continue;
				value += GlobalConstants.LINESEPERATOR + pd[i].getName() + " "
						+ BeanUtils.getProperty(this, pd[i].getName());
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return value;
	} // end toString

	public String convertToXML() {
		StringBuffer sb = new StringBuffer();
		try {
			PropertyDescriptor[] pd = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {
				if (pd[i].getName().equalsIgnoreCase("class"))
					continue;
				String property = pd[i].getName();
				String value = BeanUtils.getProperty(this, pd[i].getName());
				if (value != null)
					sb.append(buildTag(property, value));
				else
					sb.append(buildTag(property, ""));
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return sb.toString();
	}

	private String buildTag(String property, String value) {
		return GlobalConstants.LINESEPERATOR + " <" + property + ">" + value + "</" + property + "> ";
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String newCreateUser) {
		createUser = newCreateUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String newLastUpdateUser) {
		lastUpdateUser = newLastUpdateUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp newCreateDate) {
		createDate = newCreateDate;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp newLastUpdateDate) {
		lastUpdateDate = newLastUpdateDate;
	}

	public JSONObject getJSONObject(String[] dataField) {
		JSONObject obj = null;
		if (dataField == null || dataField.length == 0)
			return obj;
		for (int i = 0; i < dataField.length; i++) {
			try {
				String value = BeanUtils.getNestedProperty(this, dataField[i]);
				if (value == null)
					value = ""; // initialize the null values to empty.
				if (i == 0)
					obj = new JSONObject(); // initialiaze the object.
				obj.put(dataField[i], value);
			} catch (Exception ex) {
				System.out.println("" + ex);
			}
		}
		return obj;
	}
}