package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzb.util.StringUtil;

public class JsonForTableDetail implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException {
		
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<b>维修记录:</b><br/>");
		
		
		while(rs.next()){
			buffer.append(rs.getString(1));
			buffer.append("[").append(rs.getString(2)).append("]&nbsp;&nbsp;");
			buffer.append(rs.getString(3)).append(",&nbsp;&nbsp;");
			buffer.append(rs.getString(4)).append(",&nbsp;&nbsp;");
			buffer.append(rs.getString(5)).append(",&nbsp;&nbsp;");
			buffer.append(StringUtil.nullToEmpty(rs.getString(6))).append("<br/>");
		}
		
		return buffer.toString();
	}

}
