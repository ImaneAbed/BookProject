import com.example.demo.domaine.port.BookInterface
import com.example.demo.domaine.usecases.BookManagement
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ComponentScan

@Configuration
@ComponentScan(basePackages = ["com.example.demo"])
class AppConfig {

    @Bean
    fun bookInterface(): BookInterface {
        // Retournez une instance de BookInterface (vous devrez adapter cela à votre code)
        return BookInterfaceImpl()
    }

    @Bean
    fun bookManagement(bookInterface: BookInterface): BookManagement {
        return BookManagement(bookInterface)
    }
}
