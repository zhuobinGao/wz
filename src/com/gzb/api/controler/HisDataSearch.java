package com.gzb.api.controler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cangku.controler.InAndOutCheckControler;
import com.gzb.api.dbutil.CommonModelOracle;
import com.gzb.json.impl.JsonForTableNoNum;
import com.gzb.util.StringUtil;

public class HisDataSearch extends CommonModelOracle {

	private static Logger logger = Logger.getLogger(InAndOutCheckControler.class);

	public String getReceiveHisList(HttpServletRequest request,	HttpServletResponse response) {

		List<String> argsList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		sql.append("select r.receipt_id,m.material_bak,m.material_name,m.material_size,m.material_module,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,to_char(r.receipt_filltime,'yyyy-mm-dd') receipt_filltime,to_char(r.receipt_receivedtime,'yyyy-mm-dd') receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_note,r.catory_id,r.catory_name,m.material_id\n");
		sql.append("from bmwz_receipt_his r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");

		// sqlCountBuffer.append("select count(1) from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		// sqlCountBuffer.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno where 1=1 \n");

		sql.append("where 1=1 \n");

		if (!StringUtil.isEmptyFromRequest(request, "sel_cgr")) {
			appendBuffer.append("and r.receipt_purchaser=? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_cgr"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_slr")) {
			appendBuffer.append("and r.receipt_receipter=? \n");
			argsList.add(StringUtil.trimRequest(request, "slr_cgr"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_postrion")) {
			appendBuffer.append("and r.receipt_postion like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_postrion")
					+ "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_item")) {
			appendBuffer.append("and r.receipt_item=? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_item"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_materialName")) {
			appendBuffer.append("and m.material_name like ? \n");
			argsList.add("%"
					+ StringUtil.trimRequest(request, "sel_materialName") + "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_category")) {
			appendBuffer
					.append("and r.catory_id in ( select m.category_id from bmwz_category m start with m.category_name=? connect by m.category_fid=prior m.category_id) \n");
			argsList.add(StringUtil.trimRequest(request, "sel_category"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_invoiceNO")) {
			appendBuffer.append("and r.receipt_invoiceno like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_invoiceNO")
					+ "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_slbh")) {
			appendBuffer.append("and r.receipt_id = ? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_slbh"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_gydw")) {
			appendBuffer.append("and r.receipt_supplydept like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_gydw")
					+ "%");
		}

		// receipt_receivedtime
		if (!StringUtil.isEmptyFromRequest(request, "sel_sDate")) {
			appendBuffer
					.append("and r.receipt_receivedtime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(StringUtil.trimRequest(request, "sel_sDate"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_eDate")) {
			appendBuffer
					.append("and r.receipt_receivedtime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(StringUtil.trimRequest(request, "sel_eDate"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_isFP")
				&& !"-1".equals(StringUtil.trimRequest(request, "sel_isFP"))) {
			appendBuffer.append("and r.receipt_hasinvoice = ? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_isFP"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "calDate")) {
			appendBuffer.append("and r.receipt_caldate=? \n");
			argsList.add(StringUtil.trimRequest(request, "calDate"));
		}

		appendBuffer.append(" and r.receipt_storageno=? ");
		argsList.add((String) request.getSession().getAttribute("cangKuID"));

		

		String result = search(sql.toString() + appendBuffer.toString(),argsList, new JsonForTableNoNum());
		
		return result;
	}

	public String getReceiveList(HttpServletRequest request, HttpServletResponse response) {
		List<String> argsList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		StringBuffer appendBuffer = new StringBuffer();
		sql.append("select r.receipt_id,m.material_bak,m.material_name,m.material_size,m.material_module,r.receipt_ar_mount,r.receipt_ar_mount_has,\n");
		sql.append("r.receipt_a_dj,r.receipt_a_amount,to_char(r.receipt_filltime,'yyyy-mm-dd') receipt_filltime,to_char(r.receipt_receivedtime,'yyyy-mm-dd') receipt_receivedtime,r.receipt_invoiceno,r.receipt_receipter,r.receipt_purchaser,\n");
		sql.append("s.storehouse_name,r.receipt_supplydept,r.receipt_item,r.receipt_postion,r.receipt_note,r.catory_id,r.catory_name,m.material_id\n");
		sql.append("from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		sql.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno\n");

		// sqlCountBuffer.append("select count(1) from bmwz_receipt r left join bmwz_material m on m.material_id=r.receipt_material_id\n");
		// sqlCountBuffer.append("left join BMWZ_STOREHOUSE s on s.storehouse_id=r.receipt_storageno where 1=1 \n");

		sql.append("where 1=1 \n");

		if (!StringUtil.isEmptyFromRequest(request, "sel_cgr")) {
			appendBuffer.append("and r.receipt_purchaser=? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_cgr"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_slr")) {
			appendBuffer.append("and r.receipt_receipter=? \n");
			argsList.add(StringUtil.trimRequest(request, "slr_cgr"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_postrion")) {
			appendBuffer.append("and r.receipt_postion like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_postrion")
					+ "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_item")) {
			appendBuffer.append("and r.receipt_item=? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_item"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_materialName")) {
			appendBuffer.append("and m.material_name like ? \n");
			argsList.add("%"
					+ StringUtil.trimRequest(request, "sel_materialName") + "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_category")) {
			appendBuffer
					.append("and r.catory_id in ( select m.category_id from bmwz_category m start with m.category_name=? connect by m.category_fid=prior m.category_id) \n");
			argsList.add(StringUtil.trimRequest(request, "sel_category"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_invoiceNO")) {
			appendBuffer.append("and r.receipt_invoiceno like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_invoiceNO")
					+ "%");
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_slbh")) {
			appendBuffer.append("and r.receipt_id = ? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_slbh"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_gydw")) {
			appendBuffer.append("and r.receipt_supplydept like ? \n");
			argsList.add("%" + StringUtil.trimRequest(request, "sel_gydw")
					+ "%");
		}

		// receipt_receivedtime
		if (!StringUtil.isEmptyFromRequest(request, "sel_sDate")) {
			appendBuffer
					.append("and r.receipt_receivedtime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(StringUtil.trimRequest(request, "sel_sDate"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_eDate")) {
			appendBuffer
					.append("and r.receipt_receivedtime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(StringUtil.trimRequest(request, "sel_eDate"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "sel_isFP")	&& !"-1".equals(StringUtil.trimRequest(request, "sel_isFP"))) {
			appendBuffer.append("and r.receipt_hasinvoice = ? \n");
			argsList.add(StringUtil.trimRequest(request, "sel_isFP"));
		}

		if (!StringUtil.isEmptyFromRequest(request, "calDate")) {
			appendBuffer.append("and r.receipt_caldate=? \n");
			argsList.add(StringUtil.trimRequest(request, "calDate"));
		}

		appendBuffer.append(" and r.receipt_storageno=? ");
		argsList.add((String) request.getSession().getAttribute("cangKuID"));

		String result = search(sql.toString() + appendBuffer.toString(),argsList, new JsonForTableNoNum());

		return result;
	}

	public String getRequestListHis(HttpServletRequest request,	HttpServletResponse response){
		
		List<String> argsList = new ArrayList<String>();
		
		
		
		StringBuffer sql = new StringBuffer();
		StringBuffer appendSql = new StringBuffer();
		
		
		sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,round(r.request_dj,6) request_dj,round(r.request_tamount,2) request_tamount,to_char(r.request_filltime,'yyyy-mm-dd') request_filltime,\n");
		sql.append("to_char(r.request_issuetime,'yyyy-mm-dd') request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept,r.request_item_id,r.REQUEST_STATE,decode(me.mechanismtype,'01','门机','02','岸桥','03','场桥')||me.mechanismname mename\n");
		sql.append("from bmwz_request_his r\n");
		sql.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sql.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sql.append("left join bmwz_requestre rs on rs.request_id=r.request_id\n");
		sql.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id  \n");
		sql.append("left join bmwz_mechanism me on me.mechanismid=r.mechanismid where 1=1 ");
		
		String paramData = "";
		
		paramData = StringUtil.trimRequest(request, "calDate");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_caldate=? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_llr");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.REQUEST_REQUESTER = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_lldw");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_ldpt_id = ? \n");
			argsList.add(paramData);
		}
	
		paramData = StringUtil.trimRequest(request, "sel_postion");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r1.receipt_postion like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_item");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and f.fee_name = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_materialName");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and m.material_name like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_category");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and m.material_type in ( select m1.category_id from bmwz_category m1 start with m1.category_name=? connect by m1.category_fid=prior m1.category_id) \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_xxyt");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_usage like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_llbh");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_id = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_gydw");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r1.receipt_supplydept like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_sDate");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_issuetime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_eDate");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_issuetime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(paramData);
		}
		
		appendSql.append(" and  r.request_storageno=?  \n");
		argsList.add((String) request.getSession().getAttribute("cangKuID"));
		
		return search(sql.toString() + appendSql.toString(),argsList, new JsonForTableNoNum());
	}
	

	public String getRequestList(HttpServletRequest request, HttpServletResponse response){
		
		List<String> argsList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		StringBuffer appendSql = new StringBuffer();
		
		sql.append("select r.request_id, m.material_bak,m.material_name,m.material_size,m.material_module,\n");
		sql.append("r.request_r_mount,r.request_ai_mount,round(r.request_dj,6) request_dj,round(r.request_tamount,2) request_tamount,to_char(r.request_filltime,'yyyy-mm-dd') request_filltime,\n");
		sql.append("to_char(r.request_issuetime,'yyyy-mm-dd') request_issuetime,r.REQUEST_ISSUER,r.REQUEST_REQUESTER,r.request_ldpt_id,r.request_ldpt_manager,\n");
		sql.append("f.fee_name,r.request_usage,rs.receipt_id,r1.receipt_postion,r1.receipt_supplydept,r.request_item_id,r.REQUEST_STATE,decode(me.mechanismtype,'01','门机','02','岸桥','03','场桥')||me.mechanismname mename\n");
		sql.append("from bmwz_request r\n");
		sql.append("left join bmwz_material m on m.material_id=r.request_meterial_id\n");
		sql.append("left join bmwz_feeproject f on f.fee_id=r.request_item_id\n");
		sql.append("left join bmwz_requestre rs on rs.request_id=r.request_id\n");
		sql.append("left join bmwz_receipt r1 on r1.receipt_id=rs.receipt_id  \n");
		sql.append("left join bmwz_mechanism me on me.mechanismid=r.mechanismid where 1=1 ");
		
		String paramData = "";
		
		paramData = StringUtil.trimRequest(request, "sel_llr");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.REQUEST_REQUESTER = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_lldw");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_ldpt_id = ? \n");
			argsList.add(paramData);
		}
	
		paramData = StringUtil.trimRequest(request, "sel_postion");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r1.receipt_postion like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_item");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and f.fee_name = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_materialName");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and m.material_name like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_category");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and m.material_type in ( select m1.category_id from bmwz_category m1 start with m1.category_name=? connect by m1.category_fid=prior m1.category_id) \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_xxyt");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_usage like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_llbh");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_id = ? \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_gydw");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r1.receipt_supplydept like ? \n");
			argsList.add("%"+paramData+"%");
		}
		
		paramData = StringUtil.trimRequest(request, "sel_sDate");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_issuetime >= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(paramData);
		}
		
		paramData = StringUtil.trimRequest(request, "sel_eDate");
		if(!StringUtil.isEmpty(paramData)){
			appendSql.append("and r.request_issuetime <= to_date(?,'yyyy-mm-dd') \n");
			argsList.add(paramData);
		}
		
		appendSql.append(" and  r.request_storageno=?  \n");
		argsList.add((String) request.getSession().getAttribute("cangKuID"));
		return search(sql.toString() + appendSql.toString(),argsList, new JsonForTableNoNum());
	}
	

	public String getOutFeeItemHis(HttpServletRequest request,	HttpServletResponse response){
		
		String sqlid = StringUtil.trimRequest(request, "sqlID");
		
		List<String> argList = new ArrayList<String>();
		argList.add(StringUtil.trimRequest(request, "sel_cangKu"));
		argList.add(StringUtil.trimRequest(request, "sel_date1"));
	
		String sql = getSql(sqlid);

		return search(sql,argList, new JsonForTableNoNum());
	}
	
	
}
