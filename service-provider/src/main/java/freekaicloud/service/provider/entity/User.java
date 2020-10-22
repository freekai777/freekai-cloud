package freekaicloud.service.provider.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 *
 * </p>
 *
 * @author freekai
 * @since 2020-09-16
 */
public class User extends Model {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String ssa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }
}
