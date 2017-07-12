package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonForDataCount implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs)
			throws UnsupportedEncodingException, SQLException {
		
		if(rs.next())return rs.getString(1);
		
		return "0";
	}

}
