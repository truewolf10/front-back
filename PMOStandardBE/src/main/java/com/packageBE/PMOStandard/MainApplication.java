package com.packageBE.PMOStandard;

import com.packageBE.PMOStandard.repository.IActivationRepository;
import com.packageBE.PMOStandard.repository.IProjectPhaseRepository;
import com.packageBE.PMOStandard.repository.IProjectRepository;
import com.packageBE.PMOStandard.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired
	private IProjectRepository projectRepo;

	@Autowired
	private IProjectPhaseRepository projectPhaseRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IActivationRepository activationRepository;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		projectRepo.deleteAllInBatch();
//		projectPhaseRepository.deleteAllInBatch();
//
//		Project project = new Project("Hibernate many to many",23);
//		ProjectPhase phase1 = new ProjectPhase("Spring boot");
//		ProjectPhase phase2 = new ProjectPhase("Hibernate");
//
//		project.getPhases().add(phase1);
//		project.getPhases().add(phase2);
//
//		phase1.getProjects().add(project);
//		phase2.getProjects().add(project);
//
//		projectRepo.save(project);

//		activationRepository.deleteAllInBatch();
//		userRepository.deleteAllInBatch();
//		User user = new User("Rajeev", "Singh", "rajeev@callicoder.com", "12345", BusinessUnit.Commerce, UserRole.USER);

//		Activation activation = new Activation("asklnav msldkv");
//		User user = userRepository.findById((long)15).get();
//		user.setActivation(activation);
//		activation.setUser(user);

//		userRepository.save(user);
//		userRepository.save(user);
//				activation.setActivated(true);
//		activationRepository.save(activation);
	}
}
