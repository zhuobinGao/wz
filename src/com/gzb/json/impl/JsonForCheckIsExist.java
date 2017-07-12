package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzb.util.R;

public class JsonForCheckIsExist implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs)
			throws UnsupportedEncodingException, SQLException {
		String result = R.ERROR;
		if(rs.next()){result = R.OK;}
		return result;
	}

}
