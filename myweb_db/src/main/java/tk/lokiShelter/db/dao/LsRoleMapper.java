package tk.lokiShelter.db.dao;

import java.util.List;
import tk.lokiShelter.db.domain.LsRole;
import tk.lokiShelter.db.domain.LsRoleExample;

public interface LsRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    int insert(LsRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    int insertSelective(LsRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    List<LsRole> selectByExample(LsRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    LsRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LsRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ums_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LsRole record);
}