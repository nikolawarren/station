package station.login.service;



import station.users.pojo.Roles;
import station.users.pojo.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser extends Users implements UserDetails
{

    private static final long serialVersionUID = 1L;
    public SecurityUser(Users user) {
        if(user != null)
        {
            this.setUserid(user.getUserid());
            this.setUsername(user.getUsername());
            this.setUseraccount(user.getUseraccount());   
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            this.setPassword(encodedPassword);
            this.setMobile(user.getMobile());
            this.setTicket(user.getTicket());          
            this.setRoles(user.getRoles());
            
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Roles roles = this.getRoles();
        if(roles != null)
        {
            
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.getRolename());
                authorities.add(authority);
            
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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
