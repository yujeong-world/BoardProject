package com.jpastudy.board.Service;

import com.jpastudy.board.domain.Role;
import com.jpastudy.board.domain.UserAccount;
import com.jpastudy.board.dto.UserAccountDto;
import com.jpastudy.board.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;

    //회원 전체 조회
    public List<UserAccountDto> findAll(){
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return userAccounts.stream().map(UserAccountDto::from).collect(Collectors.toList());
    }
    @Transactional
    public UserAccount joinUser(UserAccountDto userAccountDto){
        //비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userAccountDto.setPw(passwordEncoder.encode(userAccountDto.getPw()));


        return userAccountRepository.save(userAccountDto.toEntity());
    }


/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> findByUserId = userAccountRepository.findByUserId(username);
        UserAccount userAccount = findByUserId.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(("admin").equals(username)){
            //롤을 부여하는 코드
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
        //매개변수는 각각 아이디, 비밀번호, 권한리스트
        return new User(userAccount.getUserId(), userAccount.getPw(), authorities);
    }*/
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*
    Optional<UserAccount> findByUserId = userAccountRepository.findByUserId(username);
    UserAccount userAccount = findByUserId.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
*/

    UserAccount userAccount = userAccountRepository.findByUserId(username).orElseThrow(()-> new UsernameNotFoundException(username));
    List<GrantedAuthority> authorities = new ArrayList<>();

    if(("admin").equals(username)){
        //롤을 부여하는 코드
        authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
    }else{
        authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
    }



    //매개변수는 각각 아이디, 비밀번호, 권한리스트
    return new User(userAccount.getUserId(), userAccount.getPw(), authorities);
}

}
