package br.com.eplano.commons;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication(scanBasePackages = "br.com.eplano.commons")
public class Application implements CommandLineRunner {

    // private final ModelMapper modelMapper;

    // public Application(ModelMapper modelMapper) {
    // System.out.println("Initializing Application with ModelMapper: " +
    // modelMapper);
    // this.modelMapper = modelMapper;
    // }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        // // Teste Enum -> EnumNameValueDTO
        // EnumNameValueDTO dto1 = Mapper.toEnumDTO(NaturezaJuridicaEnum.LTDA);
        // System.out.println("NaturezaJuridicaEnum.LTDA -> EnumNameValueDTO: " +
        // dto1.getId() + ", " + dto1.getLabel());

        // EnumNameValueDTO dto2 = Mapper.toEnumDTO(SegmentoEmpresaEnum.INDUSTRIA);
        // System.out
        // .println("SegmentoEmpresaEnum.INDUSTRIA -> EnumNameValueDTO: " + dto2.getId()
        // + ", " + dto2.getLabel());

        // // Teste EnumNameValueDTO -> Enum
        // EnumNameValueDTO porteDto = new EnumNameValueDTO("Empresa de Pequeno Porte",
        // "EPP");
        // PorteEmpresaEnum porteEnum = Mapper.fromEnumDTO(porteDto,
        // PorteEmpresaEnum.class);
        // System.out.println("EnumNameValueDTO('EPP') -> PorteEmpresaEnum: " +
        // porteEnum);

        // EnumNameValueDTO regimeDto = new EnumNameValueDTO("Simples Nacional",
        // "SIMPLES");
        // RegimeTributarioEnum regimeEnum = Mapper.fromEnumDTO(regimeDto,
        // RegimeTributarioEnum.class);

        // System.out.println("EnumNameValueDTO('SIMPLES') -> RegimeTributarioEnum: " +
        // regimeEnum);
    }
}
