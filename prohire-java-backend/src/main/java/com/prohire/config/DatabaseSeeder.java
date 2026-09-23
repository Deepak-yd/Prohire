package com.prohire.config;

import com.prohire.model.User;
import com.prohire.model.Profile;
import com.prohire.model.Category;
import com.prohire.model.Professional;
import com.prohire.model.Service;
import com.prohire.model.Job;
import com.prohire.model.Hire;
import com.prohire.model.Message;
import com.prohire.model.Connection;
import com.prohire.model.Share;
import com.prohire.model.PlatformSetting;
import com.prohire.repository.UserRepository;
import com.prohire.repository.ProfessionalRepository;
import com.prohire.repository.JobRepository;
import com.prohire.repository.CategoryRepository;
import com.prohire.repository.ServiceRepository;
import com.prohire.repository.HireRepository;
import com.prohire.repository.MessageRepository;
import com.prohire.repository.ConnectionRepository;
import com.prohire.repository.ShareRepository;
import com.prohire.repository.PlatformSettingRepository;
import com.prohire.repository.ProfileRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@SuppressWarnings("null")
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProfessionalRepository professionalRepository;
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;
    private final HireRepository hireRepository;
    private final MessageRepository messageRepository;
    private final ConnectionRepository connectionRepository;
    private final ShareRepository shareRepository;
    private final PlatformSettingRepository platformSettingRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void run(String... args) throws Exception {
        System.out.println("🚀 Executing Database Seeder: Purging Old Accounts and Seeding Fixed Role Accounts...");

        // 0. CLEAR ALL PREVIOUS DATA (Strict execution order to preserve Referential Integrity)
        hireRepository.deleteAllInBatch();
        messageRepository.deleteAllInBatch();
        connectionRepository.deleteAllInBatch();
        serviceRepository.deleteAllInBatch();
        jobRepository.deleteAllInBatch();
        professionalRepository.deleteAllInBatch();
        shareRepository.deleteAllInBatch();
        platformSettingRepository.deleteAllInBatch();
        profileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();

        // Passwords
        String adminPass = passwordEncoder.encode("adminpassword123");
        String clientPass = passwordEncoder.encode("clientpassword123");
        String proPass = passwordEncoder.encode("propassword123");

        // 1. ADMIN ACCOUNT (1 Account)
        User admin = User.builder()
            .email("admin@klu.in")
            .password(adminPass)
            .fullName("Deepak Admin")
            .role("ADMIN")
            .profileImage("https://api.dicebear.com/7.x/avataaars/svg?seed=Admin")
            .isEmailVerified(true)
            .build();
        Profile adminProfile = Profile.builder()
            .user(admin)
            .avatar("https://api.dicebear.com/7.x/avataaars/svg?seed=Admin")
            .bio("Lead Platform Administrator at ProHire. Managing core operations and security.")
            .location("Vijayawada, India")
            .company("KLU Global")
            .website("https://admin.klu.in")
            .phone("+91 99999 88888")
            .build();
        admin.setProfile(adminProfile);
        userRepository.save(admin);

        // 2. CLIENT ACCOUNTS (2 Accounts)
        User client1 = User.builder()
            .email("client1@klu.in")
            .password(clientPass)
            .fullName("Suresh Client")
            .role("USER")
            .profileImage("https://api.dicebear.com/7.x/avataaars/svg?seed=Suresh")
            .isEmailVerified(true)
            .build();
        Profile client1Profile = Profile.builder()
            .user(client1)
            .avatar("https://api.dicebear.com/7.x/avataaars/svg?seed=Suresh")
            .bio("Startup Founder looking for top-tier professional talent.")
            .location("Hyderabad, India")
            .company("KLU Innovations")
            .phone("+91 77777 66666")
            .build();
        client1.setProfile(client1Profile);
        userRepository.save(client1);

        User client2 = User.builder()
            .email("client2@klu.in")
            .password(clientPass)
            .fullName("Ramesh Client")
            .role("USER")
            .profileImage("https://api.dicebear.com/7.x/avataaars/svg?seed=Ramesh")
            .isEmailVerified(true)
            .build();
        Profile client2Profile = Profile.builder()
            .user(client2)
            .avatar("https://api.dicebear.com/7.x/avataaars/svg?seed=Ramesh")
            .bio("Product Director expanding digital platforms.")
            .location("Bangalore, India")
            .company("Apex Global Tech")
            .phone("+91 77777 55555")
            .build();
        client2.setProfile(client2Profile);
        userRepository.save(client2);

        // 3. PROFESSIONAL ACCOUNTS (2 Accounts)
        User pro1User = User.builder()
            .email("pro1@klu.in")
            .password(proPass)
            .fullName("Ankit Developer")
            .role("PROFESSIONAL")
            .profileImage("https://api.dicebear.com/7.x/avataaars/svg?seed=Ankit")
            .isEmailVerified(true)
            .build();
        Profile pro1Profile = Profile.builder()
            .user(pro1User)
            .avatar("https://api.dicebear.com/7.x/avataaars/svg?seed=Ankit")
            .bio("Expert Full-Stack Developer with 8+ years experience in Java, Spring Boot, and React.")
            .location("Bangalore, India")
            .company("Ankit Solutions")
            .phone("+91 88888 77777")
            .build();
        pro1User.setProfile(pro1Profile);
        userRepository.save(pro1User);

        User pro2User = User.builder()
            .email("pro2@klu.in")
            .password(proPass)
            .fullName("Sneha Designer")
            .role("PROFESSIONAL")
            .profileImage("https://api.dicebear.com/7.x/avataaars/svg?seed=Sneha")
            .isEmailVerified(true)
            .build();
        Profile pro2Profile = Profile.builder()
            .user(pro2User)
            .avatar("https://api.dicebear.com/7.x/avataaars/svg?seed=Sneha")
            .bio("Lead UI/UX Designer specializing in design systems and intuitive user journeys.")
            .location("Hyderabad, India")
            .company("DesignCraft Studio")
            .phone("+91 88888 66666")
            .build();
        pro2User.setProfile(pro2Profile);
        userRepository.save(pro2User);

        // 4. CATEGORIES
        Category webCat = categoryRepository.findByName("Web Development")
            .orElse(Category.builder().name("Web Development").build());
        webCat.setDescription("Frontend, Backend, and Full Stack Solutions");
        webCat.setIcon("🌐");
        
        Category designCat = categoryRepository.findByName("UI/UX Design")
            .orElse(Category.builder().name("UI/UX Design").build());
        designCat.setDescription("User Interface and Experience Design");
        designCat.setIcon("🎨");

        categoryRepository.saveAll(List.of(webCat, designCat));
        categoryRepository.flush();

        // 5. PROFESSIONAL PROFILES
        Professional pro1 = Professional.builder()
            .user(pro1User)
            .category(webCat)
            .title("Senior Full-Stack Architect")
            .rateValue(150.0)
            .skills(List.of("Java", "Spring Boot", "React", "Docker", "PostgreSQL"))
            .rating(5.0)
            .reviewCount(120)
            .build();
        professionalRepository.save(pro1);

        Professional pro2 = Professional.builder()
            .user(pro2User)
            .category(designCat)
            .title("Lead UI/UX Architect")
            .rateValue(125.0)
            .skills(List.of("Figma", "Design Systems", "Prototyping", "Tailwind CSS"))
            .rating(4.9)
            .reviewCount(95)
            .build();
        professionalRepository.save(pro2);

        // 6. SERVICES
        Service s1 = Service.builder()
            .professional(pro1)
            .name("Enterprise Web Application")
            .description("Complete end-to-end web application development using Spring Boot and React.")
            .price(5000.0)
            .priceLabel("$5000 Fixed")
            .duration("4 Weeks")
            .build();
        Service s2 = Service.builder()
            .professional(pro2)
            .name("UI/UX Design System & Audit")
            .description("Complete design system creation and user interface audit for web apps.")
            .price(3500.0)
            .priceLabel("$3500 Fixed")
            .duration("2 Weeks")
            .build();
        serviceRepository.saveAll(List.of(s1, s2));

        // 7. JOBS
        Job j1 = Job.builder()
            .poster(client1)
            .title("Fintech Platform Web Application")
            .description("Looking for an experienced developer to scale our core microservices backend.")
            .location("Remote")
            .type("CONTRACT")
            .budget(8000.0)
            .category("Web Development")
            .skills(List.of("Java", "Spring Boot", "PostgreSQL"))
            .status("OPEN")
            .build();
        Job j2 = Job.builder()
            .poster(client2)
            .title("Mobile & Web UI/UX Redesign")
            .description("Need an expert designer to revamp our SaaS dashboard interfaces.")
            .location("Remote")
            .type("PART_TIME")
            .budget(4000.0)
            .category("UI/UX Design")
            .skills(List.of("Figma", "Tailwind CSS", "UX Research"))
            .status("OPEN")
            .build();
        jobRepository.saveAll(List.of(j1, j2));

        // 8. HIRES
        Hire h1 = Hire.builder()
            .clientUser(client1)
            .professional(pro1)
            .service(s1)
            .serviceTitle(s1.getName())
            .amountValue(5000.0)
            .notes("Project initiated. Architecture review in progress.")
            .progress(30)
            .status("ONGOING")
            .build();
        hireRepository.save(h1);

        // 9. MESSAGES
        Message m1 = Message.builder()
            .sender(client1)
            .receiver(pro1User)
            .content("Hi Ankit, welcome to the project! Looking forward to working together.")
            .isRead(true)
            .build();
        Message m2 = Message.builder()
            .sender(pro1User)
            .receiver(client1)
            .content("Hi Suresh! Excited to build out the backend services.")
            .isRead(false)
            .build();
        messageRepository.saveAll(List.of(m1, m2));

        // 10. CONNECTIONS
        Connection c1 = Connection.builder()
            .sender(client1)
            .receiver(pro1User)
            .status("ACCEPTED")
            .build();
        Connection c2 = Connection.builder()
            .sender(client2)
            .receiver(pro2User)
            .status("ACCEPTED")
            .build();
        connectionRepository.saveAll(List.of(c1, c2));

        // 11. PLATFORM SETTINGS
        platformSettingRepository.save(PlatformSetting.builder()
            .settingKey("platform_fee")
            .settingValue("10")
            .build());
    }
}
