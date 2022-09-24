package myapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import myapp.model.Role;
import myapp.model.Status;
import myapp.model.user.User;
import myapp.repository.RoleRepository;
import myapp.repository.UserRepository;
import myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoderder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoderder = encoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        user.setPassword(passwordEncoderder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registered = userRepository.save(user);

        log.info("IN UserServiceImpl new user registered {} ", registered);

        return registered;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        log.info("IN UserService all users found : {}", users.size());

        return users;
    }

    @Override
    public User findByUserName(String userName) {
        User findByName = userRepository.findByUserName(userName);

        log.info("IN UserService user found by name {}, {} ", userName, findByName);

        return findByName;
    }

    @Override
    public User findById(Long id) {
        User findById = userRepository.findById(id).orElse(null);

        if (findById == null) {
            log.warn("IN UserService findById user NOT found {}", id);
            return null;
        }

        log.info("IN UserService findById {}, {}", id, findById);

        return findById;
    }

    @Override
    public void delete(Long id) {
        User delete = userRepository.findById(id).orElse(null);


        if (delete == null) {
            log.warn("IN UserService delete user NOT found {}", id);
        } else {
            log.info("IN UserService delete by id {}  {}", id, delete);
            userRepository.delete(delete);
        }
    }
}
