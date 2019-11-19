package top.annokshon.kungfu.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/*
 * 用户登录信息
 */
@Entity
@Table(name = "kf_user")
@Data
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kf_id")
	private int id;

	@Column(length = 100,name = "kf_username")
	private String username;  //用户名

	@Column(length = 100,name = "kf_password")
	private String password;  //密码

	@ManyToMany
	private List<Role> roles;  //用户对应的角色

	@Column(length = 100,name = "kf_state")
	private int state;  //用户账号状态(1:激活，0)

	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	@Column(name = "kf_register_time")
	private Date registerTime;  //注册时间

	@DateTimeFormat(pattern = "yy-MM-dd hh:mm:ss")
	@Column(name = "kf_last_login_time")
	private Date lastLoginTime;  //最后一次登陆时间

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<Role> roles = this.getRoles();
		for (Role role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
