package de.lubowiecki.sproducts;

import de.lubowiecki.sproducts.model.User;
import de.lubowiecki.sproducts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringProductsApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringProductsApplication.class, args);
    }

    // Über Beans kann ich bereits vorhandene, vorkonfigurierte Bestandteile von Spring
    // ersetzen und damit das Verhalten anpassen
    // Erlaubt das Ändern der Sprache über wechsel der Locale
    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(Locale.GERMANY);
        //CookieLocaleResolver resolver = new CookieLocaleResolver(); // Sprache wird im Cookie des Browser gespeichert
        SessionLocaleResolver resolver = new SessionLocaleResolver(); // Sprache wird in der Session des Servers gespeichert
        resolver.setDefaultLocale(Locale.GERMANY);
        return resolver;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("p.parker@shield.com", "geheim123");
        userRepository.save(admin);
    }

    /* Erlaubt das Ändern der Sprache über die URL, http://www.domain.de?lang=de
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    */
}
