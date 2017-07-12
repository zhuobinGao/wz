package com.gzb.json.impl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzb.util.JsonUtil;

public class JsonForAutoComplete implements JsonImpl {

	@Override
	public String encodeResultSet(ResultSet rs) throws UnsupportedEncodingException, SQLException {
		return JsonUtil.autoCompleteFromResultSet(rs);
	}

}
