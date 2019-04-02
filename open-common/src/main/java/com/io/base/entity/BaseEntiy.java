package com.io.base.entity;

import com.io.base.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * project - GitHub整理 综合客户服务系统
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/8/16 时间:10:09
 * @JDK 1.8
 * @Description 功能模块：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntiy implements Serializable {
    @Id
    @Column(name = "id")
    @Comment("主键ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Comment("创建时间")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @Comment("更新时间")
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @Column(name = "create_by")
    @Comment("创建人")
    private String createBy;
    /**
     * 更新人
     */
    @Column(name = "update_by")
    @Comment("更新人")
    private String updateBy;
    /**
     * 删除标识：0-正常；1-删除
     * （true_false=T_F；numeric_boolean=0_1；yes_no=Y_N）
     */
    @Type(type = "true_false")
    @Column(name = "remove")
    @Comment("删除标识：0-正常；1-删除")
    private Boolean remove;
    /**
     * 备注
     */
    @Column(name = "remark")
    @Comment("备注")
    private String remark;
}
