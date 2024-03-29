package sit.int221.oasipserver;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sit.int221.oasipserver.utils.ListMapper;
import sit.int221.oasipserver.utils.OverlapValidate;


@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE", "PATCH");
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }
    @Bean
    public OverlapValidate overlapValidate() {
        return OverlapValidate.getInstance();
    }

}
