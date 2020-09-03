package tk.lokiShelter.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.lokiShelter.db.domain.LsProduct;
import tk.lokiShelter.db.domain.LsProductExample;

public interface LsProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    long countByExample(LsProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int insert(LsProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int insertSelective(LsProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    List<LsProduct> selectByExample(LsProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    LsProduct selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LsProduct record, @Param("example") LsProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LsProduct record, @Param("example") LsProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LsProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pms_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LsProduct record);
}