package icu.huajuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.huajuan.model.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/***
 *
 * @author Chen Anning
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
