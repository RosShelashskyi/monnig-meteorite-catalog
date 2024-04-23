package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<MonnigUser> findAll(){
        return this.userRepository.findAll();
    }
    public MonnigUser save(MonnigUser monnigUser) {
        monnigUser.setPassword(this.passwordEncoder.encode(monnigUser.getPassword()));
        return this.userRepository.save(monnigUser);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(MyUserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("usernane." + username + " is not found."));
    }


}
