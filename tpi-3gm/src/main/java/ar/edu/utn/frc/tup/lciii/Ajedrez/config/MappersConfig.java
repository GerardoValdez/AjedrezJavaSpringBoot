package ar.edu.utn.frc.tup.lciii.Ajedrez.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    @Qualifier("mergerMapper")
    public ModelMapper mergerMapper(){
        ModelMapper mergeMapper = new ModelMapper();
        mergeMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mergeMapper;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper;
    }
}