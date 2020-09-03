package tk.lokiShelter.db.dao;

import java.util.List;
import tk.lokiShelter.db.domain.LsRoleMnuRelation;
import tk.lokiShelter.db.domain.LsRoleMnuRelationExample;

public interface LsRoleMnuRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    int insert(LsRoleMnuRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    int insertSelective(LsRoleMnuRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    List<LsRoleMnuRelation> selectByExample(LsRoleMnuRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    LsRoleMnuRelation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LsRoleMnuRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role_menu_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LsRoleMnuRelation record);
}