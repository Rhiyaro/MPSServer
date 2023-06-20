package com.mps.MPSServer;

import com.mps.MPSServer.MPSUtil.DatetimeManager;
import com.mps.MPSServer.domain.MPSUser;
import com.mps.MPSServer.domain.Role;
import com.mps.MPSServer.domain.UserCredential;
import com.mps.MPSServer.repo.CredentialRepo;
import com.mps.MPSServer.repo.ParkRepo;
import com.mps.MPSServer.repo.RoleRepo;
import com.mps.MPSServer.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MPSServerApplication {

    public static void main(String[] args) {
        DatetimeManager.initialize();
        SpringApplication.run(MPSServerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserRepo userRepo,
//                          RoleRepo roleRepo,
//                          CredentialRepo credentialRepo,
//                          ParkRepo parkRepo) {
//        return args -> {
//            Role[] cargos = new Role[]{
//                    new Role("USUARIO"),
//                    new Role("ADMIN")
//            };
//            roleRepo.saveAll(Arrays.asList(cargos));
//
//            MPSUser[] mpsUsers = new MPSUser[]{
//                    new MPSUser("Jonas", "52280994097", "24999887766", "jonas@mail.com"),
//                    new MPSUser("admin", "87558760054", "24999991111", "admin@mail.com"),
//            };
//
//            List<Role> roles = roleRepo.findAll();
//
//            UserCredential[] userCredentials = new UserCredential[]{
//                    new UserCredential(mpsUsers[0], "login1", "pass1", roles.subList(0, 1)),
//                    new UserCredential(mpsUsers[1], "admin", "admin", roles.subList(0, 2))
//            };
//
//            credentialRepo.saveAll(Arrays.asList(userCredentials));
//        };
//    }

}
