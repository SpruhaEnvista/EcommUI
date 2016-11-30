package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspCodeValuesTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspCodeValuesTbRepository extends PagingAndSortingRepository<ShpNspCodeValuesTb, Long> {

	/***
	 * select * from shp_nsp_code_values_tb where property_1='Audit Rules' property_2 = ?
	 * select * from shp_nsp_code_values_tb where property_1='Data Entry Rules' property_2 = ?;

	 * 
	 * @param customerId
	 * @return
	 */
	List<ShpNspCodeValuesTb> findAllByProperty1AndProperty2(String property1, String property2);
	
	/***
	 * select * from shp_nsp_code_values_tb where code_group_id = 1 and property_3 = 'INVOICE LOOKUP DROP DOWN VALUES';
	 * 
	 * @param customerId
	 * @return
	 */
	List<ShpNspCodeValuesTb> findAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndProperty3(long nspCodeGroupId, String property2);

	/***
	 * select * from shp_nsp_code_values_tb where code_group_id = 10 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =3 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =15 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =16 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =81 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =14 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =467 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =14 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =644 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =468 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =469 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =809 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =804 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =486 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =161 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =763 and nvl(active,'N')='Y' order by sequence;
	 * select * from shp_nsp_code_values_tb where code_group_id =201 and nvl(active,'N')='Y' order by sequence;

	 * 
	 * @param customerId
	 * @return
	 */
	//List<ShpNspCodeValuesTb> findAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActiveOrderBySequence(long nspCodeGroupId, String active);
	List<ShpNspCodeValuesTb> findAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(long nspCodeGroupId, String active);
	
	List<ShpNspCodeValuesTb> findByNspCodeValueId(long nspcodevalueid); 
	
	List<ShpNspCodeValuesTb> findAllByShpNspCodeValueGroupsTb_NspCodeGroupId(long nspCodeGroupId);
	
	

	/*@Query("from SHP_NSP_CODE_VALUES_TB cvt where cvt.CODE_GROUP_ID JOIN SHP_NSP_CODE_VALUE_GROUPS_TB cvg where cvg.NSP_CODE_GROUP_ID and cvg.CODE_GROUP_NAME=?1")*/
	List<ShpNspCodeValuesTb> findAllByShpNspCodeValueGroupsTb_CodeGroupName (String codeGroupName);
}
